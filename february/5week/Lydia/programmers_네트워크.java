import java.util.*;

class Solution {
    static boolean[] isVisited;
    public int solution(int n, int[][] computers) {
        isVisited = new boolean[n];
        
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i == j) continue;
                
                if(computers[i][j] == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<n; i++) {
            if(!isVisited[i]) {
                bfs(i, graph);
                answer += 1;
            }
        }
        
        return answer;
    }
    public static void bfs(int start, ArrayList<Integer>[] graph) {
        Queue<Integer> q = new LinkedList();
        q.offer(start);
        isVisited[start] = true;
        
        while(!q.isEmpty()) {
            int n = q.poll();
            
            for(Integer nextN : graph[n]) {
                if(!isVisited[nextN]) {
                    isVisited[nextN] = true;
                    q.offer(nextN);
                }
            }
        }
    }
}
