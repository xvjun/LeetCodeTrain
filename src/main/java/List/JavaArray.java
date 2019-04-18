package List;

public class JavaArray<E> {
    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity
     */
    public JavaArray(int capacity){ data = (E[])new Object[capacity];
        size=0;
    }

    /**
     * 无参数构造函数，默认容量10
     */
    public JavaArray(){ this(10);
    }

    /**
     * 获得size的大小
     * @return
     */
    public int getSize(){ return size;
    }
    /**
     * 获得Capacity的大小
     * @return
     */
    public int getCapacity(){ return data.length;
    }

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){ return size == 0;
    }



    /**
     * 获得指定位置的元素
     * @param index
     * @return
     */
    public E get(int index){ if(index <0 || index >= size){
            throw new IllegalArgumentException(String.format("Get faild,need index in [0,%d]",size));
        }
        return data[index];
    }

    public E getFirst(){
        return get(size-1);
    }

    public E getLast(){
        return get(0);
    }

    /**
     * 修改指定位置元素的值
     * @param index
     * @param e
     */
    public void set(int index,E e){ if(index <0 || index >= size){
            throw new IllegalArgumentException(String.format("Set faild,need index in [0,%d]",size));
        }
        data[index] = e;
    }

    /**
     * 指定位置添加元素
     * @param index
     * @param e
     */
    public void add(int index,E e){ if(size == data.length){
            //TODO... 判断并扩容
            resize(2*data.length);
        }
        if(index <0 || index > size){
            throw new IllegalArgumentException(String.format("Add faild,need index in [0,%d]",size));
        }
        for(int i =size -1;i>= index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 数组扩容
     * @param resize
     */
    private void resize(int resize){
        E[] newData = (E[])new Object[resize];
        for(int i=0;i<size;i++){
            newData[i]=data[i];
        }
        data = newData;
    }

    /**
     * 添加数据到尾部
     * @param e
     */
    public void addLast(E e){ add(size,e);
    }

    /**
     * 添加数据到头部
     * @param e
     */
    public void addFirst(E e){ add(0,e);
    }

    public
    @Override String toString(){
        StringBuilder res = new StringBuilder();
         res.append(String.format("Array: size=%d,capacity=%d\n",size,data.length));
         res.append("[");
         for(int i=0;i<size;i++){
             res.append(data[i].toString());
             if(i!=size-1){res.append(",");}
         }
         res.append("]");
         return res.toString();
    }

    /**
     * 判断是否包含该元素
     * @param e
     * @return
     */
    public boolean contains(E e){ for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否包含该元素，并返回第一个的下标
     * @param e
     * @return 存在则返回下标，不存在返回-1
     */
    public int find(E e){ for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定下标的元素，并返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){ if(index <0 || index >= size){
            throw new IllegalArgumentException(String.format("remove faild,need index in [0,%d]",size));
        }
        E ret = data[index];
        for(int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        data[size]=null;

        if(size == data.length/3){
            resize(data.length/2);
        }
        return ret;
    }

    public E removeFirst(){ return remove(0);
    }

    public E removeLast(){ return remove(size-1);
    }

    /**
     * 删除遍历到指定元素e，返回true或false
     * @param e
     */
    public boolean removeElement(E e){ for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * 删除指定元素e，返回true或false
     * @param e
     */
    public boolean removeAllElement(E e){ int sum = 0;
        for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                remove(i);
                sum++;
                i--;
            }
        }
        if(sum==0){return false;}
        else{return true;}
    }

    public static void main(String[] args) { JavaArray<Integer> arr = new JavaArray();
        for(int i=0;i<10;i++){
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(1,100);
        System.out.println(arr);
        arr.addFirst(-1);
        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
        arr.removeElement(4);
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
        arr.addLast(-1);
        arr.addLast(-1);
        System.out.println(arr);
        arr.removeAllElement(-1);
        System.out.println(arr);
        arr.remove(2);
        arr.remove(2);
        arr.remove(2);
        arr.remove(2);
        arr.remove(2);
        System.out.println(arr);
    }
}
