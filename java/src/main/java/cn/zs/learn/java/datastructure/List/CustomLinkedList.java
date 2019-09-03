package cn.zs.learn.java.datastructure.List;

import cn.zs.learn.java.datastructure.*;

import java.io.Serializable;

public abstract class CustomLinkedList<E> extends CustomAbstractList<E> implements Serializable {

    transient int size = 0;
    transient DNode<E> first;
    transient DNode<E> last;

    transient Node<E> head;
    public CustomLinkedList() {
        head = new Node<E>();
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        Node() {
            this(null,null);
        }
    }

    private static class DNode<E> {
        E item;
        DNode<E> next;

        DNode<E> prev;
        DNode(DNode<E> prev, E item, DNode<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

    }

}
