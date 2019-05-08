package Algorithm.sort.mergeSort;

import Algorithm.sort.SortTestHelper;
import Algorithm.sort.insertSort.InsertionSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    //当需要排序的数组大小小于minsize时（包括递归调用时），使用插入排序，能节省时间
    private static final int minSize = 15;

    private MergeSort(){}

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

    private static void sort(Comparable[] arr,int l,int r){

//        if(l >= r){
//            return;
//        }
        //对递归结束条件进行优化
        if(r-l <= 15){
            InsertionSort.sort(arr,l,r);
            return;
        }

        int mid = (r-l)/2 + l;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        //对于arr[mid] <= arr[mid+1]的情况,不进行merge
        //对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
        if(arr[mid].compareTo(arr[mid+1]) > 0){
            merge(arr,l,mid,r);
        }


    }

    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr,0,n-1);
    }

    public static void main(String[] args) {
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N,0,N);
        SortTestHelper.testSort("Algorithm.sort.mergeSort.MergeSort",arr);
    }

}
