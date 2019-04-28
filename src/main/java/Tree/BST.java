package Tree;

import java.util.*;

public class BST<E extends Comparable<E>>{

    private class Node{
        public E e;
        public  Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }
    /**
     * 向二叉搜索树中添加一个元素
     * @param e
     */
    public void add(E e){
        root = add(root,e);
    }

    /**
     * 向以node为根节点的二叉搜索树中插入元素（递归）
     * @param node
     * @param e
     * @return 返回插入新节点后的二分搜索树的跟
     */
    private Node add(Node node,E e){
        if (node == null) {
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }

        return node;
    }

    /**
     * 查询二分搜索树中是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        return contains(root,e);
    }

    /**
     * 查询node节点下是否包含e（尾递归）
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node,E e){
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        }
        else if(e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else{
            return contains(node.right,e);
        }
    }

    /**
     * 前序遍历（递归）
     */
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 非递归前序遍历
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder(){inOrder(root);}
    public void inOrder(Node node){
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 返回中序遍历的list
     * @return
     */
    public List<E> inorderTraversal() {

        ArrayList<E> res = new ArrayList<E>();
        if(root == null)
            return res;

        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while(cur != null || !stack.empty()){

            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            else{
                cur = stack.pop();
                res.add(cur.e);
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * 后序遍历
     */
    public void postOrder(){postOrder(root);}
    public void postOrder(Node node){
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层序遍历
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    /**
     * 寻找最小元素（递归）
     * @return
     */
    public E minimum(){
        if (size==0) {
            throw new IllegalArgumentException("BST is Empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找最小元素（迭代）
     * @return
     */
    public E minimumNR(){
        if (size==0) {
            throw new IllegalArgumentException("BST is Empty!");
        }
        Node tp = root;
        while(tp.left != null){
            tp = tp.left;
        }
        return tp.e;
    }

    /**
     * 寻找最大元素（递归）
     * @return
     */
    public E maxmum(){
        if (size==0) {
            throw new IllegalArgumentException("BST is Empty!");
        }
        return maxmum(root).e;
    }

    private Node maxmum(Node node){
        if(node.right == null){
            return node;
        }
        return maxmum(node.right);
    }

    /**
     * 寻找最大元素（迭代）
     * @return
     */
    public E maxmumNR(){
        if (size==0) {
            throw new IllegalArgumentException("BST is Empty!");
        }
        Node tp = root;
        while(tp.right != null){
            tp = tp.right;
        }
        return tp.e;
    }

    /**
     * 删除二分搜索树的最小值
     * @return
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
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
     * 删除二分搜索树的最大值
     * @return
     */
    public E removeMax(){
        E ret = maxmum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除已node为跟的二分搜索树中的最大节点
     * 返回删除节点后的树的跟
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right == null){
            Node rightNode = node.left;
            node.left = null;
            size--;
            return rightNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = remove(root,e);
    }

    /**
     * 删除已node为根节点的树中值为e的节点（递归）
     * 返回删除节点后的树的根
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node,E e){
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e)<0) {
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
            return node;
        }else{ // e==node.e
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

    /**
     * 获得元素e在二分搜索树中的前驱(迭代)
     * @param e
     * @return
     */
    public E floor(E e){
        if (root == null) {
            return null;
        }
        Node tp = root;
        Node rs = null;
        while(tp!=null){
            if(e.compareTo(tp.e) > 0){
                if(tp.right != null){
                    rs = tp;
                    tp = tp.right;
                }else{
                    return tp.e;
                }
            }else if(e.compareTo(tp.e) < 0){
                if(tp.left != null){
                    tp = tp.left;
                }else{
                    return rs.e;
                }
            }else{
                return tp.e;
            }
        }
        return rs.e;
    }

    /**
     * 获得元素e在二分搜索树中的前驱(递归)
     * @param e
     * @return
     */
    public E floorNR(E e){
        if(root == null){return null;}
        return floorNR(root,e).e;
    }

    /**
     * 已node为根节点下，找到e的前驱
     * @param node
     * @param e
     * @return
     */
    private Node floorNR(Node node,E e){
        if (node == null) {
            return null;
        }
        if(e.compareTo(node.e) > 0){
            Node tp = floorNR(node.right,e);
            if(tp != null){return tp;}
            return node;
        }else if(e.compareTo(node.e) < 0){
            return floorNR(node.left,e);
        }else{
            return node;
        }
    }
    /**
     * 获得元素e在二分搜索树中的后继(迭代)
     * @param e
     * @return
     */
    public E ceil(E e){
        if (root == null) {
            return null;
        }
        Node tp = root;
        Node rs = null;
        int cmp = e.compareTo(tp.e);
        while(tp!=null){
            if(cmp > 0){
                if(tp.right != null){
                    tp = tp.right;
                }else{
                    return rs.e;
                }

            }else if(cmp < 0){
                if(tp.left != null){
                    rs = tp;
                    tp = tp.left;
                }else{
                    return tp.e;
                }
            }else{
                return tp.e;
            }
        }
        return rs.e;
    }

    public E ceilNR(E e){
        if(root == null){return null;}
        return ceilNR(root,e).e;
    }

    /**
     * 已node为根节点下，找到e的后继
     * @param node
     * @param e
     * @return
     */
    private Node ceilNR(Node node,E e){
        if (node == null) {
            return null;
        }
        int cmp = e.compareTo(node.e);
        if(cmp > 0){
            return ceilNR(node.right,e);

        }else if(cmp < 0){
            Node tp = ceilNR(node.left,e);
            if(tp != null){return tp;}
            return node;
        }else{
            return node;
        }
    }

    /**
     * 获得e的排名
     * @param e
     * @return
     */
    public int rank(E e){
        List<E> list = inorderTraversal();
        int r = 0;
        for (E e1 : list) {
            r++;
            if(e1.compareTo(e) == 0){
                return r;
            }
        }
        return 0;
    }

    /**
     * 获得排名rank的元素的值
     * @param rank
     * @return
     */
    public E select(int rank){
        if(rank > size){return null;}
        List<E> list = inorderTraversal();
        for (int i = 0; i < list.size(); i++) {
            if(i == rank){return list.get(i);}
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    /**
     * 生成以node节点为根节点，深度为depth的描述二叉树的字符串
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }



    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] a = {13,15,22,33,37,41,58,50,63,42,53};
        for (int i : a) {
            bst.add(i);
        }
        System.out.println(bst.select(5));
        System.out.println(bst.inorderTraversal());
        System.out.println(bst.rank(34));
        System.out.println(bst.ceilNR(54));
        bst.remove(3);
        bst.preOrder();

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMax());
        }
        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if(nums.get(i-1) < nums.get(i)){
                throw new IllegalArgumentException("error");
            }

        }
        System.out.println("yes");


        bst.preOrder();
        bst.preOrderNR();
        bst.inOrder();
        bst.postOrder();
        bst.contains(8);
        bst.levelOrder();
        System.out.println(bst.toString());
        System.out.println(bst.maxmumNR());
        System.out.println(bst.maxmum());
        bst.preOrder();
        System.out.println("--------------\n"+bst.removeMax()+"\n---------------");
        bst.preOrder();
    }
}
