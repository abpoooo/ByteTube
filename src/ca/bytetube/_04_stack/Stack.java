package ca.bytetube._04_stack;

import ca.bytetube._02_dynamicarray.ArrayList;

public class Stack<E> {
    private ArrayList<E> list = new ArrayList<>();

    public void clear(){
        list.clear();
    }

    public int size(){
        return list.size();
    }

    public void push(E element){
        list.add(element);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public E pop(){
        return list.remove(list.size() - 1);
    }

    public E top(){
        return list.get(list.size() - 1);
    }

}
