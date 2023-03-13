package model.Statement;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.Expression.Exp;
import model.PrgState;
import model.Type.StringType;
import model.Type.Type;
import model.Value.StringValue;
import model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt{
    Exp expression;

    public CloseRFileStmt(Exp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value value = expression.eval(state.getSymTable(), state.getHeapTable());
        if (!value.getType().equals(new StringType()))
            throw new MyException(String.format("%s does not evaluate to StringValue", expression));
        StringValue fileName = (StringValue) value;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.IsVarDef(fileName.getVal()))
            throw new MyException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.lookUp(fileName.getVal());
        try {
            br.close();
        } catch (IOException e) {
            throw new MyException(String.format("Unexpected error in closing %s", value));
        }
        fileTable.remove(fileName.getVal());
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFileStmt(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (expression.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("CloseReadFile requires a string expression.");
    }

    @Override
    public String toString() {
        return String.format("CloseReadFile(%s)", expression.toString());
    }
}
