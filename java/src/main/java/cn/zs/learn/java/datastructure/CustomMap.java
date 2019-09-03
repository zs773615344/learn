package cn.zs.learn.java.datastructure;

public interface CustomMap<k,v> {
    int size();
    boolean isEmpty();
    boolean containKey(k key);
    boolean containValue(v value);
    v get(k key);
    void put(k key, v value);
    v remove(k key);
    void putAll(CustomMap<? extends k, ? extends v> m);
    CustomSet<k> keySet();
    CustomCollection<v> values();
    CustomSet<CustomMap.Entry<k,v>> entrySet();
    boolean equals(Object o);
    int hashCode();

    interface Entry<k,v> {
        k getKey();
        v getValue();
        v setValue(v value);
        boolean equals(Object o);
        int hashCode();
    }
}
