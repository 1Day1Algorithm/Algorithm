import java.util.*;

class Pos{
    int row;
    int col;

    public Pos(int row, int col){
        this.row = row;
        this.col = col;
    }
}

class Solution {
    int[][] dps = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public int[] solution(String[] maps) {
        int N = maps.length;
        int M = maps[0].length();

        char[][] map = new char[N][M];
        for(int i=0;i<N;i++){
            String row = maps[i];
            char[] rowArr = row.toCharArray();

            for(int j=0;j<M;j++){
                map[i][j] = rowArr[j];
            }
        }

        boolean[][] visited = new boolean[N][M];
        List<Integer> results = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(visited[i][j] || map[i][j] == 'X'){
                    continue;
                }
                results.add(getBfsResult(i, j, map, visited, N, M));
            }
        }

        if(results.isEmpty()){
            return new int[]{-1};
        }

        return results.stream()
                .sorted()
                .mapToInt(i->i)
                .toArray();
    }

    private int getBfsResult(int row, int col, char[][] map, boolean[][] visited, int N, int M){
        Queue<Pos> queue = new LinkedList<>();
        int sum = 0;
        queue.add(new Pos(row, col));
        visited[row][col] = true;
        while(!queue.isEmpty()){
            Pos newPos = queue.poll();
            char newValue = map[newPos.row][newPos.col];

            if(newValue == 'X'){
                continue;
            }

            sum += newValue-'0';
            for(int[] dp:dps){
                int newRow = newPos.row + dp[0];
                int newCol = newPos.col + dp[1];

                if(newRow<0 || newRow>=N || newCol<0 || newCol>=M){
                    continue;
                }

                if(map[newRow][newCol]=='X'){
                    continue;
                }

                if(visited[newRow][newCol]){
                    continue;
                }

                visited[newRow][newCol] = true;
                queue.add(new Pos(newRow, newCol));
            }
        }
        return sum;
    }
}
