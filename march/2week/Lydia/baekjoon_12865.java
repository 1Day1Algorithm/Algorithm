import java.io.*;
import java.util.*;

public class Main {
	public static class ProductType {
		int w;
		int v;
		public ProductType(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<ProductType> arr= new ArrayList<>();
		int w, v;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			arr.add(new ProductType(w, v));
		}
		
		int[][] dp = new int[N+1][K+1];
		for(int i=0; i<K+1; i++) {
			for(int j=1; j<N+1; j++) {
				w = arr.get(j-1).w;
				v = arr.get(j-1).v;
				
				dp[j][i] = dp[j-1][i];
				if(i >= w) {
					dp[j][i] = Math.max(dp[j][i], dp[j-1][i-w]+v); // 현재 item의 value와 과거 item의 value 의 합과 비교 후 갱신
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
