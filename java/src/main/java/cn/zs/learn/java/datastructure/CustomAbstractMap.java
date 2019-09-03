package cn.zs.learn.java.datastructure;

import java.util.Iterator;

public abstract class CustomAbstractMap<k,v>  implements CustomMap<k,v> {

    protected transient CustomSet<k> keySet;
    protected transient CustomCollection<v> values;

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containValue(v value) {
        Iterator<Entry<k,v>> i = entrySet().iterator();
        if (value==null) {
            while (i.hasNext()) {
                Entry<k,v> e = i.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<k,v> e = i.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean containKey(k key) {
        Iterator<Entry<k,v>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry<k,v> e = i.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<k,v> e = i.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public v remove(k key) {
        Iterator<Entry<k,v>> i = entrySet().iterator();
        CustomMap.Entry<k,v> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && i.hasNext()) {
                Entry<k,v> e = i.next();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && i.hasNext()) {
                Entry<k,v> e = i.next();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }

        v oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            i.remove();
        }
        return oldValue;
    }

    @Override
    public void put(k key, v value) {
        throw new UnsupportedOperationException();
    }
}
