import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int i=0; i<enemy.length; i++) {
            n -= enemy[i];
            pq.offer(enemy[i]);
            
            if(n < 0) {
                if(k > 0) {
                    k -= 1;
                    n += pq.poll();    
                } else {
                    break;
                }
            }
            
            answer += 1;
        }
        return answer;
    }
}
