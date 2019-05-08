package Tree.AVL;

import Tree.TreeSet.FileOperation;

import java.util.ArrayList;

/**
 * 自平衡二叉搜索树
 * 优化：如果height不变时，其父节点就不需要进行平衡维护
 * @param <K>
 * @param <V>
 */
public class AVLTree<K extends Comparable<K> , V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }

    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 获得节点node的高度
     * @param node
     * @return
     */
    public int getHeight(Node node){
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 判断该树是否是一款二分搜索树
     * @return
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for (int i = 1; i < keys.size(); i++) {
            if(keys.get(i-1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    public void inOrder(Node node,ArrayList<K> keys){
        if (node == null) {
            return;
        }
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    /**
     * 判断该二分搜索树是否是一刻平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 判断以node为根节点的二分搜索树是否是平衡二叉树（递归）
     * @param node
     * @return
     */
    private boolean isBalanced(Node node){
        if (node == null) {
            return  true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 获得节点的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 向二分搜索树中添加新的元素
     * @param key
     * @param value
     */
    public void add(K key,V value){
        root = add(root,key,value);
    }

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
        //更新height
        node.height = 1+Math.max(getHeight(node.left),getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);


        //平衡维护
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){ //右旋转LL
            return rightRotate(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){ //左旋转RR
            return leftRotate(node);
        }
        //对于LR可以转换成LL，RL->RR(对下两个节点左旋或者右旋)
        //LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) { //左旋转RR
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        //向右旋转过程
        x.right = y;
        y.left = T3;
        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        // 向左旋转过程
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    //返回以node为根节点的二分搜索树，key所在的节点
    private Node getNode(Node node,K key){
        if (node == null) {
            return null;
        }
        if(key.equals(node.key)){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else {  //if(key.compareTo(node.key) > 0)
            return getNode(node.right,key);
        }
    }

    public boolean contains(K key){
        return getNode(root,key) != null;
    }

    public V get(K key){
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    public void set(K key,V newvalue){
        Node node = getNode(root,key);
        if (node == null) {
            throw new IllegalArgumentException(key + "does not exist");
        }
        node.value = newvalue;
    }

    /**
     * 递归寻找最小元素
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

//    /**
//     * 删除已node为跟的二分搜索树中的最小节点
//     * 返回删除节点后的树的跟
//     * @param node
//     * @return
//     */
//    private Node removeMin(Node node){
//        if(node.left == null){
//            Node rightNode = node.right;
//            node.right = null;
//            size--;
//            return rightNode;
//        }
//        node.left = removeMin(node.left);
//        return node;
//    }


    public V remove(K key){
        Node node = getNode(root,key);
        if (node != null) {
            root = remove(root,key);
            return node.value;
        }
        return null;
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
        Node retNode; //记录待删除的节点，为后面的平衡维护用
        if (key.compareTo(node.key)<0) {
            node.left = remove(node.left,key);
            retNode =  node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            retNode = node;
        }else{ // key==node.key
            //左子树为空
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }else
            //右子树为空
            if(node.right == null){
                Node leftNode = node.left;
                node.left=null;
                size--;
                retNode = leftNode;
            }else{
                //待删除节点的左右子树都不为空的情况
                //找到比待删除节点大的最小节点，及戴删除节点的右子树的最小节点
                //用这个节点代替要删除的节点
                //注意对size变量的维护，removeMin中-1，后面又+1
                Node successor = minimum(node.right);
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if(retNode == null){return null;}
        //更新height
        retNode.height = 1+Math.max(getHeight(retNode.left),getHeight(retNode.right));

        //计算平衡因retN
        int balanceFactor = getBalanceFactor(retNode);


        //平衡维护
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){ //右旋转LL
            return rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){ //左旋转RR
            return leftRotate(retNode);
        }
        //对于LR可以转换成LL，RL->RR(对下两个节点左旋或者右旋)
        //LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) { //左旋转RR
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("E:\\Java\\LeetCodeTrain\\src\\main\\java\\Tree\\txt\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
            System.out.println("is bst:" + map.isBST());
            System.out.println("is balanced:" + map.isBalanced());

            for (String word : words) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced()) {
                    throw new IllegalArgumentException("error");
                }
            }
        }

        System.out.println();
    }

}
