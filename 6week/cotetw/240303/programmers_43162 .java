import java.util.HashSet;

public class Solution {
    static int[] parent;
    private static int solution(int n, int[][] computers) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) for (int j = 0; j < i; j++) {
            if(computers[i][j] == 0) continue;
            unionMerge(i, j);
        }
        for (int i = 0; i < n; i++) parent[i] = find(i);
        HashSet set = new HashSet();
        for (int i : parent) set.add(i);

        return set.size();
    }

    private static void unionMerge(int a, int b) {
        a = find(a);
        b = find(b);

        if(a<b) parent[b] = a;
        else    parent[a] = b;
    }
    private static int find(int x) {
        if(x == parent[x])
            return x;
        else
            return parent[x] = find(parent[x]);
    }
}
