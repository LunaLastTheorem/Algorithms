#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;
// # int[][] maze = {
// #         {0, 4, 0, 0, 0, 0, 0, 8, 0},
// #         {4, 0, 8, 0, 0, 0, 0, 11, 0},
// #         {0, 8, 0, 7, 0, 4, 0, 0, 2},
// #         {0, 0, 7, 0, 9, 14, 0, 0, 0},
// #         {0, 0, 0, 9, 0, 10, 0, 0, 0},
// #         {0, 0, 4, 14, 10, 0, 2, 0, 0},
// #         {0, 0, 0, 0, 0, 2, 0, 1, 6},
// #         {8, 11, 0, 0, 0, 0, 1, 0 ,7},
// #         {0, 0, 2, 0, 0, 0, 6, 7, 0}};

void print_vect(vector<int> v){
    std::cout<< "[";
    for(int i = 0; i < v.size(); i++){
        cout << v[i] << ",";
    }
    std::cout<< "]\n";
}

int find(int n, vector<int> &parents){
    return parents[n] == n ? n : find(parents[n], parents);
}

void union_f(int n, int m, vector<int> &parents){
    parents[find(m, parents)] = find(n, parents);
}

bool comp(vector<int> a, vector<int> b){
    return a[2] < b[2];
}

int kruskals(vector<vector<int>> maze){
    vector<int> parents = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int count = 0;
    int iter = 0;
    vector<vector<int>> verts;
    for(int i = 0; i < maze.size(); i++){
        for(int j = 0; j < maze[0].size(); j++){
            if(maze[i][j] != 0){
                verts.push_back({i, j, maze[i][j]});
            }
        }
    }

    sort(verts.begin(), verts.end(), comp);

    for(int i = 0; i < verts.size(); i++){
        int x = verts[i][0];
        int y = verts[i][1];
        int w = verts[i][2];

        if(find(x, parents) != find(y, parents)){
            printf("%d ---> %d : %d\n", x, y, w);
            union_f(x, y, parents);
            count += w;
            iter++;
            if(iter == maze.size()) break;
        }
    }
    return count;
    
}

int main(){
    vector<vector<int>> maze;
    maze.push_back({0, 4, 0, 0, 0, 0, 0, 8, 0});
    maze.push_back({4, 0, 8, 0, 0, 0, 0, 11, 0});
    maze.push_back({0, 8, 0, 7, 0, 4, 0, 0, 2});
    maze.push_back({0, 0, 7, 0, 9, 14, 0, 0, 0});
    maze.push_back({0, 0, 0, 9, 0, 10, 0, 0, 0});
    maze.push_back({0, 0, 4, 14, 10, 0, 2, 0, 0});
    maze.push_back({0, 0, 0, 0, 0, 2, 0, 1, 6});
    maze.push_back({8, 11, 0, 0, 0, 0, 1, 0 ,7});
    maze.push_back({0, 0, 2, 0, 0, 0, 6, 7, 0});


    cout << kruskals(maze) << "\n";

    return 0;
}

