package xesloganalyzer;

import AlphaMiner.AlphaMiner;
import com.itextpdf.text.DocumentException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import lpsolve.LpSolveException;
import net.matmas.pnapi.FiringSequence;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;
import net.matmas.pnsynthesizer.layout.graphviz.GraphvizLayout;
import net.matmas.pnsynthesizer.synthesis.wrongcontinuations.Synthesis;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.info.XLogInfo;
import org.deckfour.xes.info.XLogInfoFactory;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.out.XesXmlSerializer;
import org.deckfour.xes.util.XTimer;
import org.openide.util.Exceptions;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author XY
 */
public class LogTable extends JFrame implements ActionListener,MouseListener{
    
    public Object[][] dataT;
    public Object[] columnsT;
    public Object[][] dataE;
    public Object[] columnsE;
    public DefaultTableModel modelT = new DefaultTableModel(); 
    public DefaultTableModel modelE = new DefaultTableModel(); 
    public XESLog xeslog = null;
    public XLog xlogopened;
    public static JTable tableT;
    public static JTable tableE;
    private DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    private MultiLineHeaderRenderer renderer2 = new MultiLineHeaderRenderer();
    private JTextArea area = new JTextArea(7, 25);
    private int selectedTrace = 0;
    private boolean openedlog=false;
    private JButton delT = new JButton("Delete selected Case");
    private JButton delE = new JButton("Delete selected Event");
    private JButton addt = new JButton("Add Case");
    private JButton save = new JButton("Save log to file");
    private JButton open = new JButton("Open log file");
    private JButton close = new JButton("Close log");
    private JButton modify = new JButton("Modify log");
    private JButton analyze = new JButton("Analyze log");
    private JButton alpha = new JButton("Alpha miner");
    private JButton synthesize = new JButton("Synthesize");
    private JButton newlog = new JButton("Create log");
    private JButton exit = new JButton("EXIT");
    private JLabel testlab;
    
    
    public LogTable() {
        
        this.modelT.addColumn("Trace name");
        this.modelE.addColumn("Events");
        JPanel panel = new JPanel(new GridLayout(1,0,5,5));
        JPanel panelcontainer = new JPanel(new GridLayout(0,1,2,2));
        JPanel panelinfo = new JPanel(new GridLayout(0,1));
        JPanel panelmenu = new JPanel(new GridLayout(0,1,10,10));
        renderer.setHorizontalAlignment( JLabel.CENTER );
        tableT = new JTable(modelT){
          public boolean isCellEditable(int rowIndex, int colIndex) {
          return false; //Disallow the editing of any cell
          }
        };
        tableT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableT.setRowHeight(30);
        if(openedlog)
        tableT.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tableT.setPreferredScrollableViewportSize(new Dimension(450, 800));
        tableT.setFillsViewportHeight(true);
        tableT.addMouseListener(this);

        tableE = new JTable(modelE){
          public boolean isCellEditable(int rowIndex, int colIndex) {
          return false; //Disallow the editing of any cell
          }
        };
        tableE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if(openedlog)
        tableE.getColumnModel().getColumn(0).setCellRenderer(renderer2);
        tableE.setRowHeight(50);
        tableE.setPreferredScrollableViewportSize(new Dimension(450, 800));
        tableE.setFillsViewportHeight(true);
        tableE.addMouseListener(this);
        area.setEditable(false);
        area.setMargin(new Insets(30, 30, 0, 0));
        area.setAutoscrolls(true);
        area.setForeground(Color.black);
        area.setBackground(null);

        JScrollPane scrollPaneT = new JScrollPane(tableT);
        tableT.setFillsViewportHeight(true);
        JScrollPane scrollPaneE = new JScrollPane(tableE);
        tableE.setFillsViewportHeight(true);
        JPanel work = new JPanel(new GridLayout(0,1));
        JPanel delTE = new JPanel();
        JPanel modanal = new JPanel(new GridLayout(2,2,1,1));
        modanal.setBorder(new EmptyBorder(2, 10, 2, 10) );
        panelmenu.setBorder(new EmptyBorder(10, 10, 10, 10) );
        Border eBorder = BorderFactory.createEtchedBorder();  
        area.setBorder( BorderFactory.createTitledBorder( eBorder, "Element information" ) );  
        panelinfo.add(area);
        delTE.add(delT);
        delTE.add(delE);
        delTE.add(addt);
        modanal.add(modify);
        modanal.add(analyze);
        modanal.add(alpha);
        modanal.add(synthesize);
        work.add(delTE);
        work.add(modanal);
        panelinfo.add(work); 
        panelmenu.add(newlog);
        panelmenu.add(open);
        panelmenu.add(save);
        panelmenu.add(close);
        panelmenu.add(exit);
        
        panelcontainer.add(panelinfo,BorderLayout.NORTH);
        panelcontainer.add(panelmenu);
        
        panel.add(scrollPaneT);
        panel.add(scrollPaneE);
        Container contentPane = this.getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(panelcontainer, BorderLayout.EAST);
        this.setSize(900, 800);
        this.setLocationRelativeTo(getParent());
        this.setVisible(true);
        delT.addActionListener(this);
        delE.addActionListener(this);
        addt.addActionListener(this);
        newlog.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        close.addActionListener(this);
        modify.addActionListener(this);
        analyze.addActionListener(this);
        alpha.addActionListener(this);
        synthesize.addActionListener(this);
        exit.addActionListener(this);
    }
    
