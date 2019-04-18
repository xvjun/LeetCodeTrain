package Queue;

import List.JavaArray;

public class ArrayQueue<E> implements Queue<E>{

    private JavaArray<E> array;

    public ArrayQueue(int capacity){
        array = new JavaArray<E>(capacity);
    }
    public ArrayQueue(){
        array = new JavaArray<E>();
    }

    public int getSize() {
        return array.getSize();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public void enqueue(E o) {
        array.addLast(o);
    }

    public E dequeue() {
        return array.removeFirst();
    }

    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i).toString());
            if (i!=array.getSize()-1) {
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 ==2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
