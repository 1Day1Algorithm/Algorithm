import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<tangerine.length; i++) {
            int key = tangerine[i];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        for(Integer key : map.keySet()) {
            pq.offer(map.get(key));
        }
        
        int sum = 0;
        while(!pq.isEmpty()) {
            sum += pq.poll();
            answer += 1;
            if(sum >= k) break;
        }
        
        return answer;
    }
}
