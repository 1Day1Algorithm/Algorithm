import java.util.*;

class Node implements Comparable<Node>{
    int dist;
    int weight;
    
    public Node(int dist, int weight){
        this.dist = dist;
        this.weight = weight;
    }
    
    public int compareTo(Node n){
        return n.dist - this.dist;
    }
}

class Solution {
    PriorityQueue<Node> deliver = new PriorityQueue<>();
    PriorityQueue<Node> retrieve = new PriorityQueue<>();
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        for(int i=0;i<n;i++){
            
            if(deliveries[i] != 0){
                deliver.add(new Node(i+1, deliveries[i]));
            }
            
            if(pickups[i] != 0){
                retrieve.add(new Node(i+1, pickups[i]));
            }
        }
        
        long allDist = 0;
        while(!deliver.isEmpty() || !retrieve.isEmpty()){
            allDist += getMaxDist() * 2;
                
            calDist(cap, deliver);
            calDist(cap, retrieve);
        }
        
        return allDist;
    }
    
    private void calDist(int cap, PriorityQueue<Node> queue){
        int nowCap=0;
        
        while(!queue.isEmpty()){
            Node newNode = queue.poll();
            
            if(newNode.weight+nowCap <= cap){
                nowCap += newNode.weight;
                
                continue;
            } 
            
            int possibleCap = cap - nowCap;
            nowCap += possibleCap;
            
            queue.add(new Node(newNode.dist, newNode.weight - possibleCap));
            break;
        }
    }
    
    private int getMaxDist(){
        int deliverMax = 0; int retrieveMax = 0;
        
        if(!deliver.isEmpty()){
            deliverMax = deliver.peek().dist;
        }
        
        if(!retrieve.isEmpty()){
            retrieveMax = retrieve.peek().dist;
        }
        
        return deliverMax>retrieveMax?deliverMax:retrieveMax;
    }
}
