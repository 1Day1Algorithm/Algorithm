class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        for(int i=0; i<stations.length; i++) {
            if(i == 0) {
                answer += getMinStation(0, stations[i] - w, w);
            }
            
            if(i == stations.length-1) {
                answer += getMinStation(stations[i] + w, n + 1, w);
                continue;
            }
            
            answer += getMinStation(stations[i] + w, stations[i+1] - w, w);
            
        }

        return answer;
    }
    
    public static int getMinStation(int leftStation, int rightStation, int w) {
        int distance = rightStation - leftStation - 1;
        if(distance > 0) {
            int remainder = (int)(double)distance%(2 * w + 1);
            return remainder > 0 ? (int)(double)distance/(2 * w + 1) + 1 : (int)(double)distance/(2 * w + 1);
            // return (int)Math.ceil((double)distance/(2 * w + 1)); ( 시간초과 이슈 )
        }
        return 0;
    }
}
