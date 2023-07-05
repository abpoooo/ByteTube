package ca.bytetube._05_queue;

public interface List<E> {
    static final int DEFAULT_CAPACITY = 10;
    static final int ELEMENT_NOT_FOUND = -1;

//    E[] elements;

    public int size() ;

    //Is it empty
    public boolean isEmpty() ;

    // Clear all elements
    public void clear();

    // Add elements to the end
//    public void add(E element) ;

    // Add elements to the index position
    public void add(int index, E element) ; // 1.size = 0; index = 0; element = 10; no; 2. size = 2; index = 0; element...;ok  3. index = x-1; size = x;yes



    // Return the element corresponding to the index position
    public E get(int index);

    // Set the element at index position
    public E set(int index, E element);


    // Delete the element to the index position
    // 4 circumstances 1.index position, 2.start, 3. end, 4. the selected positions
    public E remove(int index);

    //Return the index of element
    public int indexOf(E element);//str text pattern manacher algorithm




    //contains a certain element
    public boolean contains(E element) ;



    //

    private void ensureCapacity(int capacity) {

    }
}
