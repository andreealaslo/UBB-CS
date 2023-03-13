package model.Statement;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Stack.MyIStack;
import model.PrgState;
import model.Type.Type;

public class CompStmt implements IStmt{

    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState state){
        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return second.typecheck(first.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return String.format("(%s|%s)", first.toString(), second.toString());
    }
}
