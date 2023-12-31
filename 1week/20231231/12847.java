import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] costs = new int[N + 1];

        //for sliding window
        int start = 1;

        long sum = 0;
        long MAX = 0;
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());

            //size validation
            int size = i - start + 1;
            sum += costs[i];

            if (size <= M) {
                MAX = sum;
                continue;
            }

            //max benefit (M+1)
            sum -= costs[start];
            start++;

            if(MAX<sum){
                MAX = sum;
            }
        }

        System.out.print(MAX);
    }
}
