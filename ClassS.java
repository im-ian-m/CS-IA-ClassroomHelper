package classroom.helper;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author ianmoore
 */
public class ClassS {
    private LinkedList<Student> attendees;
    private LinkedList<Group> groups;
    private String level;
    private ArrayList<Prompt> SAQPrompts;
    private ArrayList<Prompt> ERQPrompts;
    private LinkedList<Student> stuOps;
    private Section section;
    
    public ClassS(String lvl, LinkedList<Student> presStus, Section sxn) {
        level = lvl;
        attendees = presStus;
        groups = new LinkedList<Group>();
        section = sxn;
        stuOps = copyOf(attendees);
        loadGrus();
        loadPrompts();
    }
    
    //PRE: val is less than or equal to class size
    public void createNewGrus(boolean autoNoOfGrus, int val) {
	int classSize = attendees.size();
	int noOfGrus, stusPerGru;
	double randVal;
        boolean done;
        
	LinkedList<Student> stusLeft = copyOf(attendees);
	Student stuToSort = new Student("a", "stu", false);
	
	if(autoNoOfGrus) {
            stusPerGru = val; //stusPerGroup is the maximum group size
            noOfGrus = (int) Math.ceil((double) classSize/(double) stusPerGru);
        } else {
            noOfGrus = val;
            stusPerGru = (int) Math.ceil((double) classSize/(double) noOfGrus);
        }
	groups = new LinkedList<Group>();
        
	for(int i = 0; i < classSize; i++) {
            randVal = Math.random() * stusLeft.size();
            done = false;
            for(int j = 0; j < stusLeft.size(); j++) {
                if ((double) j + 1.0 >= randVal) { 
                    stuToSort = stusLeft.get(j);
                    stusLeft.remove(j);
                    break;
                }
            }
            
            if(!autoNoOfGrus && stusPerGru > (int) Math.ceil((double)(stusLeft.size() + 1) / (double)(noOfGrus - groups.size()))) {
                stusPerGru--;
            }

            for(int j = 0; j < noOfGrus; j++) {
                if(noOfGrus != 2 && stusPerGru * noOfGrus != classSize && j == noOfGrus - 2 && classSize - stusPerGru * noOfGrus < stusPerGru / 2 + 1) {
                    if(groups.size() >= noOfGrus - 1) { // if the second to last group already exists
                        if(groups.get(j).getMems().size() == (classSize - stusPerGru * (noOfGrus - 2)) - (classSize - stusPerGru * (noOfGrus - 2)) / 2) { //if the 2nd to last group has its intended no of members
                            continue;
                        }
                    }
                }
                if(j == groups.size()) {
                    groups.add(new Group(j, new LinkedList<Student>()));
                    groups.get(j).addMem(stuToSort);
                    break;
                } else if(groups.get(j).getMems().size() >= stusPerGru) {
                    continue;
                } else if(stuToSort.getParBlist().size() == 0) {
                    groups.get(j).addMem(stuToSort);
                    break;
                }

                for(int k = 0; k < stuToSort.getParBlist().size(); k++) {
                    if(groups.get(j).hasMem(stuToSort.getParBlist().get(k))) { // if the group has the blacklisted member
                        break;
                    } else if(k == stuToSort.getParBlist().size() - 1) {
                        groups.get(j).addMem(stuToSort);
                        done = true;
                    }
                }

                if(done == true) {
                    break;
                }
                
                //at this point there are too many exclusions to make a fully valid solution, so the program gives up and puts the student in the last group
                if (j == noOfGrus - 1) {
                    groups.get(j).addMem(stuToSort);
                }
            }
        }
        
        section.updateStuData();
    }
    //POST: the active class session will contain new, randomly generated groups
    
    //updates the attendees list by checking the data of each student in the section
    public void updatePresStus() {
        LinkedList<Student> allStus = section.getStus();
        int matched;
        
        for(int i = 0; i < allStus.size(); i++) {
            if(allStus.get(i).getIsPres()) {
                matched = 0;
                for(int j = 0; j < attendees.size(); j++) {
                    if(allStus.get(i) == attendees.get(j)) {
                        break;
                    } else if(j == attendees.size() - 1) {
                        //attendees does not contain the student who was found to be present
                        attendees.add(matched + 1, allStus.get(i));
                        break;
                    } else if(attendees.get(j).getIsPres()) {
                        matched++;
                    }
                }    
            } else {
                for(int j = 0; j < attendees.size(); j++) {
                    if(attendees.get(j) == allStus.get(i)) {
                        attendees.remove(j);
                        break;
                    }
                }
            }
        }
        
        resetStuOps();
    }
    
    private LinkedList<Student> copyOf(LinkedList<Student> list) {
        LinkedList<Student> copy = new LinkedList<Student>();
        for(int i = 0; i < list.size(); i++) {
            copy.add(list.get(i));
        }
        
        return copy;
    }
    
    public LinkedList<Student> getPresStus() {
        return attendees;
    }
    
    public String getLvl() {
        return level;
    }
    
    public ArrayList<Prompt> getSAQs() {
        return SAQPrompts;
    }
    
