#include <map>
#include <string>

class node_t
{
private:
    std::map<char, node_t> m1;
    bool is_end;
    bool is_word_end;
public:
    node_t();
    void insert(std::string word);
    void print();
    void print(const node_t* root);
    void print_words();
    void print_words(const node_t& root, std::string str);
    bool get_strings_from_index(std::string str);
};