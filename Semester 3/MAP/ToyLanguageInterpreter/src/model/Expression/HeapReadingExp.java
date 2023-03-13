package model.Expression;

import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.RefType;
import model.Type.Type;
import model.Value.RefValue;
import model.Value.Value;

public class HeapReadingExp implements Exp {
    Exp expression;

    public HeapReadingExp(Exp expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp) throws MyException {
        Value evaluated = expression.eval(symTable, hp);
        if (evaluated instanceof RefValue) {
            RefValue refvalue = (RefValue) evaluated;
            if (hp.containsKey(refvalue.getAddress())) {
                return hp.get(refvalue.getAddress());
            }
            throw new MyException("The address is not on the heap");
        }
        throw new MyException(String.format("%s is not a RefValue", evaluated));

    }

    @Override
    public Exp deepCopy() {
        return new HeapReadingExp(expression.deepCopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = expression.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else throw new MyException("the rH argument is not a Ref Type!!");
    }

    @Override
    public String toString() {
        return String.format("rH(%s)", expression);
    }
}
