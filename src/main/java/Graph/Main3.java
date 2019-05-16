package Graph;

public class Main3 {

    // 测试寻路算法
    public static void main(String[] args) {

        String filename = "src/main/java/Graph/txt/testG1.txt";
        SparseGraph g = new SparseGraph(13, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g,0);
        System.out.println("Path from 0 to 3 : ");
        path.showPath(11);
    }

}
