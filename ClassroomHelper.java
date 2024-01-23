package classroom.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ianmoore
 */
public class ClassroomHelper extends javax.swing.JFrame {
    private LinkedList<Student> sectionMems;
    private ArrayList<Section> sections = new ArrayList<Section>();
    private ArrayList<Prompt> allPrompts;
    private Section currentSxn;
    private int currentSxnNum;
    private ArrayList<String> allSxnNames;
    private ArrayList<String> allSxnPaths;
    
    public LinkedList<Student> getSxnMems() {
        return sectionMems;
    }
    
    public void setSxnMems(LinkedList<Student> stus) {
        sectionMems = stus;
    }
    
    public ArrayList<Section> getSxns() {
        return sections;
    }
    
    public Section getCurSxn() {
        return currentSxn;
    }
    
    public void setCurSxn(Section sxn) {
        currentSxn = sxn;
    }
    
    public int getCurSxnNum() {
        return currentSxnNum;
    }
    
    public void setCurSxnNum(int num) {
        currentSxnNum = num;
    }
    
    public ArrayList<String> getSxnNames() {
        return allSxnNames;
    }
    
    public ArrayList<String> getSxnPaths() {
        return allSxnPaths;
    }
    
    public ArrayList<Prompt> getAllPros() {
        return allPrompts;
    }
    
