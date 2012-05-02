/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kohary.datamodel.variables.Dvariable;

/**
 *
 * @author Godric
 */
public class Document implements Serializable {

    private Datamodels dataModels = new Datamodels();
    private String projectName;
    private List<Dvariable> variables = new LinkedList();

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    //--------------------------------------------------------
    public String getProjectName() {
        return projectName;
    }

    public Datamodels getDataModels() {
        return dataModels;
    }

    public List<Dvariable> getVariables() {
        return variables;
    }

    public void setVariables(List<Dvariable> variables) {
        this.variables = variables;
    }
//    public void readObject(ObjectInputStream objectInputStream){
//        try {
//            objectInputStream.defaultReadObject();
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void writeObject(ObjectOutputStream objectOutputStream){
//        try {
//            objectOutputStream.defaultWriteObject();
//        } catch (IOException ex) {
//            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
