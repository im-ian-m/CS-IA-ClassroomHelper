/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom.helper;

/**
 *
 * @author ianmoore
 */
public class Prompt {
    private int id = -1; //id value helps us keep track of which prompt is which
    private String question;
    private boolean isHLPrompt;
    private String type;
    private boolean answered = false;
    
    public Prompt(int idVal, String que, boolean isHL, String ty) {
        id = idVal;
        question = que;
        isHLPrompt = isHL;
        if(isHL) { //may be some redundancy here... but HL prompts must be ERQs
            type = "ERQ";
        } else {
            type = ty;
        }
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int idNum) {
        id = idNum;
    }
    
    public String getQue() {
        return question;
    }
    
    public void setQue(String que) {
        question = que;
    }
    
    public boolean getIsHL() {
        return isHLPrompt;
    }
    
    public void setIsHL(boolean isHL) {
        isHLPrompt = isHL;
    }
    
    public boolean getIsAns() {
        return answered;
    }
    
    public void setIsAns(boolean isAns) {
        answered = isAns;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String ty) {
        type = ty;
    }
}
