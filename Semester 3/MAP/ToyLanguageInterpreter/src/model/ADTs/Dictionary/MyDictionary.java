package model.ADTs.Dictionary;

import Exception.MyException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private Map<K, V> map;

    public MyDictionary() {
        map = new HashMap<K, V>();
    }

    public void put(K key, V value) {
        synchronized (this) {
            map.put(key, value);
        }
    }

    @Override
    public boolean IsVarDef(K key) {
        synchronized (this) {
            return this.map.containsKey(key);
        }
    }

    /*is syntax to implement block-level synchronization.
    It means that on this object only and only one thread can execute the enclosed block at one time.*/
    public void Update(K key, V value) throws MyException {
        synchronized (this) {
            if (!IsVarDef(key))
                throw new MyException(key + " is not found!");
            map.put(key, value);
        }
    }


    public V lookUp(K key) throws MyException {
        synchronized (this) {
            if (!IsVarDef(key))
                throw new MyException(key + " is not found!");
            return map.get(key);
        }
    }

    @Override
    public Set<K> keySet() {
        synchronized (this) {
            return map.keySet();
        }
    }

    @Override
    public void remove(K val) throws MyException {
        synchronized (this) {
            if (!IsVarDef(val))
                throw new MyException(val + " is not defined.");
            this.map.remove(val);
        }
    }

    @Override
    public Map<K, V> getContent() {
        synchronized (this) {
            return map;
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public MyIDictionary<K, V> clone_dict() {
        MyIDictionary<K, V> di = new MyDictionary<>();
        for (K key : this.keySet())
            di.put(key, map.get(key));
        return di;
    }

    @Override
    public Collection<V> values() {
        synchronized (this) {
            return this.map.values();
        }
    }

}
