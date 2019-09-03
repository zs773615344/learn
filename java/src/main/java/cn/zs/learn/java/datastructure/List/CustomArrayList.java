package cn.zs.learn.java.datastructure.List;

import cn.zs.learn.java.datastructure.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CustomArrayList<E> extends CustomAbstractList<E> implements Serializable {
    private transient Object[] element;
    private transient int size;

    public CustomArrayList(int size) {
        if (size > 0) {
            this.element = new Object[size];
        } else if (size == 0) {
            this.element = new Object[]{};
        } else {
            throw new IllegalArgumentException();
        }
    }

    public CustomArrayList() {
        this(0);
    }

    private class ArrayListIterator implements Iterator<E> {
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            cursor = i + 1;
            return (E) element[i];
        }

        @Override
        public void remove() {
            CustomArrayList.this.remove(cursor);
        }
    }

}
