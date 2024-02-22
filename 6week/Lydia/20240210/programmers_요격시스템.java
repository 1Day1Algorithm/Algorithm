import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        int answer = 0;
        int targetEnd = -1;
        for(int i=0; i<targets.length; i++) {
            if(targetEnd <= targets[i][0]) { // s와 e에서 발사하는 요격 미사일로 요격할 수 없기 때문
                targetEnd = targets[i][1];
                answer += 1;
                continue;
            }
            
            if(targetEnd > targets[i][1]) {
                targetEnd = targets[i][1];
            }
        }
        
        return answer;
    }
}
