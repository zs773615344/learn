package cn.zs.learn.java.datastructure;

import java.util.Iterator;
import java.util.Objects;

public abstract class CustomAbstractCollection<E> implements CustomCollection<E> {
    protected CustomAbstractCollection() {}

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(E e) {
        Iterator<E> iterator = iterator();
        if (e == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    iterator.remove();
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (e.equals(iterator.next())) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contain(E e) {
        Iterator<E> iterator = iterator();
        if (e == null) {
            while (iterator.hasNext())
                if (iterator.next() == null)
                    return true;
        } else {
            while (iterator.hasNext())
                if (e.equals(iterator.next()))
                    return true;
        }
        return false;
    }

    @Override
    public boolean addAll(CustomCollection<E> collection) {
        boolean add = false;
        for (E e : collection)
            if (add(e))
                add = true;
        return add;
    }

    @Override
    public boolean removeAll(CustomCollection<E> collection) {
        if (collection == null)
            throw new NullPointerException();
        boolean remove = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext())
            if (collection.contain(iterator.next())) {
                iterator.remove();
                remove = true;
            }
        return remove;
    }

    @Override
    public boolean containAll(CustomCollection<E> collection) {
        for (E e : collection)
            if (!contain(e))
                return false;
        return true;
    }

    @Override
    public boolean retainAll(CustomCollection<E> collection) {
        Objects.requireNonNull(collection);
        boolean retain = false;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!collection.contain(iterator.next())) {
                iterator.remove();
                retain = true;
            }
        }
        return false;
    }

    @Override
    public void removeAll() {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        Iterator iterator = iterator();
        for (int i = 0; i < arr.length; i++) {
            if (iterator.hasNext())
                arr[i] = iterator.next();
        }
        return arr;
    }
}
