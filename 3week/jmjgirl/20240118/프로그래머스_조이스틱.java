import java.util.*;
import java.math.*;
class Solution {
    public int solution(String name) {
        int answer = 0;
        
        // ▲, ▼
        char[] alphabet = name.toCharArray();
        for(int i=0; i<alphabet.length; i++) {
            if(alphabet[i] == 'A') continue;
            int up = 'Z' - alphabet[i] + 1;
            int down = alphabet[i] - 'A';
            if(up > down) answer += down;
            else answer += up;
        }
        
        int size = alphabet.length;
        int move = size - 1;
        // ◀, ▶ 
        for(int i=0; i<alphabet.length; i++) {
            int next = i+1;
            // 연속하는 A 확인
            while(next<size && alphabet[next] == 'A') {
                next++;
            }
            // 오른쪽으로 가는경우와 오른쪽으로 갔다가 왼쪽으로 가는경우 중
            move = Math.min(move, i*2 + size - next);
            // min과 왼쪽으로 갔다가 오른쪽으로 가는 경우 중
            move = Math.min(move, (size - next)*2 + i);
        }

        return answer + move;
    }
}
