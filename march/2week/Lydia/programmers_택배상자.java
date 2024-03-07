import java.util.*;

class Solution {
    public int solution(int[] order) {
        int len = order.length;
        
        Queue<Integer> container = new LinkedList<>();
        Stack<Integer> subContainer = new Stack<>();
        
        for(int i=1; i<len+1; i++) {
            container.offer(i);
        }
        
        boolean[] isVisited = new boolean[len+1];
        int num, q, s;
        int answer = 0;
        for(int i=0; i<len; i++) {
            num = order[i];
            
            if(!isVisited[num]) {
                while(!container.isEmpty()) {
                    q = container.poll();
                    
                    if(num != q) {
                        isVisited[q] = true;
                        subContainer.push(q);
                    } else {
                        answer += 1;
                        break;
                    }
                }
            } else {
                s = subContainer.pop();
                
                if(num != s) {
                    break;
                } else {
                    answer += 1;
                }
            }
        }
        
        
        return answer;
    }
}
