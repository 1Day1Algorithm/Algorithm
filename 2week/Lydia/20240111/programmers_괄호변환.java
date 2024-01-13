import java.util.*;

class Solution {
    public String solution(String p) {
        return getValidString(p);
    }
    
    public String getValidString(String s) {
        if(s.equals("")) return s;
    
        String[] strList = getBalance(s);
        String u = strList[0];
        String v = strList[1];
        
        if(isValid(u)) {
            u += getValidString(v);
            return u;
        } else {
            String tempStr = "(";
            tempStr += getValidString(v);
            tempStr += ")";
            tempStr += getReverse(u);
            return tempStr;
        }
    }
    
    public String getReverse(String u) {
        String s = "";
        for(int i=1; i<u.length()-1; i++) {
            char c = u.charAt(i);
            if(c == '(') {
                s += ")";
            } else {
                s += "(";  
            }
        }
        return s;
    }
    
    public String[] getBalance(String s) {
        String[] strList = new String[2];
        int left = 0;  // '('
        int right = 0; // ')'
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                left += 1;
            } else {
                right += 1;
            }
            
            if(left == right) {
                strList[0] = s.substring(0, i+1);
                strList[1] = s.substring(i+1, s.length());
                return strList;
            }
        }
        
        strList[0] = "";
        strList[1] = s;
        return strList;
    }
    
    public boolean isValid(String u) {
        Stack<Character> s = new Stack<>();
        for(int i=0; i<u.length(); i++) {
            char c = u.charAt(i);
            
            if(c == '(') s.push('(');
            else {
                if(s.size() == 0) {
                    return false;
                }
                s.pop();
            }
        }
        return true;
    }
}
