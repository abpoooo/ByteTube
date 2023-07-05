package ca.bytetube._02_dynamicarray;


import ca.bytetube._03_list.AbstractList;

public class ArrayList<E> extends AbstractList<E> {
    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements =  (E[]) new Object[capacity];
    }

    // Number of elements
//    public int size() {
//        return size;
//    }

    //Is it empty
//    public boolean isEmpty() {
//        return size == 0;
//    }

    // Clear all elements
//    public void clear() {
//        size = 0;
//    }

    // Add elements to the end
//    public void add(E element) {
//        add(size, element);
//    }

    // Add elements to the index position
    public void add(int index, E element) { // 1.size = 0; index = 0; element = 10; no; 2. size = 2; index = 0; element...;ok  3. index = x-1; size = x;yes
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;

        size++;
    }

    // Return the element corresponding to the index position
    public E get(int index){
        rangeCheck(index);
        return elements[index];
    }

    // Set the element at index position
    public E set(int index, E element){
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }


    // Delete the element to the index position
    // 4 circumstances 1.index position, 2.start, 3. end, 4. the selected positions
    public E remove(int index){
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index + 1; i < size ; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        return oldElement;
    }

    //Return the index of element
    public int indexOf(E element){ //str text pattern manacher algorithm
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }



    //contains a certain element
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }



    //

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // expand capacity
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) newElements[i] = elements[i];
        elements = newElements;
    }

//    private void outOfBounds(int index){
//        throw new IndexOutOfBoundsException("index: " + index + ", size :" + size);
//    }
//
//    private void rangeCheckForAdd(int index){
//        if (index < 0 || index > size) outOfBounds(index);
//    }
//    private void rangeCheck(int index){
//        if (index < 0 || index >= size) outOfBounds(index);
//    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayList{size= ").append( size).append("elements=[");
        for (int i = 0; i < size; i++) {
            if (i!=0)  sb.append(", ");
            sb.append(elements[i]);
        }
        sb.append("]");

        return sb.toString();
    }
}
