package StackTest;

import List.JavaArray;

public class JavaArrayStack<E> implements Stack {

    private JavaArray<E> array;
    public JavaArrayStack(int capacity){
        array = new JavaArray<E>(capacity);
    }

    public JavaArrayStack(){
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

    public void push(Object o) {
        array.addLast((E) o);
    }


    public Object pop() {
        return array.removeLast();
    }

    public Object peek() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i).toString());
            if (i!=array.getSize()-1) {
                res.append(",");
            }
        }
        res.append("] Top");
        return res.toString();
    }

    public static void main(String[] args) {
        JavaArrayStack<Integer> stack = new JavaArrayStack<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
