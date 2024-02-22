import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(
            (a, b)->{return b-a;}
        );

        int sum=0;
        int round=0;
        for(int e:enemy){
            sum+=e;
            queue.add(e);

            if(sum>n){
                if(k==0){
                    break;
                }

                int passEnemys = queue.poll();
                sum-=passEnemys;
                k--;
            }

            round++;
        }

        return round;
    }
}
