package model.Statement;
import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.Expression.Exp;
import model.PrgState;
import model.Type.IntType;
import model.Type.StringType;
import model.Type.Type;
import model.Value.IntValue;
import model.Value.StringValue;
import model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{
    Exp expression;
    String varName;

    public ReadFileStmt(Exp expression, String varName) {
        this.expression = expression;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (symTable.IsVarDef(varName)) {
            Value value = symTable.lookUp(varName);
            if (value.getType().equals(new IntType())) {
                value = expression.eval(symTable, state.getHeapTable());
                if (value.getType().equals(new StringType())) {
                    StringValue castValue = (StringValue) value;
                    if (fileTable.IsVarDef(castValue.getVal())) {
                        BufferedReader br = fileTable.lookUp(castValue.getVal());
                        try {
                            String line = br.readLine();
                            if (line == null)
                                line = "0";
                            symTable.put(varName, new IntValue(Integer.parseInt(line)));
                        } catch (IOException e) {
                            throw new MyException(String.format("Could not read from file %s", castValue));
                        }
                    } else {
                        throw new MyException(String.format("The file table does not contain %s", castValue));
                    }
                } else {
                    throw new MyException(String.format("%s does not evaluate to StringType", value));
                }
            } else {
                throw new MyException(String.format("%s is not of type IntType", value));
            }
        } else {
            throw new MyException(String.format("%s is not present in the symTable.", varName));
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(expression.deepCopy(), varName);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (expression.typecheck(typeEnv).equals(new StringType()))
            if (typeEnv.lookUp(varName).equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("ReadFile requires an int as its variable parameter.");
        else
            throw new MyException("ReadFile requires a string as es expression parameter.");
    }

    @Override
    public String toString() {
        return String.format("ReadFile(%s, %s)", expression.toString(), varName);
    }
}