    //PRE: sxnFPath is a valid file path for a folder containing a "Students.tsv" file which contains correctly formatted data
    //reads a section's student data file and uses it to create a list of student objects
    public void loadStus(String sxnFPath) {
        sectionMems = new LinkedList<Student>();
        sxnFPath += "/Students.tsv";
        Scanner scan = new Scanner("");
        File sxnF = new File(sxnFPath);
        String fName = "", lName = "";
        boolean isHL, isPres;
        int gru;
        
        try {
            scan = new Scanner(sxnF);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scan.nextLine();
        scan.nextLine();
        
        for(int i = 0; scan.hasNext(); i++) {
            switch(i % 6) {
                case 0:
                    fName = scan.next(); //first names
                    break;
                case 1:
                    lName = scan.next(); //last names
                    break;
                case 2: 
                    isHL = Boolean.parseBoolean(scan.next());//isHL
                    sectionMems.add(new Student(fName, lName, isHL));
                    break;
                case 3:
                    isPres = Boolean.parseBoolean(scan.next());
                    sectionMems.get(i / 6).setIsPres(isPres); //isPresent
                    break;
                case 4:
                    gru = Integer.parseInt(scan.next());
                    sectionMems.get(i / 6).setGru(gru); //groupNum
                    break;
                case 5:
                    sectionMems.get(i / 6).setBPBlist(scan.next());
                    break;
                default:
                    scan.next();
            }
        }
        scan.close();
        
        
    }
    //POST: the sectionMems field will contain a linked list of student objects
    
    //PRE: sxnInd refers to a valid index in both the allSxnNames list and the allSxnPaths list, lvl is either "HL" or "SL"
    public boolean startClass(int sxnInd, String lvl) {
        String sxnFPath = allSxnPaths.get(sxnInd) + "Students.tsv";
        String loadClassStr = null;
        Scanner scan = new Scanner("");
        File sxnF = new File(sxnFPath);
        FileWriter newFile;
        boolean loadClass = false, isHL = false, isPres = false;
        LinkedList<Student> stus = sectionMems;
        String fName = "", lName = "", newData, isHLLvl = "false", pBlist;
        int ind = 0, gru;
        try {
            scan = new Scanner(sxnF);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(scan.nextLine().equals(lvl)) {
            loadClassStr = (String) javax.swing.JOptionPane.showInputDialog(null, "Group and attendance data from an existing class session has been found. \nWould you like to resume this class session or start a new one?", "Start a Class - Start/Load Class Session Popup", JOptionPane.PLAIN_MESSAGE, null, new String[]{"Load Existing Class Session", "Start New Class Session"}, "Load Existing Class Session");
            if(loadClassStr == null) {
                scan.close();
                return false;
            }
            
            loadClass = loadClassStr.equals("Load Existing Class Session");
        } else {
            //class level has changed so the old class session can't be loaded
            loadClass = false;
        }
        
        scan.nextLine(); //skip over heading line
        
        if(loadClass) {
            while(scan.hasNext()) {
                switch(ind % 6) {
                    case 3:
                        isPres = Boolean.parseBoolean(scan.next());
                        stus.get(ind / 6).setIsPres(isPres); //isPresent
                        break;
                    case 4:
                        gru = Integer.parseInt(scan.next());
                        stus.get(ind / 6).setGru(gru); //groupNum
                        break;
                    case 5:
                        pBlist = scan.next();
                        stus.get(ind / 6).setBPBlist(pBlist);
                        break;
                    default:
                        scan.next();
                }
                ind++;
            }
        } else {
            newData = lvl + "\nFirstName LastName isHL isPresent Group parBlist\n";
            while(scan.hasNext()) { //loop is repeated in both cases so that loadClass isn't unecessarily checked each time
                switch(ind % 6) {
                    case 0:
                        fName = scan.next(); //first names
                        newData += fName;
                        break;
                    case 1:
                        lName = scan.next(); //last names
                        newData += " " + lName;
                        break;
                    case 2:
                        isHLLvl = scan.next(); //isHL
                        isHL = Boolean.parseBoolean(isHLLvl);
                        newData += " " + isHLLvl;
                        break;
                    case 3:
                        scan.next();
                        if(lvl.equals("HL")) {
                            if(isHL) {
                                isPres = true;
                            } else {
                                isPres = false;
                            }
                        } else {
                            isPres = true;
                        }
                        stus.get(ind / 6).setIsPres(isPres); //isPresent
                        newData += " " + isPres;
                        break;
                    case 4:
                        scan.next();
                        stus.get(ind / 6).setGru(-1); //groupNum
                        newData += " -1";
                        break;
                    case 5:
                        pBlist = scan.next();
                        stus.get(ind / 6).setBPBlist(pBlist);
                        newData += " " + pBlist + "\n";
                        break;
                }
                ind++;

            }
            newData = newData.substring(0, newData.length() - 1); //remove the final newline character
            try {
                newFile = new FileWriter(sxnFPath);
                newFile.write(newData);
                newFile.close();
            } catch (IOException ex) {
                Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        scan.close();
        int index;
        Section newSxn;
        if(sections.size() == 0) {
            sections.add(new Section(stus, Integer.parseInt(allSxnNames.get(sxnInd).substring(0, 2)), Integer.parseInt(allSxnNames.get(sxnInd).substring(3, 5)), allSxnPaths.get(sxnInd), allPrompts));
            currentSxn = sections.get(0);
            currentSxnNum = 0;
            currentSxn.setPBlists();
        } else {
            index = getIndex(sections, allSxnNames.get(sxnInd), 0, sections.size() - 1);
            if(index >= sections.size()) {
                index = 0;
            }
            if(sections.get(index).getStus() != stus) {
                newSxn = new Section(stus, Integer.parseInt(allSxnNames.get(sxnInd).substring(0, 2)), Integer.parseInt(allSxnNames.get(sxnInd).substring(3, 5)), allSxnPaths.get(sxnInd), allPrompts);
                sections.add(index, newSxn);
                currentSxn = newSxn;
                currentSxnNum = index;
                currentSxn.setPBlists();
            }
        }
        return true;
    }
    //POST: returns false if the operation failed (input data was invalid or user cancelled the operation), otherwise returns true and creates/updates a section object
    
    //finds the directory paths for each section's data returns them
    private static ArrayList<String> loadSxnPaths(String basePath) {
        File f1, f2, stusF, prosF;
        double sxnId1;
        boolean done;
        //String basePath = "";
        String[] grades, sxns;
        ArrayList<String> allPaths = new ArrayList<String>();
        /*if(new File("").getAbsolutePath().endsWith("src") || new File("").getAbsolutePath().endsWith("dist")) {
           basePath = "../"; // running from application package
        }*/
        basePath += "SectionData/";
        f1 = new File(basePath);
        grades = f1.list();
        for(int i = 1; i < grades.length; i++) {
            f2 = new File(basePath + grades[i] + "/");
            sxns = f2.list();
            for(int j = 0; j < sxns.length; j++) {
                done = false;
                if(!sxns[j].matches("[0-9]{2}")) {
                    continue;
                }
                stusF = new File(basePath + grades[i] + "/" + sxns[j] + "/Students.tsv");
                prosF = new File(basePath + grades[i] + "/" + sxns[j] + "/Prompts.tsv");
                if(stusF.exists() && prosF.exists()) {
                    if(allPaths.size() == 0) {
                        allPaths.add(basePath + grades[i] + "/" + sxns[j] + "/");
                    } else {
                    //perform a sequential search to place the new path in the allPaths list in the correct order based on the section id of the section whose path is being added
                        for(int k = 0; k < allPaths.size(); k++) {
                            // sxnId1 is the section id of the section whose path is at index k with that value being retreived from the path itself
                            sxnId1 = Double.parseDouble(allPaths.get(k).substring(allPaths.get(k).length() - 6, allPaths.get(k).length() - 4) + "." + allPaths.get(k).substring(allPaths.get(k).length() - 3, allPaths.get(k).length() - 1));
                            if(sxnId1 > Double.parseDouble(grades[i] + "." + sxns[j])) {
                                allPaths.add(k, basePath + grades[i] + "/" + sxns[j] + "/");
                                done = true;
                                break;
                            }
                        }
                        if(!done) {
                            allPaths.add(basePath + grades[i] + "/" + sxns[j] + "/");
                        }
                    }
                }
            }            
        }
        return allPaths;
    }
    
    //PRE: sxnPaths contains a list of path strings for all existing sections
    private static ArrayList<String> getSxnNames(ArrayList<String> sxnPaths) {
        String sxnGrade;
        String sxnNumber;
        ArrayList<String> sxnNames = new ArrayList<String>();
        for(int i = 0; i < sxnPaths.size(); i++) {
            sxnGrade = sxnPaths.get(i).substring(sxnPaths.get(i).length() - 6, sxnPaths.get(i).length() - 4);
            sxnNumber = sxnPaths.get(i).substring(sxnPaths.get(i).length() - 3, sxnPaths.get(i).length() - 1);
            sxnNames.add(sxnGrade  + "." + sxnNumber);
        }
        
        return sxnNames;
    }
    //POST: returns an ArrayList of strings representing the section names found
    
    //PRE: the AllPrompts.tsv file exists and contains a correctly formatted list of prompts
    private static ArrayList<Prompt> loadAllPros(String basePath) {
        Scanner scan = new Scanner("");
        File allProsF = new File(basePath + "AllPrompts.tsv");
        ArrayList<Prompt> pros = new ArrayList<Prompt>();
        int idVal = 0;
        String que = "", type;
        boolean isHL = false;
        
        try {
            scan = new Scanner(allProsF);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClassroomHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scan.useDelimiter("[_\\v]"); //changing the delimeter used from " " to "_"
        
        scan.nextLine(); //skipping over the heading line
        
        for(int i = 0; scan.hasNext(); i++) {
            
            switch(i % 4) {
                case 0:
                    idVal = Integer.parseInt(scan.next());
                    break;
                case 1:
                    que = scan.next();
                    break;
                case 2:
                    isHL = Boolean.parseBoolean(scan.next());
                    break;
                case 3:
                    if(isHL == true) {
                        type = "ERQ";
                        scan.next();
                    } else {
                        type = scan.next();
                    }
                    pros.add(new Prompt(idVal, que, isHL, type));
            }
        }
        
        return pros;
    }
    //POST: an ArrayList of Prompt objects will be returned
    
    //PRE: sxns contains all Section objects created so far, name is the name of the section to find/be inserted, start = 0 and end = sxns.size()
    //a binary search method returning the index of an existing Section object with the given name or the index to insert a new section object (numerical order)
    public static int getIndex(ArrayList<Section> sxns, String name, int start, int end) {
        int mid1;
        int mid2 = start + (end - start + 1) / 2; // 0 + (0 - 0 + 1) /2 = 0
        
        //note: this method is allowed to return an out of bounds index if the new element should go at the end
        
        //if our sublist is a length of 1, we return the correct index
        if(end - start + 1 == 1) {
            if(Double.parseDouble(sxns.get(mid2).getName()) < Double.parseDouble(name)) {                
                return start + 1;
            }
            return start;
        }
        
        mid1 = end - (end - start + 1) / 2;
        
        //corrects the values of mid1 or mid2 to keep them in sequential order and within the allowed range (at or between the two ends)
        if((end - start + 1) % 2 == 0 && mid1 > start) {
            mid1 = mid1 - 1;
        } else if((end - start + 1) % 2 == 1 && mid2 < end){ 
            mid2 = mid2 + 1;
        }
        
        if(Double.parseDouble(sxns.get(mid1).getName()) == Double.parseDouble(name)) {
            return mid1;
        }
        
        if(Double.parseDouble(sxns.get(mid2).getName()) == Double.parseDouble(name)) {
            return mid2;
        }
        
        if(Double.parseDouble(sxns.get(mid1).getName()) > Double.parseDouble(name)) {
            if(mid1 == start) {
                return mid1;
            }
            
            return getIndex(sxns, name, start, mid1);
        }
        
        //if s is after mid2, change the bounds
        if(Double.parseDouble(sxns.get(mid2).getName()) < Double.parseDouble(name)) {
            if(mid2 == end) {
                if(mid2 == sxns.size() - 1) {
                    return mid2 + 1;
                }
                return end;
            }
            return getIndex(sxns, name, mid2, end);
        }
        
        return mid2;
    }
    //POST: returns the index of the section with the given name or the index where a new section with such a name should be placed, return value will be between start and end + 1
    
    //starts the GUI
    private void start() {
        ClassroomHelper appl = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUIFrame frame = new GUIFrame(appl);
                StartPage star = new StartPage(appl, frame);
                star.setVisible(true);
                frame.add(star);
                frame.setContentPane(star);
            }
        });
    }
        
    public static void main(String[] args) {
        ClassroomHelper app = new ClassroomHelper();
        String basePath = "";
        if(new File("").getAbsolutePath().endsWith("src") || new File("").getAbsolutePath().endsWith("dist")) {
            basePath = "../";
        }
        System.out.println(new File(basePath).getAbsolutePath());
        app.allSxnPaths = loadSxnPaths(basePath);
        if(new File(basePath + "AllPrompts.tsv").exists() && app.allSxnPaths.size() != 0) {
            app.allPrompts = loadAllPros(basePath);
        } else {
            app.allPrompts = new ArrayList<Prompt>();
        }
        
        app.allSxnNames = getSxnNames(app.allSxnPaths); //sxn names will all be kept together as it is unecessary to separate by grade
        
        app.start();
         
    }
    
}
