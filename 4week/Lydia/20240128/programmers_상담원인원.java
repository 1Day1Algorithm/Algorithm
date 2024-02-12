import java.util.*;

class Solution {
    public static ArrayList<ReqType>[] reqArr;
    public static int answer = Integer.MAX_VALUE;
    public static class ReqType {
        int start;
        int end;
        public ReqType(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int solution(int k, int n, int[][] reqs) {
        
        reqArr = new ArrayList[k];
        for(int i=0; i<k; i++) {
            reqArr[i] = new ArrayList<>();
        }
        
        for(int i=0; i<reqs.length; i++) {
            int a = reqs[i][0];
            int b = reqs[i][1];
            int c = reqs[i][2];
            reqArr[c-1].add(new ReqType(a, b));
        }
        
        boolean[] isVisited = new boolean[n-k+k-1];
        combination(isVisited, 0, k-1);
        return answer;
    }
    public void combination(boolean[] isVisited, int start, int target) {
        if(target == 0) {
            getMentoCnt(isVisited);
            return;
        }
        
        for(int i=start; i<isVisited.length; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                combination(isVisited, i+1, target-1);
                isVisited[i] = false;
            }    
        }
    }
    public void getMentoCnt(boolean[] isVisited) {
        int before = -1;
        ArrayList<Integer> cntArr = new ArrayList<>();
        for(int i=0; i<isVisited.length; i++) {
            if(isVisited[i]) {
                cntArr.add(i-before-1);
                before = i;
            }
        }
        cntArr.add(isVisited.length-1-before);
        getWaitTime(cntArr);
    }
    
    public void getWaitTime(ArrayList<Integer> cntArr) {
        PriorityQueue<Integer> pq;
        int result = 0;
        for(int i=0; i<cntArr.size(); i++) {
            int mentoCnt = cntArr.get(i) + 1;
            pq = new PriorityQueue<>((a, b) -> a - b);

            for(ReqType r : reqArr[i]) {
                if(pq.size() < mentoCnt) {
                    pq.offer(r.start+r.end);
                    continue;
                }

                int end = pq.poll();
                pq.offer(Math.max(end, r.start) + r.end); // 예외처리 주의

                int waitTime = end - r.start;
                if(waitTime > 0) result += waitTime; 
            }
        }
        answer = Math.min(result, answer); 
    }
}
