package ca.bytetube._03_list.doubly;

import ca.bytetube._03_list.AbstractList;


public class LinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private LinkedList.Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node;
        if (index < size>> 1) {
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
        if (index == size){
            Node<E> oldLast = last;
            Node<E> newLast = new Node<>(element, oldLast, null);
            last = newLast;
            if (size == 0) first = newLast;
            else oldLast.next = newLast;

        }else {
            //普通和头位置
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> newNode = new Node<>(element, prevNode, nextNode);
            nextNode.prev = newNode;
            if (index == 0) first = newNode;
            else prevNode.next = newNode;
        }

        size++;

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
    public E remove(int index) {

        rangeCheck(index);
        //普通/头部/尾部/最后一个节点
        Node<E> node = node(index);
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        //头部
        if (index == 0) first = nextNode;

        else prevNode.next = nextNode;
        //尾部
        if (index == size - 1 ) last = prevNode;

        else nextNode.prev = prevNode;

        size--;
        return node.value;
    }

    @Override
    public int indexOf(E element) {

        if (element == null){
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.value == null) return i;
                node = node.next;
            }
        }
        else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.value)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
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

