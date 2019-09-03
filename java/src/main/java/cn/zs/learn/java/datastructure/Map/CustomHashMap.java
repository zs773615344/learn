package cn.zs.learn.java.datastructure.Map;


import cn.zs.learn.java.datastructure.*;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;


public class CustomHashMap<k,v> extends CustomAbstractMap<k,v> implements Serializable,Cloneable {
	static final int DEFAULT_INITIAL_CAPACITY = 1<<16;
	transient Node<k,v>[] table;
    transient CustomSet<Entry<k,v>> entrySet;
    transient int size;
    transient int modCount;

	public CustomHashMap() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
	}

    @Override
    public int size() {
        return size;
    }

    @Override
    public v get(k key) {
	    Node<k,v> node = getNode(hash(key),key);
        return node == null ? null : node.value;
    }

    final Node<k,v> getNode(int hash, k key){
        Node<k,v>[] tab; int n; Node<k,v> first,tmp;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (first = tab[hash % n]) != null) {
            if (first.hash == hash && (first.key == key || (key != null && key.equals(first.key))))
                return first;
            tmp = first.next;
            if (tmp != null) {
                do {
                    if (tmp.hash == hash && (tmp.key == key || (key != null && key.equals(tmp.key))))
                        return tmp;
                } while ((tmp = tmp.next) != null);
            }
        }
	    return null;
	}


    public void put(k key, v value) {
	    Node<k,v>[] tab; Node<k,v> p; int n, i;
        int hash = hash(key);
        // 修改map的容量，暂时不考虑
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = hash % n]) == null)
//        if ((p = tab[i = (n-1) & hash(key)]) == null)
            tab[i] = newNode(hash(key), key, value, null);
        else {
            if (p.hash == hash && (p.key == key || (key != null && key.equals(p.key))))
                p.setValue(value);
            else {
                Node<k,v> tmp = p;
                for (;;) {
                    if (tmp.next == null) {
                        tmp.next = newNode(hash, key, value, null);
                        break;
                    } else
                        tmp = tmp.next;
                }
            }
        }
        if (++size >= tab.length)
            resize();
	}

    @Override
    public v remove(k key) {
	    Node<k,v> e;
        return (e = removeNode(hash(key), key, null, false, true)) == null ?
                null : e.value;
    }

    public Node<k,v> removeNode(int hash,k key,v value,boolean matchValue, boolean movable) {
        Node<k,v>[] tab; Node<k,v> p; int n, index;
        if ((tab = table) != null && (n = tab.length) > 0 &&
                (p = tab[index = (n - 1) & hash]) != null) {
            Node<k,v> node = null, e; k k; v v;
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                node = p;
            else if ((e = p.next) != null) {
                    do {
                        if (e.hash == hash &&
                                ((k = e.key) == key ||
                                        (key != null && key.equals(k)))) {
                            node = e;
                            break;
                        }
                        p = e;
                    } while ((e = e.next) != null);
            }
            if (node != null && (!matchValue || (v = node.value) == value ||
                    (value != null && value.equals(v)))) {
                if (node == p)
                    tab[index] = node.next;
                else
                    p.next = node.next;
                ++modCount;
                --size;
                return node;
            }
        }
	    return null;
    }

    Node<k,v>[] resize() {
        Node<k,v>[] oldTab = table;
        int oldLength = oldTab.length;
        int newLength = oldLength * 2;
        table = new Node[newLength];
        for (int i = 0; i < oldLength; i ++)
            table[i] = oldTab[i];
	    return table;
	}
    Node<k,v> newNode(int hash, k key, v value, Node<k,v> next) {
	    return new Node<k,v>(hash, key, value, next);
    }

    @Override
    public void putAll(CustomMap<? extends k, ? extends v> m) {

    }

    @Override
    public CustomSet<k> keySet() {
        return (keySet == null) ? (keySet = new KetSet()) : keySet;
    }

    @Override
    public CustomCollection<v> values() {
        return values == null ? (values = new Values()) : values ;
    }

    @Override
    public CustomSet<Entry<k, v>> entrySet() {
        return entrySet == null ? (entrySet = new EntrySet()) : entrySet;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final class KetSet extends CustomAbstractSet<k> {

        @Override
        public int size() {
            return size;
        }

        @Override
        public Iterator<k> iterator() {
            return new KeyIterator();
        }

        @Override
        public boolean remove(k k) {
            return removeNode(hash(k), k, null, false, true) != null;
        }

        @Override
        public boolean contain(k k) {
            return containKey(k);
        }
    }

    final class Values extends CustomAbstractCollection<v> {

        @Override
        public int size() {
            return size;
        }

        @Override
        public Iterator<v> iterator() {
            return new ValueIterator();
        }

        @Override
        public boolean contain(v v) {
            return containValue(v);
        }
    }

    final class EntrySet extends CustomAbstractSet<CustomMap.Entry<k,v>> {

        @Override
        public int size() {
            return size;
        }

        @Override
        public Iterator<CustomMap.Entry<k, v>> iterator() {
            return new EntryIterator();
        }

        @Override
        public boolean remove(CustomMap.Entry<k, v> kvEntry) {
            k key = kvEntry.getKey();
            v value = kvEntry.getValue();
            return removeNode(hash(key), key, value, true, true) != null;
        }

        @Override
        public boolean contain(CustomMap.Entry<k, v> kvEntry) {
            k key = kvEntry.getKey();
            Node<k,v> node = getNode(hash(key), key);
            return node != null && node.equals(kvEntry);
        }
    }

    abstract class HashIterator {
        Node<k,v> next;        // next entry to return
        Node<k,v> current;     // current entry
        int expectedModCount;  // for fast-fail
        int index;             // current slot

        HashIterator() {
            expectedModCount = modCount;
            Node<k,v>[] t = table;
            current = next = null;
            index = 0;
            if (t != null && size > 0) { // advance to first entry
                do {} while (index < t.length && (next = t[index++]) == null);
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Node<k,v> nextNode() {
            Node<k,v>[] t;
            Node<k,v> e = next;
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (e == null)
                throw new NoSuchElementException();
            if ((next = (current = e).next) == null && (t = table) != null) {
                do {} while (index < t.length && (next = t[index++]) == null);
            }
            return e;
        }

        public final void remove() {
            Node<k,v> p = current;
            if (p == null)
                throw new IllegalStateException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            current = null;
            k key = p.key;
            removeNode(hash(key), key, null, false, false);
            expectedModCount = modCount;
        }
    }

    final class KeyIterator extends HashIterator
            implements Iterator<k> {
        public final k next() { return nextNode().key; }
    }

    final class ValueIterator extends HashIterator
            implements Iterator<v> {
        public final v next() { return nextNode().value; }
    }

    final class EntryIterator extends HashIterator
            implements Iterator<CustomMap.Entry<k,v>> {
        public final CustomMap.Entry<k,v> next() { return nextNode(); }
    }

    static class Node<k,v> implements CustomMap.Entry<k,v>{
        final int hash;
        final k key;
        v value;
        Node<k,v> next;

        public Node(int hash, k key, v value, Node<k,v> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final int hashcode() {
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 :value.hashCode());
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof CustomMap.Entry) {
                CustomMap.Entry<k,v> e = (CustomMap.Entry<k,v>)o;
                if ((this.key == e.getKey() | (e.getKey() != null & e.getKey().equals(this.key))) &
                        (this.value == e.getKey() | (e.getValue() != null & e.getValue() != null && e.getValue().equals(this.value))))
//                if (Objects.equals(e.getKey(), this.key) && Objects.equals(e.getValue(), this.value))
                    return true;
            }
            return false;
        }

        public String toString() {
            return key + "=" + value;
        }
        public final k getKey() {
            return key;
        }
        public final v getValue() {
            return value;
        }
        public v setValue(v newValue) {
            v oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    static class DNode<k,v> extends Node<k,v> {
        DNode<k,v> before,after;
        DNode(int hash, k key, v value, Node<k,v> next) {
            super(hash, key, value, next);
        }
    }
    
    static final class TreeNode<k,v> extends DNode<k,v> {
        TreeNode<k,v> parent;
        TreeNode<k,v> left;
        TreeNode<k,v> right;
        TreeNode<k,v> prev;
        boolean red;
        TreeNode(int hash, k key, v val, Node<k,v> next) {
            super(hash, key, val, next);
        }

    }
}