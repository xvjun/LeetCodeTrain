package UnionFind;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000 ;

//        UnionFind1 unionFind1 = new UnionFind1(size);
//        System.out.println("u1:" + testUF(unionFind1,m) + "s");
//
//        UnionFind2 unionFind2 = new UnionFind2(size);
//        System.out.println("unionFind2:" + testUF(unionFind2,m) + "s");

        UnionFind3 unionFind3 = new UnionFind3(size);
        System.out.println("unionFind3:" + testUF(unionFind3,m) + "s");

        UnionFind4 UnionFind4 = new UnionFind4(size);
        System.out.println("UnionFind4:" + testUF(UnionFind4,m) + "s");

        UnionFind5 UnionFind5 = new UnionFind5(size);
        System.out.println("UnionFind5:" + testUF(UnionFind5,m) + "s");

        UnionFind6 UnionFind6 = new UnionFind6(size);
        System.out.println("UnionFind6:" + testUF(UnionFind6,m) + "s");
    }

    private static double testUF(UF uf,int m){
        int size = uf.getSize();
        Random random = new Random();

        long st = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a,b);
        }

        long et = System.nanoTime();

        return (et-st) / 1000000000.0;
    }

}
