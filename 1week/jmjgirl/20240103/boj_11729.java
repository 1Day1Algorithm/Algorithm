import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        sb.append((int) Math.pow(2,N)-1).append('\n');
        hanoi(N, 1, 2, 3);

        System.out.println(sb);
    }

    public static void hanoi(int n, int first, int second, int third) {
        if(n == 1) {
            sb.append(first + " " + third + '\n');
            return;
        }

        // 1. n-1개의 원판을 기둥 1에서 2로 옮기기
        hanoi(n-1, first, third, second);

        // 2. n번 원판만 기둥 1에서 3으로 옮기기
        sb.append(first + " " + third + '\n');

        // 3. n-1개의 원판을 기둥 2에서 3으로 옮기기
        hanoi(n-1, second, first, third);
    }
}
