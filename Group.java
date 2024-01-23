package classroom.helper;

import java.util.LinkedList;

/**
 *
 * @author ianmoore
 */
public class Group {
    private int number;
    private LinkedList<Student> members;
    
    public Group(int num, LinkedList<Student> mems) {
        number = num;
        members = mems;
    }
    
    public int getNum() {
        return number;
    }
    
    public void setNum(int num) {
        number = num;
    }
    
    public LinkedList<Student> getMems() {
        return members;
    }
    
    //adds the student mem to this group
    public void addMem(Student mem) {
        if(members.size() == 0) {
            members.add(0, mem);
            mem.setGru(number);
        } else {
            int index = getIndex(mem.getFuName(), 0, members.size() - 1);
            members.add(index, mem);
            mem.setGru(number);
        }
    }
    
    //returns the index where a new element (in alphabetical order) should be added or where the given string is (if this string already exists)
    private int getIndex(String s, int start, int end) {
        int mid1;
        int mid2 = start + (end - start + 1) / 2;
        
        //if our sublist is a length of 1, we return the correct index
        if(end - start + 1 == 1) {
            if(isABeforeB(members.get(mid2).getFuName(), s)) {
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

        if(members.get(mid1).getFuName().equals(s)) {
            return mid1;
        }
        
        if(members.get(mid2).getFuName().equals(s)) {
            return mid2;
        }
        
        if(isABeforeB(s, members.get(mid1).getFuName())) {
            if(mid1 == start) {
                return mid1;
            }
            return getIndex(s, start, mid1);
        }
        
        //if s is after mid2, change the bounds
        if(isABeforeB(members.get(mid2).getFuName(), s)) {
            if(mid2 == end) {
                if(mid2 == members.size() - 1) {
                    return mid2 + 1;
                }
                return end;
            }
            return getIndex(s, mid2, end);
        }
        
        return mid2;
    }
    
    //PRE: a and b should ideally be compared to other strings only containing a-z or A-Z (in corresponding positions for caps vs. lowercase)
    private boolean isABeforeB(String a, String b) { 
        for(int i = 0; i < Math.min(a.length(), b.length()); i++) {
            if(a.charAt(i) < b.charAt(i)) {
                return true;
            }
            else if(a.charAt(i) > b.charAt(i)) {
                return false;
            }

            if(a.length() > b.length() && i == b.length() - 1) {
                return false;
            }
        }
        //Note: if A is the same as B, this method will say that A can come before B, but this method shouldn't trigger in the first place if the two are equal
        return true;
    }
    //POST: returns true if string a comes before String b in alphabetical order or if they are exactly the same
    
    //removes a member from this group
    public void remMem(Student mem) {
        for(int i = 0; i < members.size(); i++) {
            if(members.get(i) == mem) {
                members.remove(i);
            }
        }
    }
    
    public boolean hasMem(Student mem) {
        for(int i = 0; i < members.size(); i++) {
            if(members.get(i) == mem) {
                return true;
            }
        }
        
        return false;
    }
    //POST: returns true if this group contains student mem, otherwise returns false
}
