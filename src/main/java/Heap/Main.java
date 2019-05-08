package Heap;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int n = 1000000;
        Integer[] testData = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double t2 = testHeap(testData,true);
        System.out.println(" heapify:" + t2 + "s");

        double t1 = testHeap(testData,false);
        System.out.println("not heapify:" + t1 + "s");



//2
//        long x1 = System.nanoTime();
//
//        int n = 1000000;
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//        Random random = new Random();
//        for (int i = 0; i < n; i++) {
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//        }
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = maxHeap.extractMax();
//        }
//
//        for (int i = 1; i < n; i++) {
//            if(arr[i-1] < arr[i]){
//                throw new IllegalArgumentException("error:"+i);
//            }
//
//        }
//        System.out.println("success");
//
//        long x2 = System.nanoTime();
//
//        System.out.println((x2-x1) / 1000000000.0);
    }

    private static double testHeap(Integer[] testData,boolean isHeadpify){
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeadpify) {
            maxHeap = new MaxHeap<>(testData);
        }else{
            maxHeap = new MaxHeap<>();
            for (Integer testDatum : testData) {
                maxHeap.add(testDatum);
            }
        }

        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < testData.length; i++) {
            if(arr[i-1] < arr[i]){
                throw new IllegalArgumentException("error:"+i);
            }

        }
        System.out.println("success");

        long endTime = System.nanoTime();

        return (endTime-startTime) / 1000000000.0;
    }

}
