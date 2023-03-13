package model.Statement;

import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.ADTs.Stack.MyIStack;
import model.Expression.Exp;
import model.PrgState;
import model.Type.RefType;
import model.Type.Type;
import model.Value.RefValue;
import model.Value.Value;

public class HeapAllocationStmt implements IStmt {
    String variable_name;
    Exp expression;

    public HeapAllocationStmt(String variable_name, Exp expression) {
        this.variable_name = variable_name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeapTable();
        if (symTable.IsVarDef(variable_name)) {
            Value value = symTable.lookUp(variable_name);
            if (value.getType() instanceof RefType) {
                Value evaluated = expression.eval(symTable, heap);
                Type locationType = ((RefValue) value).getLocationType();
                if (locationType.equals(evaluated.getType())) {
                    int position = heap.add(evaluated);
                    symTable.put(variable_name, new RefValue(position, locationType));
                    state.setSymTable(symTable);
                    state.setHeapTable(heap);
                } else throw new MyException(String.format("%s not of %s", variable_name, evaluated.getType()));
            } else {
                throw new MyException(String.format("%s in not of RefType", variable_name));
            }
        } else {
            throw new MyException(String.format("%s not in symTable", variable_name));
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocationStmt(variable_name, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(variable_name);
        Type typeExp = expression.typecheck(typeEnv);
        if (typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else throw new MyException("NEW stmt: right hand side and left hand side have different types!!");
    }

    @Override
    public String toString() {
        return String.format("New(%s, %s)", variable_name, expression);
    }
}
