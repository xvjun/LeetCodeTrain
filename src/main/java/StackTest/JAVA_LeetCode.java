package StackTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JAVA_LeetCode {


    public static void main(String[] args) {
        JAVA_LeetCode main = new JAVA_LeetCode();
        System.out.println(main.simplifyPath("/a/../../b/../c//.//", "/a/../../b/../c//.//".length()));
        System.out.println("username");

        System.out.println("asdasda");
        System.out.println("asdasda");
        System.out.println("asdasda");
        System.out.println("asdasda");
        System.out.println("asdasda");
        System.out.println("asdasda");
        System.out.println("asdasda");
        System.out.println("asdasda");
    }

    private class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    /**
     *94. 二叉树的中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    /**
     * 71. 简化路径
     * @param path
     * @param len
     * @return
     */
    public String simplifyPath(String path, int len) {
        path = path + "/"; //直接在path后加"/"防止特殊情况
        Stack<Integer> s = new Stack<Integer>(); //栈,用来处理/../时,消除当前目录
        StringBuilder sb = new StringBuilder();//存储简化后的路径
        int p = 0;
        int getparameter = 0;
        for (int i = 0; i < len - 1;) {
            if (path.charAt(i) == '/') {//双指针,截取/xxx/这段字符串
                p = i + 1;
                while (path.charAt(p) != '/') {
                    p++;
                }
            }
            String temp = path.substring(i, p + 1);
            getparameter = chooseForPath(temp);
            switch (getparameter) {//根据返回参数,处理简化路径
                case 1:
                    temp = path.substring(i, p);
                    sb.append(temp);
                    s.add(temp.length());
                    i = p; //指针移位
                    break;
                case 2:
                    i = p;
                    break;
                case 3:
                    if (!s.isEmpty()) {
                        int lenOfsb = sb.length();
                        int index = lenOfsb - s.pop();
                        sb.delete(index , sb.length());
                        i = p;
                    } else {
                        i = p;
                    }
                    break;
                case 4:
                    i = p;
                    break;

                default:
                    break;
            }
        }
        if (sb.length() == 0) {//特殊情况处理
            return "/";
        }
        return sb.toString();
    }
    public int chooseForPath(String tempOfsub) {
        /*
         * "/"a"/"  返回 1
         * "/"."/"  返回 2
         * "/".."/" 返回 3
         * "/"/"    返回 4
         */
        if (tempOfsub.equals("/./"))
            return 2;
        else if (tempOfsub.equals("/../"))
            return 3;
        else if (tempOfsub.equals("//"))
            return 4;
        else {
            return 1;
        }
    }

    /**
     * 844. 比较含退格的字符串
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> s = new Stack<Character>();
        Stack<Character> t = new Stack<Character>();
        for(char i : S.toCharArray()){
            if(i=='#' && !s.empty()){s.pop();}
            else if(i!='#'){s.push(i);}
        }
        for(char i : T.toCharArray()){
            if(i=='#'&& !t.empty()){t.pop();}
            else if(i!='#'){t.push(i);}
        }
        if(s.equals(t)){return true;}
        else {return false;}
    }

}