    public void fillTable(){
        for(XESTrace trace: xeslog.getTraces()){
            modelT.addRow(new Object[]{trace.getName()});
        }
        xeslog.getTraces().get(0);
    
    for(XESEvent event:xeslog.getTraces().get(0).getEvents()){    
    modelE.addRow(new Object[]{event.getName() +"\n"+event.getResource()});
    }
        
        
    }
    
    public void addTraceToLog (XESTrace trace){
        modelE.getDataVector().removeAllElements();
        modelT.getDataVector().removeAllElements();
        this.xeslog.addTraceToLog(trace);
        
         for(XESTrace tracee: xeslog.getTraces()){
            modelT.addRow(new Object[]{tracee.getName()});
        }
        
        for(XESEvent event:xeslog.getTraces().get((xeslog.getTraces().size())-1).getEvents()){    
        modelE.addRow(new Object[]{event.getName() +"\n"+event.getResource()});
        }
        tableT.setRowSelectionInterval(tableT.getRowCount()-1, tableT.getRowCount()-1);
        tableE.getColumnModel().getColumn(0).setCellRenderer(renderer2);
        selectedTrace = (tableT.getRowCount()-1);
        tableT.repaint();
        tableE.repaint();
        
    }
    
    
    public void refresh(String title){
        modelE.getDataVector().removeAllElements();
            modelT.getDataVector().removeAllElements();
            tableT.repaint();
            tableE.repaint();
            this.xeslog = new XESLog();
            this.xeslog.setName(title);
            area.setText("");
        this.setTitle(title);
        this.repaint();
    }
    
