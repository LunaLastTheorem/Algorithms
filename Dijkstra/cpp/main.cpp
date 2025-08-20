#include "node.h"
#include <climits>
#include <queue>
#include <vector>
#include <iostream>

void dijkstra(int **maze, int n)
{
    int distances[n];
    bool visited[n];
    node_t *paths[n];

    for (int i = 0; i < n; i++)
    {
        visited[i] = false;
        distances[i] = INT_MAX;
    }

    distances[0] = 0;
    
    std::queue<node_t*> q;
    q.push(new node_t(0, 0, nullptr));
    paths[0] = q.front();

    while (!q.empty())
    {
        node_t* curr = q.front();
        q.pop();

        if (visited[curr->get_index()])
        {
            continue;
        }
        visited[curr->get_index()] = true;

        for (int i = 0; i < n; i++)
        {
            if (i == curr->get_index() || maze[curr->get_index()][i] == INT_MAX)
            {
                continue;
            }

            node_t* next = new node_t(i, curr->get_dist() + maze[curr->get_index()][i], curr);
            if (next->get_dist() < distances[i])
            {
                distances[i] = next->get_dist();
                visited[curr->get_index()] = false;
                paths[i] = next;
            }
            q.push(next);
        }
    }

    std::cout << "finals" << "\n";
    for (int i = 0; i < n; i++)
    {
        std::cout << distances[i] << " ";
    }
    std::cout<< "" << "\n";
    for(int i = 0; i < n; i++){
        std::cout<< "[";
        node_t* ptr = paths[i];
        while(ptr != nullptr){
            std::cout<< ptr->get_index() << " ";
            ptr = ptr->get_prev();
        }
        std::cout<< "]" << "\n";
    }
}

int main()
{
    int max = INT_MAX;
    int *maze[6] = {
        (int[]){0, 7, 12, max, max, max},
        (int[]){max, 0, 2, 9, max, max},
        (int[]){max, max, 0, max, 10, max},
        (int[]){max, max, max, 0, max, 1},
        (int[]){max, max, max, 4, 0, 5},
        (int[]){max, max, max, max, max, 0}
    };

    dijkstra(maze, 6);

    return 0;
}