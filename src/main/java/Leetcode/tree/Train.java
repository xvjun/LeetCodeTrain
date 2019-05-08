package Leetcode.tree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Train {

    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public void create(int[] list){

        }
    }

    /**
     * 100. 相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){return true;}
        if(p != null && q != null && p.val==q.val){
            boolean b1 = isSameTree(p.left,q.left);
            boolean b2 = isSameTree(p.right,q.right);
            return b1 && b2;
        }else{
            return false;
        }

    }

    /**
     * 101. 对称二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return function1(root.left,root.right);
    }
    private boolean function1(TreeNode p,TreeNode q){
        if(q == null && p == null){return true;}
        if(q == null || p == null){return false;}
        if(p.val != q.val){return false;}
        return function1(p.right,q.left) && function1(p.left,q.right);
    }

    /**
     * 104. 二叉树的最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null){return 0;}
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }

    /**
     * 107. 二叉树的层次遍历 II
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        func(lists, 0, root);
        for (int i = 0, j = lists.size() - 1; i < j; i++, j--) {
            List<Integer> temp = lists.get(i);
            lists.set(i, lists.get(j));
            lists.set(j, temp);
        }
        return lists;
    }

    private void func(List<List<Integer>> lists, int level, TreeNode root) {
        if (root == null) {
            return;
        }
        if (lists.size() == level) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            lists.add(list);
        } else {
            lists.get(level).add(root.val);
        }
        func(lists, level + 1, root.left);
        func(lists, level + 1, root.right);
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // 左右等分建立左右子树，中间节点作为子树根节点，递归该过程
        return nums == null ? null : buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = buildTree(nums, l, m - 1);
        root.right = buildTree(nums, m + 1, r);
        return root;
    }

    /**
     * 110. 平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        else if(Math.abs(height(root.left)-height(root.right))>1){
            return false;
        }
        else{
            return isBalanced(root.left)&&isBalanced(root.right);
        }
    }


    public int height(TreeNode root){
        if(root==null)
            return 0;
        else
            return Math.max(height(root.left),height(root.right))+1;
    }

    /**
     * 111. 二叉树的最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root == null){return 0;}
        if(root.left == null && root.right == null){return 1;}
        int a=-1,b=-1;
        if(root.left != null){
            a = minDepth(root.left);
        }
        if(root.right != null){
            b = minDepth(root.right);
        }
        if(a==-1){return b+1;}
        if(b==-1){return a+1;}
        return Math.min(a,b)+1;
    }

    /**
     * 112. 路径总和
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null){return null;}
        if(root.left == null && root.right == null){return root;}
        else{
            TreeNode l = invertTree(root.right);
            TreeNode r = invertTree(root.left);
            root.left = l;
            root.right = r;
            return root;
        }

    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return lca(root, p , q);
    }

    public TreeNode lca(TreeNode root, TreeNode p , TreeNode q){
        if((root.val - p.val)*(root.val - q.val) <= 0){
            return root;
        }else if(root.val < p.val && root.val < q.val){
            return lca(root.right, p , q);
        }else{
            return lca(root.left, p , q);
        }
    }

    /**
     * 257. 二叉树的所有路径
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new LinkedList<>();
        getPath(root,"",result);
        return result;
    }

    public void getPath(TreeNode root,String path,List result){
        if(root==null)return;
        path += root.val;
        if(root.left!=null)
            getPath(root.left,path+"->",result);
        if(root.right!=null)
            getPath(root.right,path+"->",result);
        if(root.left==null&&root.right==null)
            result.add(path);
        return;
    }

    /**
     * 437. 路径总和 III
     */
    int pathnumber;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        Sum(root,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
        return pathnumber;
    }


    public void Sum(TreeNode root, int sum){
        if(root == null) return;
        sum-=root.val;
        if(sum == 0){
            pathnumber++;
        }
        Sum(root.left,sum);
        Sum(root.right,sum);
    }

    /**
     * 303. 区域和检索 - 数组不可变
     */
