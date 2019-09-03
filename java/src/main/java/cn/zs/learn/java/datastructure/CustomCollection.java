package cn.zs.learn.java.datastructure;

public interface CustomCollection<E> extends Iterable<E> {
    boolean isEmpty();
    int size();
    boolean add(E e);
    boolean remove(E e);
    boolean contain(E e);
    boolean addAll(CustomCollection<E> collection);
    boolean removeAll(CustomCollection<E> collection);
    boolean containAll(CustomCollection<E> collection);
    boolean retainAll(CustomCollection<E> collection);
    void removeAll();
    Object[] toArray();
    int hashCode();
    boolean equals(Object obj);
    String toString();
}
