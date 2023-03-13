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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {
    Exp expression;

    public OpenRFileStmt(Exp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value result = expression.eval(state.getSymTable(), state.getHeapTable());
        if (result.getType().equals(new StringType())) {
            StringValue fileName = (StringValue) result;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.IsVarDef((fileName.getVal()))) {
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(fileName.getVal()));
                } catch (FileNotFoundException e) {
                    throw new MyException(String.format("%s could not be opened", fileName.getVal()));
                }
                fileTable.put(fileName.getVal(), br);
                state.setFileTable(fileTable);
            } else {
                throw new MyException(String.format("%s is already opened", fileName.getVal()));
            }
        } else {
            throw new MyException(String.format("%s does not evaluate to StringType", expression.toString()));
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFileStmt(expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (expression.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("OpenReadFile requires a string expression.");
    }

    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", expression.toString());
    }
}
