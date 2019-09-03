package cn.zs.learn.java.datastructure;

public interface CustomList<E> extends CustomCollection<E>{
    E get(int index);
    void set(int index, E e);
    void add(int index, E e);
    E remove(int index);
    int indexOf(E e);
    int lastIndexOf(E e);
}
