import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static PriorityQueue<Integer> plus, minus;
    static int nowPos=0;
    static int sum=0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        plus = new PriorityQueue<>();
        minus = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        int minusMax = 0; int plusMax = 0;
        for (int i = 0; i < N; i++) {
            int pos = Integer.parseInt(st.nextToken());

            if(pos<0){
                if(minusMax<pos*-1){
                    minusMax = pos*-1;
                }
                
                minus.add(pos*-1);
                continue;
            }
            
            if(plusMax < pos){
                plusMax = pos;
            }

            plus.add(pos);
        }

        if(plusMax > minusMax){
            organizeBooks(false);
            setZero();
            organizeBooks(true);
        }else{
            organizeBooks(true);
            setZero();
            organizeBooks(false);
        }

        System.out.print(sum);
    }

    private static void organizeBooks(boolean isPlus){
        PriorityQueue<Integer> queue;
        if(isPlus){
            queue = plus;
        } else {
            queue = minus;
        }

        while(!queue.isEmpty()){
            int minPos = queue.peek();

            int allDist;
            if(queue.size()<=M){
                allDist = minPos - nowPos;
            } else {
                int pairs = (int)Math.ceil((double)queue.size()/M);
                int dist = minPos - nowPos;
                allDist = 2*dist*(pairs-1)+dist;
            }

            sum+=allDist;
            nowPos = minPos;
            queue.poll();
        }
    }

    private static void setZero(){
        sum += nowPos;
        nowPos=0;
    }
}
