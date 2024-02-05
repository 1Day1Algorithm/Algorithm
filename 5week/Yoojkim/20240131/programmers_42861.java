import java.util.*;

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int cost;
    
    public Edge(int start, int end, int cost){
        this.start = start;
        this.end = end;
        this.cost= cost;
    }
    
    public int compareTo(Edge e){
        return Integer.compare(this.cost, e.cost);
    }
}

class Solution {
    public int solution(int n, int[][] costs) {
        LinkedList<Edge>[] islands = new LinkedList[n];
        for(int i=0;i<n;i++){
            islands[i] = new LinkedList<>();
        }
        
        for(int[] cost:costs){
            int start = cost[0]; int end = cost[1]; int value = cost[2];
            
            islands[start].add(new Edge(start, end, value));
            islands[end].add(new Edge(end, start, value));
        }
        
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[n];
        int count=0;
        
        int startIsland = 0;
        visited[startIsland] = true;
        queue.addAll(islands[startIsland]);
        while(!queue.isEmpty()){
            Edge newEdge = queue.poll();
            
            if(visited[newEdge.end]){
                continue;
            }
            
            visited[newEdge.end]=true;
            count += newEdge.cost;
            queue.addAll(islands[newEdge.end]);
        }
        
        return count;
    }
}
