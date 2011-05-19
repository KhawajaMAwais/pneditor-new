/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.matmas.pneditor.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;
import net.matmas.pnapi.*;
import net.matmas.pneditor.DynSound.DirectedGraph;
import net.matmas.pneditor.DynSound.DynSoundActionFrame;
import net.matmas.pneditor.DynSound.GraphArc;
import net.matmas.pneditor.DynSound.InvariantsMatrix;
import net.matmas.pneditor.PNEditor;
import net.matmas.util.GraphicsTools;


/**
 *
 * @author Samo
 */
public class DynSoundAction extends Action {


    private PetriNet petriNet;
    private ArrayList<Place> places;
    private ArrayList<Transition> transitions;
    private int result = 0;
    private InvariantsMatrix transformedNet;
    private InvariantsMatrix transformedInput;
    private InvariantsMatrix incidenceMatrix;
    private InvariantsMatrix inputMatrix;
    private int staticCount;
    private int[][] staticPosAndTokens;
    private int[] dangerous;
    private int inputPosition;
    private int outputPosition;
    private boolean sound = true;
    private int unsoundInstances = 0;
    private int TOposition = 0;

    public DynSoundAction() {
        String name = "";
        putValue(NAME, name);
        putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/ds.png"));
        putValue(SHORT_DESCRIPTION, name);

    }

