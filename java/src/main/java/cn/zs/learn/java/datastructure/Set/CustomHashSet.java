package cn.zs.learn.java.datastructure.Set;

import cn.zs.learn.java.datastructure.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet<E> extends CustomAbstractSet<E> {

    private int size;
    private Entry<E>[] element;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new SetIterator();
    }

    final class Entry<E> {
        private int hash;
        private E key;
        private Entry next;

        public Entry(int hash, E key, Entry next) {
            this.hash = hash;
            this.key = key;
            this.next = next;
        }

    }

    final class SetIterator implements Iterator {
        Entry<E> next;
        Entry<E> current;
        int index;

        SetIterator() {
            current = next = null;
            index = 0;
            if (element != null && size > 0 )
                do {
                } while (index < element.length && (next = element[index++]) == null);
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {
            Entry<E> tmp = next ;
            if (tmp == null)
                throw new NoSuchElementException();
            if ((next = (current = tmp).next) == null && element != null)
                do {
                } while (index < element.length && (next = element[index++]) == null);
            return tmp;
        }

        @Override
        public void remove() {
            Entry<E> p = current;
            current = null;
            CustomHashSet.this.remove(p.key);
        }
    }
}
