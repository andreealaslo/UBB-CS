package model.Statement;

import Exception.MyException;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.Expression.Exp;
import model.PrgState;
import model.Type.RefType;
import model.Type.Type;
import model.Value.RefValue;
import model.Value.Value;

public class HeapWritingStmt implements IStmt {
    String variable_name;
    Exp expression;

    public HeapWritingStmt(String variable_name, Exp expression) {
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
                RefValue refValue = (RefValue) value;
                if (heap.containsKey(refValue.getAddress())) {
                    Value evaluated = expression.eval(symTable, heap);
                    if (evaluated.getType().equals(refValue.getLocationType())) {
                        heap.update(refValue.getAddress(), evaluated);
                        state.setHeapTable(heap);
                    } else throw new MyException(String.format("%s not of %s", evaluated, refValue.getLocationType()));
                } else throw new MyException(String.format("The RefValue %s is not defined in the heap", value));
            } else throw new MyException(String.format("%s not of RefType", value));
        } else throw new MyException(String.format("%s not present in the symTable", variable_name));
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new HeapWritingStmt(variable_name, expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if (typeEnv.lookUp(variable_name).equals(new RefType(expression.typecheck(typeEnv))))
            return typeEnv;
        else
            throw new MyException("WriteHeap: right hand side and left hand side have different types.");
    }

    @Override
    public String toString() {
        return String.format("wH(%s, %s)", variable_name, expression);
    }
}
