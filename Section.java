package classroom.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ianmoore
 */
public class Section {
    private int grade;
    private int number;
    private String name;
    private LinkedList<Student> students;
    private ArrayList<Prompt> prompts;
    private File stuFile;
    private File proFile;
    private ClassS classSession;
    
    public Section(LinkedList<Student> stus, int gra, int num, String baseFPath, ArrayList<Prompt> allPrompts) {
        students = stus;
        grade = gra;
        number = num;
        name = Integer.toString(grade) + ".";
        if(number < 10) {
            name += "0";
        }
        name += Integer.toString(number);
        stuFile = new File(baseFPath + "/Students.tsv");
        proFile = new File(baseFPath + "/Prompts.tsv");
        loadSxnPros(allPrompts);
    }
    
    //creates a new class session
    public ClassS loadClass(String lvl) {
        LinkedList<Student> presStus = new LinkedList<Student>();
        
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getIsPres()) {
                if(lvl.equals("HL") && students.get(i).getIsHL()) {
                    presStus.add(students.get(i));
                } else if(lvl.equals("SL")) {
                    presStus.add(students.get(i));
                }
            }
        }
        
        classSession = new ClassS(lvl, presStus, this);
        return classSession;
    }
    
    public ClassS getClaSsn() {
        return classSession;
    }
    
    public LinkedList<Student> getStus() {
        return students;
    }
    
    //creates dynamic Student ArrayLists from the bPBList of each student (basic string format)
    public void setPBlists() { 
        String pBlist;
        ArrayList<String[]> parBlist;
        
        for(int i = 0; i < students.size(); i++) {
            pBlist = students.get(i).getBPBlist(); 
            System.out.println(pBlist);
            if(pBlist.equals("N/A")) {
                continue;
            } else {
                parBlist = stringToList(students.get(i).getBPBlist());
                
                for(int j = 0; j < parBlist.size(); j++) {
                    for(int k = 0; k < students.size(); k++) {
                        //if the name matches the name on the blacklist
                        if(students.get(k).getFiName().equals(parBlist.get(j)[0]) && students.get(k).getLaName().equals(parBlist.get(j)[1])) {
                            students.get(i).addParToBlist(students.get(k));
                        }
                    }
                }    
            }
        }
    }
    
    //PRE: str will contain a list of names separated by ","s and in the format "firstName_lastName"
    public ArrayList<String[]> stringToList(String str) {
        ArrayList<String[]> names = new ArrayList<String[]>();
        String[] temp = new String[2];
        
        while(str.length() > 0) {
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == ',') {
                    temp = splitString(str.substring(0, i));
                    str = str.substring(i + 1);
                    names.add(copyOf(temp));
                    break;
                } else if(i == str.length() - 1) {
                    temp = splitString(str);
                    str = "";
                    names.add(copyOf(temp));
                }
                
            }
        }
        
        return names; //triggers when we have one name left
    }
    //POST: returns an ArrayList of string arrays from str which had ","s separating each ArrayList element and "_"s separating each array element
    
    //PRE: input string must contain _
    public String[] splitString(String str) {
        String[] result = new String[2];
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '_') {
                result[0] = str.substring(0, i);
                result[1] = str.substring(i + 1);
                return result;
            }
        }
        
        return result;
    }
    
    public String[] copyOf(String[] arr) {
        String[] newArr = new String[arr.length];
        for(int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }
        
        return newArr;
    }
    
    //copies shared prompts list to prompts with each copied object having its status set from data in save file 
    private void loadSxnPros(ArrayList<Prompt> allPros) {
        prompts = new ArrayList<Prompt>();
        String current;
        Scanner scan = new Scanner("");
        Prompt temp;
        
        try {
            scan = new Scanner(proFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scan.nextLine(); //skipping over the heading line
        
        for(int i = 0; scan.hasNext() && (i / 2) < allPros.size(); i++) {
            current = scan.next();
            if(i % 2 == 1) { //if the value retrieved is in the isAnswered column
                temp = new Prompt(i / 2, allPros.get(i / 2).getQue(), allPros.get(i / 2).getIsHL(), allPros.get(i / 2).getType()); //make a copy of each prompt
                temp.setIsAns(Boolean.parseBoolean(current)); //set status of prompt
                prompts.add(temp);
            }
        }

        scan.close();
    }
    
    //PRE: valid student data file exists in the correct directory
    public void updateStuData() {
        FileWriter newFile;
        String pBList = "";
        String newData = "";
        
        if(classSession == null) {
            Scanner scan = new Scanner("");
            try {
                scan = new Scanner(stuFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            newData = scan.nextLine();
            scan.close();
        } else {
            newData = classSession.getLvl();
        }
        
        newData += "\nFirstName LastName isHL isPresent Group parBlist\n";
        
        for(int i = 0; i < students.size() * 6; i++) {
            switch(i % 6) {
                case 0:
                    newData += students.get(i / 6).getFiName();
                    break;
                case 1:
                    newData += " " + students.get(i / 6).getLaName();
                    break;
                case 2:
                    newData += " " + students.get(i / 6).getIsHL();
                    break;
                case 3:
                    newData += " " + students.get(i / 6).getIsPres();
                    break;
                case 4:
                    newData += " " + students.get(i / 6).getGru();
                    break;
                case 5:
                    newData += " " + students.get(i / 6).getBPBlist() + "\n";
            }
            
        }
        newData = newData.substring(0, newData.length() - 1); //remove the final newline character
        try {
            newFile = new FileWriter(getStuF());
            newFile.write(newData);
            newFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //POST: data in student section save file will match data in program
    
    //removes all records of this student from the program
    public void purgeStudent(int stuToPurge) {
        Student studentToRemove = students.get(stuToPurge);
        Student blistedPartner;

        //remove this student from the pBlists of all other students
        while(studentToRemove.getParBlist().size() > 0) {
            blistedPartner = studentToRemove.getParBlist().get(0);
            blistedPartner.remFromParBlist(studentToRemove);
            blistedPartner.updateBPBlist();
        }
        if(classSession != null) {
            classSession.removeStuFromCurrentGru(studentToRemove);
        }
        students.remove(stuToPurge);
    }
    
    public void updateSxnProData() {
        FileWriter newFile;
        String newData = "PromptNo isAnswered\n";
        for(int i = 0; i < prompts.size(); i++) {
            newData += i + " " + prompts.get(i).getIsAns() + "\n";
        }
        newData = newData.substring(0, newData.length() - 1);
        
        try {
            newFile = new FileWriter(proFile);
            newFile.write(newData);
            newFile.close();
        } catch (IOException ex) {
            Logger.getLogger(Section.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Prompt> getPros() {
        return prompts;
    }
    
    public String getName() {
        return name;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public File getStuF() {
        return stuFile; 
    }
    
    public File getProF() {
        return proFile;
    }
}
