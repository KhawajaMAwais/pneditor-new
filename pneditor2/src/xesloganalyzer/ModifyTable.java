/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.matmas.pneditor.PNEditor;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.out.XesXmlSerializer;

/**
 *
 * @author XY
 */
public class ModifyTable extends JFrame implements ActionListener,MouseListener{
    
    public DefaultTableModel modelE = new DefaultTableModel(); 
    public DefaultTableModel modelED = new DefaultTableModel();
    public Object[][] dataE;
    public Object[] columnsE;
    public Object[][] dataED;
    public Object[] columnsED;
    public XESLog xeslog;
    public XLog xlog;
    public static JTable tableE;
    public static JTable tableED;
    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    private JButton next = new JButton(">");
    private JButton back = new JButton("<");
    private JButton save = new JButton("Save modification");
    private JButton close = new JButton("Close");
    private JCheckBox combo = new JCheckBox("Remove same traces");
    private XESanalyze analyze=null;
    private ArrayList<XESEvent> eventsToRem = new ArrayList<XESEvent>();
    public double allevent=0;
    
    public ModifyTable(XLog xlog){
        this.analyze = new XESanalyze(xlog);
        ReadXESLogFromFileToClass xlogr = new ReadXESLogFromFileToClass(xlog);
        this.xeslog = xlogr.getXeslog();
        this.allevent = analyze.getCountevents();
        this.modelE.addColumn("Events");
        this.modelED.addColumn("Events to delete");
        this.tableE = new JTable(modelE);
        this.tableED = new JTable(modelED);
        tableE.setRowHeight(50);
        tableED.setRowHeight(50);
        filltable();
        JPanel panel = new JPanel();
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel.setLayout(new GridBagLayout());
        JScrollPane scrollPaneE = new JScrollPane(tableE);
        tableE.setFillsViewportHeight(true);
        JScrollPane scrollPaneED = new JScrollPane(tableED);
        tableED.setFillsViewportHeight(true);
        
        GridBagConstraints c = new GridBagConstraints();
        
        
        c.weightx = 0.5;  
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 600;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(scrollPaneE, c);
        
        JPanel nexbac = new JPanel(new GridLayout(0,1));
        nexbac.add(next);
//        nexbac.add(new JLabel());
//        nexbac.add(back);
        
        c.weightx = 0.5;  
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 50;
        c.gridx = 1;
        c.gridy = 0;
        panel.add(nexbac, c);
        
        c.weightx = 0.5;  
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 600;
        c.gridx = 2;
        c.gridy = 0;
        panel.add(scrollPaneED, c);
        
        
        JPanel panel1 = new JPanel(new GridLayout(1,0,10,10));
        JPanel panel2 = new JPanel(new GridLayout(1,0,10,10));
        JPanel panel3 = new JPanel(new GridLayout(1,0,10,10));
        JPanel panel4 = new JPanel(new GridLayout(1,0,10,10));
        panel2.add(combo);
        panel4.add(save);
        panel4.add(close);
        panel1.add(panel2);
        panel1.add(panel3);
        panel1.add(panel4);
        
        JPanel panelup = new JPanel();
        panelup.add(new JLabel("Select events to remove from log"));
        
        Container contentPane = this.getContentPane();
        contentPane.add(panelup, BorderLayout.NORTH);
        contentPane.add(panel,BorderLayout.CENTER);
        contentPane.add(panel1,BorderLayout.AFTER_LAST_LINE);
        this.setSize(900, 800);
        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
        next.addActionListener(this);
        save.addActionListener(this);
        close.addActionListener(this);
    }
    
    public void filltable(){
        modelE.getDataVector().removeAllElements();
        DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
        for (XESEvent event : analyze.getEvents()) {    
             double relative = 0;
             relative=(event.getCount()/allevent)*100;   
             modelE.addRow(new Object[]{event.getName()+"  occurrence : "+twoDigit.format(relative)+" %"});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(">")){
             int rowIndex = tableE.getSelectedRow();
             if(rowIndex!=-1){
             DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
              double relative = 0;
              relative=(analyze.getEvents().get(rowIndex).getCount()/allevent)*100;   
              modelED.addRow(new Object[]{analyze.getEvents().get(rowIndex).getName()+"  occurrence : "+twoDigit.format(relative)+" %"});
              eventsToRem.add(analyze.getEvents().get(rowIndex));
              modelE.removeRow(rowIndex);
              analyze.getEvents().remove(rowIndex);
              filltable();
              tableE.repaint();
              tableED.repaint();
             }
        }
        if(e.getActionCommand().equals("Close")){
            modelE.getDataVector().removeAllElements();
            modelED.getDataVector().removeAllElements();
            tableED.repaint();
            tableE.repaint();
            this.xeslog = null;
            this.hide();
        }
        
        if(e.getActionCommand().equals("Save modification")){
            if(eventsToRem.size()>0){
                for(XESEvent event: eventsToRem){
                    xeslog.removeEventFromLog(event.getName());
                }
            }     
           if(combo.isSelected()){
               xeslog.createIndentificatorsForTraces();
               xeslog.createIdentities();
           }                   
                PNEditor.getInstance().getMainFrame().getDrawingBoard().getCanvas().logtable.modify(xeslog);
                this.dispose();
        }
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
