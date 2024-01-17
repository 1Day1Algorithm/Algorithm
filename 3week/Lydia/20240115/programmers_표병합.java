import java.util.*;

class Solution {
    static ValueType[][] graph;
    public class Type {
        int r;
        int c;
        public Type(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public class ValueType {
        String val;
        ArrayList<Type> arr;
        public ValueType(String val, ArrayList<Type> arr) {
            this.val = val;
            this.arr = arr;
        }
    }
    public String[] solution(String[] commands) {
        ArrayList<String> answer = new ArrayList<>();
        graph = new ValueType[51][51];
        
        ArrayList<Type> tempArr;
        for(int i=0; i<51; i++) {
            for(int j=0; j<51; j++) {
                tempArr = new ArrayList<Type>();
                graph[i][j] = new ValueType(null, tempArr);
            }
        }
        
        boolean[][] isVisited;
        for(int i=0; i<commands.length; i++) {
            String[] command = commands[i].split(" ");
            
            switch(command[0]) {
                case "UPDATE":
                    if(command.length == 4) {
                        isVisited = new boolean[51][51];
                        changeValue(isVisited, Integer.parseInt(command[1]), Integer.parseInt(command[2]), command[3]);
                    } else {
                        changeAllValue(command[1], command[2]);
                    }
                    break;
                case "MERGE":
                    mergeCell(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
                    break;
                case "UNMERGE":
                    isVisited = new boolean[51][51];
                    unMergeCell(isVisited, Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                    break;
                case "PRINT":
                    int r = Integer.parseInt(command[1]);
                    int c = Integer.parseInt(command[2]);
                    String val = !isStringEmpty(graph[r][c].val) ? graph[r][c].val : "EMPTY";
                    answer.add(val);
                    break;
                default:
                    break;
            }
        }
        
        return answer.toArray(new String[answer.size()]);
    }
    
    public void changeValue(boolean[][] isVisited, int r, int c, String value) {
        ArrayList<Type> arr = graph[r][c].arr;
        isVisited[r][c] = true;
        
        for(Type t : arr) {
            if(!isVisited[t.r][t.c]) {
                graph[t.r][t.c].val = value;
                isVisited[t.r][t.c] = true;
                changeValue(isVisited, t.r, t.c, value); // 값을 변경할 경우 병합된 셀들의 값도 모두 변경해줘야함 (1)
            }
        }
        graph[r][c].val = value;
    }
    
    public void changeAllValue(String value1, String value2) {
        String val;
        for(int i=0; i<51; i++) {
            for(int j=0; j<51; j++) {
                val = graph[i][j].val;
                if(isStringEmpty(val)) continue;
                if(val.equals(value1)) graph[i][j].val = value2;
            }
        }
    }
    
    public void mergeCell(int r1, int c1, int r2, int c2) {
        if(r1 == r2 && c1 == c2) return;
        
        String val1 = graph[r1][c1].val;
        String val2 = graph[r2][c2].val;
        boolean[][] isVisited;
        
        if(!isStringEmpty(val1)) {
            graph[r2][c2].val = val1;
            
            isVisited = new boolean[51][51];
            changeValue(isVisited, r2, c2, val1); // 새로운 병합을 할 경우 이전 병합의 값들도 모두 갱신해 줘야함 (2)
            
            graph[r1][c1].arr.add(new Type(r2, c2));
            graph[r2][c2].arr.add(new Type(r1, c1));
        } else{
            graph[r1][c1].val = val2;
            
            isVisited = new boolean[51][51];
            changeValue(isVisited, r1, c1, val2); // 새로운 병합을 할 경우 이전 병합의 값들도 모두 갱신해 줘야함 (2)
            
            graph[r1][c1].arr.add(new Type(r2, c2));
            graph[r2][c2].arr.add(new Type(r1, c1));
        }
    }
    
    public void unMergeCell(boolean[][] isVisited, int r, int c) {
        ArrayList<Type> arr = graph[r][c].arr;
        ArrayList<Type> tempArr;
        isVisited[r][c] = true;
        
        for(Type t : arr) {
            if(!isVisited[t.r][t.c]) {
                tempArr = new ArrayList<>();
                isVisited[t.r][t.c] = true;
                unMergeCell(isVisited, t.r, t.c); // 병합을 해제할 경우에는 연결된 병합을 모두 해제해줘야함 (3)
                graph[t.r][t.c] = new ValueType(null, tempArr);
            }
        }
        
        String val = graph[r][c].val;
        tempArr = new ArrayList<>();
        graph[r][c] = new ValueType(val, tempArr);
    }
    
    public boolean isStringEmpty(String s) {
        return s == null || s.isEmpty();
    }
}   
