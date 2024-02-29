// 한 심사대에는 동시에 한 명만
// 가장 앞에 있는 사람은 비어 있는 심사대 or 더 빨리 끝나는 심사대가 있으면 기다렸다 심사
// 걸리는 시간 최소
import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        long max = (long) times[times.length-1] * n; // 최악의 시간
        long min = 1; 
        
        while(min <= max) {
            long mid = (max + min) / 2;
            
            long sum = 0; // 처리할 수 있는 사람 수 (long)
            // mid 값으로 times 배열의 시간 값을 가진 심사관들이 몇명 심사할 수 있는지
            for(int time : times) {
                sum += mid / time;
            }
            
            if(sum < n) {
                min = mid+1;
            } else {
                max = mid-1;
                answer = mid;
            }
        }
        
        return answer;
    }
}
