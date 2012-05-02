package AlphaMiner;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author XY
 */
public class XwLoop {
    
    private ArrayList<MultiRelation> xwloops = new ArrayList<MultiRelation>();
    private ArrayList<MultiRelation> helparray = new ArrayList<MultiRelation>();
    private ArrayList<MultiRelation> baseelements = new ArrayList<MultiRelation>();
    private OneLoop oneloop;
    private NextRelation nextcomplet;
    private TwoLoop twoloop;
    private Paralel paralelcomplet;
    private NoWay nowaycomlet;
    private ArrayList<String> alphabethelp=new ArrayList<String>();
    private String actualloop = null;
    private ArrayList<String> starwords = new ArrayList<String>();
    private ArrayList<String> endwords = new ArrayList<String>();

    public XwLoop(NextRelation nextcomplet, TwoLoop twoloop, Paralel paralelcomplet, NoWay nowaycomlet,OneLoop oneloop) {
        this.nextcomplet = nextcomplet;
        this.twoloop = twoloop;
        this.paralelcomplet = paralelcomplet;
        this.nowaycomlet = nowaycomlet;
        this.oneloop = oneloop;
        
        firstStep();
            
    }

    public ArrayList<MultiRelation> getXwloops() {
        return xwloops;
    }
    
    
    
    private void firstStep(){
        for(String ol : this.oneloop.getOneloop()){
            alphabethelp.clear();
            actualloop = ol;
            for(DoubleRelation drnext : this.nextcomplet.getNext()){
                if((drnext.getStart().equals(ol)||drnext.getEnd().equals(ol))&&(!drnext.getStart().equals(drnext.getEnd()))){
                    MultiRelation xwloop = new MultiRelation();
                    xwloop.setForloop(ol);
                    xwloop.getStart().add(drnext.getStart());
                    xwloop.getEnd().add(drnext.getEnd());
                    helparray.add(xwloop);
                }
            }
            alphabethelp.add(ol);
            createAlphabetHelp();
            alphabethelp.remove(actualloop);
           
            createBaseElements();

            createAllElements();

            clearElements();

            helparray.clear();
            baseelements.clear();
        }
        
        comlpletizeElements();
        
    }
    
    public void comlpletizeElements(){
        for(MultiRelation mr:this.xwloops){
            mr.getStart().add(mr.getForloop());
            mr.getEnd().add(mr.getForloop());
        }
    }
    
    public void clearElements(){
        if(baseelements.size()>1){
        for(int i=0;i<(baseelements.size()-1);i++){
            int is=0;
            for(int j=i+1;j<baseelements.size();j++){
                if(baseelements.get(j).containsAllElements(baseelements.get(i))){
                    is++;
                }
            }
            if(is==0){
                xwloops.add(baseelements.get(i));
            }
        }
        xwloops.add(baseelements.get(baseelements.size()-1)); 
       }
        else{
            xwloops.add(baseelements.get(0)); 
        }
    }
    
    
    public void createAllElements(){
        ArrayList<MultiRelation> toAdd = new ArrayList<MultiRelation>();
        while(!isAllElementsChecked()){
            for(MultiRelation mr:this.baseelements){
                if(!mr.isChcecked()){
                for(String startw : starwords){
                   if(!mr.isInStart(startw)){          
                       if(isAllElementsInNoWayRelation(mr.getStart(), startw)){
                           MultiRelation mrToAdd = new MultiRelation();
                           mrToAdd.getStart().addAll(mr.getStart());
                           mrToAdd.getEnd().addAll(mr.getEnd());
                           mrToAdd.getStart().add(startw);
                           mrToAdd.setForloop(actualloop);
                           toAdd.add(mrToAdd);
                       }                   
                   }
                }
                
                for(String endw : endwords){
                    if(!mr.isInEnd(endw)){
                       if(isAllElementsInNoWayRelation(mr.getEnd(), endw)){
                           MultiRelation mrToAdd = new MultiRelation();
                           mrToAdd.getStart().addAll(mr.getStart());
                           mrToAdd.getEnd().addAll(mr.getEnd());
                           mrToAdd.getEnd().add(endw);
                           mrToAdd.setForloop(actualloop);
                           toAdd.add(mrToAdd);
                       }
                   }
                }
              mr.setChcecked(true);
            }
            }
            baseelements.addAll(toAdd);
        }
        
    }
    
    public boolean isAllElementsInNoWayRelation(ArrayList<String> words,String caseWord){
        for(String word : words){
            if(!nowaycomlet.isThere(new DoubleRelation(word, caseWord))){
                return false;
            }
        }
        return true;
    }
    
    public boolean isAllElementsChecked(){
        for(MultiRelation mr:this.baseelements){
           if(!mr.isChcecked()){
               return false;
           } 
        }
        return true;
    }
    
    public void createBaseElements(){
      starwords.clear();
      endwords.clear();
      for(MultiRelation mr:this.helparray){
          for(String starte: mr.getStart()){
               if(!actualloop.equals(starte)){
                starwords.add(starte);
               }
          }
          for(String ende: mr.getEnd()){
              if(!actualloop.equals(ende)){
                endwords.add(ende);
              }
          }
      }
      for(String start: starwords){
          for(String end:endwords){
              if(!this.paralelcomplet.isThere(new DoubleRelation(start, end)))
              {
                  MultiRelation mr = new MultiRelation();
                  mr.getStart().add(start);
                  mr.getEnd().add(end);
                  mr.setForloop(actualloop);
                  baseelements.add(mr);
              }
          }
      }
        
    }
    
    public void createAlphabetHelp(){
        for(MultiRelation mr:this.helparray){
            for(String starte: mr.getStart()){
                if(!isInAlphabetHelp(starte)){
                    alphabethelp.add(starte);
                }   
            }
            for(String ende: mr.getEnd()){
                if(!isInAlphabetHelp(ende)){
                    alphabethelp.add(ende);
                }   
            }
        }
    }
    
    public boolean isInAlphabetHelp(String newWord){
        for(String word:alphabethelp){
            if(word.equals(newWord)){
                return true;
            }
        }
        return false;
    }
    
    
    
    
}
