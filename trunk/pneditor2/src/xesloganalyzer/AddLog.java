/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.matmas.pneditor.PNEditor;

/**
 *
 * @author XY
 */
public class AddLog extends JFrame implements ActionListener{

    private JButton save = new JButton("Save");
    private JButton close = new JButton("Close");
    private JTextField namelog = new JTextField();
    private JLabel label = new JLabel("Insert new name");

    public AddLog(){
        this.setTitle("Create new log");
        JPanel panel = new JPanel(new GridLayout(2,2,5,5));
        panel.add(label);
        panel.add(namelog);
        panel.add(save);
        panel.add(close);
        Container contentPane = this.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        this.setLocationRelativeTo(getParent());
        this.setSize(500, 100);
        this.setVisible(true);
        save.addActionListener(this);
        close.addActionListener(this);
        
    }
    
    
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Save")){
             PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().logtable.refresh(namelog.getText());
             namelog.setText("");
             this.hide();
        }
        
        if(e.getActionCommand().equals("Close")){
             namelog.setText("");
             this.hide();
        }
    }
    
}
