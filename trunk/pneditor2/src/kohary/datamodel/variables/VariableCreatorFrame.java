/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kohary.datamodel.variables;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Godric
 */
public class VariableCreatorFrame extends JFrame implements WindowListener{

    private VariableEditor atbEditor;
 
    public VariableCreatorFrame(List<Dvariable> variables){
        startup();
        atbEditor.getAtPanel().setVariables(variables);
    }
    public VariableCreatorFrame() {       
        startup();
    }
    
    
    
    private void startup(){
            setSize(600, 300);
        setBackground(Color.WHITE);
        setTitle("DataModel variables");
      
        atbEditor = new VariableEditor(this);
        setLayout(new BorderLayout());

        add(atbEditor);
        setVisible(true);
        repaint();
        addWindowListener(this);
    }

    public VariableEditor getAtbEditor() {
        return atbEditor;
    }

 

    public void windowClosing(WindowEvent e) {        
       
    }
    
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}    
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
