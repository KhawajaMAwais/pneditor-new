/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AlphaMiner;

import java.util.ArrayList;

/**
 *
 * @author XY
 */
public class XW {
    
    private Imply imply;
    private NoWay noway;
    private ArrayList<String> alphabet= new ArrayList<String>();
    private ArrayList<MultiRelation> helpimply = new ArrayList<MultiRelation>();
    private ArrayList<MultiRelation> YW = new ArrayList<MultiRelation>();

    public XW(Imply imply, NoWay noway,ArrayList<MultiRelation> xwloops) {
        this.imply = imply;
        this.noway = noway;
        this.helpimply=imply.getImlply();
        createAlphabetHelp();
        System.out.println("Alphabet from WX :");
        for(String word : this.alphabet){
            System.out.println(word);
        }
        System.out.println("imply Base");
        for(MultiRelation mr : this.imply.getImlply()){
            System.out.println("start:"+ mr.getStart()+ "   ends"+mr.getEnd());
        }
        createAllElements();
        System.out.println("All imply wx");
        for(MultiRelation mr : this.helpimply){
            System.out.println("start: "+mr.getStart()+"  ends: "+mr.getEnd());
        }
        if(xwloops!=null)
        helpimply.addAll(xwloops);
        clearElements();
        
        System.out.println("YW");
        for(MultiRelation mr : this.YW){
            System.out.println("start: "+mr.getStart()+"  ends: "+mr.getEnd());
        }
    }
    
    

    public ArrayList<MultiRelation> getYW() {
        return YW;
    }
    
        private void createAllElements(){
        ArrayList<MultiRelation> toAdd = new ArrayList<MultiRelation>();
        while(!isAllElementsChecked()){
            for(MultiRelation mr:this.helpimply){
                if(!mr.isChcecked()){
                for(String startw : alphabet){
                   if(!mr.isInStart(startw)){          
                       if(isAllElementsInNoWayRelation(mr.getStart(), startw)&&isAllElementsInImplyRelationFront(mr.getEnd(), startw)){
                           MultiRelation mrToAdd = new MultiRelation();
                           mrToAdd.getStart().addAll(mr.getStart());
                           mrToAdd.getEnd().addAll(mr.getEnd());
                           mrToAdd.getStart().add(startw);
                           toAdd.add(mrToAdd);
                       }                   
                   }
                }
                
                for(String endw : alphabet){
                    if(!mr.isInEnd(endw)){
                       if(isAllElementsInNoWayRelation(mr.getEnd(), endw)&&isAllElementsInImplyRelationBack(mr.getStart(), endw)){
                           MultiRelation mrToAdd = new MultiRelation();
                           mrToAdd.getStart().addAll(mr.getStart());
                           mrToAdd.getEnd().addAll(mr.getEnd());
                           mrToAdd.getEnd().add(endw);                           
                           toAdd.add(mrToAdd);
                       }
                   }
                }
              mr.setChcecked(true);
            }
            }
            helpimply.addAll(toAdd);
        }
        
    }
        
    public void clearElements(){
        if(helpimply.size()>1){
        for(int i=0;i<(helpimply.size()-1);i++){
            int is=0;
            for(int j=i+1;j<helpimply.size();j++){
                if(helpimply.get(j).containsAllElements(helpimply.get(i))){
                    is++;
                }
            }
            if(is==0){
                YW.add(helpimply.get(i));
            }
        }
        YW.add(helpimply.get(helpimply.size()-1)); 
       }
        else{
            YW.add(helpimply.get(0)); 
        }
    }    
        
    public boolean isAllElementsChecked(){
        for(MultiRelation mr:this.helpimply){
           if(!mr.isChcecked()){
               return false;
           } 
        }
        return true;
    }
    
    private void createAlphabetHelp(){
        for(MultiRelation mr:this.imply.getImlply()){
            for(String starte: mr.getStart()){
                if(!isInAlphabetHelp(starte)){
                    alphabet.add(starte);
                }   
            }
            for(String ende: mr.getEnd()){
                if(!isInAlphabetHelp(ende)){
                    alphabet.add(ende);
                }   
            }
        }
    }
    
    public boolean isInAlphabetHelp(String newWord){
        for(String word:alphabet){
            if(word.equals(newWord)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isAllElementsInNoWayRelation(ArrayList<String> words,String caseWord){
        for(String word : words){
            if(!noway.isThere(new DoubleRelation(word,caseWord))){
                return false;
            }
        }
        return true;
    }
    
    public boolean isAllElementsInImplyRelationFront(ArrayList<String> words,String caseWord){
        for(String word : words){
            if(!imply.isThere(caseWord,word)){
                return false;
            }
        }
        return true;
    }
    
    public boolean isAllElementsInImplyRelationBack(ArrayList<String> words,String caseWord){
        for(String word : words){
            if(!imply.isThere(word,caseWord)){
                return false;
            }
        }
        return true;
    }
    
}
