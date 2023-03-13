package model.Statement;

import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Stack.MyIStack;
import model.Expression.Exp;
import model.Expression.NotExpression;
import model.PrgState;
import model.Type.BoolType;
import model.Type.Type;
import Exception.MyException;

public class RepeatUntilStmt implements IStmt {
    IStmt statement;
    Exp expression;

    public RepeatUntilStmt(IStmt statement, Exp expression) {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getExeStack();
        IStmt repeat_until = new CompStmt(statement, new WhileStmt(new NotExpression(expression), statement));
        stack.push(repeat_until);
        state.setExeStack(stack);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatUntilStmt(statement.deepCopy(), expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = expression.typecheck(typeEnv);
        if (typeExp.equals(new BoolType())){
            statement.typecheck(typeEnv.clone_dict());
            return typeEnv;
        }
        else throw new MyException("The expression does not have type Bool!");

    }

    @Override
    public String toString() {
        return String.format("repeat %s until %s", statement, expression);
    }
}
