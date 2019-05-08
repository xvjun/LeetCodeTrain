package Tree.EBTree;

import Tree.TreeSet.FileOperation;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private boolean isRED(Node node){
        if(node == null)
            return BLACK;
        return node.color;
    }

    /**
     * 左旋转
     *        node                     x
     *       /   \     左旋转         /  \
     *      T1   x   --------->   node   T3
     *          / \              /   \
     *         T2 T3            T1   T2
     * @param node
     * @return
     */
    private Node leftRotate(Node node){
        Node y = node.right;
        node.right = y.left;
        y.left = node;
        y.color = BLACK;
        node.color = RED;
        return y;
    }

    /**
     * 颜色反转
     * 当一个黑节点的left是红的，同时来了一个节点且需要加到right上，要讲下面的红色节点编程黑色，
     * 同时上面的黑色的节点要向上融合，所以需要变从红色，总体看起来就是所有的节点都颜色反转
     *     黑                 红
     *    /  \     反转——>   /  \
     *   红  红             黑   黑
     */
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 右旋转,实际操作时，需要对右旋转后的树进行反转。因为右旋转后树结构变成上面黑，下面两个是红色的。
     * @param node
     * @return
     */
    private Node rightRotate(Node node){
        Node y = node.left;
        node.left = y.right;
        y.right = node;

        y.color = node.color;
        node.color = RED;
        return y;
    }

    // 向红黑树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;  //为什么需要在每次添加节点时，都将根节点设置为黑色，一次不行吗？
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        //最坏的情况需要左旋转 ->右旋转 ->反转，其他情况为该情况的子情况
        if(isRED(node.right) && !isRED(node.left)){
            node = leftRotate(node);
        }
        if(isRED(node.left) && isRED(node.left.left)){
            node = rightRotate(node);
        }
        if(isRED(node.left) && isRED(node.right)){
            flipColors(node);
        }
        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;

        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            return node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            return node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args){
        String filename = "E:\\Java\\LeetCodeTrain\\src\\main\\java\\Tree\\txt\\pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            System.out.println("total:" + words.size());
            RBTree<String,Integer> map = new RBTree<>();
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

