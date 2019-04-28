package Map;

import Tree.TreeSet.FileOperation;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public  Node left,right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;

            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }


    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    /**
     * 向以node为根节点的二叉搜索树中插入元素（递归）
     * @param node
     * @param
     * @return 返回插入新节点后的二分搜索树的跟
     */
    private Node add(Node node,K key,V value){
        if (node == null) {
            size++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else{
            node.value = value;
        }

        return node;
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }


    /**
     * 删除已node为跟的二分搜索树中的最小节点
     * 返回删除节点后的树的跟
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 删除已node为根节点的树中值为e的节点（递归）
     * 返回删除节点后的树的根
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node,K key){
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key)<0) {
            node.left = remove(node.left,key);
            return node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            return node;
        }else{ // key==node.key
            //左子树为空
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //右子树为空
            if(node.right == null){
                Node leftNode = node.left;
                node.left=null;
                size--;
                return leftNode;
            }
            //待删除节点的左右子树都不为空的情况
            //找到比待删除节点大的最小节点，及戴删除节点的右子树的最小节点
            //用这个节点代替要删除的节点
            //注意对size变量的维护，removeMin中-1，后面又+1
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if (node != null) {
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    private Node getNode(Node node,K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else {
            return getNode(node.right,key);
        }
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root,key);
        if (node == null) {
            throw new IllegalArgumentException(key+"do not exist");
        }else{
            node.value = value;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public static void main(String[] args) {
        String filename = "E:\\Java\\LeetCodeTrain\\src\\main\\java\\Tree\\txt\\pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            System.out.println("total:" + words.size());
            BSTMap<String,Integer> map = new BSTMap<String, Integer>();
            for (String word : words) {
                if(map.contains(word)){
                    map.set(word,map.get(word)+1);
                }else{
                    map.add(word,1);
                }
            }
            System.out.println("total words:" + map.getSize());
            System.out.println("pride" + map.get("pride"));
        }else{
            System.out.println("error");
        }
    }
}
