import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        int[][] maze = {
                { 0, 1, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 0, 1 },
                { 0, 0, 0, 0, 0, 0 },
        };
        Map<Integer, int[]> adjList = new HashMap<>();
        adjList.put(0, new int[] { 1, 2 });
        adjList.put(1, new int[] { 2, 5 });
        adjList.put(2, new int[] { 3 });
        adjList.put(3, new int[] {  });
        adjList.put(4, new int[] { });
        adjList.put(5, new int[] { 3, 4});
        adjList.put(6, new int[] {1, 5});

        List<Integer> res = toposort(adjList);

        System.out.println(res.reversed());
    }

    private static List<Integer> toposort(Map<Integer, int[]> list) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[list.size()];

        for (int key : list.keySet()) {
            if (visited[key]) {
                continue;
            } else {
                helper(key, list, visited, res);
            }
        }
        return res;
    }

    private static void helper(int key, Map<Integer, int[]> list, boolean[] visited, List<Integer> res) {
        visited[key] = true;

        for (int i : list.get(key)) {
            if (visited[i]) {
                continue;
            }
            helper(i, list, visited, res);
        }

        res.add(key);
    }

    private static List<Integer> topologicalSort(int[][] maze) {
        int n = maze.length;
        boolean[] visited = new boolean[n];
        List<Integer> res = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            s.push(i);
            while (!s.isEmpty()) {
                int curr = s.pop();
                if (visited[curr]) {
                    continue;
                }
                visited[curr] = true;
                for (int j = 0; j < n; j++) {
                    if (maze[i][j] == 1) {
                        s.push(j);
                    }
                }
                res.add(curr + 1);
            }
        }

        return res;
    }
}
