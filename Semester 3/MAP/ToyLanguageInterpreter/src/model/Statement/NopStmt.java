package model.Statement;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.PrgState;
import model.Type.Type;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "NopStatement";
    }
}
