package model.Statement;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.PrgState;
import model.Type.Type;
import model.Value.Value;

public class VarDeclStmt implements IStmt{
    String identifier;
    Type type;

    public VarDeclStmt(String identifier, Type type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.IsVarDef(identifier)){
            throw new MyException("Variable " + identifier + "is already declared!");
        }
        else{
            symTable.put(identifier, type.default_value());
            state.setSymTable(symTable);
            return null;
        }
    }

    @Override
    public IStmt deepCopy() {
        return new VarDeclStmt(identifier, type.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(identifier, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return String.format("%s %s", type.toString(), identifier);
    }
}
