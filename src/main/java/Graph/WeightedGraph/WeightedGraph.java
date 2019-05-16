package Graph.WeightedGraph;

public interface WeightedGraph<Weight extends Number & Comparable> {

    public int V();
    public int E();
    public void addEdge(Edge<Weight> e);
    public boolean hasEdge(int v,int w);
    public void show();
    public Iterable<Edge<Weight>> adj(int v);

}
