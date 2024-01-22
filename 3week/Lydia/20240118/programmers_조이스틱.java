import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
                          "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int len = name.length();     
        int nowIdx = 0;
        int move = len - 1; // A가 없다는 가정하에 끝 인덱스까지 쭉 갈 경우
        boolean check = false;
        
        for(int i=0; i<name.length(); i++) {
            char c = name.charAt(i);
            
            if(c == 'A') continue;
            check = true;
            
            if(c > 'N') {
                answer += (26-Arrays.asList(alpha).indexOf(c+""));
            } else {
                answer += Arrays.asList(alpha).indexOf(c+"");
            }
            
            nowIdx = i + 1;
            while(nowIdx < len && name.charAt(nowIdx) == 'A'){
                nowIdx++;
            }
            move = Math.min(move, i * 2 + len - nowIdx);
            move = Math.min(move, (len - nowIdx) * 2 + i);
        }
  
        return check? answer+move : 0;
    }
}
