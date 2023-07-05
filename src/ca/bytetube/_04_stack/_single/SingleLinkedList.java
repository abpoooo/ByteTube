package ca.bytetube._04_stack._single;

import ca.bytetube._03_list.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {

    Node<E> first = null;

    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        //头位置， 第一个节点
        if (index == 0) {
            first = new Node<>(element, first);
        }
        else {
            //普通位置， 尾部
            Node<E> preNode = node(index - 1);
            /**
             * "=" 2 functions
             * 1. give value int i = 10; from right to left
             * 2.指向
             * */

            preNode.next = new Node<>(element, preNode.next);
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
        Node<E> removed = first;
            if (index == 0){
                //头位置， 最后一个节点
                first = first.next;
            }
            else {
                //普通位置， 尾部
                Node<E> preNode = node(index - 1); //0
                removed = preNode.next; // 1
                preNode.next = removed.next; // 0 -> 2 or final null
            }
        size--;
        return removed.value;
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


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("SingleLinkedList{size= ").append( size).append("elements=[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i!=0)  sb.append(", ");

            sb.append(node.value);

            node = node.next;
        }
        sb.append("]");

        return sb.toString();
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next){
            this.value = value;
            this.next = next;
        }
    }

}
