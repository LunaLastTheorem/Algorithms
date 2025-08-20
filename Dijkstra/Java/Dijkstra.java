package Dijkstra.Java;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        int[][] maze = {
        {0, 7, 12, max, max, max},
        {max, 0, 2, 9, max, max},
        {max, max, 0, max, 10, max},
        {max, max, max, 0, max, 1},
        {max, max, max, 4, 0, 5},
        {max, max, max, max, max, 0}
        };

        // int[][] maze = {
        //         {0, 4, 8, max, max},
        //         {4, 0, max, 6, max},
        //         {8, max, 0, max, 2},
        //         {max, 6, max, 0, 10},
        //         {max, max, 2, 10, max}
        // };
        // dijkstra(0, 3, maze);
        dijkstraForAll(maze);
    }

    private static void dijkstraForAll(int[][] maze) {
        int[] distances = new int[maze.length];
        Node[] paths = new Node[maze.length];
        boolean[] visited = new boolean[maze.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Queue<Node> q = new LinkedList<>();

        distances[0] = 0;
        q.offer(new Node(0, 0, null));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (visited[curr.index]) {
                continue;
            }
            visited[curr.index] = true;

            for (int i = 0; i < maze[curr.index].length; i++) {
                if (curr.index == i || maze[curr.index][i] == Integer.MAX_VALUE) {
                    continue;
                }
                Node next = new Node(i, maze[curr.index][i] + curr.dist, curr);
                if (distances[i] > next.dist) {
                    distances[i] = next.dist;
                    visited[curr.index] = false;
                    paths[i] = next;
                }
                q.offer(next);
            }
        }
        System.out.println(Arrays.toString(distances));
        for(Node path : paths){
            List<Node> pathList = new ArrayList<>();
            while(path != null){
                pathList.add(path);
                path = path.prev;
            }
            System.out.println(pathList);
        }
    }

    private static void dijkstra(int i, int j, int[][] maze) {
        Node start = new Node(i, 0, null);
        char end = (char) (j + 'a');
        List<Node> path = new ArrayList<>();
        Queue<Node> q = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        Set<Character> visited = new HashSet<>();
        q.offer(start);

        while (!q.isEmpty()) {
            System.out.println(q);
            Node curr = q.poll();

            if (!visited.add(curr.name)) {
                continue;
            }

            if (curr.name == end) {
                while (curr != null) {
                    path.add(curr);
                    curr = curr.prev;
                }
                break;
            }

            int index = curr.name - 'a';
            for (int k = 0; k < maze[index].length; k++) {
                if (index == k || maze[index][k] == Integer.MAX_VALUE) {
                    continue;
                }

                Node next = new Node(k, maze[index][k] + curr.dist, curr);
                if (q.contains(next)) {
                    List<Node> temp = new ArrayList<>();
                    Node nodeFromOldHeap = null;
                    while (!q.isEmpty()) {
                        nodeFromOldHeap = q.poll();
                        if (nodeFromOldHeap.equals(next)) {
                            break;
                        }
                        temp.add(nodeFromOldHeap);
                    }
                    q.addAll(temp);
                    if (nodeFromOldHeap.dist > next.dist) {
                        q.offer(next);
                    } else {
                        continue;
                    }
                }
                q.offer(next);
            }
        }
        System.out.println("path: " + path);
    }
}
