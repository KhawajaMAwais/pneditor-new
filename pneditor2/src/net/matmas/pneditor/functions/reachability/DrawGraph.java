package net.matmas.pneditor.functions.reachability;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class DrawGraph extends JPanel{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graph graph;
	int barb = 10;
    double phi = Math.toRadians(17);
    private ArrayList<Rectangle> nodes;
    private int nodeSize;
    private int xStart;
    private Rectangle r1;
    private Rectangle r2;
    private double labelX;
    private double labelY;
    private int width = 40;
    private int height = 40;
    private String labelValue;
	
   public DrawGraph(Graph graph) {
	   	super();
	   	this.graph = graph;
                this.labelValue = graph.getLabel();
	   	nodes = new ArrayList<Rectangle>();
	   	this.setSize(this.width,this.height);
                this.setBackground(Color.white);
	   	int rows = 0;
	   	int columns = 0;
	   	int levelCount = 1;
	   	int level = 0;
	   	nodeSize = graph.getNodes().size()>0?graph.getNodes().get(0).getMarking().getMarking().size():0;
	   	
	   	for(Node n : graph.getNodes()){
	   		if(n.getLevel() > rows)
	   			rows = n.getLevel();
	   	}
	   	
	   	while(levelCount != 0){
			   levelCount = 0;
			   for(Node n : this.graph.getNodes()){
				   if(n.getLevel() == level){
					   levelCount++;
				   }
			   }
			   if(levelCount > columns)
				   columns = levelCount;
			   level++;
		}
	   	this.setSize(columns*(nodeSize*17+40)+nodeSize*17+50, ((50+rows*80)+80));
	   	System.out.println("width: "+(columns*(nodeSize*17+40)+nodeSize*17+50)+" height: "+((50+rows*80)+80)+" columns: "+columns+" node size: "+nodeSize);

   }   
   
   public void paint(Graphics g) {
	   super.paintComponent(g);
     Graphics2D g2d = (Graphics2D)g;
     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

     layoutNodes(g2d);
     layoutEdges(g2d);     
     
   }
   
   public void layoutNodes(Graphics2D g2d){
	   int levelCount = 1;
	   int level = 0;
	   int order;
	   
	   ArrayList<Node> levelNodes = new ArrayList<Node>();
	   while(levelCount != 0){
		   levelCount = 0;
		   for(Node n : this.graph.getNodes()){
			   if(n.getLevel() == level){
				   levelNodes.add(n);
				   levelCount++;
			   }
		   }
		   
		   xStart = (this.getWidth()-levelNodes.size()*(nodeSize*17+40))/2;
		   
		   for(int i=0;i<levelNodes.size();i++){
			   g2d.drawRect(xStart+i*(nodeSize*17+40), 50+level*80, nodeSize*17, 40);
                           g2d.drawString(this.labelValue.toString(), xStart+i*(nodeSize*17+40)+5, 65+level*80);
			   g2d.drawString(levelNodes.get(i).getMarking().toString(), xStart+i*(nodeSize*17+40)+15, 85+level*80);
			   this.nodes.add(new Rectangle(xStart+i*(nodeSize*17+40), 50+level*80, nodeSize*17, 40));
			   levelNodes.get(i).setX(xStart+i*(nodeSize*17+40)+((nodeSize*17)/2));
			   levelNodes.get(i).setY(60+level*80);
			   order = 1;
			   for(Edge e : graph.getEdges()){
				   if(e.getSourceId() == levelNodes.get(i).getId() && e.getSourceId() == e.getDestinationId()){
					   Arc2D arc = drawArc(graph.getNodes().get(e.getDestinationId()), order++);
					   g2d.draw(arc);
					   g2d.drawString(e.getLabel(), (int)arc.getCenterX(), (int)arc.getCenterY());
				   }
			   }
		   }
		   levelNodes.clear();
		   level++;
	   }
   }
   
   public void layoutEdges(Graphics2D g2d){
	   GeneralPath path;
	   for(Edge e : graph.getEdges()){
		   if(e.getSourceId() != e.getDestinationId()){
		   for(Node n : graph.getNodes()){
			   if(n.getId() == e.getSourceId()){
				   for(Node n2: graph.getNodes()){
					   if(n2.getId() == e.getDestinationId()){
						   //g2d.drawLine(n.getX(), n.getY(), n2.getX(), n2.getY());
						   path = getPath(n, n2);
						   g2d.draw(path);
						   g2d.drawString(e.getLabel(), (int)this.labelX, (int)this.labelY);
					   }
				   }
			   }
		   }
	   }
	   }
   }
   
   private GeneralPath getPath(Node n1, Node n2) {
	   r1 = new Rectangle(n1.getX()-(this.nodeSize*17/2), n1.getY()-10, this.nodeSize*17, 40);
	   r2 = new Rectangle(n2.getX()-(this.nodeSize*17/2), n2.getY()-10, this.nodeSize*17, 40);
       double x1 = r1.getCenterX();
       double y1 = r1.getCenterY();
       double x2 = r2.getCenterX();
       double y2 = r2.getCenterY();
       double theta = Math.atan2(y2 - y1, x2 - x1);
       Point2D.Double p1 = getPoint(theta, r1);
       Point2D.Double p2 = getPoint(theta+Math.PI, r2);
       GeneralPath path = new GeneralPath(new Line2D.Float(p1, p2));
       // Add an arrow head at p2.
       double x = p2.x + barb*Math.cos(theta+Math.PI-phi);
       double y = p2.y + barb*Math.sin(theta+Math.PI-phi);
       path.moveTo((float)x, (float)y);
       path.lineTo((float)p2.x, (float)p2.y);
       x = p2.x + barb*Math.cos(theta+Math.PI+phi);
       y = p2.y + barb*Math.sin(theta+Math.PI+phi);
       path.lineTo((float)x, (float)y);
       this.labelX = (p1.x + p2.x)/2 + 10;
       this.labelY = (p1.y + p2.y)/2;
       return path;
   }

   private Point2D.Double getPoint(double theta, Rectangle r) {
       double cx = r.getCenterX();
       double cy = r.getCenterY();
       double w = r.width/2;
       double h = r.height/2;
       double d = Point2D.distance(cx, cy, cx+w+2, cy+h+2);
       double x = cx + d*Math.cos(theta);
       double y = cy + d*Math.sin(theta);
       Point2D.Double p = new Point2D.Double();
       int outcode = r.outcode(x, y);
       switch(outcode) {
           case Rectangle.OUT_TOP:
               p.x = cx - h*((x-cx)/(y-cy));
               p.y = cy - h;
               break;
           case Rectangle.OUT_LEFT:
               p.x = cx - w;
               p.y = cy - w*((y-cy)/(x-cx));
               break;
           case Rectangle.OUT_BOTTOM:
               p.x = cx + h*((x-cx)/(y-cy));
               p.y = cy + h;
               break;
           case Rectangle.OUT_RIGHT:
               p.x = cx + w;
               p.y = cy + w*((y-cy)/(x-cx));
               break;
           case 3:
               p.x = cx - w;
               p.y = cy - h;
               break;
           case 12:
               p.x = cx + w;
               p.y = cy + h;
               break;
           case 6:
               p.x = cx + w;
               p.y = cy - h;
               break;
           case 9:
               p.x = cx - w;
               p.y = cy + h;
               break;
           default:
               System.out.println("Non-cardinal outcode: " + outcode);
       }
       return p;
   }
   
   private Arc2D drawArc(Node n, int order){
	   Rectangle rect = new Rectangle(n.getX()-(this.nodeSize/2), n.getY()-10, this.nodeSize, 40);
	   double x,y;
	   float radius;
	   Arc2D arc;
	   if((order % 2) == 0){
		   x = rect.getCenterX()-(rect.getWidth()/2)-40;
		   y = rect.getCenterY();
		   radius = 40+order*5;
		   arc = new Arc2D.Double(new Rectangle2D.Double(x-radius/2-10, y-radius/2, radius, radius), Math.toDegrees(Math.asin(10/(radius/2))), 360-2*Math.toDegrees(Math.asin(10/(radius/2))), Arc2D.OPEN);
	   }
	   else{
		   x = rect.getCenterX()+(rect.getWidth()/2)+40;
		   y = rect.getCenterY();
		   radius = 40+(order+1)*5;
		   arc = new Arc2D.Double(new Rectangle2D.Double(x-radius/2+10, y-radius/2, radius, radius), Math.toDegrees(Math.asin(10/(radius/2)))+180, 360-2*Math.toDegrees(Math.asin(10/(radius/2))), Arc2D.OPEN);
	   }
	   return arc;
   }
   
   
 }