import java.util.*;

enum Type {
    A, B;
}

enum Graph{
    DOUGHNUT, ROD, EIGHT;
}

class Solution {
    final int MAX = 1000000;
    List<Integer>[] nodes;
    public int[] solution(int[][] edges) {
        nodes = new ArrayList[MAX+1];
        for(int i=1;i<=MAX;i++){
            nodes[i] = new ArrayList<>();
        }
        Type[] types = new Type[MAX+1];
        for(int[] edge:edges){
            int a = edge[0]; int b = edge[1];
            
            nodes[a].add(b);
            
            if(types[a] == null){
                types[a] = Type.A;
            }
            
            types[b] = Type.B;
        }
        
        int startNode = getFirst(types);
        
        int[] results = new int[4];
        results[0] = startNode;
        
        for(int a:nodes[startNode]){
            Graph graphType = getGraphType(a);
            
            if(graphType == Graph.DOUGHNUT){
                results[1]++;
            } else if (graphType == Graph.ROD){
                results[2]++;
            } else{
                results[3]++;
            }
        }
        
        return results;
    }
    
    private int getFirst(Type[] types){
        for(int i=1;i<=MAX;i++){
            if(types[i] == Type.A){
                return i;
            }
        }
        
        return -1; // Exception
    }
    
    private Graph getGraphType(int node){
        boolean[] visited = new boolean[MAX+1];
        
        while(!visited[node]){
            if(nodes[node].isEmpty()){
                return Graph.ROD;
            }
            
            if(nodes[node].size()==2){
                return Graph.EIGHT;
            }
            
            visited[node] = true;
            node = nodes[node].get(0);
        }
        
        return Graph.DOUGHNUT;
    }
}
