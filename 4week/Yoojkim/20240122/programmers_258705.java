class Solution {
    public int solution(int n, int[] tops) {
        int section = 2*n+1;
        long[][] dp = new long[section+1][2]; //[i][j] - i번째 구간, j:0 삼각형 j:1 마름모
        
        dp[1][0] = 1;
        dp[1][1] = 0;
        
        for(int i=2;i<=section;i++){
            dp[i][0] = (dp[i-1][0]+dp[i-1][1])%10007;
            dp[i][1] = dp[i-1][0];
            
            if(i%2==0 && tops[i/2-1]==1){
                dp[i][1] += dp[i][0];
            }
            
            
        }
        
        long sum = dp[section][0] + dp[section][1];
        return (int)sum%10007;
    }
}
