import java.util.*;
import java.io.*;

public class Main{
    static final int MAX = 20;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[N];
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N-1][MAX+1];
        dp[0][nums[0]] = 1;

        for(int i=1;i<N-1;i++){
            for(int n=0;n<=20;n++){
                if(dp[i-1][n]>0){
                    int plusSum = n + nums[i];

                    if(plusSum >=0 && plusSum<=20){
                        dp[i][plusSum] += dp[i-1][n];
                    }

                    int minusSum = n-nums[i];
                    if(minusSum>=0 && minusSum<=20){
                        dp[i][minusSum] += dp[i-1][n];
                    }
                }
            }
        }
        
        System.out.println(dp[N-2][nums[N-1]]);
    }
}
