import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine()); // 동전 가지 수

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] moneys = new int[n];
            for(int j=0; j<n; j++) {
                moneys[j] = Integer.parseInt(st.nextToken()); // 각 금액 오름차순
            }

            int totalMoney = Integer.parseInt(br.readLine()); // 만들어야 할 금액

            int[] dp = new int[totalMoney + 1];
            dp[0] = 1;

            for(int j=0; j<n; j++) {
                for(int k=moneys[j]; k<totalMoney+1; k++) {
                    dp[k] += dp[k-moneys[j]];
                }
            }

            System.out.println(dp[totalMoney]);
        }
    }
}
