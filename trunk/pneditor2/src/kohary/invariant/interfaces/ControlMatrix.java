/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.invariant.interfaces;

import kohary.invariant.BigMatrix;

/**
 *
 * @author Godric
 */
public interface ControlMatrix {
    void editMatrix();
    BigMatrix getMatrix();
    void deleteMatrix();
    void createMatrix(String type); //type of invariant p/t correct values:"P","T"
    
    

}
