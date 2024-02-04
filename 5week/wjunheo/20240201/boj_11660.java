import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 표 숫자 받는 배열하나 
        int[][] table = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] sectorSum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sectorSum[i][j] = sectorSum[i - 1][j] + sectorSum[i][j - 1] - sectorSum[i - 1][j - 1] + table[i][j];
            }
        }
      //구간 합 M개만큼
        for (int k = 0; k < M; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = getSum(sectorSum, x1, x2, y1, y2);
            System.out.println(result);
        }
    }

    private static int getSum(int[][] sectorSum, int x1, int x2, int y1, int y2) {
        return sectorSum[x2][y2] - sectorSum[x2][y1 - 1] - sectorSum[x1 - 1][y2] + sectorSum[x1 - 1][y1 - 1];
    }
}
