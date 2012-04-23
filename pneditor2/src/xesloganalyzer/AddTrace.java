/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
public class AddTrace extends JFrame implements ActionListener{
    
    private JButton save = new JButton("Save");
    private JButton close = new JButton("Close");
    private JTextField nametrace = new JTextField();
    private JLabel label = new JLabel("Insert new name");
    private JTextField destrace = new JTextField();
    private JLabel dlabel = new JLabel("Insert description");
    
    public AddTrace(){
         this.setTitle("Create new log");
         JPanel panel = new JPanel(new GridLayout(3,2,5,5));
         nametrace.setText("");
         destrace.setText("");
         panel.add(label);
         panel.add(nametrace);
         panel.add(dlabel);
         panel.add(destrace);
         panel.add(save);
         panel.add(close);
         Container contentPane = this.getContentPane();
         contentPane.add(panel, BorderLayout.CENTER);
         this.setLocationRelativeTo(getParent());
         this.setSize(500, 150);
         this.setVisible(true);
         save.addActionListener(this);
         close.addActionListener(this);
    }

    
    
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals("Save")){ 
       PNEditor.getInstance().getMainFrame().infolog.setText("Logging");    
       PNEditor.getInstance().getMainFrame().panel.setVisible(true);
       PNEditor.getInstance().getMainFrame().panel.repaint();
       PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().logtable.hide();
       PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().setIsLogging(true);
       PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().newTrace = new XESTrace(nametrace.getText(), destrace.getText());
       nametrace.setText("");
       destrace.setText("");
       this.hide();
       }
       if(e.getActionCommand().equals("Close")){
            nametrace.setText("");
            destrace.setText("");
            this.hide();
       }
    }
    
}
