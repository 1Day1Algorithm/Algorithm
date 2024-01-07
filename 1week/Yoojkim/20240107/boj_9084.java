import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringJoiner sj = new StringJoiner("\n");
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());

            int[] coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++){
                coins[j] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M+1];
            dp[0] = 1;
            
            for(int coin:coins){
                for(int m = coin; m<=M; m++){
                    dp[m] += dp[m-coin];
                }
            }
            
            sj.add(Integer.toString(dp[M]));
        }
        
        System.out.print(sj);
    }
}