    public void modify(XESLog xeslog){
        modelE.getDataVector().removeAllElements();
            modelT.getDataVector().removeAllElements();
            tableT.repaint();
            tableE.repaint();
            this.xeslog = xeslog;
            area.setText("");
            fillTable();
            this.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Alpha miner")){
             PNEditor.getInstance().getDocument().getPetriNet().clear();
             PNEditor.getInstance().getMainFrame().refreshActions();
             XTimer timer = new XTimer();
             timer.start();
             AlphaMiner alphaminer = new AlphaMiner(this.xeslog);
             timer.stop();
             System.out.println("Time to alphaminer is :"+timer.getDurationString());
             try {
                     PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
                        new GraphvizLayout(petriNet).layout();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
		
		PNEditor.getInstance().getMainFrame().refreshActions();
        }
        
        if(e.getActionCommand().equals("Synthesize")){
            FileOutputStream fileOutputStream = null;
            {
                OutputStreamWriter out = null;
                try {
                    
                    PNEditor.getInstance().getDocument().getPetriNet().clear();
                    PNEditor.getInstance().getMainFrame().refreshActions();
                    File writefile = new File(System.getProperty("user.home") + "/log-pneditor.txt");
                    fileOutputStream = new FileOutputStream(writefile);
                    out = new OutputStreamWriter(fileOutputStream, "UTF-8");
                    int i=0;
                    for (XESTrace trace : this.xeslog.getTraces()) {
                            
                            for (XESEvent event : trace.getEvents()) {
                            try {
                                out.write(i + " " + event.getName() + '\n');
                            } catch (IOException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                            }
                            i++;
                        }
                    try {
                        out.close();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    File logFile = new File(System.getProperty("user.home") + "/log-pneditor.txt");
                    PetriNet petriNet = PNEditor.getInstance().getDocument().getPetriNet();
                    Set<FiringSequence> additionalBehaviour;
                    try {
                         XTimer timer = new XTimer();
                         timer.start();
                        additionalBehaviour = new Synthesis(logFile, petriNet).synthesize();
                         timer.stop();
                         System.out.println("Time to synthesis is :"+timer.getDurationString());
                    } catch (LpSolveException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                   
		try {
                 
                        new GraphvizLayout(petriNet).layout();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
		
		PNEditor.getInstance().getMainFrame().refreshActions();
                   
                } catch (UnsupportedEncodingException ex) {
                    Exceptions.printStackTrace(ex);
                }catch (FileNotFoundException ex) {
                   Exceptions.printStackTrace(ex);
               } finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    try {
                        out.close();
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
		
            
        }        
                
        if(e.getActionCommand().equals("Add Case")){
            if(this.xeslog!= null){
            new AddTrace();
            this.hide();}
        }
        
        if(e.getActionCommand().equals("Create log")){
            new AddLog();
            
        }
        
        
        if(e.getActionCommand().equals("EXIT")){
             modelE.getDataVector().removeAllElements();
            modelT.getDataVector().removeAllElements();
            tableT.repaint();
            tableE.repaint();
            this.xeslog = null;
            area.setText("");
            this.hide();
        }
        
        if(e.getActionCommand().equals("Modify log")){
             if(xeslog != null){
            XlogWriter writerlog = new XlogWriter(xeslog);
            XLog log2 = writerlog.getXlog();       
            new ModifyTable(log2);
             }
        }
        
        if(e.getActionCommand().equals("Analyze log")){
            if(xeslog != null){
         XlogWriter writerlog = new XlogWriter(xeslog);
         XLog log2 = writerlog.getXlog();   
         XESanalyze analyze = new XESanalyze(log2);
         
         if(xeslog!=null){
            
            JFileChooser chooser = new JFileChooser();
                File file1 = null;
        FileFilter filter = new FileNameExtensionFilter("PDF log file", "pdf");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
         
               file1 =  chooser.getSelectedFile();
        }
        
         if(file1!=null){
          String fileName=file1.getName();
          String fname="";
          String ext="";
          int mid= fileName.lastIndexOf(".");
          if(mid!=-1){
          fname=fileName.substring(0,mid);
          ext=fileName.substring(mid+1,fileName.length());
          }
          File file = null;
         
          if(ext.equals("pdf")){
              file =  new File(file1.getPath());
          } 
          else
          {file =  new File(file1.getPath() + ".pdf");}
                        
                        
                         final JChartLibApp app = new JChartLibApp(analyze.getEvents(),"Events occurrences","Events","Occurrences");
                                app.addWindowListener(app);
                                app.pack();
                                app.setVisible(true);
                                
                        final JChartLibAppR appr = new JChartLibAppR(analyze.getResources(),"Resources occurrences","Resources","Occurrences");
                                appr.addWindowListener(app);
                                appr.pack();
                                appr.setVisible(true);
                try {
                    RunLenghtEvent createPDF = new RunLenghtEvent(analyze,file);
                } catch (DocumentException ex) {
                    Logger.getLogger(LogTable.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LogTable.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LogTable.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LogTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         }
        }
        }   
        if(e.getActionCommand().equals("Close log")){
            modelE.getDataVector().removeAllElements();
            modelT.getDataVector().removeAllElements();
            tableT.repaint();
            tableE.repaint();
            area.setText("");
            this.xeslog = null;
        }
        
        if(e.getActionCommand().equals("Delete selected Case")){
            int rowIndex = tableT.getSelectedRow();
            if(rowIndex!=-1){
            xeslog.removeTraceFromLogViaTrace(xeslog.getTraces().get(rowIndex));
            modelT.removeRow(rowIndex);
            tableT.repaint();}
            selectedTrace = 1;
            tableT.setRowSelectionInterval(0, 0);
            
        }
        
         if(e.getActionCommand().equals("Delete selected Event")){
             
             int rowIndex = tableE.getSelectedRow();
             if(rowIndex!=-1){
             xeslog.getTraces().get(selectedTrace).removeEventFromTraceViaEvent(xeslog.getTraces().get(selectedTrace).getEvents().get(rowIndex));
             modelE.removeRow(rowIndex);
             tableE.repaint();
             tableE.setRowSelectionInterval(0, 0);
             }
        }
         
        if(e.getActionCommand().equals("Open log file")){
           
            try {
                JFileChooser chooser = new JFileChooser();
                File file = null;
        FileFilter filter = new FileNameExtensionFilter("XES log file", "xes");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
         
               file =  chooser.getSelectedFile();
        }
        
        if(file!=null){
        XesXmlParser xparser = new XesXmlParser();
        XLog log3 = null;
        List<XLog> log2list = xparser.parse(new BufferedInputStream(new FileInputStream(file)));
        for(XLog logg:log2list){
            log3 = logg;
        }
         modelE.getDataVector().removeAllElements();
            modelT.getDataVector().removeAllElements();
            tableT.repaint();
            tableE.repaint();
            this.xeslog = null;
        ReadXESLogFromFileToClass xlog = new ReadXESLogFromFileToClass(log3);
        this.xeslog = xlog.getXeslog();
        this.setTitle(xeslog.getName());
        fillTable();
        tableT.getColumnModel().getColumn(0).setCellRenderer(renderer);
        tableE.getColumnModel().getColumn(0).setCellRenderer(renderer2);
         XLogInfo info = XLogInfoFactory.createLogInfo(log3);
         area.setText(" Number of traces: "+info.getNumberOfTraces()+"\n"
        +"\n Number of events: " +info.getNumberOfEvents()+"\n"
        );
        this.repaint();
        }
        
            } catch (Exception ex) {
                Logger.getLogger(LogTable.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    
        }
        if(e.getActionCommand().equals("Save log to file")){
            
            if(xeslog!=null){
            
            JFileChooser chooser = new JFileChooser();
                File file1 = null;
        FileFilter filter = new FileNameExtensionFilter("XES log file", "xes");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
         
               file1 =  chooser.getSelectedFile();
        }
        
        
          String fileName=file1.getName();
          String fname="";
          String ext="";
          int mid= fileName.lastIndexOf(".");
          if(mid!=-1){
          fname=fileName.substring(0,mid);
          ext=fileName.substring(mid+1,fileName.length());
          }
          File file = null;
         
          if(ext.equals("xes")){
              file =  new File(file1.getPath());
          } 
          else
          {file =  new File(file1.getPath() + ".xes");}
         
         XlogWriter writerlogs = new XlogWriter(xeslog);
         XLog log = writerlogs.getXlog();
        
        XesXmlSerializer xserializer = new XesXmlSerializer();
       
        OutputStream oStream;
        try {
            oStream = new BufferedOutputStream(new FileOutputStream(file));
           // xstream.toXML(log, oStream);
            xserializer.serialize(log, oStream);
        }   catch (IOException ex) {
                Logger.getLogger(LogTable.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
        }
    }  
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getComponent().equals(tableT)){
        if(xeslog!=null){    
        int rowIndex = tableT.getSelectedRow();
        if(rowIndex != -1){
        modelE.getDataVector().removeAllElements();
        for(XESEvent event:xeslog.getTraces().get(rowIndex).getEvents()){    
        modelE.addRow(new Object[]{event.getName() +"\n"+event.getResource()});
        }
        area.setText(" Attributes for case:"+this.xeslog.getTraces().get(rowIndex).getName()+"\n"
        +"\n name: " +this.xeslog.getTraces().get(rowIndex).getName()+"\n"
        +" description: " +this.xeslog.getTraces().get(rowIndex).getDescription()+"\n"
        +" number of events: " +this.xeslog.getTraces().get(rowIndex).getEvents().size()
        );
        area.repaint();
        tableE.repaint();
        selectedTrace = rowIndex;
        }
        }
        } 
        
        if(e.getComponent().equals(tableE)){
        if(xeslog!=null){     
        int rowIndex = tableE.getSelectedRow();    
        if(rowIndex+1<=this.xeslog.getTraces().get(selectedTrace).getEvents().size()&&(rowIndex!=-1)){
        area.setText("Attributes for event:"+(rowIndex+1)+"\n"
        +"\n name: " +this.xeslog.getTraces().get(selectedTrace).getEvents().get(rowIndex).getName()+"\n"
        +" resource: " +this.xeslog.getTraces().get(selectedTrace).getEvents().get(rowIndex).getResource()+"\n"
        +" lifecycle: " +this.xeslog.getTraces().get(selectedTrace).getEvents().get(rowIndex).getLifecycle()+"\n"
        +" timestamp: " +this.xeslog.getTraces().get(selectedTrace).getEvents().get(rowIndex).getTime()
        );
        area.repaint();
        }
        }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    public void repaintTables(){
        tableE.repaint();
        tableT.repaint();
    }
    
    
}
