import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        Arrays.sort(book_time, (a, b) -> {
            if(!a[0].equals(b[0])) return a[0].compareTo(b[0]);
            else return a[1].compareTo(b[1]);
        });
        
        boolean[] isVisited = new boolean[book_time.length];
        
        int count = 0;
        int idx = 0;
        while(idx < book_time.length) {
            if(isVisited[idx]) {
                idx += 1;
                continue;
            }
                
            String[] endTime = book_time[idx][1].split(":");
            int endMin = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
            
            isVisited[idx] = true;
            count += 1;
            
            for(int i=idx+1; i<book_time.length; i++) {
                String[] startTime = book_time[i][0].split(":");
                int startMin = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
                
                if(isVisited[i]) continue;
                if(endMin + 10 > startMin) { // 퇴실시간+10분(청소) 이후에 온 손님인지 체크
                    continue;
                }
                
                isVisited[i] = true;
                endTime = book_time[i][1].split(":");
                endMin = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
            }  
            
            idx += 1;
        }
            
        return count;
    }
}
