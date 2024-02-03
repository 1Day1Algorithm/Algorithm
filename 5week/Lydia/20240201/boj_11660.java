import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringJoiner sj = new StringJoiner("\n");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		int num;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			num = 0;
			for(int j=0; j<N; j++) {
				num += Integer.parseInt(st.nextToken());
				arr[i][j] = num;
			}
		}
		
		int startR, startC, endR, endC;
		int sum;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			startR = Integer.parseInt(st.nextToken()) - 1;
			startC = Integer.parseInt(st.nextToken()) - 1;
			endR = Integer.parseInt(st.nextToken()) - 1;
			endC = Integer.parseInt(st.nextToken()) - 1;
			
			sum = 0;
			for(int r=startR; r<=endR; r++) {
				if(startC > 0) {
					sum += arr[r][endC] - arr[r][startC-1];
					continue;
				}
				
				sum += arr[r][endC];
			}
			
			sj.add(sum+"");
		}
		
		System.out.println(sj);
	}
}
