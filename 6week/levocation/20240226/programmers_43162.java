import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        Queue<Integer> q = new LinkedList<>();
        int cur;
        
        int len = computers.length;
        
        for (int i = 0; i < len; i++) {
            if (computers[i][i] == 1) { // computers[i][i]는 초기에 항상 1로 시작하지만, 이미 탐색한 경우에는 -1이다.
                q.add(i);

                while (!q.isEmpty()) {
                    cur = q.poll();
                    if (computers[cur][cur] == -1) continue; // 이미 탐색한 컴퓨터는 두 번 탐색할 필요 없음
                    for (int nxt = 0; nxt < len; nxt++) {
                        if (computers[cur][nxt] == 1) {
                            computers[cur][nxt] = -1;
                            computers[nxt][cur] = -1;
                            q.add(nxt);
                        }
                    }
                }
                answer++;
            }
        }
        
        return answer;
    }
}