    public void actionPerformed(ActionEvent e) {
        long start = System.currentTimeMillis();
        boolean hasStatic = false;
        boolean hasOutput = false;
        boolean hasInput = false;
        boolean mayPass = true;
        int outputCount = 0;
        int inputCount = 0;
        staticCount = 0;
        result = 0;
        dangerous = new int[100];
        sound = true;
        unsoundInstances = 0;
        TOposition = 0;
        for (int i = 0; i < 100; i++) {
            dangerous[i] = 0;
        }


        this.petriNet = PNEditor.getInstance().getDocument().getPetriNet();

        List<Place> placesSet = petriNet.getPlaces();
        places = new ArrayList<Place>();
        int pom = 0;
        for (Place place : placesSet) {
            places.add(place);
            if (place.isStatic()) {
                pom++;
            }
        }
        staticPosAndTokens = new int[pom][2];
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getLabel() == null) {
                if (!places.get(i).isStatic()) {
                    places.get(i).getLabel().setText("P" + i);
                } else {
                    places.get(i).getLabel().setText("S" + i);
                }
            }
        }

        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).isStatic()) {
                hasStatic = true;
                staticPosAndTokens[staticCount][0] = i;
                staticPosAndTokens[staticCount][1] = petriNet.getMarking().getTokens(places.get(i));
                staticCount += 1;
            }
            if (places.get(i).getConnectedArcs(false,petriNet).isEmpty()) {
                inputPosition = i;
                inputCount++;
            }
            if (places.get(i).getConnectedArcs(true,petriNet).isEmpty()) {
                outputPosition = i;
                outputCount++;
            }
        }

        if (inputCount == 1) {
            hasInput = true;
        }
        if (outputCount == 1) {
            hasOutput = true;
        }

        List<Transition> transitionsSet = petriNet.getTransitions();
        transitions = new ArrayList<Transition>();

        for (Transition transition : transitionsSet) {
            transitions.add(transition);
        }
        for (int i = 0; i < transitions.size(); i++) {
            if (transitions.get(i).getLabel() == null) {

                transitions.get(i).getLabel().setText("T" + i);
            }
        }

        if (!hasStatic) {
            result = -1;
            mayPass = false;
        } else if (!hasInput || !hasOutput) {
            result = -2;
            mayPass = false;
        }

        if (mayPass) {
           
                
            try {
                createIncidenceMatrix();
                //log("incidence matrix");
                //incidenceMatrix.print();
                if (!createTransformedNet()) {
                    result = -3;
                    mayPass = false;
                }
            } catch (Exception ex) {
                Logger.getLogger(DynSoundAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (mayPass) {

            log("Dangerous:");
            for (int i = 0; i < transformedNet.height(); i++) {
                log("" + dangerous[i]);
            }
            for (int i = 0; i < transformedNet.height(); i++) {
                mayPass = false;
                result = 1;
                if (dangerous[i] == 1) {
                    mayPass = true;
                    result = 0;
                    break;
                }
            }

        }

        if (mayPass) {
            InvariantsMatrix marking = new InvariantsMatrix(transformedNet.height(), 1);
            int result1 = -1;
            int instCount1 = 1;
            int result2 = -1;
            int instCount2 = 1;

            for (int i = 0; i < transformedNet.height(); i++) {
                if (i == 0) {
                    marking.setPositionValue(i, 0, 1);
                } else {
                    if (i < staticCount + 1) {
                        marking.setPositionValue(i, 0, staticPosAndTokens[i - 1][1]);
                    } else if (i == 0) {
                        marking.setPositionValue(i, 0, 0);
                    }
                }
            }
            int i = 1;
            do {
                i++;
                log("pocet instancii " + i);
                instCount1 = instCount2;
                instCount2 = i;
                marking.setPositionValue(0, 0, i);
                result1 = result2;
                result2 = checkDynSound(marking);
                if (!sound) {
                    result = -4;
                    unsoundInstances = instCount2;
                    mayPass = false;
                    break;
                }
            } while (result1 != result2);
        }
        if (mayPass) {
            result = 1;
        }
        log("incidencna matica:");
        incidenceMatrix.print();
        log("input matica");
        inputMatrix.print();
        log("transformovana siet");
        transformedNet.print();
        log("tnet input matica");
        transformedInput.print();
        
            
        try {
            InvariantsMatrix spt = new InvariantsMatrix(staticPosAndTokens);
            log("spt");
            spt.print();
        } catch (Exception ex) {
            Logger.getLogger(DynSoundAction.class.getName()).log(Level.SEVERE, null, ex);
        }
            
 

        //---------------------------------------------------------------
        long end = System.currentTimeMillis();
        final long executionTime = end - start;
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                DynSoundActionFrame window = null;
                try {
                    window = new DynSoundActionFrame(result, unsoundInstances, executionTime);
                } catch (Exception ex) {
                    Logger.getLogger(DynSoundAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                window.setSize(500, 250);
                window.setVisible(true);
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    public boolean createTransformedNet() throws Exception {
        transformedNet = new InvariantsMatrix(staticCount + 1, 1);
        transformedInput = new InvariantsMatrix(staticCount + 1, 1);
        ArrayList<InvariantsMatrix> tnetpom = new ArrayList<InvariantsMatrix>();
        DirectedGraph g = new DirectedGraph();
        InvariantsMatrix mark = new InvariantsMatrix(places.size(), 1);
        InvariantsMatrix parent = new InvariantsMatrix(places.size(), 1);
        int i = 0;

        for (i = 0; i < transformedNet.height(); i++) {
            transformedNet.setPositionValue(i, 0, 0);
            transformedInput.setPositionValue(i, 0, 0);
        }
        i = 0;
        for (Place place : places) {
            mark.setPositionValue(i, 0, petriNet.getMarking().getTokens(place));
            i++;
        }
        g.addStartNode(mark);
        tnetpom.add(mark);
        for (i = 0; i < staticCount; i++) {
            mark = new InvariantsMatrix(places.size(), 1);
            for (int j = 0; j < mark.height(); j++) {
                mark.setPositionValue(j, 0, -10000);
            }
            tnetpom.add(mark);
        }



        for (i = 0; i < tnetpom.size(); i++) {
            parent = tnetpom.get(i);
            int j = 0;
            int order = 0;
            int branchCount = 0;
            for (InvariantsMatrix t : incidenceMatrix.getColumns()) {
                InvariantsMatrix newMarking = t.plusMatrix(parent);
                InvariantsMatrix input = inputMatrix.getRowM(order);
                InvariantsMatrix testMarking = parent.minusMatrix(input);
                if (testMarking.isPositive()) {

                    for (InvariantsMatrix node : g.getParents(parent)) {
                        if (newMarking.minusMatrix(node).isPositive() && !newMarking.minusMatrix(node).isNull()) {
                            log("neohranicena siet");
                            return false;
                        }
                    }
                    boolean contains = false;
                    for (InvariantsMatrix node : g.getNodes()) {
                        if (node.equals(newMarking)) {
                            contains = true;
                        }
                    }
                    if (!contains) {
                        g.addNode(parent, newMarking);
                        tnetpom.add(newMarking);
                        for (j = 0; j < staticCount; j++) {
                            if (newMarking.getPositionValue(staticPosAndTokens[j][0], 0) < staticPosAndTokens[j][1]) {
                                dangerous[tnetpom.size() - 1] = -1;
                                for (int k = 0; k < tnetpom.size(); k++) {
                                    if (tnetpom.get(k).equals(parent)) {
                                        if (dangerous[k] == -1) {
                                            dangerous[k] = 1;
                                        }
                                    }
                                }
                            }
                        }
                        if (i == 0) {
                            if (branchCount>0){
                                transformedNet = transformedNet.addColumn();
                                transformedInput = transformedInput.addColumn();
                            }
                            transformedNet = transformedNet.addRow();
                            transformedNet.setPositionValue(0, branchCount, -1);
                            transformedInput = transformedInput.addRow();
                            transformedInput.setPositionValue(0, branchCount, 1);
                            for (j = 0; j < staticCount; j++) {
                                transformedNet.setPositionValue(j + 1, branchCount, newMarking.getPositionValue(staticPosAndTokens[j][0], 0) - parent.getPositionValue(staticPosAndTokens[j][0], 0));
                                if (input.getPositionValue(staticPosAndTokens[j][0], 0) > 0) {
                                    transformedInput.setPositionValue(j + 1, branchCount, input.getPositionValue(staticPosAndTokens[j][0], 0));
                                }
                            }
                            transformedNet.setPositionValue(transformedNet.height() - 1, branchCount, 1);
                        } else {
                            transformedNet = transformedNet.addColumn();
                            transformedNet = transformedNet.addRow();
                            transformedInput = transformedInput.addColumn();
                            transformedInput = transformedInput.addRow();
                            for (j = 0; j < tnetpom.size(); j++) {
                                if (tnetpom.get(j).equals(parent)) {
                                    transformedNet.setPositionValue(j, transformedNet.width() - 1, -1);
                                    transformedInput.setPositionValue(j, transformedNet.width() - 1, 1);
                                }
                                if (tnetpom.get(j).equals(newMarking)) {
                                    transformedNet.setPositionValue(j, transformedNet.width() - 1, 1);
                                }
                            }
                            for (j = 0; j < staticCount; j++) {
                                transformedNet.setPositionValue(j + 1, transformedNet.width() - 1, newMarking.getPositionValue(staticPosAndTokens[j][0], 0) - parent.getPositionValue(staticPosAndTokens[j][0], 0));
                                if (input.getPositionValue(staticPosAndTokens[j][0], 0) > 0) {
                                    transformedInput.setPositionValue(j + 1, transformedNet.width() - 1, input.getPositionValue(staticPosAndTokens[j][0], 0));
                                }
                            }
                        }
                    } else {
                        if (!parent.equals(newMarking)) {
                            g.getArcs().add(new GraphArc(parent, newMarking));
                            transformedNet = transformedNet.addColumn();
                            transformedInput = transformedInput.addColumn();
                            for (j = 0; j < tnetpom.size(); j++) {
                                if (tnetpom.get(j).equals(parent)) {
                                    transformedNet.setPositionValue(j, transformedNet.width() - 1, -1);
                                    transformedInput.setPositionValue(j, transformedNet.width() - 1, 1);
                                }
                                if (tnetpom.get(j).equals(newMarking)) {
                                    transformedNet.setPositionValue(j, transformedNet.width() - 1, 1);
                                }
                            }
                            for (j = 0; j < staticCount; j++) {
                                transformedNet.setPositionValue(j + 1, transformedNet.width() - 1, newMarking.getPositionValue(staticPosAndTokens[j][0], 0) - parent.getPositionValue(staticPosAndTokens[j][0], 0));
                                if (input.getPositionValue(staticPosAndTokens[j][0], 0) > 0) {
                                    transformedInput.setPositionValue(j + 1, transformedNet.width() - 1, input.getPositionValue(staticPosAndTokens[j][0], 0));
                                }

                            }
                        }
                    }
                    branchCount++;
                }
                order++;
            }
        }
        boolean correctFinal = false;
        boolean pom = true;
        //log("pocet nodes: " + g.getNodes().size());
        for (InvariantsMatrix a : g.getNodes()) {
            //log("node");
            //a.print();
            if (a.getPositionValue(outputPosition, 0) == 1) {
                //log("mozny output marking");
                //a.print();
                for (i = 0; i < a.height(); i++) {
                    if (a.getPositionValue(i, 0) > 0) {
                        for (int j = 0; j < staticPosAndTokens.length; j++) {
                            if ((a.getPositionValue(i, 0) != staticPosAndTokens[j][1]) && (i == staticPosAndTokens[j][0])) {
                                pom = false;
                            }
                            if ((a.getPositionValue(i, 0) == staticPosAndTokens[j][1]) && (i == staticPosAndTokens[j][0])) {
                            }
                        }
                    }
                }
                if (g.getNodes().size() != g.getParents(a).size()) {
                    log("nekorektna pre 1 instanciu");
                    pom = false;
                }
                if (pom) {
                    correctFinal = true;
                } else {
                    correctFinal = false;
                }
                for (int j = 0; j < tnetpom.size(); j++) {
                    if (a.equals(tnetpom.get(j))) {
                        TOposition = j;
                    }
                }
            }
        }
        //log("transformovana siet");
        //transformedNet.print();
        return correctFinal;
    }

    private void createIncidenceMatrix() {
        int placesSize = places.size();
        int transitionsSize = transitions.size();

        InvariantsMatrix outputMatrix = new InvariantsMatrix(placesSize, transitionsSize);

        for (int i = 0; i < placesSize; i++) {
            Place p = places.get(i);
            for (int j = 0; j < transitionsSize; j++) {
                Transition t = transitions.get(j);

                Arc a = null;
                a = p.getConnectedArcTP(t, false,petriNet);

                if (a != null) {
                    outputMatrix.setPositionValue(i, j, a.getMultiplicity());
                } else {
                    outputMatrix.setPositionValue(i, j, 0);
                }
            }
        }

        inputMatrix = new InvariantsMatrix(placesSize, transitionsSize);

        for (int i = 0; i < placesSize; i++) {
            Place p = places.get(i);
            for (int j = 0; j < transitionsSize; j++) {
                Transition t = transitions.get(j);

                Arc a = null;
                a = p.getConnectedArcPT(t, true,petriNet);

                if (a != null) {
                    inputMatrix.setPositionValue(i, j, a.getMultiplicity());
                } else {
                    inputMatrix.setPositionValue(i, j, 0);
                }
            }
        }

        //outputMatrix.print();
        //inputMatrix.print();
        incidenceMatrix = new InvariantsMatrix();
        incidenceMatrix = outputMatrix;
        incidenceMatrix.minusMatrix(inputMatrix.getData());
    }

    public void log(String s) {
        System.out.println(s);
    }

    private int checkDynSound(InvariantsMatrix initialMarking) {
        ArrayList<InvariantsMatrix> markingSet = new ArrayList<InvariantsMatrix>();
        DirectedGraph g = new DirectedGraph();
        InvariantsMatrix parent = new InvariantsMatrix(initialMarking.height(), 1);
        int maximum = 0;
        int instances = initialMarking.getPositionValue(0, 0);

        g.addStartNode(initialMarking);
        markingSet.add(initialMarking);

        for (int i = 0; i < markingSet.size(); i++) {
            parent = markingSet.get(i);
            int j = 0;
            int order = 0;
            for (InvariantsMatrix t : transformedNet.getColumns()) {
                InvariantsMatrix newMarking = t.plusMatrix(parent);
                InvariantsMatrix input = transformedInput.getRowM(order);
                InvariantsMatrix testMarking = parent.minusMatrix(input);
                if (testMarking.isPositive()) {
                    boolean contains = false;
                    for (InvariantsMatrix node : g.getNodes()) {
                        if (node.equals(newMarking)) {
                            contains = true;
                        }
                    }
                    if (!contains) {
                        g.addNode(parent, newMarking);
                        markingSet.add(newMarking);

                    } else {
                        if (!parent.equals(newMarking)) {
                            g.getArcs().add(new GraphArc(parent, newMarking));
                        }
                    }
                }
                order++;
            }
        }

        DirectedGraph gr = new DirectedGraph();
        InvariantsMatrix finalMarking = new InvariantsMatrix();
        boolean isReachable = false;

        for (InvariantsMatrix m : g.getNodes()) {
            int sum = 0;
            for (int i = 0; i < transformedNet.height(); i++) {
                if (dangerous[i] == 1) {
                    sum += m.getPositionValue(i, 0);
                }
            }
            if (maximum < sum) {
                maximum = sum;
            }
            if (m.getPositionValue(TOposition, 0) == instances) {
                finalMarking = m;
                isReachable = true;
            }
        }

        if (isReachable) {
            gr.addStartNode(finalMarking);
            for (int i = 0; i < gr.getNodes().size(); i++) {
                for (InvariantsMatrix m : g.getNodes()) {
                    for (GraphArc a : g.getArcs()) {
                        if (a.isArc(m, gr.getNodes().get(i))) {
                            boolean contains = false;
                            for (InvariantsMatrix node : gr.getNodes()) {
                                if (node.equals(m)) {
                                    contains = true;
                                }
                            }
                            if (!contains) {
                                gr.addNode(gr.getNodes().get(i), m);
                            }
                        }
                    }
                }
            }
        }
        log("nodes " + g.getNodes().size() + " can reach final " + gr.getNodes().size());
        if (g.getNodes().size() != gr.getNodes().size()) {
            sound = false;
        }
        log("maximum " + maximum);
        return maximum;
    }
}
