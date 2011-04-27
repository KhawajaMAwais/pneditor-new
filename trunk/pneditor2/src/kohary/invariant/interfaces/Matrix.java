/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.invariant.interfaces;

/**
 *
 * @author Godric
 */
public interface Matrix {
    void setValueAt(int row,int column,int value);
    int getValueAt(int row,int column);
    int getCountOfA();

}
