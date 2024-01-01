import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		final int MAX_LEN = 5001;
		
		long[][] dp = new long[MAX_LEN][MAX_LEN];
		
		dp[1][1] = 1;
		
		// r: '(' 괄호의 개수, c: 총 몇 글자인지
		for(int c=2; c<MAX_LEN; c++) {
			for(int r=1; r<MAX_LEN; r++) {
				int leftCnt = r;    // '(' 개수
				int rightCnt = c-r; // ')' 개수
				
				if(leftCnt < rightCnt) continue;
				else {
					dp[r][c] = (dp[r-1][c-1]%1000000007 + dp[r][c-1]%1000000007)%1000000007;
				}
			}
		}
		
		while(T > 0) {
			int L = Integer.parseInt(br.readLine());

			if(L%2 != 0) {
				bw.write(0 + "\n");
			} else {
				bw.write(dp[L/2][L]%1000000007 + "\n");
			}
			
			T -= 1;
		}
		
		bw.flush();
		bw.close();
	}
}
