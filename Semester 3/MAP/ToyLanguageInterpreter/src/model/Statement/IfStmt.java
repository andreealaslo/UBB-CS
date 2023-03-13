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

public class IfStmt implements IStmt{
    Exp expression;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp expression, IStmt thenS, IStmt elseS) {
        this.expression = expression;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value condition = expression.eval(state.getSymTable(), state.getHeapTable());
        if(condition instanceof BoolValue boolCondition){
            IStmt statement;
            if(boolCondition.isVal()){
                statement = thenS;
            }
            else{
                statement = elseS;
            }
            MyIStack<IStmt> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return null;
        }
        else{
            throw new MyException("You have to provide a boolean expression in an if-statement!");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(expression.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = expression.typecheck(typeEnv);
        if(typeExp.equals(new BoolType())){
            thenS.typecheck(typeEnv.clone_dict());
            elseS.typecheck(typeEnv.clone_dict());
            return typeEnv;
        }
        else throw new MyException("The condition of IF has not the type bool!");
    }

    @Override
    public String toString(){
        return "( IF ("+ expression.toString()+") THEN (" +thenS.toString()+") ELSE ("+elseS.toString()+"))";}
}
