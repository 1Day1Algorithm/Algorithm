import java.io.*;
import java.util.*;

public class Main {
	static long answer;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		while(T > 0) {
			int L = Integer.parseInt(br.readLine());
			
			answer = 0;
			getStringByDFS("", 0, L, 0, 0);
			bw.write(answer%1000000007+"\n");
			
			T -= 1;
		}
		
		bw.flush();
		bw.close();
	}
	public static void getStringByDFS(String str, int cnt, int maxLen, int leftCnt, int rightCnt) {
		if(leftCnt < rightCnt) return;
		if(cnt == maxLen) {
			if(isCorrectStr(str)) {
				answer += 1;
			}
			return;
		}
		
		String newStr = str + "(";
		getStringByDFS(newStr, cnt+1, maxLen, leftCnt+1, rightCnt);
		
		newStr = str + ")";
		getStringByDFS(newStr, cnt+1, maxLen, leftCnt, rightCnt+1);
	}
	public static boolean isCorrectStr(String str) {
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			
			if(c == '(') {
				stack.push('(');
			} else {
				if(stack.size() > 0) {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		
		if(stack.size() > 0) return false;
		
		return true;
		
	}
}
