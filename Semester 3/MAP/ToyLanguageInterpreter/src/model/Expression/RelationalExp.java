package model.Expression;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.BoolType;
import model.Type.IntType;
import model.Type.Type;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.Value;

import java.util.Objects;

public class RelationalExp implements Exp{
    private final Exp e1;
    private final Exp e2;
    private final String operator;

    public RelationalExp(Exp e1, Exp e2, String operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1,v2;
        v1 = e1.eval(symTable, hp);
        if (v1.getType().equals(new IntType())){
            v2 = e2.eval(symTable, hp);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int value1, value2;
                value1 = i1.getVal();
                value2 = i2.getVal();
                if (Objects.equals(this.operator, "<"))
                    return new BoolValue(value1 < value2);
                else if (Objects.equals(this.operator, ">"))
                    return new BoolValue(value1 > value2);
                else if (Objects.equals(this.operator, "=="))
                    return new BoolValue(value1 == value2);
                else if (Objects.equals(this.operator, "<="))
                    return new BoolValue(value1 <= value2);
                else if (Objects.equals(this.operator, ">="))
                    return new BoolValue(value1 >= value2);
                else if (Objects.equals(this.operator, "!="))
                    return new BoolValue(value1 != value2);
            }else throw new MyException("second operand is not integer");
        }else throw new MyException("first operand is not an integer");
        return null;
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(e1.deepCopy(),e2.deepCopy(),operator);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else throw new MyException("Second operand is not an integer!!");
        }else throw new MyException("First operand is not an integer!!");
    }

    @Override
    public String toString(){
        return e1.toString() + " " + operator + " " + e2.toString();
    }
}
