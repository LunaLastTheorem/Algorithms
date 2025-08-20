class node_t{
    private:
        int index;
        int dist;
        node_t* prev;
    public:
        node_t(int index, int dist, node_t * prev);
        node_t();
        int get_index();
        int get_dist();
        node_t* get_prev();
};