import java.util.*;

class Solution {
    private int count;
    private boolean[] visited;
    private ArrayList<Integer>[] graph;

    public int solution(int n, int[][] lighthouse) {
        initialize(n, lighthouse);
        dfs(1);
        return count;
    }

    private void initialize(int n, int[][] lighthouse){
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<Integer>();
        }

        visited = new boolean[n+1];
        count=0;

        for(int[] edge:lighthouse){
            int node1 = edge[0];
            int node2 = edge[1];

            graph[node1].add(node2);
            graph[node2].add(node1);
        }
    }

    private boolean dfs(int node){
        visited[node] = true;

        if(graph[node].size()==1 && visited[graph[node].get(0)]){
            return false; //leaf: off
        }

        //root
        boolean dfsResult = true;
        for(int child:graph[node]){
            if(visited[child]){
                continue;
            }

            if(!dfs(child)){
                dfsResult = false;
            }
        }

        if(!dfsResult){
            //child: off 
            //node: on

            count++;
            return true;
        }

        return false;
    }
}
