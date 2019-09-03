package cn.zs.learn.java.datastructure.List;

import cn.zs.learn.java.datastructure.*;

/**
 * 与ArrayList类似，但线程安全
*/
public abstract class CustomVector<E> extends CustomAbstractList<E> {
    Object[] element;
    int size;
}
