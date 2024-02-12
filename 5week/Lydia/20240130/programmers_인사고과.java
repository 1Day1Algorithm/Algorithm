import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int x = scores[0][0]; // 완호 근무 태도 점수
        int y = scores[0][1]; // 완호 동료 평가 점수
        
        Arrays.sort(scores, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        
        int peerReview = scores[0][1]; // 동료 평가
        for(int i=1; i<scores.length; i++) {
            if(scores[i][1] < peerReview) {
                if(x == scores[i][0] && y == scores[i][1]) return -1;
                
                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
               peerReview = scores[i][1]; 
            }
        }
        
        int sum;
        int answer = 1;
        for(int i=0; i<scores.length; i++) {
            sum = scores[i][0] + scores[i][1];
            
            if(sum > x + y) answer += 1;
        }
        
        return answer;
    }
}
