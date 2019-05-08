package Algorithm.sort.bubbleSort;

import Algorithm.sort.SortTestHelper;

import static Algorithm.sort.SortTestHelper.printArray;
import static Algorithm.sort.SortTestHelper.swap;

public class BubbleSort {

    private BubbleSort(){}

    public static void sort(Comparable[] arr){
        //方法一
//        int n =0;
//        boolean swapped = false;
//        do{
//            swapped = false;
//            for (int i = 1; i < n; i++) {
//                if(arr[i-1].compareTo(arr[i]) > 0){
//                    swap(arr,i-1,i);
//                    swapped = true;
//                }
//            }
//            // 优化, 每一趟Bubble Sort都将最大的元素放在了最后的位置
//            // 所以下一次排序, 最后的元素可以不再考虑
//            n--;
//        }while (swapped);

        //方法二
        int n = arr.length;
        int newn;
        do{
            newn = 0;
            for (int i = 1; i < n; i++) {
                if(arr[i-1].compareTo(arr[i]) > 0){
                    swap(arr,i-1,i);
                    newn = i;
                }
            }
            //记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
            n = newn;
        }while (newn > 0);

    }

    public static void main(String[] args) {
        int N = 10;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10);
        System.out.println(printArray(arr));
        sort(arr);
        System.out.println(printArray(arr));
//        SortTestHelper.testSort("Algorithm.sort.selectSort.SelectionSort", arr);

        return;
    }

}
