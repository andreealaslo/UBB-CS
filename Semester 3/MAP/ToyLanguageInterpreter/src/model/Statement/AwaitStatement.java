package model.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Exception.MyException;
import javafx.util.Pair;
import model.ADTs.CyclicBarrier.MyIBarrierTable;
import model.ADTs.Dictionary.MyIDictionary;
import model.PrgState;
import model.Type.IntType;
import model.Type.Type;
import model.Value.IntValue;
import model.Value.Value;

public class AwaitStatement implements IStmt{
    String var;
    Lock lock = new ReentrantLock();

    public AwaitStatement(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        lock.lock();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIBarrierTable barrierTable = state.getBarrierTable();
        if (symTable.IsVarDef(var)) {
            IntValue value = (IntValue) symTable.lookUp(var);
            int foundIndex = value.getVal();
            if (barrierTable.containsKey(foundIndex)) {
                Pair<Integer, List<Integer>> foundBarrier = barrierTable.get(foundIndex);
                int NL = foundBarrier.getValue().size();
                int N1 = foundBarrier.getKey();
                ArrayList<Integer> list = (ArrayList<Integer>) foundBarrier.getValue();
                if (N1 > NL) {
                    if (list.contains(state.getId()))
                        state.getExeStack().push(this);
                    else {
                        list.add(state.getId());
                        barrierTable.update(foundIndex, new Pair<>(N1, list));
                        state.setBarrierTable(barrierTable);
                    }
                }
            } else {
                throw new MyException("index not in Barrier Table!");
            }
        } else {
            throw new MyException("var is not defined!");
        }
        lock.unlock();
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (typeEnv.lookUp(var).equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("Var does not have type int!");
    }

    @Override
    public IStmt deepCopy() {
        return new AwaitStatement(var);
    }

    @Override
    public String toString() {
        return String.format("await(%s)", var);
    }
}
