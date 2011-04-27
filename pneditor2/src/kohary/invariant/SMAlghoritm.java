/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.invariant;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.matmas.pnapi.PetriNet;

/**
 *
 * @author Godric
 */
public final class SMAlghoritm {

    private PetriNet net;
    private BigMatrix matrix, matrixTemp1, matrixTemp2;
    private ControlerMatrix controlerT, controlerP;
    private String type;
    private JFrame frame;//PNeditorFrame

    public SMAlghoritm(PetriNet net, JFrame frame) {
        this.net = net;
        this.frame = frame;
        System.out.println("-------T invariant----------");
        controlerT = new ControlerMatrix(net, "T");
        matrix = controlerT.getMatrix();
        //controlerT.printMatrix();
        getInvariants();
        //controlerT.printMatrix();
        System.out.println("-------P invariant----------");
        controlerP = new ControlerMatrix(net, "P");
        matrix = controlerP.getMatrix();
        getInvariants();
        controlerP.printMatrix();
        output();

    }

    private void getInvariants() {
        for (int j = matrix.getCountOfA(); j < matrix.getCountOfColumn(); j++) {
            for (int i = 0; i < matrix.getCountOfRows(); i++) {
                if (matrix.getValueAt(i, j) < 0) { // hladame zaporne hodnoty v stlpci
                    for (int k = 0; k < matrix.getCountOfRows(); k++) {
                        if (matrix.getValueAt(k, j) > 0) { // hladame kladne
                            matrix.addLinearCombination(i, k, j);
                        }
                    }
                }
            }

            for (int h = 0; h < matrix.getCountOfRows(); h++) {
                int value = matrix.getValueAt(h, j);
                if (value != 0) {
                    matrix.deleteRow(h);
                    h--; //ked ho vymaze posunu sa riadky !!
                }
            }
            
        }
    }

    public void output() {
        /*
        String[] columnNames = {"First Name",
                        "Last Name",
                        "Sport",
                        "# of Years",
                        "Vegetarian"};
Object[][] data = {
    {"Kathy", "Smith",
     "Snowboarding", new Integer(5), new Boolean(false)},
    {"John", "Doe",
     "Rowing", new Integer(3), new Boolean(true)},
    {"Sue", "Black",
     "Knitting", new Integer(2), new Boolean(false)},
    {"Jane", "White",
     "Speed reading", new Integer(20), new Boolean(true)},
    {"Joe", "Brown",
     "Pool", new Integer(10), new Boolean(false)}
};
JTable table = new JTable(data, columnNames);

*/
        JOptionPane.showMessageDialog(frame,
                
                "T - invarianty:\n"
               
                + controlerT.toStringMatrix() + "\n"
                + "P - invarianty:\n" + controlerP.toStringMatrix(),
                "P/T invariants analysis",
                JOptionPane.INFORMATION_MESSAGE,
                null);
  /* JFrame frame;
        Object[][] object = new Object[controlerT.getMatrix().getCountOfRows()][controlerT.getMatrix().getCountOfColumn()];
        String[] columnNames = new String[controlerT.getMatrix().getCountOfColumn()];
        for(int i=0;i<controlerT.getMatrix().getCountOfColumn();i++){
            if(i<controlerT.getMatrix().getCountOfRows())
            columnNames[i]=net.getTransitions().get(i).toString();
            else{
                columnNames[i]=net.getPlaces().get(i-controlerT.getMatrix().getCountOfRows()).toString();
            }
        }
        for(int i=0;i<controlerT.getMatrix().getCountOfRows();i++){
            for(int j=0;j<controlerT.getMatrix().getCountOfColumn();j++){
                object[i][j]=controlerT.getMatrix().getValueAt(i, j);
            }

        }
JTable table = new JTable( object,columnNames);

    frame = new JFrame();
    frame.setSize(200, 200);
    frame.setVisible(true);
    frame.add(table);

       */
    }
}
