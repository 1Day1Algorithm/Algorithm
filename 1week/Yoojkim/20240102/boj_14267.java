import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parents = new int[N+1];
        st = new StringTokenizer(br.readLine());
        st.nextToken();

        for(int i=2;i<=N;i++){
            parents[i] = Integer.parseInt(st.nextToken());
        }

        int[] praises = new int[N+1];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int staff = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            praises[staff] += amount; //칭찬 여러번
        }

        StringJoiner sj = new StringJoiner(" ");
        for(int i=1;i<=N;i++){
            int parentPraise = praises[parents[i]];
            praises[i] += parentPraise;
            sj.add(Long.toString(praises[i]));
        }

        System.out.print(sj);
    }
}
