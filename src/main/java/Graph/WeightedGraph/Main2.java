package Graph.WeightedGraph;

import java.util.Vector;

//// 使用Prim算法求图的最小生成树
public class Main2 {

    public static void main(String[] args) {

        String filename = "src/main/java/Graph/WeightedGraph/txt/testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + lazyPrimMST.getMstWeight());

        System.out.println();
    }
}
