package Graph;
//求无权图的联通分量

public class Components {

    Graph G;  // 图的引用
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    private int ccount;  // 记录联通分量个数
    private int[] id;  // 每个节点所对应的联通分量标记

    //图的深度优先遍历
    public void dfs(int v){
        visited[v] = true;
        id[v] = ccount;

        for (Integer i : G.adj(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    //构造函数，求无权图的联通分量
    public Components(Graph g){
        G = g;
        visited = new boolean[G.V()];
        id=  new int[G.V()];
        ccount = 0;
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            id[i] = -1;
        }
//        float f1 = 0.421f;
//        double f2 = 0.42;
        //求解
        for (int i = 0; i < G.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }



    /**
     * 返回图的联通分量个数
     * @return
     */
    public int count(){
        return ccount;
    }

    /**
     * 查询v和w是否联通
     * @param v
     * @param w
     * @return
     */
    public boolean isConnected(int v,int w){
        if(!(v >= 0 && v < G.V())){
            throw new IllegalArgumentException(String.format("v:%d >= 0 && v < %d",v,G.V()));
        }
        if(!(w >= 0 && w < G.V())){
            throw new IllegalArgumentException(String.format("w:%d >= 0 && w < %d",w,G.V()));
        }
        return id[v] == id[w];
    }

}
