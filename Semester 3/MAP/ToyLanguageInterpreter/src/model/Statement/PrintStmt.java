package model.Statement;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.List.MyIList;
import model.Expression.Exp;
import model.PrgState;
import model.Type.Type;
import model.Value.Value;

public class PrintStmt implements IStmt{
    Exp expression;

    public PrintStmt(Exp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> output_list = state.getOut();
        output_list.add(expression.eval(state.getSymTable(), state.getHeapTable()));
        state.setOut(output_list);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return "print(" + expression.toString() + ")";
    }
}
