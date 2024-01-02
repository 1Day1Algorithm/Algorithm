import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static ArrayList<Integer>[] suborList;
    static int[] praiseReceived;
    static int[] totalPraise;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numEmployees = Integer.parseInt(st.nextToken());
        int numPraises = Integer.parseInt(st.nextToken());

        praiseReceived = new int[numEmployees + 1];
        totalPraise = new int[numEmployees + 1];
        suborList = new ArrayList[numEmployees + 1];

        for (int i = 1; i <= numEmployees; i++) {
            suborList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        st.nextToken();
        //사장은 직속상사 설정 필요없음
        for (int i = 2; i <= numEmployees; i++) { 
          // 상하관계 설정
            int boss = Integer.parseInt(st.nextToken());//직속상사
            suborList[boss].add(i); // 현재직원의 직속상사 
        }

        for (int j = 0; j < numPraises; j++) {
            st = new StringTokenizer(br.readLine());
            int employee = Integer.parseInt(st.nextToken());
            int praiseValue = Integer.parseInt(st.nextToken());

            praiseReceived[employee] += praiseValue;
        }
        dfs(1, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numEmployees; i++) {
            sb.append(totalPraise[i]).append(" ");
        }
        System.out.println(sb);
    }

      //start 현재직원 parent 칭찬 next suborList[start]에서 가져온값 
    static void dfs(int start, int parent) {
        totalPraise[start] += totalPraise[parent] + praiseReceived[start];

        for (int next : suborList[start]) {
            dfs(next, start);
        }
    }
}
