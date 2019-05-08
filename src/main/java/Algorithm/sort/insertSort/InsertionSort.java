package Algorithm.sort.insertSort;

import Algorithm.sort.SortTestHelper;

import static Algorithm.sort.SortTestHelper.swap;

public class InsertionSort {

    public static void sort(Comparable[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            //寻找元素arr.i合适的位置插入
            //方法一

//            for (int j = i; j > 0; j--) {
//                if(arr[j].compareTo(arr[j-1]) < 0){
//                    swap(arr,j,j-1);
//                }else{
//                    break;
//                }
//            }
            //方法二
//            for(int j=i;j>0 && arr[j].compareTo(arr[j-1]) < 0 ;j--)
//                swap(arr,j,j-1);
            //方法三
            Comparable e= arr[i];
            int j =i;
            for( ; j>0 && arr[j-1].compareTo(e) > 0 ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }

    /**
     * 对arr的[l,r]的区间使用插入排序
     * @param arr
     * @param l
     * @param r
     */
    public static void sort(Comparable[] arr,int l,int r){
        for (int i = l+1; i <= r; i++) {
            Comparable e = arr[i];
            int j = i;
            for(; j> l && arr[j-1].compareTo(e) > 0 ;j--){
                arr[j] = arr[j-1];
            }
            arr[j] = e;
        }
    }

    // 测试InsertionSort
    public static void main(String[] args) {

        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Algorithm.sort.insertSort.insertionSort", arr);

        return;
    }

}
