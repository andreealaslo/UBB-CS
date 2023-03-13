package model.ADTs.List;
import Exception.MyException;
import model.Value.Value;

import java.util.List;

public interface MyIList<T> {
    void add(T elem);
    List<T> getList();

    T pop() throws MyException;

    boolean isEmpty();
}
