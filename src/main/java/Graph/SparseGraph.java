package Graph;

import java.util.Vector;

/**
 * 稀疏图 - 邻接表
 */
public class SparseGraph implements Graph {

    private int n;
    private int m;
    private boolean directed;
    private Vector<Integer>[] g;

    public SparseGraph(int n,boolean directed){
        if (n < 0) {
            throw new IllegalArgumentException("n need > 0");
        }
        this.n = n;
        this.m = 0;
        this.directed = directed;
        //g初始化为n个空的vector, 表示每一个g[i]都为空, 即没有任和边
        g = (Vector<Integer>[]) new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<Integer>();
        }
    }

    public int V(){return n;} //返回节点的个数
    public int E(){return m;} //返回边的个数

    public void addEdge(int v,int w){
        if(!(v >= 0 && v < n && w >=0 && w < n)){
            throw new IllegalArgumentException(String.format("v and w need in [0,%d)",n));
        }
        if(hasEdge(v,w)){
            return;
        }
        g[v].add(w);
        if (v != w && !directed) {
            g[w].add(v);
        }
        m++;
    }

    /**
     * 验证图中是否有v -> w的边
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v,int w){
        if(!(v >= 0 && v < n && w >=0 && w < n)){
            throw new IllegalArgumentException(String.format("v and w need in [0,%d)",n));
        }
        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].elementAt(i) == w) {
                return true;
            }
        }
        return false;
    }

    // 显示图的信息
    public void show(){

        for( int i = 0 ; i < n ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ )
                System.out.print(g[i].elementAt(j) + "\t");
            System.out.println();
        }
    }

    /**
     * 返回图中一个顶点的所有邻边
     * 由于java使用引用机制，返回一个Vector不会带来额外开销,
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        if (!(v >= 0 && v < n)) {
            throw new IllegalArgumentException(String.format("v need in [0,%d)",n));
        }

        return g[v];
    }

}
