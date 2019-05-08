package Leetcode.tree;

import java.util.TreeMap;

public class Map_sum_Pairs_677 {

    private class Node{
        public int value;
        public TreeMap<Character,Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }
        public Node(){
            this(0);
        }
    }

    private Node root;


    public Map_sum_Pairs_677() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c= word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }
    //node节点下的所有节点的val的和
    private int sum(Node node){
        if(node.next.size() == 0){
            return node.value;
        }
        int rs = node.value;
        for (Character c : node.next.keySet()) {
            rs += sum(node.next.get(c));
        }
        return rs;

    }

}
