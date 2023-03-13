package model.Statement;

import model.ADTs.Dictionary.MyDictionary;
import model.ADTs.Dictionary.MyIDictionary;
import model.PrgState;
import Exception.MyException;
import model.Type.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    IStmt deepCopy();

    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv)  throws MyException;


}
