package model.Expression;

import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.Type;
import model.Value.BoolValue;
import model.Value.Value;
import Exception.MyException;

public class NotExpression implements Exp {
    Exp expression;

    public NotExpression(Exp expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp) throws MyException {
        BoolValue value = (BoolValue) expression.eval(symTable, hp);
        if (!value.isVal())
            return new BoolValue(true);
        else return new BoolValue(false);
    }

    @Override
    public Exp deepCopy() {
        return new NotExpression(expression.deepCopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return expression.typecheck(typeEnv);
    }

    @Override
    public String toString() {
        return String.format("!(%s)", expression);
    }
}
