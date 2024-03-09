import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String dna = br.readLine();
		
		int[] alphaCnt = new int[4]; // 부분 문자열에 포함되어야할 최소 개수 배열
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			alphaCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Character> q = new LinkedList<>();
		Map<Character, Integer> cntMap = new HashMap<>(); // 매번 cnt를 갱신해줄 Map
		
		char c;
		int answer = 0;
		for(int i=0; i<dna.length(); i++) {
			c = dna.charAt(i);
			
			q.offer(c);
			cntMap.put(c, cntMap.getOrDefault(c, 0) + 1);
			
			if(q.size() > P) {
				c = q.poll();
				cntMap.put(c, cntMap.get(c) - 1);
			}
			
			if(q.size() == P) {
				if(alphaCnt[0] <= cntMap.getOrDefault('A', 0) && alphaCnt[1] <= cntMap.getOrDefault('C', 0)
					&& alphaCnt[2] <= cntMap.getOrDefault('G', 0) && alphaCnt[3] <= cntMap.getOrDefault('T', 0)) answer += 1;
			}
		}
		
		System.out.println(answer);
	}
}
