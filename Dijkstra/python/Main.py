class Node:
    def __init__(self, name : int, dist : int, prev):
        self.name = name
        self.dist = dist
        self.prev = prev

    def __str__(self):
        return f"{self.name}:{self.dist}:{self.prev}"
        
def dijkstra(maze):
    paths = [None for _ in range(len(maze[0]))]
    distances = [float('inf') for _ in range(len(maze[0]))]
    visited = [False for _ in range(len(maze[0]))]
    q = []
    q.append(Node(0, 0, None))

    distances[0] = 0
    paths[0] = Node(0,0, None)

    while(not len(q) == 0):
        curr : Node = q.pop(0)

        if(visited[curr.name]):
            continue

        visited[curr.name] = True

        for i in range(len(maze[curr.name])):
            if(maze[curr.name][i] == 0 or maze[curr.name][i] == float('inf')):
                continue
            next : Node = Node(i, curr.dist + maze[curr.name][i], curr)
            if(distances[i] > next.dist):
                distances[i] = next.dist
                paths[i] = next
                visited[curr.name] = False
            q.append(next)
    print(distances)

    for i in range(len(paths)):
        current : Node = paths[i]
        path = []
        while not current is None:
            path.append(current.name)
            current = current.prev
        print(path)

def main():
    print("hello world!")
    infty = float('inf')
    maze = [[0, 7, 12, infty, infty, infty],
            [infty, 0, 2, 9, infty, infty],
            [infty, infty, 0, infty, 10, infty],
            [infty, infty, infty, 0, infty ,1],
            [infty, infty, infty, 4, 0, 5],
            [infty, infty, infty, infty, infty, 0]]
    dijkstra(maze)
    
main()