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
    private ArrayList<DoubleRelation> oneloops = new ArrayList<DoubleRelation>();
    private TwoLoop twoloop;
    private TwoLoopBack twoloopback;
    private OneLoop oneloop;
    private NextRelation next;
    private NextBack nextback;
    private NoWay noway;
    private Paralel paralel;
    
    public AlphaMiner(XESLog xlog) {
        this.alphalog = new AlphaLog(xlog);
        createRelations();
        
        // test 
        this.alphalog.createAlphabet();
        for(String alpha : this.alphalog.getAlphabet()){
            System.out.println(alpha);
        }
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
        System.out.println("Next:");
        for(DoubleRelation dr : this.next.getNext()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        System.out.println("Nextback:");
        for(DoubleRelation dr : this.nextback.getNextBack()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        System.out.println("Noway:");
        for(DoubleRelation dr : this.noway.getNoway()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        System.out.println("Paralel:");
        for(DoubleRelation dr : this.paralel.getParalel()){
            System.out.println("start: " + dr.getStart() + "  end: "+dr.getEnd());
        }
        
        
    }
    
    public void createRelations(){
        twoloop = new TwoLoop(this.alphalog);
        twoloopback = new TwoLoopBack(twoloop);
        oneloop = new OneLoop(this.alphalog);
        next = new NextRelation(this.alphalog);
        nextback = new NextBack(next);
        this.alphalog.createAlphabet();
        noway = new NoWay(this.alphalog.getAlphabet(), next);
        paralel = new Paralel(next, nextback, twoloop, twoloopback);
    }
    
    
    
    
}
