        # adjList.put(0, new int[] { 1, 2 });
        # adjList.put(1, new int[] { 2, 5 });
        # adjList.put(2, new int[] { 3 });
        # adjList.put(3, new int[] {  });
        # adjList.put(4, new int[] { });
        # adjList.put(5, new int[] { 3, 4});
        # adjList.put(6, new int[] {1, 5});
# [6, 0, 1, 5, 4, 2, 3]
def helper(key, adj, visited, res):
    visited[key] = True
    for node in adj[key]:
        if visited[node]:
            continue
        helper(node, adj, visited, res)
    res.append(key)

def toposort(adj) -> list:
    visited = [False for _ in range(len(adj))]
    res = []

    for key in adj:
        if(visited[key]):
            continue
        helper(key, adj, visited, res)
    
    return res

def main():
    adj = {0:[1, 2],
           1:[2,5],
           2:[3],
           3:[],
           4:[],
           5:[3, 4],
           6:[1, 5]}
    
    res = toposort(adj)
    res.reverse()
    print(res)

main()