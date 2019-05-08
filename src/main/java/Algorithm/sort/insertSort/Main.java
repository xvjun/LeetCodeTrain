package Algorithm.sort.insertSort;

import Algorithm.sort.SortTestHelper;

import java.util.Arrays;

public class Main {

    // 比较SelectionSort和InsertionSort两种排序算法的性能效率
    // 此时，插入排序比选择排序性能略低
    public static void main(String[] args) {

        int N = 20000;
        System.out.println("Test for random array, size = " + N + " , random range [0, " + N + "]");

//        Integer[] arr1 = SortTestHelper.generateRandomArray(N, 0,N);
        Integer[] arr1 = SortTestHelper.generateNearlyOrderedArray(N, 10);
        Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr4 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr5 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr6 = Arrays.copyOf(arr1, arr1.length);
        Integer[] arr7 = Arrays.copyOf(arr1, arr1.length);

        SortTestHelper.testSort("Algorithm.sort.selectSort.SelectionSort", arr1);
        SortTestHelper.testSort("Algorithm.sort.insertSort.InsertionSort", arr2);
        SortTestHelper.testSort("Algorithm.sort.bubbleSort.BubbleSort", arr3);
        SortTestHelper.testSort("Algorithm.sort.insertSort.ShellSort", arr4);
        SortTestHelper.testSort("Algorithm.sort.mergeSort.MergeSort",arr5);
        SortTestHelper.testSort("Algorithm.sort.quickSort.QuickSort", arr6);
        SortTestHelper.testSort("Algorithm.sort.quickSort.QuickSort3Ways", arr7);
        return;
    }
}
