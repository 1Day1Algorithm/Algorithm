import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        Map<Integer, Integer> edgeMap = new HashMap<>();
        for(int i=0; i<edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            
            edgeMap.put(start, edgeMap.getOrDefault(start, 0) + 1);
            edgeMap.put(end, edgeMap.getOrDefault(end, 0) - 1);
        }
        
        int[][] edgeArr = new int[edgeMap.size()+1][2]; // [0] : 들어오는 화살표, [1] : 나가는 화살표
        for(int i=0; i<edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            
            // start -> end
            edgeArr[start][1] += 1;
            edgeArr[end][0] += 1;
        }
        
        for(int i=1; i<edgeMap.size()+1; i++) {
            if(edgeArr[i][1] >= 2 && edgeArr[i][0] == 0) { // 생성한 정점
                answer[0] = i;
            } else if(edgeArr[i][1] == 0 && edgeArr[i][0] >= 1) { // 막대 모양 그래프
                answer[2] += 1;
            } else if(edgeArr[i][1] == 2 && edgeArr[i][0] >= 2) { // 8자 모양 그래프
                answer[3] += 1;
            }
        }
        
        // 생성한 정점을 기준으로 생성된 그래프 개수에서 (막대 모양 그래프 개수 + 8자 모양 그래프 개수) 를 빼면 도넛 모양 그래프 개수
        answer[1] = edgeArr[answer[0]][1] - (answer[2] + answer[3]);
        
        return answer;
    }
}
