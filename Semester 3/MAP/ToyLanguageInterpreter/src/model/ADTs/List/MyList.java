package model.ADTs.List;

import Exception.MyException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> out;

    public MyList() {
        out = new ArrayList<>();
    }

    public void add(T elem) {
        synchronized (this) {
            out.add(elem);
        }
    }

    @Override
    public List<T> getList() {
        synchronized (this) {
            return out;
        }
    }

    @Override
    public T pop() throws MyException {
        synchronized (this) {
            if (out.isEmpty())
                throw new MyException("The list is empty!");
            return this.out.remove(0);
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return this.out.isEmpty();
        }
    }

    @Override
    public String toString() {
        return out.toString();
    }
}
