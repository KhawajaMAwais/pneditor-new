/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.creator.position;

import kohary.datamodel.creator.util.DataModel;
import kohary.datamodel.creator.util.DataModels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pnapi.properties.RoleDefinitionProperty;
import net.matmas.pnapi.properties.RoleProperty;

/**
 *
 * @author Godric
 */
public class Canvas extends JPanel implements MouseMotionListener, MouseListener {

    private Set<ConnectArc> changableLines = new HashSet<ConnectArc>();
    private int i, control = 0;
    private Point lastClick;
    protected DataModels dataModels;
    private ConnectArc movingLine, possibleLine;
    private List<ElementVV> elements = new LinkedList<ElementVV>();
    private PlaceSetFrame frame;
    private Set<ConnectArc> arcs = new HashSet<ConnectArc>();
    private PetriNet petriNet;
    private RoleDefinitionProperty role;
    protected List<Transition> transitions = new LinkedList<Transition>();

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g2);

        for (ConnectArc line : changableLines) {
            line.draw(g);
        }
        for (ConnectArc line : arcs) {
            if (line.getRole().equals(role)) {
                line.draw(g);
            }
        }

        for (ElementVV element : elements) {
            if (element instanceof TransitionElement) {
                element.getCenterPoint().x = frame.getSize().width - 60;
            }
            element.draw(g);
        }

        for (ElementVV element : elements) {
            if (element instanceof TransitionElement) {
                element.draw(g);
            }
        }


        changableLines.clear();

    }

    public Canvas(DataModels dataModels, PetriNet petriNet, PlaceSetFrame frame) {
        this.dataModels = dataModels;
        this.petriNet = petriNet;
        this.frame = frame;

        // loadDataModels(dataModels);
        //loadTransitions(petriNet.getTransitions());
        setLayout(new BorderLayout());
        setBackground(Color.white);

        addMouseMotionListener(this);
        addMouseListener(this);

        setBackground(Color.white);
        repaint();

    }

    public void loadDataModels(DataModels dataModels) {
        Point center = new Point(50, 40);
        for (DataModel dataModel : dataModels) {
            DataModelElement element = new DataModelElement(center);
            element.setId(dataModel.getID());
            element.setName(dataModel.getName());
            elements.add(element);
            center = new Point(center.x, center.y + 60);
        }
    }

    public void loadArcs() {
      
        for (DataModel dataModel : dataModels) {
         
        for(RoleDefinitionProperty role1 :dataModel.getPosition().keySet()) {
                         for (Transition transition : dataModel.getPosition().get(role1)) { // pretoze moze byt viac ciar pre jednotlive prechody
                        possibleLine = new ConnectArc();
            frame.getRoleBox().setSelectedItem(role1);
            setVisualPlaces();
                        for (ElementVV element : elements) {
                            if ((element instanceof DataModelElement) && (element.getId() == dataModel.getID())) {
                                
                                possibleLine.setStart(element.getNodePoint());
                            }
                          
                            if ((element instanceof TransitionElement) && (element.getName().equals(transition.toString()))) {
                                possibleLine.setDestination(element.getNodePoint());
                                possibleLine.setTransition(transition);
                            }
                        }
                        possibleLine.setRole(role1);
                        possibleLine.setDataModel(dataModel);
                        
                        arcs.add(possibleLine);

                    }

                }
            }

        }
    
    // Sulzi na zistenie kolko transitionov je pre danu rolu pre Datamodel
    // Jej uloha je urcenie suradnice koncoveho miesta hrany

    public int getTransitionCountForRole(DataModel dataModel, RoleDefinitionProperty role) {

        return dataModel.getPosition().get(role).size();

    }

    public void loadTransitions(List<Transition> transitions, RoleDefinitionProperty role) {
        this.role = role;
        elements.clear();
        loadDataModels(dataModels);
        Point center = new Point(frame.getSize().width - 50, 50);
        for (Transition transition : transitions) {
            TransitionElement element = new TransitionElement(center);
            element.setName(transition.toString());
            elements.add(element);
            center = new Point(center.x, center.y + 100);
        }

        repaint();
    }

    public void setVisualPlaces() {

        RoleDefinitionProperty role2 = (RoleDefinitionProperty) frame.getRoleBox().getSelectedItem();
        transitions.clear();
        /////////////////////////////////////////////////////////
        for (Transition transition : petriNet.getTransitions()) {
            for (RoleProperty rolein : transition.getProperties().getFilteredByClass(RoleProperty.class)) {
                if (rolein.getRoleId().equals(role2.getId())) {
                    transitions.add(transition);
                }
            }
        }
        /////////////////////////////////////////////////////////

        loadTransitions(transitions, role2);

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

        for (ElementVV element : elements) {
            if (element.containPoint(e.getPoint()) && (element instanceof DataModelElement)) {

                lastClick = element.getNodePoint();
                control = 1;


                for (DataModel dataModel : dataModels) {
                    if (element.getId() == dataModel.ID) {
                        possibleLine.setStart(lastClick);
                        possibleLine.setDataModel(dataModel);
                    }
                }

            }
        }

    }

    public void mouseReleased(MouseEvent e) {
        control = 0;
        changableLines.clear();
        for (ElementVV element : elements) {
            if ((element instanceof TransitionElement) && (element.containPoint(e.getPoint()))) {
                possibleLine.setDestination(element.getNodePoint());
                for (Transition transition : petriNet.getTransitions()) {
                    if (element.name.equals(transition.toString())) {
                        possibleLine.setTransition(transition);
                    }
                }
                possibleLine.setRole(role);
                arcs.add(possibleLine);
            }
        }

        repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public Set<ConnectArc> getArcs() {
        return arcs;
    }

    public void setArcs(Set<ConnectArc> arcs) {
        this.arcs = arcs;
    }
}
