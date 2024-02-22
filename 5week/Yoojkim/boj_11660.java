import java.io.*;
import java.util.*;

class Main{
    static int N, M;
    static long[][] dp;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); M = Integer.parseInt(inputs[1]);

        dp = new long[N+1][N+1];
        map = new int[N+1][N+1];

        StringTokenizer st;
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int value = Integer.parseInt(st.nextToken());

                map[i][j] = value;
                //누적합 계산
                long prefixSum = calcPrefixSum(i, j);
                dp[i][j] = prefixSum;
            }
        }

        StringJoiner sj = new StringJoiner("\n");
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sj.add(Long.toString(calcSectionSum(x1, y1, x2, y2)));
        }

        System.out.print(sj);
    }

    private static long calcSectionSum(int x1, int y1, int x2, int y2){
        long left = 0;
        if(isPossible(x2, y1-1)){
            left = dp[x2][y1-1];
        }

        long upside = 0;
        if(isPossible(x1-1, y2)){
            upside = dp[x1-1][y2];
        }

        long duplicate =0;
        if(isPossible(x1-1, y1-1)){
            duplicate = dp[x1-1][y1-1];
        }

        return dp[x2][y2] - left - upside + duplicate;
    }


    //누적합 계산
    private static long calcPrefixSum(int i, int j){
        long left=0;
        if(isPossible(i, j-1)){
            left = dp[i][j-1];
        }

        long upside=0;
        if(isPossible(i-1, j)){
            upside = dp[i-1][j];
        }

        long duplicate=0;
        if(isPossible(i-1, j-1)){
            duplicate = dp[i-1][j-1];
        }

        return map[i][j]+left+upside-duplicate;
    }

    private static boolean isPossible(int i, int j){
        if(i<0 || i>N || j<0 || j>N){
            return false;
        }

        return true;
    }
}
