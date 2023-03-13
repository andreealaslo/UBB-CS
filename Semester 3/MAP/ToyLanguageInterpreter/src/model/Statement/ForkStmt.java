package model.Statement;
import Exception.MyException;
import model.ADTs.Dictionary.MyDictionary;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Stack.MyIStack;
import model.ADTs.Stack.MyStack;
import model.PrgState;
import model.Type.Type;
import model.Value.Value;

import java.util.Map;

public class ForkStmt implements IStmt{
    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> newStack = new MyStack<>();
        newStack.push(stmt);
        MyIDictionary<String, Value> newSymTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry: state.getSymTable().getContent().entrySet()){
            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
        }
        return new PrgState(newStack, newSymTable, state.getOut(), state.getFileTable(),  state.getHeapTable(),state.getBarrierTable());
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv.clone_dict());
        return typeEnv;
    }

    @Override
    public String toString() {
        return String.format("fork(%s)", stmt.toString());
    }
}
