import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        if(k>= enemy.length){
            return enemy.length;
        }
        
        int rounds =0;
        int soldiers=n;
        PriorityQueue<Integer> enemyQueue = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<enemy.length; i++){
            int currentEnemy= enemy[i];
            enemyQueue.add(currentEnemy);
            soldiers -=currentEnemy;
            
            if(soldiers<0){
                if(k>0){
                    soldiers += enemyQueue.poll();
                    k--;
                }else{
                    break;
                }
            }
            rounds++;
        }
        answer= rounds;
        return answer;
    }
}
