class Solution {
    public long solution(int n, int[] times) {
        long MAX_VALUE = (long)1000000000 * n;
        long answer = MAX_VALUE;
        
        long left = 1, right = MAX_VALUE, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            
            long total = 0;
            for(int i=0; i<times.length; i++) {
                total += mid/times[i];
            }
            
            if(total >= n) {
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
