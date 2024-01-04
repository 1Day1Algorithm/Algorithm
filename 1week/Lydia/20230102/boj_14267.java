import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] bossArr = new int[n]; // 직속 상사 번호
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			bossArr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] praiseArr = new int[n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 직원번호
			int w = Integer.parseInt(st.nextToken());   // 칭찬의 수치
			praiseArr[num] += w;
		}
		
		for(int i=2; i<=n; i++) {
			praiseArr[i] += praiseArr[bossArr[i-1]];
		}
		
		for(int i=1; i<praiseArr.length; i++) {
			bw.write(praiseArr[i] + " ");
		}
		
		bw.flush();
		bw.close();
	}
}
