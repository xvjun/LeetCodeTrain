package Heap;

import List.JavaArray;

public class MaxHeap<E extends Comparable<E>> {

    private JavaArray<E> data;
    public MaxHeap(int capacity ){
        data = new JavaArray<>(capacity);
    }

    public MaxHeap(){
        data = new JavaArray<>();
    }

    public MaxHeap(E[] arr){
        data = new JavaArray<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }
    //返回index的父节点的下标
    private int parent(int index){
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent");
        }
        return (index-1) / 2;
    }
    //返回index的左孩子下标
    private int leftChild(int index){
        return index * 2 +1;
    }
    //返回index的右孩子的下标
    private int rightChild(int index){
        return index * 2 +2;
    }

    /**
     * 向对中添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int k){
        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

     /**
     * 查看堆中最大的元素
     * @return
     */
    public E findMax(){
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("can not findMax when heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大的元素
     * @return
     */
    public E extractMax(){
        E ret = findMax();
        data.swap(0,data.getSize() -1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){
        while(leftChild(k) < data.getSize()){
            int j = leftChild(k);
            if(j + 1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) > 0){
                j = rightChild(k);
            }
            if(data.get(k).compareTo(data.get(j)) >= 0){break;}
            data.swap(k,j);
            k=j;
        }
    }

    /**
     * 取出堆顶元素，并放入一个元素，并重排堆
     * @param e
     * @return
     */
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
