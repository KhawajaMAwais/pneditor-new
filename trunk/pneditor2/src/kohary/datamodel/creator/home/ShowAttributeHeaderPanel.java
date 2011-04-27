/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kohary.datamodel.creator.home;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Godric
 */
public class ShowAttributeHeaderPanel extends JPanel{
    private JLabel label,type,dataInterpreter;
    public ShowAttributeHeaderPanel() {
        setLayout(new GridLayout(1,3));
        label = new JLabel();
        type = new JLabel();
        dataInterpreter = new JLabel();

        
        label.setFont(new Font("ITALIC", Font.BOLD, 12));
        type.setFont(new Font("ITALIC", Font.BOLD, 12));
        dataInterpreter.setFont(new Font("ITALIC", Font.BOLD, 12));

        
        label.setText("Label:");
        type.setText("Type:");
        dataInterpreter.setText("Visually demo");

        add(label);
        add(type);
        add(dataInterpreter);
        setVisible(true);
    }

}
