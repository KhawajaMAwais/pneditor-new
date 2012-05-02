/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaMiner;

import java.util.ArrayList;
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
    
    public AlphaMiner(XESLog xlog) {
        this.alphalog = new AlphaLog(xlog);
        createRelations();
        
        // test 
        this.alphalog.createAlphabet();
        
        System.out.println("Relations : twoloop");
        for(DoubleRelation dr : this.twoloop.getTwoloops()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        System.out.println("TwoloopsBack :");
        for(DoubleRelation dr : this.twoloopback.getTwoloopsBack()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        System.out.println("Oneoop:");
        for(String word : this.oneloop.getOneloop()){
            System.out.println(word);
        }
        
        
        System.out.println("Next complet:");
        for(DoubleRelation dr : this.nextcomplet.getNext()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        System.out.println("Nextback compete:");
        for(DoubleRelation dr : this.nextbackcomplet.getNextBack()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        
        System.out.println("Noway complete:");
        for(DoubleRelation dr : this.nowaycomplet.getNoway()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        
        System.out.println("Paralel complete:");
        for(DoubleRelation dr : this.paralelcomplet.getParalel()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        
        
    }
    
    public void createRelations(){
        twoloop = new TwoLoop(this.alphalog);
        twoloopback = new TwoLoopBack(twoloop);
        oneloop = new OneLoop(this.alphalog);
        nextcomplet = new NextRelation(this.alphalog);
        nextbackcomplet = new NextBack(nextcomplet);
        this.alphalog.createAlphabet();
        nowaycomplet = new NoWay(this.alphalog.getAlphabet(), nextcomplet);
        paralelcomplet = new Paralel(nextcomplet, nextbackcomplet, twoloop, twoloopback);
        
        if(oneloop.getOneloop().size()>0){    
        xwloop = new XwLoop(nextcomplet, twoloop, paralelcomplet, nowaycomplet, oneloop);
        
        System.out.println(" ");
        System.out.println("XWLoop");
        for(MultiRelation mr : this.xwloop.getXwloops()){
            System.out.println("start:"+ mr.getStart()+ "   ends"+mr.getEnd());
        }
            
        for(String remove : oneloop.getOneloop()){
            this.alphalog.removeEvent(remove);
        }
        
        for(AlphaTrace trace : alphalog.getTraces()){
            for(AlphaEvent event : trace.getEvents()){
                System.out.print(event.getName()+" ");
            }
            System.out.println(" ");
        }
        
        
        next = new NextRelation(this.alphalog);
        
        System.out.println("Next:");
        for(DoubleRelation dr : this.next.getNext()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        nextback = new NextBack(next);
        
        System.out.println("Nextback:");
        for(DoubleRelation dr : this.nextback.getNextBack()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        this.alphalog.createAlphabet();
        noway = new NoWay(this.alphalog.getAlphabet(), next);
        
        System.out.println("Noway:");
        for(DoubleRelation dr : this.noway.getNoway()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        paralel = new Paralel(next, nextback, twoloop, twoloopback);
        
         System.out.println("Paralel:");
        for(DoubleRelation dr : this.paralel.getParalel()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        imply = new Imply(next, nextback, twoloop, twoloopback);
        wx = new XW(imply, noway,xwloop.getXwloops());
        YW = wx.getYW();
        }
        else{
        imply = new Imply(nextcomplet, nextbackcomplet, twoloop, twoloopback);
        wx = new XW(imply, nowaycomplet,null);
        YW = wx.getYW();  
        }
        
        
    }
    
    
    
    
}
