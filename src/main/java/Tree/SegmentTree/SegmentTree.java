package Tree.SegmentTree;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree =(E[]) new Object[4* arr.length];
        buildSegmentTree(0,0, data.length-1);
    }

    /***
     * 在treeindex的位置创建表示区间【l，r】的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private  void buildSegmentTree(int treeIndex,int l,int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l+(r-l) / 2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal.");
        }
        return data[index];
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

    public E query(int queryL,int queryR){
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("index is illegal");
        }
        return query(0,0, data.length-1,queryL,queryR);
    }

    /**
     * 在以treeIndex为跟的线段树中【l，r】的范围里，搜索区间[queryL,queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int mid = l + (r-l) / 2;
        //treeIndex的节点分为[1,mid]和[mid+1,r]两部分
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid+1) {
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }else if(queryR <= mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }
        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
        return merger.merge(leftResult,rightResult);
    }

    /**
     * 将index的位置的值更新为e
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        data[index] = e;
        set(0,0, data.length-1,index,e);
    }
    //在以treeindex为根的线段树中更新值为index的e
    private void set(int treeIndex,int l,int r,int index,E e){
        if(l ==r){
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r-l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid +1){
            set(rightTreeIndex,mid+1,r,index,e);
        }else{
            set(leftTreeIndex,l,mid,index,e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
