package ca.bytetube._08_heap;

public interface Heap<E> {


    int size() ;

    boolean isEmpty();

    void clear();

    void add(E element);

    E remove();

    E set();

    E get();

    E replace(E elements);


}
