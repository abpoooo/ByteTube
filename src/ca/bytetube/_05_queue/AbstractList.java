package ca.bytetube._05_queue;

public abstract class AbstractList<E> implements List<E> {
    protected int size;

    private E[] elements;

    public int size() {
        return size;
    }

    //Is it empty
    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
    }

    // Add elements to the index position
    public void add(E element) {
        add(size, element);
    }

    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + ", size :" + size);
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) outOfBounds(index);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) outOfBounds(index);
    }
}
