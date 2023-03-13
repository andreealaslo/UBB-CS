package model.Expression;

import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.IntType;
import model.Type.Type;
import model.Value.IntValue;
import model.Value.Value;
import Exception.MyException;

public class ArithExp implements Exp {
    private Exp e1;
    private Exp e2;
    private char operator;

    public ArithExp(Exp e1, Exp e2, char operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(symTable, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (this.operator == '+') return new IntValue(n1 + n2);
                else if (this.operator == '-') return new IntValue(n1 - n2);
                else if (this.operator == '*') return new IntValue(n1 * n2);
                else if (this.operator == '/')
                    if (n2 == 0) throw new MyException("Division by zero");
                    else return new IntValue(n1 / n2);
            } else throw new MyException("second operand is not integer");
        } else throw new MyException("first operand is not an integer");

        return null;
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(e1.deepCopy(), e2.deepCopy(), operator);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                    return new IntType();
            } else throw new MyException("Second operand is not an integer!!");
        }else throw new MyException("First operand is not an integer!!");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + operator + " " + e2.toString();
    }

}