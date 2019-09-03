package cn.zs.learn.java.datastructure;

public abstract class CustomAbstractList<E> extends CustomAbstractCollection<E> implements CustomList<E> {
    protected CustomAbstractList() {}

    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }
}