//    private int[] sum;
//    public void NumArray(int[] nums){
//        sum = new int[nums.length+1];
//        sum[0] = 0;
//        for (int i = 1; i < sum.length; i++) {
//            sum[i] = sum[i-1] + nums[i-1];
//        }
//    }
//    public int sumRange(int i,int j){
//        return sum[j]-sum[i];
//    }
    /**
     * 307
     */
    private int[] sum;
    private int[] data;
    public void NumArray(int[] nums){
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }

        sum = new int[nums.length+1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
    }
    public int sumRange(int i,int j){
        return sum[j]-sum[i];
    }
    public void update(int i, int val) {
        data[i] = val;
        for (int j = i+1; j < sum.length; j++) {
            sum[j] = sum[j-1] + data[j-1];
        }
    }

    /**
     * 103. 二叉树的锯齿形层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> rs = new ArrayList<>();
        Stack<TreeNode> st_right = new Stack<>();
        Stack<TreeNode> st_left = new Stack<>();
        if(root == null){return rs;}
        st_right.push(root);
        boolean direction = true;
        while(!st_right.isEmpty() || !st_left.isEmpty()){
            List<Integer> list = new ArrayList<>();
            if(direction){
                while(!st_right.isEmpty()){
                    TreeNode cur = st_right.pop();
                    list.add(cur.val);
                    if(cur.left != null) st_left.push(cur.left);
                    if(cur.right != null) st_left.push(cur.right);
                }
            }else{
                while(!st_left.isEmpty()){
                    TreeNode cur = st_left.pop();
                    list.add(cur.val);
                    if(cur.right != null) st_right.push(cur.right);
                    if(cur.left != null) st_right.push(cur.left);
                }
            }
            direction = !direction;
            rs.add(list);
        }
        return rs;
    }

    /**
     * 144. 二叉树的前序遍历(迭代)
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> rs = new ArrayList<>();
        if(root == null){return rs;}
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            rs.add(cur.val);
            if(cur.right != null) stack.push(cur.right);
            if(cur.left != null) stack.push(cur.left);
        }
        return rs;
    }

    /**
     * 后续遍历{迭代}
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root){

        ArrayList<Integer> rs = new ArrayList<>();
        if (root == null) {
            return rs;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;

        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(cur.right == null || pre == cur.right){
                rs.add(cur.val);
                pre = cur;
                cur = null;
            }else{
                stack.push(cur);
                cur = cur.right;
            }
        }
        return rs;
    }

    /**
     * 150. 逆波兰表达式求值
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+")){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a+b);
            }else if(tokens[i].equals("-")){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a-b);
            }else if(tokens[i].equals("*")){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a*b);
            }else if(tokens[i].equals("/")){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a/b);
            }else{
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    /**
     * 331. 验证二叉树的前序序列化
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        int degree = 1;
        for (String s : preorder.split(",")) {
            if(degree == 0){return false;}
            if(s.equals("#")){degree--;}
            else{degree++;}
        }
        return degree==0;
    }

    /**
     * 394. 字符串解码
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<String> stack=new Stack<String>();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)==']') {
                String string="";
                while(!stack.peek().equals("[")) {
                    string=stack.pop()+string;
                }
                stack.pop();

                String countString="";
                while((!stack.isEmpty())&&(stack.peek().charAt(0)>='0'&&stack.peek().charAt(0)<='9')) {
                    countString=stack.pop()+countString;
                }
                int count=Integer.parseInt(countString);

                String retString="";
                for(int j=0;j<count;j++) {
                    retString=retString+string;
                }
                stack.push(retString);
            }
            else {
                String str=""+s.charAt(i);
                stack.push(str);
            }
        }

        String aaa="";
        while(!stack.isEmpty()) {
            aaa=stack.pop()+aaa;
        }
        return aaa;

    }

    /**
     * 402. 移掉K位数字
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        //贪心算法+栈
        if(k>=num.length()||num.length()==0)
            return "0";
        //栈顶始终是最大值
        Stack<Integer>stack=new Stack<>();
        stack.push(num.charAt(0)-'0');
        for(int i=1;i<num.length();i++)
        {
            int now=num.charAt(i)-'0';
            //可能好几个值都比当前值大，那么我们就在k允许的情况下，去去除它。
            while(!stack.isEmpty()&&k>0&&now<stack.peek()){
                stack.pop();
                k--;
            }
            //不等于0可以添加进去,
            //等于0，栈不为空可以填进去，
            if(now!=0||!stack.isEmpty())
            {
                stack.push(now);
            }
        }
        //56789这种情况，前面一直比后面小，那就去除栈顶，谁让栈顶最大
        while(k>0)
        {
            k--;
            stack.pop();
        }
        //10，1(当now=0时，满足条件，去掉1，但now为0，且为空。)
        if(stack.isEmpty())
            return "0";
        StringBuilder sb=new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop());
        //从后往前添加所以我们要逆序
        return sb.reverse().toString();
    }

    /**
     * 404. 左叶子之和
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root,0);
    }

    private int dfs(TreeNode root,int cmp){
        if(root == null)
            return 0;
        int sum = 0;
        if(root.left != null)
            sum = dfs(root.left,-1);
        if(root.right != null){
            sum += dfs(root.right,1);
        }
        if(root.left == null && root.right == null && cmp< 0)
            return root.val;
        return sum;
    }

    /**
     * 429. N叉树的层序遍历
     * @param root
     * @return
     *//**
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(root != null){
            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            while(q.peek() != null){
                List<Integer> l = new ArrayList<Integer>();
                Queue<Node> childrenQ = new LinkedList<>();
                while(q.peek() != null){
                    Node n = q.poll();
                    l.add(Integer.valueOf(n.val));
                    if(n.children.size()>0){
                        for(Node node : n.children)
                            childrenQ.offer(node);
                    }
                }
                q = childrenQ;
                list.add(l);
            }
        }
        return list;
    }
    //*/

}
