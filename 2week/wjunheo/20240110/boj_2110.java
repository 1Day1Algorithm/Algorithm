import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 집의 좌표를 담을 배열
        int[] house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        
        Arrays.sort(house);

        
        int left = 1;
        int right = house[N - 1] - house[0];
        int result = 0;

        while (left <= right) {
            
            int mid = (left + right) / 2;

            // 첫 번째 공유기 설치
            int installed = 1;
            // 이전에 설치한 집의 인덱스
            int prevIndex = 0;

            // 중간 거리를 기준으로 공유기 설치
            for (int i = 1; i < N; i++) {
                if (house[i] - house[prevIndex] >= mid) {
                    installed++;
                    prevIndex = i;
                }
            }

            // 설치한 공유기의 개수가크거나 같으면 더 큰 거리를 시도
            if (installed >= C) {
                result = mid;
                left = mid + 1;
            } else {
                // 설치한 공유기의 개수가 작으면 더 작은 거리를 시도
                right = mid - 1;
            }
        }
        System.out.println(result);
    }
}
