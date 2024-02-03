import java.util.*;
import java.io.*;

public class Main {
    static boolean[] isTrue;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int trueN = Integer.parseInt(st.nextToken());
        ArrayList<Integer> trueArr = new ArrayList<>();
        for(int i=0; i<trueN; i++) {
            trueArr.add(Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] partyLeader = new int[M];
        ArrayList<Integer> tempArr;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int totalPeople = Integer.parseInt(st.nextToken());

            tempArr = new ArrayList<>();
            while(totalPeople-- > 0) {
                int people = Integer.parseInt(st.nextToken());
                partyLeader[i] = people;
                tempArr.add(people);
            }

            for(Integer a : tempArr) {
                for (Integer b : tempArr) {
                    if (a == b) continue;
                    graph[a].add(b);
                    graph[b].add(a);
                }
            }
        }

        isTrue = new boolean[N+1];
        for(Integer t : trueArr) {
            isTrue[t] = true;
            dfs(t, graph);
        }

        int answer = 0;
        for(int i=0; i<M; i++) {
            int people = partyLeader[i];
            if(!isTrue[people]) {
                answer +=1;
            }
        }

        System.out.println(answer);
    }
    public static void dfs(int memberA, ArrayList<Integer>[] graph) {
        for(Integer memberB : graph[memberA]) {
            if(!isTrue[memberB]) {
                isTrue[memberB] = true;
                dfs(memberB, graph);
            }
        }
    }
}
