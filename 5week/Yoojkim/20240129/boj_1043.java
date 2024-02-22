import java.util.*;
import java.io.*;

public class Main{
    static boolean[][] related;
    static boolean[] truths;
    static String[] partyInfo;
    static int N, M;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); M = Integer.parseInt(inputs[1]);
        related = new boolean[N+1][N+1];
        truths = new boolean[N+1];
        partyInfo = new String[M];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int truthSize = Integer.parseInt(st.nextToken());
        for(int i=0;i<truthSize;i++){
            queue.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0;i<M;i++){
            partyInfo[i] = br.readLine();
            String[] party = partyInfo[i].split(" ");

            int size = Integer.parseInt(party[0]);
            if(size == 1){
                continue;
            }

            for(int j=0;j<size-1;j++){
                int x = Integer.parseInt(party[1+j]);
                int y = Integer.parseInt(party[1+j+1]);
                related[x][y] = true;
                related[y][x] = true;
            }
        }

        while(!queue.isEmpty()){
            int truthPerson = queue.poll();

            truths[truthPerson] = true;
            for(int i=1;i<=N;i++){
                if(!related[truthPerson][i] || truths[i]){
                    continue;
                }

                queue.add(i);
                truths[i] = true;
            }
        }

        int partyCount = 0;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(partyInfo[i]);

            int size = Integer.parseInt(st.nextToken());
            boolean possibleLie = true;
            for(int j=0;j<size;j++){
                int partyPerson = Integer.parseInt(st.nextToken());
                if(truths[partyPerson]){
                    possibleLie = false;
                    break;
                }
            }

            if(possibleLie){
                partyCount++;
            }
        }

        System.out.println(partyCount);
    }
}
