import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        m = replaceString(m);
        
        int max = 0; // 재생 시간이 가장 긴
        
        for(int i=0; i<musicinfos.length; i++) {
            String[] service = musicinfos[i].split(",");
            String[] startTime = service[0].split(":");
            String[] endTime = service[1].split(":");
            int startMinutes = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            int endMinutes = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
            int minutes = endMinutes - startMinutes;
            String title = service[2];
            String content = replaceString(service[3]);
            
            int contentLength = content.length();
            content = content.repeat(minutes/contentLength) + content.substring(0, minutes % contentLength);
            if(content.contains(m)) {
                if(minutes > max) {
                    max = minutes;
                    answer = title;
                }
            }  
        }

        return answer;
    }
    
    public String replaceString(String str) {
        return str.replaceAll("A#","H")
                  .replaceAll("C#","I")
                  .replaceAll("D#","J")
                  .replaceAll("F#","K")
                  .replaceAll("G#","L");
    }
}
