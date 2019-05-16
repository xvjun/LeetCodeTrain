package Graph;

import java.util.Stack;
import java.util.Vector;

//求一个点到另一个点的路径，不一定是最短路径
public class Path {
    private Graph G; // 图的引用
    private  int s;  // 起始点
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    private int[] from;  // 记录路径, from[i]表示查找的路径上i的上一个节点

    //图的深度优先遍历
    public void dfs(int v){
        visited[v] = true;

        for (Integer i : G.adj(v)) {
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
        }
    }

    //// 构造函数, 寻路算法, 寻找图graph从s点到其他点的路径
    public Path(Graph g,int s){
        G = g;
        if(!(s >= 0 && s < G.V())){throw new IllegalArgumentException(String.format("s:%d need in [0,%d)",s,G.V()));}

        visited = new boolean[G.V()];
        from = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }
        this.s = s;

        dfs(s);
    }

    /**
     * // 查询从s点到w点的路径, 存放在vec中
     * @param w
     * @return
     */
    public Vector<Integer> path(int w){
        if(!hasPath(w)){
            System.out.println(String.format("Graph not s:%d to w:%d",s,w));
            return null;
        }
        Stack<Integer> s = new Stack<>();
        //// 通过from数组逆向查找到从s到w的路径, 存放到栈中
        int p = w;
        while (p != -1) {
            s.push(p);
            p = from[p];
        }
        // 从栈中依次取出元素, 获得顺序的从s到w的路径
        Vector<Integer> res = new Vector<>();
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }

    /**
     * 查询从s到w是否有路径
     * @param w
     * @return
     */
    public boolean hasPath(int w){
        if(!(w >= 0 && w < G.V())){
            throw new IllegalArgumentException(String.format("w:%d need in [0,%d)",w,G.V()));
        }
        return visited[w];
    }

    public void showPath(int w){
        if(!hasPath(w)){
            System.out.println(String.format("Graph not s:%d to w:%d",s,w));
            return;
        }
        Vector<Integer> vec = path(w);
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.elementAt(i));
            if (i == vec.size()-1) {
                System.out.println();
            }else{
                System.out.println("->");
            }
        }

    }

}
