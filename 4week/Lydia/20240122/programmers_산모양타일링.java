class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][4];
        
        // dp 초기화
        for(int i=0; i<4; i++) {
            if(tops[0] == 0 && i == 3) continue; // top이 없을 경우만 삼각형이 위아래 붙은 경우 제외
            dp[0][i] = 1;
        }
        
        for(int i=1; i<n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3]) % 10007;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3]) % 10007;
            dp[i][2] = (dp[i-1][0] + dp[i-1][2] + dp[i-1][3]) % 10007;
            dp[i][3] = tops[i] == 1 ? (dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3]) % 10007 : 0;
        }
        
        int answer = 0;
        for(int i=0; i<4; i++) {
            answer += dp[n-1][i] % 10007;
        }
        
        return answer % 10007;
    }
}
