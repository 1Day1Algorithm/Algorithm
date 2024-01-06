import java.util.*;
import java.io.*;

class Main {
    static StringJoiner sj = new StringJoiner("\n");
    static int sum = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        hanoi(1, 2, 3, N);
        System.out.println(sum);
        System.out.println(sj);
    }

    private static void hanoi(int start, int mid, int end, int section) {
        //종료조건
        if (section == 1) {
            sj.add(start + " " + end);
            sum++;

            return;
        }

        hanoi(start, end, mid, section - 1);

        sj.add(start + " " + end);
        sum++;

        hanoi(mid, start, end, section - 1);
    }
}
