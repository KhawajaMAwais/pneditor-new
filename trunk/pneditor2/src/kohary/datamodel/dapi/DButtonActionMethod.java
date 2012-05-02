/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.dapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import kohary.datamodel.DatamodelCreator;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;

/**
 *
 * @author Godric
 */
public class DButtonActionMethod implements Serializable{

    private Transition toTransition = new Transition();

    public DButtonActionMethod() {
        toTransition.getLabel().setText("Not defined");
    }
    

    public Transition getToTransition() {
        return toTransition;
    }

    public void setToTransition(Transition toTransition) {
        this.toTransition = toTransition;
    }



    public void chooseFromAvaible() {
        DataModel datamodel = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
        
        if(datamodel.getPosition().isEmpty()){
            JOptionPane.showMessageDialog(null, "First, you should associate datamodel with some transition");
        }else{
        
        Transition s = (Transition) JOptionPane.showInputDialog(
                null,
                "Choose transition, after SUBMIT this button",
                "After fire submit button",
                JOptionPane.PLAIN_MESSAGE,
                null,
                getNextFireTransition().toArray(),
               getToTransition());
        
        setToTransition(s);
        }
    }
    

    
    
    private List<Transition> getNextFireTransition(){
        
        DataModel datamodel = DatamodelCreator.getInstance().getDataModelSelectManager().getCurrentDataModel();
        List<String> dataModelTransitions=datamodel.getPosition();
        List<Arc> allArcs =PNEditor.getInstance().getDocument().getPetriNet().getArcs();
        List<Place> places = new ArrayList<Place>();
        List<Transition> nextTransitions = new ArrayList();
        for(Arc arc : allArcs){
            if(!arc.isPlaceToTransition()&& dataModelTransitions.contains(arc.getTransition().getId())){
                places.add(arc.getPlace());
            }
        }
        for(Arc arc : allArcs){
            if(arc.isPlaceToTransition()&& places.contains(arc.getPlace()) && hasTransitionDiffrenetDatamodel(arc.getTransition()) ){
               nextTransitions.add(arc.getTransition());
            }
        }        
        return nextTransitions;
    }
    
        private boolean hasTransitionDiffrenetDatamodel(Transition transition) {
        for (DataModel dataModel : DatamodelCreator.getInstance().getDocument().getDataModels().getElements()) {
            if (dataModel.getPosition().contains(transition.getId())) {
                return true;
            }
        }
        return false;
    }

}
