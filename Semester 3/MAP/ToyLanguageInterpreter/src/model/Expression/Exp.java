package model.Expression;

import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.Type;
import model.Value.Value;
import Exception.MyException;

public interface Exp {
    Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp) throws MyException;
    Exp deepCopy();

    Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
