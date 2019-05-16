package Graph;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

//无权图的最短路径
public class ShortestPath {

    private Graph G; // 图的引用
    private int s;  // 起始点
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    private int[] from;  // 记录路径, from[i]表示查找的路径上i的上一个节点
    private int[] ord;  // 记录路径中节点的次序。ord[i]表示i节点在路径中的次序。

    public ShortestPath(Graph graph,int s){
        G = graph;
        if(!(s >= 0 && s < G.V())){
            throw new IllegalArgumentException(String.format("s:%d need in [0,%d)",s,G.V()));
        }
        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
        this.s =s;

        // 无向图最短路径算法, 从s开始广度优先遍历整张图
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        visited[s] = true;
        ord[s] = 0;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (Integer i : G.adj(v)) {
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }
    }


    public int fun(int[] arr,int k,int target){
        int sum=0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(sum == target && count == k ){
                return i-k;
            }else if(sum < target && count < k){
                sum +=arr[i];
                count++;
            }else{ // if(sum > target)  if(sum < target && count > k)
                sum=sum-arr[i-k];
                count--;
            }
        }
        return -1;
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

    /**
     * // 查询从s点到w点的路径, 存放在vec中
     * @param w
     * @return
     */
    public Vector<Integer> path(int w){
        if(!hasPath(w)){
            System.out.println(String.format("Graph donot s:%d to w:%d",s,w));
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
                System.out.println(" ->");
            }
        }

    }

    // 查看从s点到w点的最短路径长度
    // 若从s到w不可达，返回-1
    public int length(int w){
        assert w >= 0 && w < G.V();
        return ord[w];
    }

}
