/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.invariant;

//variable "row" gives count of number in Big MATRIX´s row
import kohary.invariant.interfaces.Matrix;

//[A|B] -> BigMatrix
//A-identity matrix
/**
 *
 * @author Godric , All rights reserved.
 */
public class BigMatrix implements Matrix {

    private int[][] matrix, temp;
    private int[] tempRow;
    private int row, column;
    private Integer countA;

    public BigMatrix(int row, int column) {
        matrix = new int[row][column];
        this.row = row;
        this.column = column;
        countA = new Integer(row);
    }

    public void setValueAt(int row, int column, int value) {
        this.matrix[row][column] = value;
    }

    public int getValueAt(int row, int column) {
        return matrix[row][column];

    }

    public int getCountOfA() {
        return this.countA;
    }

    public int getCountOfRows() {
        return row;
    }

    public int getCountOfColumn() {
        return column;
    }

    public int getCountOfB() {
        return column - row;
    }

    public void addRow(int[] newRow) {
        temp = matrix.clone();
        matrix = new int[getCountOfRows() + 1][getCountOfColumn()];

        for (int i = 0; i < row; i++) {
            System.arraycopy(temp[i], 0, matrix[i], 0, column);
        }

        matrix[getCountOfRows()] = newRow;
        this.row = row + 1;

    }
    public void addLinearCombination(int negativeRowIndex,int secondRowIndex,int actualColumn){
        tempRow = new int[column];
        int lcm= LCM(matrix[negativeRowIndex][actualColumn]*(-1),matrix[secondRowIndex][actualColumn]);
        int firstCoeficient= lcm/matrix[negativeRowIndex][actualColumn]*(-1);
        int secondCoeficient= lcm/matrix[secondRowIndex][actualColumn];
       for(int j=0;j<column;j++){
           tempRow[j]=firstCoeficient*matrix[negativeRowIndex][j]+secondCoeficient*matrix[secondRowIndex][j];
       }
       addRow(tempRow);
    }
  

    public void deleteRow(int rowIndex) {
        temp = matrix.clone();
        matrix = new int[row - 1][column];
       // System.out.println("Count of row="+row+"\ndeleting row:"+rowIndex+"\n");
        int selector = 0;
        for (int i = 0; i < row-1 ; i++) {
            if(i == rowIndex)
                selector=1;
            if (selector == 0) {
                for (int j = 0; j < column; j++) {
                    matrix[i][j] = temp[i][j];
                }
            }else{
                for (int j = 0; j < column; j++) {
                    if(rowIndex!=(row-1))
                    matrix[i][j] = temp[i+1][j];
                }
            }
        }
        this.row = row-1;
    }
        //Zdroj: http://www.devx.com/tips/Tip/33554 ->LCM alghoritm
     public  int LCM(int x,int y){
      int prod;
      if(y%x==0)
         return y;
      else
      {
         prod=x*y;
         while(x!=y) // get the GCD of 2 given integers
         {
            if(x>y)
               x=x-y;
            else
               y=y-x;   //x now is the GCD
         }
         return LCM(y,prod/x);  //recurse, changing x to y and vice versa
      }   //LCM = (x*y)/(GCD)
   }
  
}
