package Dijkstra.Java;

public class Node {
    int dist;
    int index;
    char name;
    Node prev;

    Node(int name, int dist, Node prev){
        this.name = (char)(name + 'a');
        this.index = name;
        this.dist = dist;
        this.prev = prev;
    }

    @Override
    public boolean equals(Object obj) {
        Node o = (Node) obj;
        return o.name == this.name;
    }

    @Override
    public String toString() {
        if(this.prev == null){
            return name + ":" + dist + ":null"; 
        }
        return name + ":" + dist + ":" + prev.name; 
    }
}
