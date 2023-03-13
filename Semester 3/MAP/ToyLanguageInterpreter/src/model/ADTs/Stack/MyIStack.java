package model.ADTs.Stack;
import java.util.List;
import Exception.MyException;

public interface MyIStack<T> {
    boolean isEmpty();
    void push(T elem);
    T pop() throws MyException;
    List<T> getReverse();
}
