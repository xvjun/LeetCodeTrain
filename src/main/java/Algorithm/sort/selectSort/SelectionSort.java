package Algorithm.sort.selectSort;

import Algorithm.sort.SortTestHelper;

import static Algorithm.sort.SortTestHelper.swap;

public class SelectionSort {
    // 优化：在每一轮中, 可以同时找到当前未处理元素的最大值和最小值
    public static void sort(Comparable[] arr){
        //方法一
//        for (int i = 0; i < arr.length; i++) {
//            int minIndex = i;
//            for (int j = i+1; j < arr.length; j++) {
//                if(arr[j].compareTo(arr[minIndex]) < 0){
//                    minIndex = j;
//                }
//            }
//            SortTestHelper.swap(arr,i,minIndex);
//        }

        //方法二
        int left = 0, right = arr.length - 1;
        while(left < right){
            int minIndex = left;
            int maxIndex = right;

            // 在每一轮查找时, 要保证arr[minIndex] <= arr[maxIndex]
            if(arr[minIndex].compareTo(arr[maxIndex]) > 0)
                swap(arr, minIndex, maxIndex);

            for(int i = left + 1 ; i < right; i ++)
                if(arr[i].compareTo(arr[minIndex]) < 0)
                    minIndex = i;
                else if(arr[i].compareTo(arr[maxIndex]) > 0)
                    maxIndex = i;

            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);

            left ++;
            right --;
        }
    }

    public static void main(String[] args) {
        // 测试排序算法辅助函数
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Algorithm.sort.selectSort.SelectionSort", arr);

        return;
    }

}