    public ArrayList<Prompt> getERQs() {
        return ERQPrompts;
    }
    
    public LinkedList<Student> getStuOps() {
        return stuOps;
    }
    
    //sets the prompts available to this class, divided into ERQs and SAQs
    public void loadPrompts() {
        if(level.equals("SL")) { //SAQ prompts are only asked in SL classes
            SAQPrompts = new ArrayList<Prompt>();
        }
        ERQPrompts = new ArrayList<Prompt>();
        
        for(int i = 0; i < section.getPros().size(); i++) {
            if(section.getPros().get(i).getType().equals("SAQ")) {
                //Prompt is an SAQ (and therefore an SL prompt)
                if(level.equals("SL")) {
                    SAQPrompts.add(section.getPros().get(i));
                }
            } else {
                //prompt is an ERQ
                if(level.equals("HL") == section.getPros().get(i).getIsHL()) { //if level of prompt and class match
                    ERQPrompts.add(section.getPros().get(i));
                }
            }
        }
    }
    
    //chooses one prompt randomly from the provided options
    public Prompt rollPro(ArrayList<Prompt> ops) {
        double randVal = Math.random() * ops.size();
        
        for(int i = 0; i < ops.size(); i++) {
            if(randVal <= (double) i + 1.0 ) {
                ops.get(i).setIsAns(true);
                section.updateSxnProData();
                return ops.get(i);
            }
        }

        return null; // error
    }
    
    //returns one student randomly from the stuOps list after removing them from the list
    public Student rollStu() {
        double randVal = Math.random() * stuOps.size();
        Student tempStu;
        for(int i = 0; i < stuOps.size(); i++) {
            if(randVal <= (double) i + 1.0) {
                stuOps.get(i).setAnsQue(true);
                tempStu = stuOps.get(i);
                stuOps.remove(i); 
                return tempStu;
            }
        }
        
        return null; //error
    }
    
    //resets the stuOps list so that all present students can be chosen again
    public void resetStuOps() {
        boolean opExists;
        if(stuOps.size() == 0) {
            stuOps = copyOf(attendees);
            for(int i = 0; i < stuOps.size(); i++) {
                stuOps.get(i).setAnsQue(false);
            }
        } else {
            for(int i = 0; i < attendees.size(); i++) {
                opExists = false;
                for(int j = 0; j < stuOps.size(); j++) {
                    if(attendees.get(i) == stuOps.get(j)) {
                        opExists = true;
                        break;
                    } 
                }
                //helps us break out if stuOps already contains the student we are considering adding
                if(opExists) {
                    continue;
                }

                stuOps.add(attendees.get(i));
                attendees.get(i).setAnsQue(false);
            }
        }
    }
    
    
    public LinkedList<Group> getGrus() {
        return groups;
    }
    
    //PRE: all students in the group are being purged (since groupNum is never set)
    public void remGru(int groupNum) {
        for(int i = groupNum + 1; i < groups.size(); i++) {
            for(int j = 0; j < groups.get(i).getMems().size(); j++) {
                groups.get(i).getMems().get(j).setGru(i - 1);
            }
            groups.get(i).setNum(i - 1);
        }
        
        groups.remove(groupNum);
    }
    
    //creates group objects based on the group numbers associated with a class's student objects
    public void loadGrus() {
        LinkedList<Student> mems;
        for(int i = 0; i < section.getStus().size(); i++) {
            if(section.getStus().get(i).getGru() == -1) {
                continue;
            } 
            if(groups.size() - 1 < section.getStus().get(i).getGru()) { 
                //if our new group would come after all existing ones
                for(int j = groups.size(); j < section.getStus().get(i).getGru(); j++) {
                    groups.add(new Group(j, new LinkedList<Student>())); //fills list with empty groups between current end and the location of the group being added
                }
                mems = new LinkedList<Student>();
                mems.add(section.getStus().get(i));
                groups.add(new Group(section.getStus().get(i).getGru(), mems));
            } else {
                groups.get(section.getStus().get(i).getGru()).addMem(section.getStus().get(i));
            }
        }
    }
    
    //used in the process of reverting changes made to groups
    public void clearGrus() {
        for(int i = 0; i < groups.size(); i++) {
            groups.set(i, new Group(i, new LinkedList<Student>()));
        }
    }
    
    //removes a student from their current group, setting their group number to -1 and deleting their group if it becomes empty
    public void removeStuFromCurrentGru(Student studentToRemove) {
        if(studentToRemove.getGru() != -1) {
            //remove this student from their group (if they're in one)
            if(groups.get(studentToRemove.getGru()).getMems().size() > 1) {
                groups.get(studentToRemove.getGru()).remMem(studentToRemove);
            } else {
                remGru(studentToRemove.getGru());
            }
            studentToRemove.setGru(-1);
        }
    }
    
    //swaps the groups of two students
    public void swapToGru(Student stu1, Student stu2) {
        int temp;
        groups.get(stu2.getGru()).remMem(stu2);
        temp = stu1.getGru();
        groups.get(stu1.getGru()).remMem(stu1);
        groups.get(stu2.getGru()).addMem(stu1);
        groups.get(temp).addMem(stu2);
    }
}
