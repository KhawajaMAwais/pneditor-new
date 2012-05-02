/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.position;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import kohary.datamodel.DatamodelCreator;
import kohary.datamodel.Datamodels;
import kohary.datamodel.dapi.DataModel;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;

/**
 *
 * @author Godric
 */
public class PositionCanvas extends JPanel implements MouseMotionListener, MouseListener {

    private Set<ConnectArc> changableLines = new HashSet<ConnectArc>();
    private int i, control = 0;
    private Point lastClick;
    private ConnectArc movingLine, possibleLine;
    private Set<ElementVV> elements = new HashSet<ElementVV>();
    private PlaceSetFrame frame;
    private PetriNet petriNet;
    protected List<Transition> transitions = new LinkedList<Transition>();
    private Set<ElementVV> selectedElements = new HashSet<ElementVV>();
    private Datamodels dataModels;
    private ElementVV selectedElement = null;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g2);
        for (ConnectArc line : changableLines) {
            line.draw(g);
        }
        for (ElementVV element : elements) {


            element.draw(g);

        }
        changableLines.clear();

    }

    public PositionCanvas(PetriNet petriNet, PlaceSetFrame frame) {

        this.petriNet = petriNet;
        this.frame = frame;
        dataModels = DatamodelCreator.getInstance().getDocument().getDataModels();
        // loadDataModels(dataModels);
        //loadTransitions(petriNet.getTransitions());
        setLayout(new BorderLayout());
        setBackground(Color.white);

        addMouseMotionListener(this);
        addMouseListener(this);

        setBackground(Color.white);
        repaint();

    }

    public void loadDataModels() {
        Point center = new Point(50, 40);
        for (DataModel dataModel : dataModels) {
            DataModelElement element = new DataModelElement(center);
            element.setId(dataModel.getId());
            element.setName(dataModel.getName());
            element.setDatamodel(dataModel);
            elements.add(element);
            center = new Point(center.x, center.y + 60);
        }
    }

    public void loadArcs() {
 setVisualPlaces();
        for (DataModel dataModel : dataModels) {


            for (String transitionId : dataModel.getPosition()) { // pretoze moze byt viac ciar pre jednotlive prechody
                possibleLine = new ConnectArc();

               
                for (ElementVV element : elements) {
                    if ((element instanceof DataModelElement) && (element.getId() == dataModel.getId())) {

                        possibleLine.setStart(element.getNodePoint());
                    }

                    if ((element instanceof TransitionElement) && (element.getName().equals(DataModel.getTransitionById(transitionId).toString()))) {
                        possibleLine.setDestination(element.getNodePoint());
                        possibleLine.setTransition(DataModel.getTransitionById(transitionId));
                    }
                }

                possibleLine.setDataModel(dataModel);

                elements.add(possibleLine);

            }


        }

    }

    // Sulzi na zistenie kolko transitionov je pre danu rolu pre Datamodel
    // Jej uloha je urcenie suradnice koncoveho miesta hrany
    public void loadTransitions(List<Transition> transitions) {

        clearTransitions();
        loadDataModels();
        Point center = new Point(frame.getSize().width - 55, 50);
        for (Transition transition : transitions) {
            TransitionElement element = new TransitionElement(center, transition);
            element.setName(transition.toString());
            elements.add(element);
            center = new Point(center.x, center.y + 100);
        }

        repaint();
    }

    private void clearTransitions() {
        List<ElementVV> deleteElement = new LinkedList<ElementVV>();
        for (ElementVV element : elements) {
            if (element instanceof TransitionElement) {
                deleteElement.add(element);
            }
        }
        elements.removeAll(deleteElement);

    }

    public void setVisualPlaces() {

        loadTransitions(petriNet.getTransitions());

    }

    public void mouseDragged(MouseEvent e) {
        if (control == 1) {

            movingLine = new ConnectArc(lastClick, e.getPoint());
            changableLines.add(movingLine);
            repaint();
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {


        possibleLine = new ConnectArc();
        selectedElements.clear();
        selectedElement = null;

        for (ElementVV element : elements) {
            if (element.containPoint(e.getPoint())) {

                if (element instanceof DataModelElement) {
                    selectedElement = element;
                    lastClick = element.getNodePoint();
                    control = 1;


                    for (DataModel dataModel : dataModels) {
                        if (element.getId() == dataModel.getId()) {
                            possibleLine.setStart(lastClick);
                            possibleLine.setDataModel(dataModel);
                        }
                    }

                }
                if (element instanceof TransitionElement) {
                    selectedElement = element;
                    TransitionElement transitionElement = (TransitionElement) element;
                    Transition transition = transitionElement.getTransition();
                    PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection().clear();
                    PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().getSelection().add(transition);
                }
                if (element instanceof ConnectArc) {
                    ConnectArc arc = (ConnectArc) element;
                   if(arc.containPoint(e.getPoint())){
                   selectedElement = element;
                  
                   }
                }

            }
        }
        if (selectedElement == null) {
            selectedElements.clear();
        } 
        else  if (selectedElement instanceof ConnectArc){
            elements.remove(selectedElement);
            save();
            repaint();
        }
        else {
            selectedElements.add(selectedElement);
        }

    }

    public void mouseReleased(MouseEvent e) {
        control = 0;
        changableLines.clear();

        ElementVV releasedElement = getElementByLocation(e.getPoint());

        if (selectedElement instanceof DataModelElement && releasedElement instanceof TransitionElement) {

            for (ElementVV element : elements) {
                if ((element instanceof TransitionElement) && (element.containPoint(e.getPoint()))) {
                    possibleLine.setDestination(element.getNodePoint());
                    for (Transition transition : petriNet.getTransitions()) {
                        if (element.name.equals(transition.toString())) {
                            possibleLine.setTransition(transition);
                        }
                    }


                }
            }
            if (!hasTransitionDatamode(possibleLine.getTransition())) {
                elements.add(possibleLine);
                save();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Every transition can be associated with only one datamodel",
                        "Warning",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (releasedElement instanceof DataModelElement) {
            DataModelElement dataModelElement = (DataModelElement) releasedElement;
            DatamodelCreator.getInstance().getDataModelSelectManager().setCurrentDataModel(dataModelElement.getDatamodel());
            DatamodelCreator.getInstance().getMainFrame().getSelectDataModelPanel().getListPanel().getList().setSelectedValue(dataModelElement.getDatamodel(), true);
        }

        repaint();
    }

    private boolean hasTransitionDatamode(Transition transition) {
        for (DataModel dataModel : DatamodelCreator.getInstance().getDocument().getDataModels().getElements()) {
            if (dataModel.getPosition().contains(transition.getId())) {
                return true;
            }
        }
        return false;
    }

    private void save() {
      
        for (DataModel dataModel : dataModels) {
            
            dataModel.getPosition().clear();

              Set<Transition> localtransitions = new HashSet<Transition>();
            for (ElementVV element : frame.getCanvas().getElements()) {
                if (element instanceof ConnectArc) {
                    ConnectArc arc = (ConnectArc) element;

                    if (arc.getDataModel().equals(dataModel)) {
                        localtransitions.add(arc.getTransition());
                    }
                }
            }

            if (!localtransitions.isEmpty()) {
                for(Transition transition: localtransitions)
                dataModel.getPosition().add(transition.getId());
                // dataModel.getPosition().get(role).addAll(transitions);

            }

        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private ElementVV getElementByLocation(Point point) {
        for (ElementVV element : elements) {
            if (element.containPoint(point)) {
                return element;
            }
        }
        return null;
    }

    public Set<ElementVV> getElements() {
        return elements;
    }
}
