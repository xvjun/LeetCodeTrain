package Leetcode.tree;

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
}
