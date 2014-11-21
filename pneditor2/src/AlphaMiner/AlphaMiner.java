/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaMiner;

import java.util.ArrayList;
import net.matmas.pnapi.Arc;
import net.matmas.pnapi.PetriNet;
import net.matmas.pnapi.Place;
import net.matmas.pnapi.Transition;
import net.matmas.pneditor.PNEditor;
import xesloganalyzer.XESLog;

/**
 *
 * @author XY
 */
public class AlphaMiner {
    private AlphaLog alphalog;
    private TwoLoop twoloop;
    private TwoLoopBack twoloopback;
    private OneLoop oneloop;
    private NextRelation next;
    private NextRelation nextcomplet;
    private NextBack nextback;
    private NextBack nextbackcomplet;
    private NoWay noway;
    private NoWay nowaycomplet;
    private Paralel paralel;
    private Paralel paralelcomplet;
    private Imply imply;
    private XwLoop xwloop;
    private XW wx;
    private ArrayList<MultiRelation> YW=new ArrayList<MultiRelation>();
    private StartEvents startevents;
    private EndEvents endevents;
    private ArrayList<String> transitionsclass;
    private PetriNet petriNet; 
    
    public AlphaMiner(XESLog xlog) {
        petriNet = PNEditor.getInstance().getDocument().getPetriNet();
        this.alphalog = new AlphaLog(xlog);
        this.startevents = new StartEvents(alphalog);
        this.endevents = new EndEvents(this.alphalog);
        this.alphalog.createAlphabet();
        transitionsclass = this.alphalog.getAlphabet();
        createRelations();
        this.alphalog.createAlphabet();      
    }
    
    public void createRelations(){
        twoloop = new TwoLoop(this.alphalog);
        twoloopback = new TwoLoopBack(twoloop);
        oneloop = new OneLoop(this.alphalog);
        nextcomplet = new NextRelation(this.alphalog);
        for(DoubleRelation n:nextcomplet.getNext()){
            System.out.println("'"+n.getStart()+"'->'"+n.getEnd()+"';");
        }
        nextbackcomplet = new NextBack(nextcomplet);
        this.alphalog.createAlphabet();
        nowaycomplet = new NoWay(this.alphalog.getAlphabet(), nextcomplet);
        paralelcomplet = new Paralel(nextcomplet, nextbackcomplet, twoloop, twoloopback);
        if(oneloop.getOneloop().size()>0){    
                  
            xwloop = new XwLoop(nextcomplet, twoloop, paralelcomplet, nowaycomplet, oneloop);
            
            
        for(String remove : oneloop.getOneloop()){
            this.alphalog.removeEvent(remove);
        }
        
        next = new NextRelation(this.alphalog);           
        nextback = new NextBack(next);            
        this.alphalog.createAlphabet();
        noway = new NoWay(this.alphalog.getAlphabet(), next);              
        paralel = new Paralel(next, nextback, twoloop, twoloopback);       
        imply = new Imply(next, nextback, twoloop, twoloopback);
        wx = new XW(imply, noway,xwloop.getXwloops());
        YW = wx.getYW();
        }
        else{
        imply = new Imply(nextcomplet, nextbackcomplet, twoloop, twoloopback);
        wx = new XW(imply, nowaycomplet,null);
        YW = wx.getYW();  
        }
        createTransitions();
        createNet();
    }
    
    public void createTransitions(){
        for(String tran : this.transitionsclass){
            Transition transition = new Transition();
            transition.getLabel().setText(tran);
            petriNet.addTransition(transition);
        }
    }
    
    public void createNet(){
        int i = 0;
        for(MultiRelation mr : this.YW){
           Place place = new Place();
           String label = "p"+i;
           i++;
           place.getLabel().setText(label);
           petriNet.addPlace(place);
           for(String starts: mr.getStart()){        
               for(Transition tran : petriNet.getTransitions()){
                   if(tran.getLabel().getText().equals(starts)){
                   Arc arc = new Arc();
                   arc.setSource(tran);
                   arc.setDestination(place);
                   petriNet.addArc(arc);
                   }
               }
               
           }
           for(String ends: mr.getEnd()){
               for(Transition tran : petriNet.getTransitions()){               
                   if(tran.getLabel().getText().equals(ends)){
                   Arc arc = new Arc();
                   arc.setSource(place);
                   arc.setDestination(tran);
                   place.getPetriNet().addArc(arc);
                   }
               }
               
           }
        }
        Place start = new Place();
        start.getLabel().setText("start");
        petriNet.addPlace(start);
        for(String event : startevents.getStartEvents()){
            for(Transition tran : petriNet.getTransitions()){
                   if(tran.getLabel().getText().equals(event)){
                   Arc arc = new Arc();
                   arc.setSource(start);
                   arc.setDestination(tran);
                   petriNet.addArc(arc);
                   }
               }
        }
        
        System.out.println("end places");
         for(String event : endevents.getEndEvents()){
             System.out.println(event);
         }
        
        Place end = new Place();
        end.getLabel().setText("end");
        petriNet.addPlace(end);
        for(String event : endevents.getEndEvents()){
            for(Transition tran : petriNet.getTransitions()){
                   if(tran.getLabel().getText().equals(event)){
                   Arc arc = new Arc();
                   arc.setSource(tran);
                   arc.setDestination(end);
                   petriNet.addArc(arc);
                   }
               }
        }
    }
    
    
    
    
}
