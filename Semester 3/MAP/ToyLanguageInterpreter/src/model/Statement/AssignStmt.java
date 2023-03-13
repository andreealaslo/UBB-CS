package model.Statement;

import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Stack.MyIStack;
import model.Expression.Exp;
import model.PrgState;
import Exception.MyException;
import model.Type.Type;
import model.Value.Value;

public class AssignStmt implements IStmt{
    String identifier;
    Exp expression;

    public AssignStmt(String identifier, Exp expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.IsVarDef(identifier)){
            Value val = expression.eval(symTable, state.getHeapTable());
            Type typeIdentifier = (symTable.lookUp(identifier)).getType();
            if(val.getType().equals(typeIdentifier))
                symTable.Update(identifier,val);
            else throw new MyException("Type of " + identifier + " and type of the expression to assign " +
                    "does not match!");
        }
        else throw new MyException("The variable " + identifier + " you try to use is not " +
                "declared!");
        state.setSymTable(symTable);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(identifier, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(identifier);
        Type typeExp = expression.typecheck(typeEnv);
        if (typeVar.equals(typeExp)){
            return typeEnv;
        }else throw new MyException("Assignment: right hand side and left hand side have different types!");
    }

    @Override
    public String toString(){
        return identifier + "=" + expression.toString();
    }
}
