package model.ADTs.Heap;
import Exception.MyException;
import model.Value.Value;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Set;

public class MyHeap<K, V> implements MyIHeap<K, V>{
    HashMap<K, V> heap;
    static Integer freeAddress=1;

    public MyHeap() {
        this.heap = new HashMap<>();
    }

    public int newAddress() {
        freeAddress += 1;
        while (freeAddress == 0 || heap.containsKey(freeAddress))
            freeAddress += 1;
        return freeAddress;
    }


    @Override
    public int getFreeAddress() {
        synchronized (this){
        return freeAddress;}
    }

    @Override
    public HashMap<K, V> getContent() {
        synchronized (this){
        return heap;}
    }

    @Override
    public void setContent(HashMap<K, V> newMap) {
        synchronized (this){
        this.heap = newMap;}
    }

    @Override
    public int add(V value) {
        synchronized (this){
        heap.put((K) freeAddress, value);
        Integer returnAddress = freeAddress;
        freeAddress = newAddress();
        return returnAddress;}
    }

    @Override
    public void update(K position, V newValue) throws MyException {
        synchronized (this){
        if (!heap.containsKey(position))
            throw new MyException (String.format("%d is not present in the heap", position));
        heap.put(position, newValue);
    }}

    @Override
    public V get(K position) throws MyException {
        synchronized (this){
        if (!heap.containsKey(position))
            throw new MyException (String.format("%d is not present in the heap", position));
        return this.heap.get(position);
    }}

    @Override
    public boolean containsKey(K position) {
        synchronized (this){
        return this.heap.containsKey(position);
    }}

    @Override
    public void remove(K key) throws MyException {
        synchronized (this){
        if(!containsKey(key))
            throw new MyException(key + " is not defined");
        freeAddress = (Integer)key;
        this.heap.remove(key);
    }}

    @Override
    public Set<K> keySet() {
        synchronized (this){
        return heap.keySet();
    }}

    @Override
    public boolean is_empty() {
        return heap.isEmpty();
    }

    @Override
    public String toString(){
        return heap.toString();
    }
}
