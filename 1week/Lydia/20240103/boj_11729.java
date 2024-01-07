import java.io.*;
import java.util.*;

public class Main {
	static StringJoiner sj;
	static int sum;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		sj = new StringJoiner("\n");
		sum = 0;
		
		dfsHanoi(1, 3, K);
		
		System.out.println(sum + "\n" + sj);
	}
	
	public static void dfsHanoi(int start, int end, int count) throws IOException {
		if(count == 1) {
			sj.add(start+" "+end);
			sum += 1;
			return;
		}
		
		// 원판 3개를 예시로 작성하면,
		int mid = getMid(start, end);
		dfsHanoi(start, mid, count-1); // 먼저, 3을 제외한 (1,2)의 원판을 1->2 장대로 옮기고
		dfsHanoi(start, end, 1);       // 다음으로 3 원판을 1->3 장대로 옮김
		dfsHanoi(mid, end, count-1);   // 마지막으로 (1,2)의 원판을 3원판이로 옮겨야 하므로 2->3 장대로 옮김
	}
	
	public static int getMid(int start, int end) {
		int mid = 6 - (start + end);
		return mid;
	}
}
