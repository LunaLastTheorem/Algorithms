package UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[][] maze = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0 ,7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        int c = mst(maze);
        System.out.println(c);
    }

    private static int mst(int[][] maze) {
        int v = maze.length;
        int[] parents = new int[v];
        for(int i = 0; i < v; i++){
            parents[i] = i;
        }

        List<int[]> edges = new ArrayList<>();

        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] != 0){
                    edges.add(new int[]{i, j, maze[i][j]});
                }
            }
        }

        edges = edges.stream().sorted((a, b) -> a[2] - b[2]).toList();

        int cost = 0;
        int count = 0;    
        
        for(int[] e : edges){
            int x = e[0], y = e[1], z = e[2];

            if(find(x, parents) != find(y, parents)){
                System.out.printf("%d ---> %d : %d\n", x, y, z);
                union(x, y, parents);
                cost += z;
                count++;
                if(count == v - 1) break;
            }
        }

        return cost;
    }

    private static void union(int a, int b, int[] parents) {
        parents[find(b, parents)] = find(a, parents);
    }

    private static int find(int n, int[] parents) {
        if (parents[n] == n) {
            return n;
        }
        return find(parents[n], parents);
    }
}
