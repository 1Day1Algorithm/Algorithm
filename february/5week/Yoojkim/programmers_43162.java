import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        LinkedList<Integer>[] edges = new LinkedList[n];
        for(int i=0;i<n;i++){
            edges[i] = new LinkedList<>();
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(computers[i][j] == 0){
                    continue;
                }

                edges[i].add(j);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int count=0;

        for(int i=0; i<n;i++){
            if(visited[i]){
                continue;
            }

            count++;
            //BFS 수행
            visited[i] = true;
            queue.addAll(edges[i]);
            while(!queue.isEmpty()){
                int near = queue.poll();
                if(visited[near]){
                    continue;
                }

                visited[near] = true;
                for(int node:edges[near]){
                    if(visited[node]){
                        continue;
                    }

                    queue.add(node);
                }
            }
        }

        return count;
    }
}
