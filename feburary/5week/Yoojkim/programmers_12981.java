import java.util.*;

class Solution {
    int turn = 0;
    char lastChar = '0';
    int[] result = {0,0};
    Set<String> set = new HashSet<>();
    
    public int[] solution(int n, String[] words) {
        for(String word:words){
            if(turn==0){
                nextProcess(word);
                
                continue;
            }
            
            if(lastChar != word.charAt(0) || set.contains(word)){
                result = new int[]{turn%n+1, turn/n+1};
                break;
            }
            
            nextProcess(word);
        }

        return result;
    }
    
    private void nextProcess(String word){
        turn++;
        lastChar = word.charAt(word.length()-1);
        set.add(word);
    }
}
