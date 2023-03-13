package model.Expression;

import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.Type;
import model.Value.Value;
import Exception.MyException;

public class VarExp implements Exp{
    private String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp) throws MyException {
        return symTable.lookUp(id);
    }

    @Override
    public Exp deepCopy() {
        return new VarExp(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookUp(id);
    }

    @Override
    public String toString(){
        return id;
    }
}
