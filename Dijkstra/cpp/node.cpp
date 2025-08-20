#include "node.h"

node_t::node_t(int index, int dist, node_t* prev): index(index), dist(dist), prev(prev) {};

node_t::node_t(){};

int node_t::get_index(){
    return index;
}

int node_t::get_dist(){
    return dist;
}

node_t* node_t::get_prev(){
    return prev;
}