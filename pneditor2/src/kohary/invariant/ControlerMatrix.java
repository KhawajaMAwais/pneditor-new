/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.invariant;

import kohary.invariant.interfaces.ControlMatrix;
import net.matmas.pnapi.PetriNet;

/**
 *
 * @author Godric
 */
public final class ControlerMatrix implements ControlMatrix {

    private BigMatrix matrix;
    private PetriNet net;
    private int sizeOfIdentityMatrix, widthOfIncidenceMatrix;
    

    public ControlerMatrix(PetriNet net, String type) {
        this.net = net;
        createMatrix(type);
        printMatrix();
    }

    public void createMatrix(String type) {
        int column = 0, row = 0;

        if (type.equals("P")) {
           
            sizeOfIdentityMatrix = net.getPlaces().size();
            widthOfIncidenceMatrix = net.getTransitions().size();

        } else {  //T-invariant

            sizeOfIdentityMatrix = net.getTransitions().size();
            widthOfIncidenceMatrix = net.getPlaces().size();
        
        }
   

        matrix = new BigMatrix(sizeOfIdentityMatrix, sizeOfIdentityMatrix + widthOfIncidenceMatrix);


        ///filling identity Matrix/////////
        for (int i = 0; i < matrix.getCountOfRows(); i++) { //rows of Iden Matrix

            for (int j = 0; j < matrix.getCountOfRows(); j++) { //column of Big Matrix
                if (i == j) {
                    matrix.setValueAt(i, j, 1);
                } else {
                    matrix.setValueAt(i, j, 0);
                }
            }

        }
        ////////////////////////////////////

        //filling incidence matrix
        for (int i = 0; i < sizeOfIdentityMatrix; i++) {
            for (int j = matrix.getCountOfRows(); j < matrix.getCountOfColumn(); j++) {
                     if (type.equals("P")) {
                    row = i;
                    column = j - matrix.getCountOfRows(); 
                }else{
                         row = j - matrix.getCountOfRows();
                    column = i;
                }

                if (net.getArc(net.getPlaces().get(row), net.getTransitions().get(column), true) == null) { // ak nie je P->T
                    if (net.getArc(net.getPlaces().get(row), net.getTransitions().get(column), false) == null) { //a nie je T->P
                       //  if (type.equals("P")) {
                          matrix.setValueAt(i, j, 0); // hodnota je 0
                        
                        
                    } else {
                        matrix.setValueAt(i, j,  net.getArc(net.getPlaces().get(row), net.getTransitions().get(column), false).getMultiplicity()); //ak je T->P hodnota je "nasobnost"
                    }
                } else {               //ak  je P->T
                    if (net.getArc(net.getPlaces().get(row), net.getTransitions().get(column), false) == null) { //a ak nie je T->P
                        matrix.setValueAt(i, j,  (-1) * net.getArc(net.getPlaces().get(row), net.getTransitions().get(column), true).getMultiplicity()); //hodnota P->T
                    } else {  //ak je aj P->T a T->P tak urobi rozdiel
                        int muliplicity = net.getArc(net.getPlaces().get(row), net.getTransitions().get(column), false).getMultiplicity() - net.getArc(net.getPlaces().get(row), net.getTransitions().get(column), true).getMultiplicity();
                        matrix.setValueAt(i, j,  muliplicity);// hodnota je rozdiel multiplicit P->T a T->P
                    }
                }
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.getCountOfRows(); i++) { //rows of Iden Matrix

            for (int j = 0; j < matrix.getCountOfColumn(); j++) { //column of Big Matrix
                if(j==matrix.getCountOfA()){
                    System.out.print(" |");
                }
                System.out.print(matrix.getValueAt(i, j));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------");
    }
    public String toStringMatrix(){
        String output="";
           for (int i = 0; i < matrix.getCountOfRows(); i++) { //rows of Iden Matrix

            for (int j = 0; j < matrix.getCountOfColumn(); j++) { //column of Big Matrix
                if(j==matrix.getCountOfA()){
                    output+=" |";
                }
                output+=matrix.getValueAt(i, j);
                output+="   ";
            }
            output+="\n";
        }
        return output;
    }

    public void editMatrix() {
    }

    public BigMatrix getMatrix() {
        return matrix;
    }

    public void deleteMatrix() {
      
    }
}
