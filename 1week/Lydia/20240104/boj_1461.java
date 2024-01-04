import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> positiveNumArr = new ArrayList<>();
		ArrayList<Integer> negativeNumArr = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(num >= 0) {
				positiveNumArr.add(num);
			} else {
				negativeNumArr.add(num);
			}
		}
		
		Collections.sort(positiveNumArr, (a,b) -> b-a);
		Collections.sort(negativeNumArr, (a,b) -> a-b);
		
		int count = 0; // 걸음 수
		for(int i=0; i<positiveNumArr.size();) {
			count += getNumOfSteps(positiveNumArr.get(i));
			i += M;
		}
		
		for(int i=0; i<negativeNumArr.size();) {
			count += getNumOfSteps(negativeNumArr.get(i));
			i += M;
		}
		
		// 책을 모두 제자리에 놔둔 후에는 다시 0으로 돌아올 필요가 없으므로 최대 걸음수가 제일 큰걸 한번 빼줌
		int maxCount = 0;
		if(positiveNumArr.size() <= 0) {
			maxCount = Math.abs(negativeNumArr.get(0));
		} else if(negativeNumArr.size() <= 0) {
			maxCount = positiveNumArr.get(0);
		} else {
			maxCount = Math.max(positiveNumArr.get(0), Math.abs(negativeNumArr.get(0)));
		}
		
		System.out.print(count - maxCount);

		
		bw.flush();
		bw.close();
	}
	
	public static int getNumOfSteps(int num) {
		if(num < 0) return num * (-1) * 2;
		return num * 2;
	}
}
