import java.util.*;

class Solution {
    static boolean[] isVisited;
    static boolean[] isLight;
    public int solution(int n, int[][] lighthouse) { 
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i=0; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int edge1, edge2;
        for(int i=0; i<lighthouse.length; i++) {
            edge1 = lighthouse[i][0];
            edge2 = lighthouse[i][1];
            
            graph[edge1].add(edge2);
            graph[edge2].add(edge1);
        }
        
        isVisited = new boolean[n+1];
        isLight = new boolean[n+1];
        isVisited[1] = true;
        dfs(1, graph);
        
        int answer = 0;
        for(int i=1; i<n+1; i++) {
            if(isLight[i]) answer += 1;
        }
        
        return answer;
    }
    public static void dfs(int parent, ArrayList<Integer>[] graph) {
        if(isLeafNode(parent, graph)) return;
        
        for(Integer child : graph[parent]) {
            if(!isVisited[child]) {
                isVisited[child] = true;
                dfs(child, graph);
                
                if(!isLight[child]) {
                    isLight[parent] = true;
                }
            }
        }
    }
    public static boolean isLeafNode(int parent, ArrayList<Integer>[] graph) {
        for(Integer child : graph[parent]) {
            if(!isVisited[child]) return false;
        }
        return true;
    }
}
