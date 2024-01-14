import java.util.*;

class Solution {
    static Map<Integer, Integer> aMap;
    static Map<Integer, Integer> bMap;
    static ArrayList<Type> aWinList;
    public class Type {
        int win_A; // A가 승리한 확률
        boolean[] isVisited;
        public Type(int win_A, boolean[] isVisited) {
            this.win_A = win_A;
            this.isVisited = isVisited;
        }
    }
    public int[] solution(int[][] dice) {
        boolean[] isVisited = new boolean[dice.length];
        aWinList = new ArrayList<>();
        
        combination(dice, isVisited, 0, dice.length/2);
        
        Collections.sort(aWinList, (a, b) -> b.win_A - a.win_A);
        
        int[] answer = new int[dice.length/2];
        boolean[] aMaxWinList = aWinList.get(0).isVisited;
        int idx = 0;
        for(int i=0; i<aMaxWinList.length; i++) {
            if(aMaxWinList[i]) {
                answer[idx] = i+1;
                idx += 1;
            }
        }
        
        return answer;
    }
    public void combination(int[][] dice, boolean[] isVisited, int idx, int target) {
        if(target == 0) {
            aMap = new HashMap<>();
            bMap = new HashMap<>();
            
            calculate_A(dice, isVisited, 0, isVisited.length/2, 0); // A의 경우의수
            calculate_B(dice, isVisited, 0, isVisited.length/2, 0); // B의 경우의수
            calculate_AB(isVisited); // A와 B의 경우의수를 가지고 A가 승리할 확률을 저장
            
            return;
        }
        
        for(int i=idx; i<isVisited.length; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                combination(dice, isVisited, i+1, target-1);
                isVisited[i] = false; 
            }
        }
    }
    public void calculate_A(int[][] dice, boolean[] isVisited, int idx, int target, int sum) {
        if(target == 0) {
            aMap.put(sum, aMap.getOrDefault(sum, 0) + 1);
            return;
        }        
        
        if(isVisited[idx]) {
            for(int i=0; i<6; i++) {
                calculate_A(dice, isVisited, idx+1, target-1, sum+dice[idx][i]);
            }
        } else {
            calculate_A(dice, isVisited, idx+1, target, sum);
        }
    }
    public void calculate_B(int[][] dice, boolean[] isVisited, int idx, int target, int sum) {
        if(target == 0) {
            bMap.put(sum, bMap.getOrDefault(sum, 0) + 1);
            return;
        }        
        
        if(!isVisited[idx]) {
            for(int i=0; i<6; i++) {
                calculate_B(dice, isVisited, idx+1, target-1, sum+dice[idx][i]);
            }
        } else {
            calculate_B(dice, isVisited, idx+1, target, sum);
        }
    }
    public void calculate_AB(boolean[] isVisited) {
        int A, B;
        int win_A = 0;
        for(Integer aKey: aMap.keySet()) {
            for(Integer bKey: bMap.keySet()) {
                A = aKey;
                B = bKey;
                
                if(A > B) win_A += aMap.get(aKey) * bMap.get(bKey); // a가 뽑힌 경우(승리) * b가 뽑힌 경우(패배)
            }
        }
        
        aWinList.add(new Type(win_A, copyArray(isVisited)));
    }
    public boolean[] copyArray(boolean[] isVisited) {
        boolean[] tempArr = new boolean[isVisited.length];
        for(int i=0; i<isVisited.length; i++) {
            tempArr[i] = isVisited[i];
        }
        return tempArr;
    }
}
