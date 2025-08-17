#include <cstdio>
#include "node.h"
#include <iostream>

int main(){
    node_t trie;
    trie.insert("poop");
    trie.insert("poo");
    trie.insert("pee");
    trie.insert("peepee");
    trie.print();
    std::cout<< "" << "\n";
    trie.get_strings_from_index("p");
    return 0;
}
