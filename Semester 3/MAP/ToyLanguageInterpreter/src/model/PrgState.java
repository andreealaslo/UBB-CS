package model;

import Exception.MyException;
import model.ADTs.CyclicBarrier.MyIBarrierTable;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyIHeap;
import model.ADTs.List.MyIList;
import model.ADTs.Stack.MyIStack;
import model.Statement.IStmt;
import model.Value.Value;

import java.io.BufferedReader;
import java.util.List;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIDictionary<String, BufferedReader> fileTable;
    MyIList<Value> out;
    MyIHeap<Integer, Value> heapTable;

    MyIBarrierTable barrierTable;
    IStmt originalProgram;
    private int id;
    private static int lastId = 0;


    public PrgState(MyIStack<IStmt> exeS, MyIDictionary<String, Value> symT, MyIList<Value> output, MyIDictionary<String, BufferedReader> fileT, MyIHeap<Integer, Value> heapT,MyIBarrierTable barrierT,  IStmt prg) {
        exeStack = exeS;
        symTable = symT;
        out = output;
        fileTable = fileT;
        heapTable = heapT;
        barrierTable = barrierT;
        originalProgram = prg.deepCopy();
        exeStack.push(prg);
        this.id = setId();
    }

    public PrgState(MyIStack<IStmt> exeS, MyIDictionary<String, Value> symT, MyIList<Value> output, MyIDictionary<String, BufferedReader> fileT, MyIHeap<Integer, Value> heapT, MyIBarrierTable barrierT) {
        exeStack = exeS;
        symTable = symT;
        out = output;
        fileTable = fileT;
        heapTable = heapT;
        barrierTable = barrierT;
        this.id = setId();
    }


    public synchronized int setId() {
        lastId++;
        return lastId;
    }
    public int getId() {
        return this.id;
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIBarrierTable getBarrierTable() {
        return barrierTable;
    }

    public void setBarrierTable(MyIBarrierTable barrierTable) {
        this.barrierTable = barrierTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public boolean isNotComplete() {
        return exeStack.isEmpty();
    }

    public MyIHeap<Integer, Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(MyIHeap<Integer, Value> heapTable) {
        this.heapTable = heapTable;
    }

    public PrgState oneStep() throws MyException {
        if (exeStack.isEmpty()) throw new MyException("Stack is empty!!");
        IStmt currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStmt> stack = exeStack.getReverse();
        for (IStmt statement : stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws MyException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key : symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (Value elem : out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key : fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }

    public String heapTableToString() throws MyException {
        StringBuilder heapTableStringBuilder = new StringBuilder();
        for (int key : heapTable.keySet()) {
            heapTableStringBuilder.append(String.format("%s -> %s\n", key, heapTable.get(key).toString()));
        }
        return heapTableStringBuilder.toString();
    }
    public String barrierTableToString() throws MyException {
        StringBuilder barrierTableStringBuilder = new StringBuilder();
        for (int key: barrierTable.getBarrierTable().keySet()) {
            barrierTableStringBuilder.append(String.format("%d -> (%d, %s)\n", key, barrierTable.get(key).getKey(), barrierTable.get(key).getValue()));
        }
        return barrierTableStringBuilder.toString();
    }

    public String toString() {
        return "Id: " + id  + "\n" +
                "Exe Stack: " + exeStack.getReverse() + "\n" +
                "Sym Table: " + symTable.toString() + "\n" +
                "Output List: " + out.toString() + "\n" +
                "File Table: " + fileTable.toString() + "\n" +
                "Heap Table:" + heapTable.toString() + "\n"+
                "Barrier Table:" + barrierTable.toString() + "\n";
    }

    public String programStateToString() throws MyException {
        return "Id: " + id + "\n"+
                "Execution stack: \n" + exeStackToString() +
                "Symbol table: \n" + symTableToString() +
                "Output list: \n" + outToString() +
                "File table:\n" + fileTableToString() +
                "Heap Table:\n" + heapTableToString() +
                "Barrier Table:\n" + barrierTableToString() + "\n";
    }
}
