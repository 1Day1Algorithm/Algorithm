import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        LinkedList<Integer>[] graph = new LinkedList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new LinkedList<Integer>();
        }
        
        for(int[] values:edge){
            int node1=values[0];
            int node2=values[1];
            
            graph[node1].add(node2);
            graph[node2].add(node1);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        queue.add(1);
        visited[1] = true;
        
        List<Integer> records = new ArrayList<>();
        while(!queue.isEmpty()){
            LinkedList<Integer> tempQueue= new LinkedList<>();
            
            while(!queue.isEmpty()){
                int newNode = queue.poll();
                
                for(int nextNode: graph[newNode]){
                    if(visited[nextNode]){
                        continue;
                    }
                    
                    tempQueue.add(nextNode);
                    visited[nextNode] = true;;
                }
            }
            
            records.add(tempQueue.size());
            queue.addAll(tempQueue);
        }
        
        return records.get(records.size()-2);
    }
}
