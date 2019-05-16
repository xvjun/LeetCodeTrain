package Graph.WeightedGraph;
// 使用Prim算法求图的最小生成树

import java.util.PriorityQueue;
import java.util.Vector;

public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightedGraph<Weight> G;  // 图的引用
    private PriorityQueue<Edge<Weight>> priorityQueue;  // 最小堆, 算法辅助数据结构
    private boolean[] marked;  // 标记数组, 在算法运行过程中标记节点i是否被访问
    private Vector<Edge<Weight>> mst;  // 最小生成树所包含的所有边
    private Number mstWeight;  // 最小生成树的权值

    public LazyPrimMST(WeightedGraph<Weight> graph){
        // 算法初始化
        G = graph;
        priorityQueue = new PriorityQueue<Edge<Weight>>();
        marked = new boolean[G.V()];
        mst = new Vector<>();
        // Lazy Prim
        visit(0);
        while (!priorityQueue.isEmpty()) {
            // 使用最小堆找出已经访问的边中权值最小的边
            Edge<Weight> e = priorityQueue.poll();
            // 如果这条边的两端都已经访问过了, 则扔掉这条边
            if(marked[e.v()] == marked[e.w()]){continue;}
            // 否则, 这条边则应该存在在最小生成树中
            mst.add(e);
            // 访问和这条边连接的还没有被访问过的节点
            if(!marked[e.v()]){
                visit(e.v());
            }else{
                visit(e.w());
            }
        }

        //最小生成树已完成，计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
        }
    }

    private void visit(int v){
        assert !marked[v] : "node was visit.";
        marked[v] = true;
        // 将和节点v相连接的所有未访问的边放入最小堆中
        for (Edge<Weight> edge : G.adj(v)) {
            if (!marked[edge.other(v)]) {
                priorityQueue.add(edge);
            }
        }
    }
    // 返回最小生成树的所有边
    public Vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    // 返回最小生成树的权值
    public Number getMstWeight(){
        return mstWeight;
    }


}
