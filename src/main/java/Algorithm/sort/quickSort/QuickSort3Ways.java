package Algorithm.sort.quickSort;

import Algorithm.sort.SortTestHelper;
import Algorithm.sort.insertSort.InsertionSort;

import static Algorithm.sort.SortTestHelper.swap;

public class QuickSort3Ways {

    //当需要排序的数组大小小于minsize时（包括递归调用时），使用插入排序，能节省时间
    private static final int minSize = 15;

    private QuickSort3Ways(){}

    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr,0,n-1);
    }

    /**
     * 递归使用三路快速排序，对arr[l,r]的范围进行排序
     * @param arr
     * @param l
     * @param r
     */
    private static void sort(Comparable[] arr,int l,int r){
//        if(l >= r){return;}

        if(r-l <= 15){
            InsertionSort.sort(arr,l,r);
            return;
        }

        //优化：对于近乎有序的情况下，随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr,l,(int) (Math.random()*(r-l+1)) + l);

        Comparable v = arr[l];

        int lt = l;   // arr[l+1,lt] < v
        int gt = r+1;   // arr[gt,r] > v
        int i = l+1;  //arr[lt+1,i] = v
        while(i < gt){
            if(arr[i].compareTo(v) < 0){
                swap(arr,i,lt+1);
                i++;lt++;
            }
            else if(arr[i].compareTo(v) > 0){
                swap(arr,i,gt-1);
                gt--;
            }else{
                i++;
            }
        }
        swap(arr,l,lt);
        sort(arr,l,lt-1);
        sort(arr,gt,r);

    }

//    /**
//     * 对arr[l,r]部分进行partition操作
//     * 返回P,使得arr[l,p-1] < arr[p] , arr[p+1,r] > arr[p]
//     * @param arr
//     * @param l
//     * @param r
//     * @return
//     */
//    private static int partition(Comparable[] arr ,int l,int r){
//        //优化：对于近乎有序的情况下，随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
//        swap(arr,l,(int) (Math.random()*(r-l+1)) + l);
//
//        Comparable v = arr[l];
//        int j=l; //arr[l+1,j] < v , arr[j+1,r] > v
//        for (int i = l+1; i <= r; i++) {
//            if(arr[i].compareTo(v) < 0){
//                j++;
//                swap(arr,j,i);
//            }
//        }
//        swap(arr,l,j);
//        return j;
//
//    }
//
//    /**
//     * 对于具有大量重复值的时候，使用单路排序会将算法退化到 O（n^2）,所以使用双路快排解决
//     * 双路快排处理的元素正好等于arr[p]的时候要注意
//     * @param arr
//     * @param l
//     * @param r
//     * @return 返回p, 使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
//     */
//    private static int partition2(Comparable[] arr ,int l,int r){
//        //优化：对于近乎有序的情况下，随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
//        swap(arr,l,(int) (Math.random()*(r-l+1)) + l);
//
//        Comparable v = arr[l];
//        //arr[l+1,i] < v , arr[j,r] > v
//        int i = l+1,j=r;
//        while(true){
//            while(i <= r && arr[i].compareTo(v) < 0){
//                i++;
//            }
//            while(j >= l+1 && arr[j].compareTo(v) > 0){
//                j--;
//            }
//            if(i > j){break;}
//            swap(arr,i,j);
//            i++;j--;
//        }
//        swap(arr,l,j);
//        return j;
//    }

    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10);
        SortTestHelper.testSort("Algorithm.sort.quickSort.QuickSort", arr);
    }

}
