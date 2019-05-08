package Tree.Trie;

import Tree.TreeSet.BSTSet;
import Tree.TreeSet.FileOperation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("E:\\Java\\LeetCodeTrain\\src\\main\\java\\Tree\\txt\\pride-and-prejudice.txt",words)){
            long starttime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }

            long endtime = System.nanoTime();

            double t = (endtime-starttime) / 1000000000.0;

            System.out.println("Total:" + set.getSize());
            System.out.println("bst:" + t + "s");

            //////
            starttime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }

            endtime = System.nanoTime();

            t = (endtime-starttime) / 1000000000.0;

            System.out.println("Total:" + set.getSize());
            System.out.println("trie:" + t + "s");
        }
    }
}
