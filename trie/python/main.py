class Node:
    def __init__(self):
        self.my_dict = {}
        self.is_end = False
        self.is_word_end = False
    
    def insert(self, word):
        curr = self
        for i in range(len(word)):
            ch = word[i]
            curr.is_end = False
            if not ch in curr.my_dict:
                curr.my_dict[ch] = Node()
            curr = curr.my_dict[ch]
        curr.is_end = True
        curr.is_word_end = True
    
    def print_tree(self):
        self.print(self)
    
    def print_tree(self, root):
        if root.is_end:
            return
        for ch in root.my_dict.keys():
            print(ch, end="")
            if(not root.my_dict[ch].is_end):
                print("(", end="")
                self.print_tree(root.my_dict[ch])
                print(")", end="")

    def get_words_from_index(self, str):
        curr = self
        for i in range(len(str)):
            ch = str[i]
            if not ch in curr.my_dict:
                return False
            curr = curr.my_dict[ch]
        self.print_words(curr, str)
        return True

    def print_words(self, root, curr_str : str):
        if root.is_word_end:
            print(curr_str)

        for ch in root.my_dict.keys():
            curr_str += ch
            self.print_words(root.my_dict[ch], curr_str)
            curr_str = curr_str[:-1]
                

def main():
    trie = Node()
    trie.insert("cat")
    trie.insert("cast")
    trie.insert("poop")
    trie.insert("poopoo")
    trie.insert("peepoo")
    trie.get_words_from_index("po")
    
main()