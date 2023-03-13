package model.Expression;


import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.BoolType;
import model.Type.Type;
import model.Value.BoolValue;
import model.Value.Value;
import Exception.MyException;

import java.util.Objects;

public class LogicExp implements Exp {
    private final Exp e1;
    private final Exp e2;
    private final String operator;

    public LogicExp(Exp e1, Exp e2, String operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(symTable, hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(symTable, hp);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean a1, a2;
                a1 = b1.isVal();
                a2 = b2.isVal();
                if (Objects.equals(this.operator, "and")) {
                    return new BoolValue(a1 && a2);
                }
                if (Objects.equals(this.operator, "or")) {
                    return new BoolValue(a1 || a2);
                }
            } else throw new MyException("second operand is not a boolean type");

        } else throw new MyException("first operand is not a boolean type");
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), operator);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not a boolean type!!");
        } else throw new MyException("First operand is not a boolean type!!");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + operator + " " + e2.toString() + "\n";
    }

}
