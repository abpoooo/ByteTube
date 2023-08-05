package ca.bytetube._08_heap;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;


    private BinaryHeap(Comparator<E> comparator,E[] elements){
        //need deep copy 目前是浅copy 对于容器初始化一定要深copy for loop is deep copy
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int length = elements.length;
            length = length < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : length;
            for (int i = 0; i < length; i++) {
                this.elements[i] = elements[i];
            }
        }
        heapify();
    }
//


    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(E[] elements) {
        this.elements = elements;
    }

    public BinaryHeap(int initialCapacity) {
        initialCapacity = initialCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : initialCapacity;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }
    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);

        elements[size++] = element;
        siftUp(size - 1);
    }



    @Override
    public E remove() {
        E root = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);

        return root;
    }


    @Override
    public E set() {
        return null;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }



    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            root = element;
            size++;
        }
        else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void siftUp(int index) {
        E element = elements[index];


        while (index > 0){
            int parentIndex = (index - 1) >> 1; // shift right
            E parent = elements[parentIndex];
            //If node <= parent node, or node has not parent node
            //exit loop
            if (compare(element, parent) <= 0) break;
            // If node > parent
            //Cover
            elements[index] = parent;
            index = parentIndex;

        }
        elements[index] = element;

    }


    // 1, Cover the root node with the last node
    // 2. delete the last node
    // 3. loop the following
    // If node < max child node
    // swap with max child node
    // if node >= max child node, or node has no child node
    //exit the loop
    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        while (index < half) {
            //对于child node 有两种情况
            // 1. 只有左
            // 2. 有左右
            // 3.默认与左节点比较
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;
            //从左右孩子中找到较大值
//            if (elements[rightIndex] != null){
//
//            }
            if ( rightIndex < size&& compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            //拿较大值和parent节点的值进行比较
            if (compare(element, child) >= 0) break;

            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }


    private void heapify(){
        for (int i = (this.size >> 1) -1; i >= 0 ; i--) {
            siftDown(i);
        }
    }
    private void emptyCheck() {
        if (elements.length == 0 || elements == null) throw new RuntimeException(" heap is empty!");
    }

    private void elementNotNullCheck(E element){
        if (element == null) throw new IllegalArgumentException("element cannot be null");
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // expand capacity
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) newElements[i] = elements[i];
        elements = newElements;
    }

}
