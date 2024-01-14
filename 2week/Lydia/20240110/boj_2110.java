import java.io.*;
import java.util.*;

public class Main {
	static StringJoiner sj;
	static int sum;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] homeArr = new int[N];
		for(int i=0; i<N; i++) {
			homeArr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(homeArr);
		
		int left = 0; 
		int right = homeArr[N-1]-homeArr[0]; 
		int mid=0;
		int maxAns = 0;
		while(left <= right) {
			mid = (left + right) / 2;
			
			int distance = homeArr[0] + mid;
			int count = 1; // 공유기 개수
			for(int i=0; i<N; i++) {
				if(homeArr[i] >= distance) {
					count += 1;
					distance = homeArr[i] + mid;
				}
			}
			
			if(count >= C) {
				left = mid+1;
				maxAns = Math.max(mid, maxAns); // 공유기 개수가 조건에 만족할 경우에만 정답 갱신
			} else {
				right = mid-1;
			}
		}
		
		System.out.println(maxAns);
	}
}
