import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int booKNum = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> pnArr = new ArrayList<>(); // 양의 정수
        ArrayList<Integer> nnArr = new ArrayList<>(); // 음의 정수
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<booKNum; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num > 0) pnArr.add(num);
            else nnArr.add(num);
        }

        Collections.sort(pnArr, Collections.reverseOrder());
        Collections.sort(nnArr);

        int footPrint = 0;

        if(pnArr.isEmpty()) footPrint += -1 * nnArr.get(0);
        else if(nnArr.isEmpty()) footPrint +=  pnArr.get(0);
        else {
            footPrint += Math.max(pnArr.get(0), -1 * nnArr.get(0));
            footPrint += 2 * Math.min(pnArr.get(0), -1 * nnArr.get(0));
        }

        for(int i=m; i<pnArr.size(); i+=m) {
            footPrint += pnArr.get(i) * 2;
        }

        for(int i=m; i<nnArr.size(); i+=m) {
            footPrint += nnArr.get(i) * -2;
        }
        System.out.println(footPrint);
    }
}
