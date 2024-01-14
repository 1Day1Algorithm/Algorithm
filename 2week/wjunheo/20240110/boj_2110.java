import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        int N = scanner.nextInt();
        int C = scanner.nextInt();

        int[] homePlaced = new int[N];
        for (int i = 0; i < N; i++) {
            homePlaced[i] = scanner.nextInt();
        }

      
        Arrays.sort(homePlaced);

        // 이진 탐색을 통해 최대 거리를 찾기
        int result = makeDistance(N, C, homePlaced);

        
        System.out.println(result);

        
    }

    private static int makeDistance(int N, int C, int[] homePlaced) {
        int left = 0;  // 가장 작은 거리
        int right = homePlaced[N - 1] - homePlaced[0];  // 가장 큰 거리
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 1;
            int lastPosition = homePlaced[0];

            // 두 번째 집부터의 거리확인
            for (int i = 1; i < N; i++) {
                if (homePlaced[i] - lastPosition >= mid) {
                    count++;
                    lastPosition = homePlaced[i];
                }
            }

            // 현재까지 설치된 공유기의 개수가 주어진 개수보다 크거나 같으면 결과 갱신
            if (count >= C) {
                result = mid;
                left = mid + 1;
            } else {
                // 그렇지 않으면 거리를 줄여서 다시 탐색
                right = mid - 1;
            }
        }

        return result;
    }
}
