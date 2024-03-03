import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		int totalDiff = 0, diff;
		for(int i=0; i<N-1; i++) {
			diff = arr.get(i+1) - arr.get(i);
			
			pq.offer(diff); // 두 수의 차이
			totalDiff += diff;
		}
		
		int len = 1;
		while(!pq.isEmpty()) {
			if(len == K) break;
			
			totalDiff -= pq.poll();
			len += 1;
		}
		
		System.out.println(totalDiff);
	}
}
