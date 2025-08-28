def find(n, parents):
    if parents[n] == n:
        return n
    return find(parents[n], parents)

def union(n, m, parents):
    parents[find(m, parents)] = find(n, parents)
# int[][] maze = {
#         {0, 4, 0, 0, 0, 0, 0, 8, 0},
#         {4, 0, 8, 0, 0, 0, 0, 11, 0},
#         {0, 8, 0, 7, 0, 4, 0, 0, 2},
#         {0, 0, 7, 0, 9, 14, 0, 0, 0},
#         {0, 0, 0, 9, 0, 10, 0, 0, 0},
#         {0, 0, 4, 14, 10, 0, 2, 0, 0},
#         {0, 0, 0, 0, 0, 2, 0, 1, 6},
#         {8, 11, 0, 0, 0, 0, 1, 0 ,7},
#         {0, 0, 2, 0, 0, 0, 6, 7, 0}};
def kruskals(maze):
    verts = []
    parents = [i for i in range(10)]
    for i in range(len(maze)):
        for j in range(len(maze[0])):
            if(maze[i][j] != 0):
                verts.append([i, j, maze[i][j]])
    
    verts.sort(key=lambda x: x[2])
    count = 0
    iteration = 0

    for vert in verts:
        x = vert[0]
        y = vert[1]
        w = vert[2]

        if(find(x, parents) != find(y, parents)):
            print(f"{x} ----> {y} : {w}")
            union(x, y, parents)
            count += w
            iteration = iteration + 1
            if iteration == len(maze):
                break

    print(count)

def main():
    maze = [[0, 4, 0, 0, 0, 0, 0, 8, 0],
            [4, 0, 8, 0, 0, 0, 0, 11, 0],
            [0, 8, 0, 7, 0, 4, 0, 0, 2],
            [0, 0, 7, 0, 9, 14, 0, 0, 0],
            [0, 0, 0, 9, 0, 10, 0, 0, 0],
            [0, 0, 4, 14, 10, 0, 2, 0, 0],
            [0, 0, 0, 0, 0, 2, 0, 1, 6],
            [8, 11, 0, 0, 0, 0, 1, 0 ,7],
            [0, 0, 2, 0, 0, 0, 6, 7, 0]]
    kruskals(maze)

main()