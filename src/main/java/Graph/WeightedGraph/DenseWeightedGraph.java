package Graph.WeightedGraph;

import java.util.Vector;

//稠密图 - 邻接矩阵
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {
    private int n; //节点数
    private int m; //边数
    private boolean directed; //是否为有向图
    private Edge<Weight>[][] g; //邻接矩阵

    //构造函数
    public DenseWeightedGraph(int n,boolean directed){
        assert n >= 0 : "n < 0,assert error.";
        this.n = n;
        // 初始化没有任何边
        this.m = 0;
        this.directed = directed;
        g = new Edge[n][n];
        // g初始化为n*n的布尔矩阵, 每一个g[i][j]均为null, 表示没有任和边
        // false为boolean型变量的默认值
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = null;
            }
        }
    }

    public int V(){ return n;} // 返回节点个数
    public int E(){ return m;} // 返回边的个数



    @Override
    public void addEdge(Edge e) {
        assert e.v() >= 0 && e.v() < n : String.format("e.v : %d need in [0,n)",e.v());
        assert e.w() >= 0 && e.w() < n : String.format("e.w : %d need in [0,n)",e.w());
        if(hasEdge(e.v(),e.w())){
            return;
        }
        g[e.v()][e.w()] = new Edge<>(e);
        if(e.v() != e.w() && !directed){
            g[e.w()][e.v()] = new Edge(e.w(),e.v(),e.wt());
        }
        m++;
    }

    @Override
    public boolean hasEdge(int v, int w) {

        assert v >= 0 && v < n : String.format("v not exist",v);
        assert w >= 0 && w < n : String.format("w not exist",w);

        return g[v][w] != null;
    }


    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != null) {
                    System.out.print(g[i][j].wt()+"\t");
                }else{
                    System.out.print("NULL\t");
                }
            }
            System.out.println();
        }
    }

    // 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;
        Vector<Edge<Weight>> adjV = new Vector<Edge<Weight>>();
        for (int i = 0; i < n; i++) {
            if (g[v][i] != null) {
                adjV.add(g[v][i]);
            }
        }
        return adjV;
    }
}
