#include <vector>
#include <bits/stdc++.h>
// adjList.put(0, new int[] { 1, 2 });
// adjList.put(1, new int[] { 2, 5 });
// adjList.put(2, new int[] { 3 });
// adjList.put(3, new int[] {  });
// adjList.put(4, new int[] { });
// adjList.put(5, new int[] { 3, 4});
// adjList.put(6, new int[] {1, 5});

void helper(int key, std::map<int, std::vector<int>> m1, std::vector<bool>& visited, std::vector<int>& res){
    visited[key] = true;
    for(int i = 0; i < m1[key].size(); i++){
        if(visited[m1[key][i]]){
            continue;
        }
        helper(m1[key][i], m1, visited, res);
    }

    res.push_back(key);
}

std::vector<int> toposort(std::map<int, std::vector<int>> list){
    std::vector<bool> visited;
    for(int i = 0; i < list.size(); i++){
        visited.push_back(false);
    }

    std::vector<int> res;
    for(auto j = list.begin(); j != list.end(); j++){
        if(visited[j->first]){
            continue;
        }
        helper(j->first, list, visited, res);
    }
    return res;
}

int main(){
    std::map<int, std::vector<int>> maze;
    maze[0] = {1, 2};
    maze[1] = {2, 5};
    maze[2] = {3};
    maze[3] = {};
    maze[4] = {};
    maze[5] = {3, 4};
    maze[6] = {1, 5};
    std::vector<int> res = toposort(maze);
    for(int i = res.size() - 1; i >= 0; i--){
        std::cout<< res[i] << " ";
    }
    return 0;
}