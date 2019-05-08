package Algorithm.sort;

import java.lang.reflect.Method;

public class SortTestHelper {
    /**
     * 生成一个无序数组
     * @param n 数组大小
     * @param rangeL 左边界（包含）
     * @param rangeR 右边界 （不包含）
     * @return
     */
    public static Integer[] generateRandomArray(int n,int rangeL,int rangeR){
        if(rangeL > rangeR){
            throw new IllegalArgumentException("rangeL need <= rangeR");
        }
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR-rangeL+1) + rangeL));
        }
        return arr;
    }

    /**
     * 生成一个近乎有序的数组
     * 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
     * swapTimes定义了数组的无序程度:
     * swapTimes == 0 时, 数组完全有序
     * swapTimes 越大, 数组越趋向于无序
     * @param n 数组大小
     * @param swapTimes 交换的次数
     * @return
     */
    public static Integer[] generateNearlyOrderedArray(int n,int swapTimes){
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }
        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            swap(arr,a,b);
        }
        return arr;
    }

    public static String printArray(Object[] a){
        StringBuffer s = new StringBuffer();
        s.append("Array[");
        for (Object i : a) {
            s.append(i+ ",");
        }
        s.append("]");
        return s.toString();
    }

    public static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     *判断arr数组是否有序
     * @param arr
     * @param judge false:从大到小排序，true：从小到代排序
     * @return
     */
    public static boolean isSorted(Comparable[] arr,boolean judge){
        if(judge){
            for (int i = 0; i < arr.length-1; i++) {
                if(arr[i].compareTo(arr[i+1]) > 0){
                    return false;
                }
            }
        }else{
            for (int i = 0; i < arr.length-1; i++) {
                if(arr[i].compareTo(arr[i+1]) < 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void testSort(String sortClassName,Comparable[] arr){
        try{
            //通过sortClassName获得排序函数的class对象
            Class sortClass = Class.forName(sortClassName);
            //通过排序方法的对象获得排序方法
            Method sortMethod = sortClass.getMethod("sort",new Class[]{Comparable[].class});
            //排序参数只有一个，是课比较数组arr
            Object[] params = new Object[]{arr};
            long startTime = System.nanoTime();
            //调用排序函数
            sortMethod.invoke(null,params);
            long endTime = System.nanoTime();

            if(!isSorted(arr,true)){
                throw new IllegalArgumentException("Sort faild");
            }

            System.out.println(sortClass.getSimpleName() + ":" + (endTime-startTime)/1000000000.0 + "s");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
