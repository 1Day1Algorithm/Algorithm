import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 집의 개수
        int c = Integer.parseInt(st.nextToken()); // 공유기의 개수

        int[] home = new int[n];
        for(int i=0; i<n; i++) {
            home[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(home); // 오름차순 정렬

        int low = 1; // 가능한 최소 간격
        int high = home[n-1] - home[0]; // 집들의 최대 간격
        while(low <= high) {
            int mid = (low + high) / 2; // 최소 거리 설정

            // 첫번째 집은 무조건 설치한다는 가정!
            int index = 0;
            int count = 1; // 설치할 수 있는 공유기 개수
            for(int i=1; i<n; i++) {
                if(home[i] - home[index] >= mid) { // 직전에 설치했던 집과 현재의 집 사이의 거리가 최소거리 보다 크거나 같다면
                    index = i;
                    count+=1;
                }
            }

            if(count < c) { // 거리가 너무 길기때문에
                high = mid - 1;
            } else {  // 거리가 짧기 때문에
                low = mid + 1;
            }
        }

        System.out.println(low-1);
    }
}
