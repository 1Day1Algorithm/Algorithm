import java.util.*;
class Solution {
    public int n = 0;
    public String[][] map;
    public int[][] visited;
    
    public String[] solution(String[] commands) {
        map = new String[51][51];
        visited = new int[51][51];
        ArrayList<String> answerList = new ArrayList<String>();
        
        for (int i = 0; i < 51; i++) {
            Arrays.fill(map[i], "");
        }
        
        for(String command : commands) {
            String[] commandArr = command.split(" ");
            if(commandArr[0].equals("UPDATE")) update(commandArr);
            else if(commandArr[0].equals("MERGE")) merge(commandArr);
            else if(commandArr[0].equals("UNMERGE")) unmerge(commandArr);
            else {
                int r = Integer.parseInt(commandArr[1]);
                int c = Integer.parseInt(commandArr[2]);
                if(!map[r][c].isEmpty()) {
                    answerList.add(map[r][c]);
                } else {
                    answerList.add("EMPTY");
                } 
            }
        }
        
        String[] answer = new String[answerList.size()];
        int index = 0;
        for(String al : answerList) {
            answer[index++] = al;
        }
        return answer;
    }
    
    public void update(String[] commandArr) {
        if(commandArr.length == 4) {
            int r = Integer.parseInt(commandArr[1]);
            int c = Integer.parseInt(commandArr[2]);
            String value = commandArr[3];
            
            map[r][c] = value;
            
            if(visited[r][c] > 0) {
                int num = visited[r][c];
                isUpdateMerge(value,num); 
            }
        } else {
            String value1 = commandArr[1];
            String value2 = commandArr[2];
            for(int i=1; i<=50; i++) {
                for(int j=1; j<=50; j++) {
                    if(map[i][j].equals(value1)) {
                        map[i][j] = value2;
                    }
                }
            }
        }
    }
    
    public void isUpdateMerge(String value, int num) {
        for(int i=1; i<=50; i++) {
            for(int j=1; j<=50; j++) {
                if(visited[i][j] == num) 
                    map[i][j] = value;
            }
        }
    }
    
    public void merge(String[] commandArr) {
        int r1 = Integer.parseInt(commandArr[1]);
        int c1 = Integer.parseInt(commandArr[2]);
        int r2 = Integer.parseInt(commandArr[3]);
        int c2 = Integer.parseInt(commandArr[4]);

        // 병합될 값 구하기
        String change = map[r1][c1].length()==0 ? map[r2][c2] : map[r1][c1];
        
        // 둘다 머지되어 있지 않을 경우
        if(visited[r1][c1] == 0 && visited[r2][c2] == 0) {
            n++;
            visited[r1][c1] = n;
            visited[r2][c2] = n;
        }
        
        // 왼쪽만 머지 되어 있을 경우
        else if(visited[r1][c1] != 0 && visited[r2][c2] == 0) {
            visited[r2][c2] = visited[r1][c1];
        }
        
        // 오른쪽만 머지 되어 있을 경우
        else if(visited[r2][c2] != 0 && visited[r1][c1] == 0) {
            visited[r1][c1] = visited[r2][c2];
        }

        // 두쪽다 머지 되어 있을 경우
        else if(visited[r1][c1] != 0 && visited[r2][c2] != 0) {
            int tempVal = visited[r2][c2]; // - 추가
            for(int i=1; i<51; i++) {
                for(int j=1; j<51; j++) {
                    if(visited[i][j] == tempVal) {
                        visited[i][j] = visited[r1][c1];
                    }
                }
            }
        }  
        
        for(int i=1; i<=50; i++) {
            for(int j=1; j<=50; j++) {
                if (visited[i][j] == visited[r1][c1]) {
                    map[i][j] = change;
                }
            }
        }
    }    
    
    public void unmerge(String[] commandArr) {
        int r = Integer.parseInt(commandArr[1]);
        int c = Integer.parseInt(commandArr[2]);
        String temp = map[r][c];
        
        int val = visited[r][c];
        
        // 이부분이 문제였다...! 추가해주니까 성공
        if (val == 0)
            return;
        
        for(int i=1; i<=50; i++) {
            for(int j=1; j<=50; j++) {
                if(visited[i][j] == val) {
                    if(i==r && j==c) continue;
                    visited[i][j] = 0;
                    map[i][j] = "";
                    
                }
            }
        }
    } 
}
