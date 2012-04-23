/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

/**
 *
 * @author XY
 */
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


/**
 * @version 1.0 11/09/98
 */
public class MultiLineHeaderRenderer extends JList implements TableCellRenderer {
  public MultiLineHeaderRenderer() {
    ListCellRenderer renderer = getCellRenderer();
    ((JLabel)renderer).setHorizontalAlignment(JLabel.CENTER);
    setCellRenderer(renderer);
  }
 
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    String str = (value == null) ? "" : value.toString();
    BufferedReader br = new BufferedReader(new StringReader(str));
    String line;
    
    if (isSelected) {
           setBackground(Color.lightGray); // cell (and perhaps other cells) are selected
        }
     if (!isSelected) {
           setBackground(Color.white); // cell (and perhaps other cells) are selected
        }
    Vector v = new Vector();
    try {
      while ((line = br.readLine()) != null) {
        v.addElement(line);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    setListData(v);
    return this;
  }
}