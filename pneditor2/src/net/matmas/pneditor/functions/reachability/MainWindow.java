package net.matmas.pneditor.functions.reachability;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.io.File;
import javax.swing.*;


public class MainWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public MainWindow() {
        initComponents();
        this.setSize(800, 600);
        this.setTitle("Reachability");
        //loadFile();
    }

    private void initComponents() {
        open = new JButton("Open file");
        check = new JButton(new ImageIcon(MainWindow.class.getResource("/resources/pneditor/clean.png"),"sss"));
        JToolBar toolBar = new JToolBar();
        toolBar.setBorderPainted(false);
        toolBar.setFloatable(false);
        toolBar.add(check);
        //toolBar.add(open);
        this.add(toolBar,BorderLayout.NORTH);


        toolBar.addSeparator();
        ScrollPane = new javax.swing.JScrollPane();
        graphPanel = new JPanel();
        this.order = new JLabel();

        //setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        //org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(desktopapplication1.DesktopApplication1.class).getContext().getResourceMap(MainWindow.class);
        //open.setText(resourceMap.getString("open.text")); // NOI18N
        open.setName("open"); // NOI18N
        open.setActionCommand("open");
        open.addActionListener(this);

        //check.setText(resourceMap.getString("check.text")); // NOI18N
        check.setName("check"); // NOI18N
        check.setActionCommand("reach");
        check.addActionListener(this);

        ScrollPane.setName("ScrollPane"); // NOI18N
        graphPanel.setName("graph"); // NOI18N
        graphPanel.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                graphAncestorMoved(evt);
            }

			@Override
			public void ancestorResized(HierarchyEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        
        graphPanel.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                graphCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        graphPanel.setMaximumSize(new Dimension(60000, 60000));
        GroupLayout graphLayout = new GroupLayout(graphPanel);
        graphPanel.setBackground(Color.red);
        //ScrollPane.setBackground(Color.blue);
       // graphPanel.setLayout(graphLayout);

        graphLayout.setHorizontalGroup(
            graphLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        graphLayout.setVerticalGroup(
            graphLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        ScrollPane.setViewportView(graphPanel);
        this.add(ScrollPane);
        

        pack();
        this.net =  LoadFile.load();
         System.out.print(net.toString());
         this.graph = Reachability.findGraph(this.net);
         System.out.print(graph.toString());
         this.graphPanel = new DrawGraph(this.graph);
         this.graphPanel.setPreferredSize(this.graphPanel.getSize());
         this.graphPanel.revalidate();
         this.ScrollPane.setViewportView(this.graphPanel);
         this.ScrollPane.revalidate();
         System.out.print(graphPanel.toString());
         String order = "";
         for(Place p : this.net.getPlaces()){
             order += p.getLabel()+" , ";
         }
         order = order.substring(0, order.length()-3);

         this.order.setText("M = ( "+order+" )   Deadlocks: "+this.graph.getDeadlocks());

         if(graph.isBounded()){
            String liveness = Reachability.checkLiveness(this.graph, this.net) ? " | Net is L4 live" : " | Net is NOT L4 live";
            this.order.setText(this.order.getText()+liveness);
         }

         this.validate();
         this.setVisible(true);
         System.out.println(this.graphPanel.getSize());
    }// </editor-fold>//GEN-END:initComponents
    
    private void graphAncestorMoved(java.awt.event.HierarchyEvent evt) {
    	this.graphPanel.revalidate();
    }
    
    private void graphCaretPositionChanged(java.awt.event.InputMethodEvent evt) {
    	this.graphPanel.revalidate();
    }
    

    
    public void loadFile(){
 	   	fc = new JFileChooser();
 	     int returnVal = fc.showOpenDialog(this);
 	     if (returnVal == JFileChooser.APPROVE_OPTION) {
 	         xml = fc.getSelectedFile();
 	     }
 	     else{
 	    	 xml = new File("Semafor.pflow");
 	     }
 	     this.net =  LoadFile.load();
 	     System.out.print(net.toString());
 	     this.graph = Reachability.findGraph(this.net);
 	     System.out.print(graph.toString());
 	     this.graphPanel = new DrawGraph(this.graph);
 	     this.graphPanel.setPreferredSize(this.graphPanel.getSize());
 	     this.graphPanel.revalidate();
 	     this.ScrollPane.setViewportView(this.graphPanel);
 	     this.ScrollPane.revalidate();
 	     System.out.print(graphPanel.toString());
 	     String order = "";
 	     for(Place p : this.net.getPlaces()){
 	    	 order += p.getLabel()+" , "; 
 	     }
 	     order = order.substring(0, order.length()-3);
 	     
 	     this.order.setText("M = ( "+order+" )   Deadlocks: "+this.graph.getDeadlocks());
 	     
 	     if(graph.isBounded()){
 	    	String liveness = Reachability.checkLiveness(this.graph, this.net) ? " | Net is L4 live" : " | Net is NOT L4 live";
 	    	this.order.setText(this.order.getText()+liveness);
 	     }
 	    	 
 	     this.validate();
 	     System.out.println(this.graphPanel.getSize());


    }
    
    public void actionPerformed(ActionEvent arg0) {
    	if("open".equals(arg0.getActionCommand())){
    		loadFile();
    	}
    	if("reach".equals(arg0.getActionCommand())){
    		JFrame check = new CheckReachability(this.graph, this.net);
    		check.setLocation(this.getX()+30,this.getY()+30);
    	}
    	
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuBar menubar;
    private JMenu menu;
    private JScrollPane ScrollPane;
    private JButton check;
    private JPanel graphPanel;
    private JButton open;
    private Graph graph;
	private Net net;
    private JFileChooser fc;
    private File xml;
    private JLabel order;
    // End of variables declaration//GEN-END:variables

}
