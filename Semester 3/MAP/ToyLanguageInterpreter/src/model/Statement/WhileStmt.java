package model.Statement;

import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Stack.MyIStack;
import model.Expression.Exp;
import model.PrgState;
import model.Type.BoolType;
import model.Type.Type;
import model.Value.BoolValue;
import model.Value.Value;

public class WhileStmt implements IStmt {
    Exp expression;
    IStmt statement;

    public WhileStmt(Exp expression, IStmt statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value value = expression.eval(state.getSymTable(), state.getHeapTable());
        MyIStack<IStmt> stack = state.getExeStack();
        if (!value.getType().equals(new BoolType()))
            throw new MyException(String.format("%s is not of BoolType", value));
        BoolValue boolValue = (BoolValue) value;
        if (boolValue.isVal()) {
            stack.push(this);
            stack.push(statement);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = expression.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())){
            statement.typecheck(typeEnv.clone_dict());
            return typeEnv;
        }else throw new MyException("The condition on WHILE is not type bool!");
    }

    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }
}
