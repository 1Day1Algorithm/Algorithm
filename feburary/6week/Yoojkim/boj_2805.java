import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ns = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            ns[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ns);

        int left = 0; int right = ns[ns.length-1];
        int ans = 0;
        while(left<=right){
            int mid = (left+right)/2;
            long midM = 0;
            for(int i=N-1;i>=0;i--){
                if(mid>=ns[i]){
                    break;
                }

                midM+=ns[i]-mid;
            }

            if(midM == M){
                ans = mid;
                break;
            }

            if(midM > M){
                ans = mid;
                left = mid+1;
                continue;
            }

            if(midM < M){
                right = mid-1;
                continue;
            }
        }

        System.out.print(ans);
    }
}
