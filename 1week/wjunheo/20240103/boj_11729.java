import java.util.Scanner;

public class Main {
    static int count = 0; // 이동 count
    static StringBuilder sb = new StringBuilder(); // 결과를 저장할 StringBuilder

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); //원판 개수
        hanoi(n, 1, 2, 3); 
        System.out.println(count); 
        System.out.println(sb); 
    }

    // 재귀 함수 이동 순서 출력
    static void hanoi(int n, int from, int via, int to) {
        if (n == 1) {
            
            sb.append(from).append(" ").append(to).append("\n");
            count++;
        } else {
            
            hanoi(n - 1, from, to, via); 
          //맨 밑 제일 큰 원판 제외 위에 원판을 via 로 이동 1이될때까지 
            sb.append(from).append(" ").append(to).append("\n"); // 가장 큰 원판을 from에서 to로 이동
            count++;
            hanoi(n - 1, via, from, to); // via로 옮겨진 원판을 다시 to로 이동
        }
    }
}
