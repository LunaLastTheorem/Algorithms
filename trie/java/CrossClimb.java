import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class CrossClimb {

    public record Pair(String str, int val, Pair prev) {
        @Override
        public final String toString() {
            return String.format("[%s:%d]", str, val);
        }

        @Override
        public final boolean equals(Object arg0) {
            Pair p1 = (Pair) arg0;
            return this.str.equals(p1.str) && this.val == p1.val;
        }
    }

    public static void main(String[] args) {
        File file = new File("./trie/java/text.txt");
        // Scanner inputScan = new Scanner(System.in);
        // String word1 = inputScan.nextLine().trim();
        // String word2 = inputScan.nextLine().trim();
        // int n = word1.length();

        // if (word2.length() != n) {
        // System.out.println("words need to be the same length");
        // System.exit(-1);
        // }
        // Set<String> allWords = new HashSet<>();

        // try {
        // Scanner scan = new Scanner(file);
        // while (scan.hasNextLine()) {
        // String word = scan.nextLine().trim();
        // if (word.length() == n) {
        // allWords.add(word);
        // }
        // }
        // scan.close();
        // } catch (Exception e) {
        // System.err.println(e);
        // }

        // System.out.println(aStar(word1, word2, allWords));
        // System.out.println(bfs(word1, word2, allWords));

        // inputScan.close();

        Set<Set<String>> sets = new HashSet<>();
        // for (int i = 2; i < 10; i++) {
            Set<String> curr = new HashSet<>();
            try {
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine()) {
                    String word = scan.nextLine();
                    if (word.length() == 4) {
                        curr.add(word);
                    }
                }
                scan.close();
            } catch (Exception e) {
                System.err.println(e);
            }
            sets.add(curr);
        // }

        for(Set<String> set : sets){
            List<String> longest = new ArrayList<>(0);
            int longestVal = 0;
            for(String str : set){
                List<String> currentList = longestLadder(str, set);
                if(currentList.size() > longestVal){
                    System.out.println(currentList);
                    longest = currentList;
                    longestVal = currentList.size();
                }
            }
            System.out.println("longest: " + longest);
        }
        
    }

    private static List<String> longestLadder(String str, Set<String> allWords){
        Queue<Pair> q = new LinkedList<>();
        List<String> res = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        q.offer(new Pair(str, 0, null));

        Pair curr = null;
        while(!q.isEmpty()){
            curr = q.poll();

            for(String s :  getWordSet(curr.str, allWords)){
                if(seen.add(s)){
                    q.offer(new Pair(s, curr.val + 1, curr));
                }
            }
        }

        while(curr != null){
            res.add(curr.str);
            curr = curr.prev;
        }

        return res;
    }

    private static List<String> bfs(String word1, String word2, Set<String> allWords) {
        Queue<Pair> q = new LinkedList<>();
        List<String> path = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        q.offer(new Pair(word1, 0, null));
        seen.add(word1);

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            if (curr.str.equals(word2)) {
                while (curr.prev != null) {
                    path.add(curr.str);
                    curr = curr.prev;
                }
                path.add(curr.str);
                break;
            }

            Set<String> nextSet = getWordSet(curr.str, allWords);
            for (String s : nextSet) {
                if (seen.add(s)) {
                    q.offer(new Pair(s, 0, curr));
                }
            }
        }

        return path;
    }

    private static List<String> aStar(String word1, String word2, Set<String> allWords) {
        Set<String> currSet = getWordSet(word1, allWords);
        Queue<Pair> q = new PriorityQueue<>((a, b) -> a.val - b.val);
        List<String> path = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (String s : currSet) {
            q.offer(new Pair(s, wordDiff(s, word2), new Pair(word1, wordDiff(word1, word2), null)));
        }
        seen.add(word1);

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            if (seen.contains(curr.str)) {
                continue;
            }

            seen.add(curr.str);
            if (curr.val == 0) {
                while (curr.prev != null) {
                    path.add(curr.str);
                    curr = curr.prev;
                }
                path.add(curr.str);
                break;
            }

            Set<String> nextSet = getWordSet(curr.str, allWords);
            for (String s : nextSet) {
                q.offer(new Pair(s, wordDiff(s, word2), curr));
            }
        }

        return path;
    }

    private static Set<String> getWordSet(String str, Set<String> allWords) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            String reg = str.substring(0, i) + "[a-z]" + str.substring(i + 1, str.length());
            set.addAll(allWords.stream().filter(s -> s.matches(reg)).toList());
        }
        return set;
    }

    private static int wordDiff(String str1, String str2) {
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}