package classroom.helper;

import java.util.ArrayList;

/**
 *
 * @author ianmoore
 */
public class Student {
    private String firstName, lastName;
    private boolean isPresent = true, isHL, answeredQue;
    private int groupNum = -1;
    private ArrayList<Student> partnerBlist = new ArrayList<Student>();
    private String basicPBlist = "N/A";
            
    public Student(String fName, String lName, boolean isHLStu) {
        firstName = fName;
        lastName = lName;
        isHL = isHLStu;
    }
    
    public String getFuName() {
        return firstName + " " + lastName;
    }
    
    public String getFiName() {
        return firstName;
    }
    
    public void setFiName(String fiName) {
        firstName = fiName;
    }
    
    public String getLaName() {
        return lastName;
    }
    
    public void setLaName(String laName) {
        lastName = laName;
    }
    
    public boolean getIsHL() {
        return isHL;
    }
    
    public void setIsHL(boolean isH) {
        isHL = isH;
    }
    
    public boolean getIsPres() {
        return isPresent;
    }
    
    public void setIsPres(boolean isPres) {
        isPresent = isPres;
    }
    
    public boolean getAnsQue() {
        return answeredQue;
    }
    
    public void setAnsQue(boolean ansQue) {
        answeredQue = ansQue;
    }
    
    public int getGru() {
        return groupNum;
    }
    
    public void setGru(int gru) {
        groupNum = gru;
    }
    
    public void addToParBlist(Student stu) {
        partnerBlist.add(stu);
        stu.addParToBlist(this); //mutual addition of this student to the other student's partner blacklist
    }
    
    public void addParToBlist(Student stu) {
        partnerBlist.add(stu);
    }
    
    public void remFromParBlist(Student stu) {
        remParFromBlist(stu);
        stu.remParFromBlist(this); //mutual removal of this student from the other student's partner blacklist
    }
    
    private void remParFromBlist(Student stu) {
        for(int i = 0; i < partnerBlist.size(); i++) {
            if(stu == partnerBlist.get(i)) {
                partnerBlist.remove(i);
                break;
            }
        }
    }
        
    public ArrayList<Student> getParBlist() {
        return partnerBlist;
    }
    
    public void setParBlistToEmpty() {
        partnerBlist = new ArrayList<Student>();
    }
    
    public String getBPBlist() {
        return basicPBlist;
    }
    
    public void setBPBlist(String blist) {
        basicPBlist = blist;
    }
    
    //updates the textual representation of this student's partner blacklist (i.e. the basic partner blist or BPBlist) to match their dynamic blacklist
    public void updateBPBlist() {
        basicPBlist = "";
        for(int i = 0; i < partnerBlist.size(); i++) {
            basicPBlist += partnerBlist.get(i).getFiName() + "_" + partnerBlist.get(i).getLaName() + ",";
        }
        
        if(partnerBlist.size() > 0) {
            basicPBlist = basicPBlist.substring(0, basicPBlist.length() - 1); //removes the extra "," at the end for stus with blisted partners
        } else {
            basicPBlist += "N/A"; //no blisted partners so BPBlist is "N/A"
        }
    }
}
