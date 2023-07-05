package ca.bytetube._05_queue.doubly;

import ca.bytetube._03_list.AbstractList;


public class DoublyCircularLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    private Node<E> current;

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node;
        if (index < size >> 1) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node;
    }


    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 尾部插入第一个节点
        if (index == size) {
            Node<E> oldLast = last;
            Node<E> newLast = new Node<>(element, oldLast, first);
            last = newLast;
            if (size == 0) {
                first = newLast;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = newLast;
                first.prev = last;
            }

        } else {
            //普通和头位置
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> newNode = new Node<>(element, prevNode, nextNode);
            newNode.prev = newNode;
            prevNode.next = newNode;
            if (index == 0) first = newNode;
        }

        size++;

    }

    private E remove(Node<E> node){
        if (size == 1){
            first = null;
            last = null;
        }
        //普通/头部/尾部
        else {
            Node<E> prevNode = current.prev;
            Node<E> nextNode = current.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            //头部
            if (current == first) first = nextNode;
            //尾部
            if (current == last) last = prevNode;
        }
        size--;
        return current.value;
    }

    //todo
    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);

//        if (size == 1){
//            first = null;
//            last = null;
//        }
//        //普通/头部/尾部
//        else {
//            Node<E> prevNode = node.prev;
//            Node<E> nextNode = node.next;
//            prevNode.next = nextNode;
//            nextNode.prev = prevNode;
//            //头部
//            if (index == 0) first = nextNode;
//            //尾部
//            if (index == size - 1) last = prevNode;
//        }
//        size--;
        return node.value;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return node(index).value;
    }

    // need review
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> oldNode = node(index);
        E oldElement = node(index).value;
        oldNode.value = element;
        return oldElement;
    }


    @Override
    public int indexOf(E element) {

        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.value == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.value)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }


    //todo
    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.value;
    }

    public E remove(){
        if (current == null) return null;
        Node<E> next = current.next;
        E val = remove(current);
        if (size == 0) current = null;
        current = next;
        return val;
    }


    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value, Node<E> next, Node<E> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

    }
}

