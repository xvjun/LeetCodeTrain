package Queue;

import java.util.Random;

public class Main {

    // 测试使用stack运行opCount个push和pop操作所需要的时间，单位：秒
    private static double testStack(Queue<Integer> queue, int opCount){

        long startTime = System.nanoTime();

        Random random = new Random();
        for(int i = 0 ; i < opCount ; i ++)
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0 ; i < opCount ; i ++)
            queue.dequeue();

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
        double time1 = testStack(arrayQueue, opCount);
        System.out.println("arrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> LoopQueue = new LoopQueue<Integer>();
        double time2 = testStack(LoopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");

        LinkListQueue<Integer> LinkListQueue = new LinkListQueue<Integer>();
        double time3 = testStack(LinkListQueue, opCount);
        System.out.println("LinkListQueue, time: " + time3 + " s");

        // 其实这个时间比较很复杂，因为LinkedListStack中包含更多的new操作
    }
}
