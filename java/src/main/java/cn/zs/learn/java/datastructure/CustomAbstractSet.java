package cn.zs.learn.java.datastructure;

import java.util.Iterator;
import java.util.Objects;

public abstract class CustomAbstractSet<E> extends CustomAbstractCollection<E> implements CustomSet<E>{
    protected CustomAbstractSet() {}

    @Override
    public boolean removeAll(CustomCollection<E> collection) {
        Objects.requireNonNull(collection);
        boolean modified = false;

        if (size() > collection.size()) {
            for (Iterator<E> i = collection.iterator(); i.hasNext(); )
                modified |= remove(i.next());
        } else {
            for (Iterator<E> i = iterator(); i.hasNext(); ) {
                if (collection.contain(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }

    @Override
    public int hashCode() {
        int h = 0;
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            h += (next == null ? 0 : next.hashCode());
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof CustomSet))
            return false;
        CustomCollection c = (CustomCollection) obj;
        if (c.size() != size())
            return false;
        try {
            return containAll(c);
        } catch (ClassCastException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
