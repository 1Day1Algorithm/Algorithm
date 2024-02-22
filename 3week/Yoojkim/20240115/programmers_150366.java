import java.util.*;

class Pos{
    int row;
    int col;

    public Pos(int row, int col){
        this.row=row;
        this.col=col;
    }
}

class Cell{
    Queue<Pos> queue;
    String value;

    public Cell(){
        queue = new LinkedList<Pos>();
        value = null;
    }

    public void changeValue(String value){
        this.value=value;
    }

    public boolean isValueEqual(String value){
        if(this.value == null){
            if(value == null){
                return true;
            }

            return false;
        }

        return this.value.equals(value);
    }

    public String getValue(){
        if(value == null){
            return "EMPTY";
        }

        return value;
    }

    public boolean isValueEmpty(){
        return value == null;
    }

    public void clearQueue(){
        this.queue.clear();
    }

    public void setValueNull(){
        this.value=null;
    }

    public void setValue(String value){
        this.value=value;
    }

    public void merge(Pos pos){
        //중복검사 x

        queue.add(pos);
    }
}

class Solution {
    final int MAX = 50;
    Cell[][] cells= new Cell[MAX+1][MAX+1];
    List<String> results = new ArrayList<>();

    public String[] solution(String[] commands) {
        initializeCells();

        for(String command: commands){
            String typeStr = command.split(" ")[0];

            if(typeStr.equals("UPDATE")){
                update(command);
                continue;
            }

            if(typeStr.equals("MERGE")){
                merge(command);
                continue;
            }

            if(typeStr.equals("UNMERGE")){
                unmerge(command);
                continue;
            }

            if(typeStr.equals("PRINT")){
                print(command);
                continue;
            }
        }

        String[] resultArr = new String[results.size()];
        resultArr = (String[]) results.toArray(resultArr);

        return resultArr;
    }

    private void initializeCells(){
        for(int i=1;i<=MAX;i++){
            for(int j=1;j<=MAX;j++){
                cells[i][j] = new Cell();
            }
        }
    }

    private void update(String command){
        String[] commands = command.split(" ");

        if(commands.length==4){
            int r = Integer.parseInt(commands[1]);
            int c = Integer.parseInt(commands[2]);
            String value = commands[3];

            Pos pos = new Pos(r, c);
            changeRelatedCells(pos, value);
        } else {
            String oldValue = commands[1];
            String newValue = commands[2];

            changeCellsByValue(oldValue, newValue);
        }
    }

    private void merge(String command){
        String[] commands = command.split(" ");

        int r1 = Integer.parseInt(commands[1]);
        int c1 = Integer.parseInt(commands[2]);

        int r2 = Integer.parseInt(commands[3]);
        int c2 = Integer.parseInt(commands[4]);

        boolean isEmpty1 = cells[r1][c1].isValueEmpty();
        boolean isEmpty2 = cells[r2][c2].isValueEmpty();

        if(!isEmpty1 && !isEmpty2){
            mergeWithValue(new Pos(r1, c1), new Pos(r2, c2), cells[r1][c1].getValue());
            return;
        }

        if(!isEmpty1){
            mergeWithValue(new Pos(r1, c1), new Pos(r2, c2), cells[r1][c1].getValue());
            return;
        }

        if(!isEmpty2){
            mergeWithValue(new Pos(r1, c1), new Pos(r2, c2), cells[r2][c2].getValue());
            return;
        }

        if(isEmpty1 && isEmpty2){
            mergeWithValue(new Pos(r1, c1), new Pos(r2, c2), null);
        }
    }

    private void mergeWithValue(Pos pos1, Pos pos2, String value){
        Cell cell1 = cells[pos1.row][pos1.col];
        Cell cell2 = cells[pos2.row][pos2.col];

        cell1.merge(pos2);
        cell2.merge(pos1);

        changeRelatedCells(pos1, value);
        changeRelatedCells(pos2, value);
    }

    private void unmerge(String command){
        String[] commands = command.split(" ");
        int r = Integer.parseInt(commands[1]);
        int c = Integer.parseInt(commands[2]);

        Queue<Pos> queue = new LinkedList<>();
        boolean[][]visited = new boolean[MAX+1][MAX+1];
        visited[r][c] = true;

        queue.addAll(cells[r][c].queue);
        cells[r][c].clearQueue();

        while(!queue.isEmpty()){
            Pos relatedPos = queue.poll();

            if(visited[relatedPos.row][relatedPos.col]){
                continue;
            }

            queue.addAll(cells[relatedPos.row][relatedPos.col].queue);
            cells[relatedPos.row][relatedPos.col].clearQueue();

            visited[relatedPos.row][relatedPos.col]=true;
            cells[relatedPos.row][relatedPos.col].setValueNull();
        }
    }

    private void print(String command){
        String[] commands = command.split(" ");

        int r = Integer.parseInt(commands[1]);
        int c = Integer.parseInt(commands[2]);

        results.add(cells[r][c].getValue());
    }

    private void changeCellsByValue(String oldValue, String newValue){
        for(int r=1;r<=MAX;r++){
            for(int c=1;c<=MAX;c++){
                Cell cell = cells[r][c];
                if(!cell.isValueEqual(oldValue)){
                    continue;
                }

                cell.changeValue(newValue);
            }
        }
    }

    private void changeRelatedCells(Pos pos, String newValue){
        Cell cell = cells[pos.row][pos.col];

        boolean[][] visited = new boolean[MAX+1][MAX+1];

        cell.changeValue(newValue);
        visited[pos.row][pos.col] = true;

        Queue<Pos> queue = new LinkedList<>();
        queue.addAll(cell.queue);
        while(!queue.isEmpty()){
            Pos relatedPos = queue.poll();
            if(visited[relatedPos.row][relatedPos.col]){
                continue;
            }

            Cell relatedCell = cells[relatedPos.row][relatedPos.col];

            relatedCell.changeValue(newValue);

            queue.addAll(relatedCell.queue);
            visited[relatedPos.row][relatedPos.col] = true;
        }
    }
}
