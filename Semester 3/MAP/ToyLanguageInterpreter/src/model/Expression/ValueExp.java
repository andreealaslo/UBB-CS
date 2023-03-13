package model.Expression;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Type.Type;
import model.Value.Value;

public class ValueExp implements Exp{
    private Value exp;

    public ValueExp(Value exp) {
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap<Integer, Value> hp)  {
        return exp;
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(exp.deepCopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return exp.getType();
    }

    @Override
    public String toString() {
        return this.exp.toString();
    }
}
