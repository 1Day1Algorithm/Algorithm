import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
    
        // 무적권이 enemy의 길이보다 많으면 enemy.length 반환
        if(k >= enemy.length) return enemy.length;
        
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=0; i<enemy.length; i++) {
            // 무적권을 다 썼는데 n이 enemy[i]보다 작아서 막지 못하면 break
            if(n < enemy[i] && k == 0) break;

            // 우선순위큐에 저장
            queue.add(enemy[i]);
            // n이 enemy[i] 보다 작으면 우선순위큐에서 가장 많은 적의 수를 꺼내고 무적권 사용
            if(n < enemy[i]) { 
                n += queue.poll();
                k--;
            }
            
            n -= enemy[i];
            answer++;
        }
        
        return answer;
    }
}
