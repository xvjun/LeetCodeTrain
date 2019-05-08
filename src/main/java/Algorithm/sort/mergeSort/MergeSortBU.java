package Algorithm.sort.mergeSort;

import Algorithm.sort.SortTestHelper;
import Algorithm.sort.insertSort.InsertionSort;

import java.util.Arrays;

public class MergeSortBU {
    //当需要排序的数组大小小于minsize时（包括递归调用时），使用插入排序，能节省时间
    private static final int minSize = 15;

    private MergeSortBU(){}

    /**
     * 将arr[l,mid]和arr[mid+1,r]两部分进行归并
     * copyOfRange可以自己指定起点和终点，用下标表示，copyof只能从0开始
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(Comparable[] arr,int l,int mid,int r){
        Comparable[] aux = Arrays.copyOfRange(arr,l,r+1);
        //初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i=l,j = mid+1;
        for (int k = l; k <= r; k++) {
            if (i>mid) { //如果左半部分元素已经全部处理完毕
                arr[k] = aux[j-l];
                j++;
            }else if( j > r){ // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i-l];
                i++;
            }else if(aux[i-l].compareTo(aux[j-l]) < 0){ // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i-l];
                i++;
            }else{  // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j-l];
                j++;
            }
        }
    }


    public static void sort(Comparable[] arr){
        int n = arr.length;
        //无优化版本
//        for (int sz = 1; sz < n; sz*=2) {
//            for(int i=0;i < n-sz;i += sz+sz){
//                //对arr[i,i+sz-1]和[i+sz-1,i+2*sz-1]进行归并
//                merge(arr,i,i+sz-1,Math.min(i+2*sz-1,n-1));
//            }
//        }
        //对于小数组，使用插入排序
        for (int i = 0; i < n; i+=minSize) {
            InsertionSort.sort(arr,i,Math.min(i+minSize-1,n-1));
        }
        for(int sz = minSize;sz<n;sz+=sz){
            for(int i=0;i < n - sz;i+=(2*sz)){
                if(arr[i+sz-1].compareTo(arr[i+sz]) > 0){
                    merge(arr,i,i+sz-1,Math.min(i+sz+sz-1,n-1));
                }
            }
        }
    }

    // 测试 MergeSort BU
    public static void main(String[] args) {

        // Merge Sort BU 也是一个O(nlogn)复杂度的算法，虽然只使用两重for循环
        // 所以，Merge Sort BU也可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易根据循环层数来判断算法的复杂度，Merge Sort BU就是一个反例
        // 关于这部分陷阱，推荐看我的《玩转算法面试》课程，第二章：《面试中的复杂度分析》：）
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Algorithm.sort.mergeSort.MergeSortBU", arr);

        return;
    }

}
