import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Node{
    HashMap<Character, Node> map;
    boolean isEnd;
    boolean wordEnd;

    Node(){
        isEnd = false;
        wordEnd = false;
        map = new HashMap<>();
    }

    private void insert(String word){
        Node curr = this;
        for(int i = 0; i < word.length(); i++){
            curr.isEnd = false;
            char ch = word.charAt(i);
            if(!curr.map.containsKey(ch)){
                curr.put(ch);
            }
            curr = curr.get(ch);
        }
        curr.isEnd = true;
        curr.wordEnd = true;
    }

    private Node get(char ch){
        return this.map.get(ch);
    }

    private Node put(char ch){
        Node node = new Node();
        this.map.put(ch, node);
        return node;
    }

    private static void print(Node root){
        if(root.isEnd){
            return;
        }
        for(char ch : root.map.keySet()){
            System.out.print(ch);
            if(!root.get(ch).isEnd){
                System.out.print("(");
                print(root.get(ch));
                System.out.print(")");
            }
        }
    }

    private static void printWords(Node root, StringBuilder str){
        if(root.wordEnd){
            System.out.println(str.toString());
        }
        for(char ch : root.map.keySet()){
            str.append(ch);
            printWords(root.get(ch), str);
            str.deleteCharAt(str.length() - 1);
        }
    }

    private static void printWords(Node root){
        printWords(root, new StringBuilder());
    }

    private static boolean getStringsStartingWith(String str, Node root){
        for(char ch : str.toCharArray()){
            if(!root.map.containsKey(ch)){
                return false;
            }
            root = root.get(ch);
        }
        printWords(root, new StringBuilder(str));
        return true;
    }

    public static void main(String[] args) {
        Node trie = new Node();
        // try {
        //     // Scanner scan = new Scanner(new File("./text.txt"));
        //     // while(scan.hasNext()){
        //     //     trie.insert(scan.next().toLowerCase().trim());
        //     // }
        //     // scan.close();
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // }
        trie.insert("cat");
        trie.insert("cast");
        print(trie);
        while(true){
            Scanner scan = new Scanner(System.in);
            getStringsStartingWith(scan.nextLine(), trie);
        }

    }
}