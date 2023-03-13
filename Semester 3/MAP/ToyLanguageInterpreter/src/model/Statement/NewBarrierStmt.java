package model.Statement;


import javafx.util.Pair;
import Exception.MyException;
import model.ADTs.CyclicBarrier.MyIBarrierTable;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Expression.Exp;
import model.PrgState;

import model.Type.IntType;
import model.Type.Type;
import model.Value.IntValue;
import model.Value.Value;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStmt implements IStmt {
    String var;
    Exp expression;
    private static final Lock lock = new ReentrantLock();

    public NewBarrierStmt(String var, Exp expression) {
        this.var = var;
        this.expression = expression;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeapTable();
        MyIBarrierTable barrierTable = state.getBarrierTable();
        IntValue number = (IntValue) (expression.eval(symTable, heap));
        int nr = number.getVal();
        int freeAddress = barrierTable.getFreeAddress();
        barrierTable.put(freeAddress, new Pair<>(nr, new ArrayList<>()));
        if (symTable.IsVarDef(var))
            symTable.Update(var, new IntValue(freeAddress));
        else
            throw new MyException("Var is not defined in symbol table!");
        lock.unlock();
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (typeEnv.lookUp(var).equals(new IntType()))
            if (expression.typecheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("Expression does not have type int!");
        else
            throw new MyException("Variable does not have type int!");
    }

    @Override
    public IStmt deepCopy() {
        return new NewBarrierStmt(var, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("newBarrier(%s, %s)", var, expression);
    }
}