import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] trees = new int[N];
        for(int i=0; i<N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        int left=1, right=2000000001, mid, answer = 0;
        long sum = 0;
        while(left <= right) {
            mid = (left + right) / 2;

            sum = 0;
            for(int i=0; i<N; i++) {
                if(trees[i] >= mid) {
                    sum += trees[i] - mid;
                }
            }

            if(sum >= M) i{
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
