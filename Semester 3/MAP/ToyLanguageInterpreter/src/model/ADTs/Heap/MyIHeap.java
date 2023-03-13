package model.ADTs.Heap;
import model.Value.Value;
import Exception.MyException;
import java.util.HashMap;
import java.util.Set;
public interface MyIHeap<K, V> {

    int getFreeAddress();

    HashMap<K, V> getContent();

    void setContent(HashMap<K, V> newMap);

    int add(V value);

    void update(K position, V newValue) throws MyException;

    V get(K position) throws MyException;

    boolean containsKey(K position);

    void remove(K key) throws MyException;

    Set<K> keySet();

    boolean is_empty();

}

