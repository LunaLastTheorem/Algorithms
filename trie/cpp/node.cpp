#include "node.h"
#include <iostream>

node_t::node_t() : is_end(false), is_word_end(false){}

void node_t::insert(std::string word) {
    node_t* curr = this;
    for(int i = 0; i < word.length(); i++){
        curr->is_end = false;
        char ch = word.at(i);
        if(curr->m1.find(ch) == curr->m1.end()){
            curr->m1.insert({ch, node_t()});
        }
        curr = &curr->m1.at(ch);
    }
    curr->is_end = true;
    curr->is_word_end = true;
}

void node_t::print(){
    print(this);
}

void node_t::print(const node_t* root) {
    if(root->is_end){
        return;
    }
    for(auto it = root->m1.begin(); it != root->m1.end(); it++){
        std::cout << it->first;
        if(!it->second.is_end){
            std::cout << "(";
            print(&it->second);
            std::cout << ")";
        }
    }
}

void node_t::print_words() {
    print_words(*this, "");
}

void node_t::print_words(const node_t& root, std::string str) {
    if(root.is_word_end){
        std::cout<< str << std::endl;
    }
    for(auto it = root.m1.begin(); it != root.m1.end(); it++){
        str.push_back(it->first);
        print_words(it->second, str);
        str.pop_back();
    }
}

bool node_t::get_strings_from_index(std::string str) {
    node_t* root = this;
    for(int i = 0; i < str.length(); i++){
        char ch = str.at(i);
        if(root->m1.find(ch) == root->m1.end()){
            return false;
        }
        root = &root->m1.at(ch);
    }
    print_words(*root, str);
    return true;
}