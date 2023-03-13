package model.ADTs.Dictionary;
import Exception.MyException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<K,V> {
    void put(K key, V value);
    boolean IsVarDef(K key);
    void Update (K key, V value)throws MyException;
    V lookUp(K key) throws MyException;
    Set<K> keySet();
    void remove(K val) throws MyException;
    Map<K,V> getContent();
    MyIDictionary<K, V> clone_dict();
    Collection<V> values();

}
