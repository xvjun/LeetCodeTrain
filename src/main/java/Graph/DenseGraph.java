package Graph;

import java.util.Vector;

/**
 * 稠密图 - 邻接矩阵
 */
public class DenseGraph implements Graph {

    private int n; //节点数
    private int m; //边数
    private boolean directed; //是否为有向图
    private boolean[][] g; //邻接矩阵

    public DenseGraph(int n,boolean directed){
        if (n < 0) {
            throw new IllegalArgumentException("n need > 0");
        }
        this.n = n;
        this.m = 0;
        this.directed = directed;
        //g初始为n*n的bool变量，均为false，表述没有任何边。false为boolean型变量的默认值
        g = new boolean[n][n];
    }

    @Override public int V(){return n;} //返回节点的个数
    @Override public int E(){return m;} //返回边的个数
    @Override
    public void addEdge(int v,int w){
        if(!(v >= 0 && v < n && w >=0 && w < n)){
            throw new IllegalArgumentException(String.format("v and w need in [0,%d)",n));
        }

        if(hasEdge(v,w)){
            return;
        }
        g[v][w] = true;
        if (!directed) {
            g[w][v] = true;
        }
        m++;
    }

    /**
     * 验证图中是否有v -> w的边
     * @param v
     * @param w
     * @return
     */
    @Override
    public boolean hasEdge(int v,int w){
        if(!(v >= 0 && v < n && w >=0 && w < n)){
            throw new IllegalArgumentException(String.format("v and w need in [0,%d)",n));
        }
        return g[v][w];
    }

    @Override
    // 显示图的信息
    public void show(){

        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                System.out.print(g[i][j]+"\t");
            System.out.println();
        }
    }

    /**
     * 返回图中一个顶点的所有邻边
     * 由于java使用引用机制，返回一个Vector不会带来额外开销,
     * @param v
     * @return
     */
    @Override
    public Iterable<Integer> adj(int v){
        if (!(v >= 0 && v < n)) {
            throw new IllegalArgumentException(String.format("v need in [0,%d)",n));
        }
        Vector<Integer> adjV = new Vector<>();
        for (int i = 0; i < n; i++) {
            if(g[v][i]){
                adjV.add(i);
            }
        }
        return adjV;
    }

}
