package Algorithm.sort.quickSort;

import Algorithm.sort.SortTestHelper;
import Algorithm.sort.insertSort.InsertionSort;

import static Algorithm.sort.SortTestHelper.swap;

public class QuickSort {

    //当需要排序的数组大小小于minsize时（包括递归调用时），使用插入排序，能节省时间
    private static final int minSize = 15;

    private QuickSort(){}

    public static void sort(Comparable[] arr){
        int n = arr.length;
        sort(arr,0,n-1);
    }

    /**
     * 递归使用快速排序，对arr[l,r]的范围进行排序
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

        int p = partition2(arr,l,r);
        sort(arr,l,p-1);
        sort(arr,p+1,r);

    }

    /**
     * 对arr[l,r]部分进行partition操作
     * 返回P,使得arr[l,p-1] < arr[p] , arr[p+1,r] > arr[p]
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition(Comparable[] arr ,int l,int r){
        //优化：对于近乎有序的情况下，随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr,l,(int) (Math.random()*(r-l+1)) + l);

        Comparable v = arr[l];
        int j=l; //arr[l+1,j] < v , arr[j+1,r] > v
        for (int i = l+1; i <= r; i++) {
            if(arr[i].compareTo(v) < 0){
                j++;
                swap(arr,j,i);
            }
        }
        swap(arr,l,j);
        return j;

    }

    /**
     * 对于具有大量重复值的时候，使用单路排序会将算法退化到 O（n^2）,所以使用双路快排解决
     * 双路快排处理的元素正好等于arr[p]的时候要注意
     * @param arr
     * @param l
     * @param r
     * @return 返回p, 使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
     */
    private static int partition2(Comparable[] arr ,int l,int r){
        //优化：对于近乎有序的情况下，随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr,l,(int) (Math.random()*(r-l+1)) + l);

        Comparable v = arr[l];
        //arr[l+1,i] < v , arr[j,r] > v
        int i = l+1,j=r;
        while(true){
            while(i <= r && arr[i].compareTo(v) < 0){
                i++;
            }
            while(j >= l+1 && arr[j].compareTo(v) > 0){
                j--;
            }
            if(i > j){break;}
            swap(arr,i,j);
            i++;j--;
        }
        swap(arr,l,j);
        return j;
    }

    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10);
        SortTestHelper.testSort("Algorithm.sort.quickSort.QuickSort", arr);
    }

}
