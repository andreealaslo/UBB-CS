package view;

import controller.Controller;
import model.ADTs.CyclicBarrier.MyBarrierTable;
import model.ADTs.Dictionary.MyDictionary;
import model.ADTs.Heap.MyHeap;
import model.ADTs.List.MyList;
import model.ADTs.Stack.MyStack;
import model.Expression.*;
import model.PrgState;
import model.Statement.*;
import model.Type.IntType;
import model.Type.RefType;
import model.Type.StringType;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.StringValue;
import repository.IRepository;
import repository.Repository;
import view.command.ExitCommand;
import view.command.RunExampleCommand;
import Exception.MyException;

import java.io.IOException;

public class Interpreter {
    public static void main(String[] args) throws IOException, MyException {
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        IStmt program1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v",
                new ValueExp(new BoolValue(true))), new PrintStmt(new VarExp("v"))));
        try {
            program1.typecheck(new MyDictionary<>());
            PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyBarrierTable(), program1);
            IRepository repo1 = new Repository(prg1, "log1.txt");
            Controller controller1 = new Controller(repo1);
            menu.addCommand(new RunExampleCommand("1", program1.toString(), controller1));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        IStmt program2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)),
                        new ValueExp(new IntValue(5)), '*'), '+')), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"),
                        new ValueExp(new IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));
        try {
            program2.typecheck(new MyDictionary<>());
            PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyBarrierTable(),program2);
            IRepository repo2 = new Repository(prg2, "log2.txt");
            Controller controller2 = new Controller(repo2);
            menu.addCommand(new RunExampleCommand("2", program2.toString(), controller2));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        IStmt program3 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(3))), (new IfStmt(new RelationalExp(new VarExp("a"), new VarExp("b"), ">="), new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))))))));
        try {
            program3.typecheck(new MyDictionary<>());
            PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyBarrierTable(),program3);
            IRepository repo3 = new Repository(prg3, "log3.txt");
            Controller controller3 = new Controller(repo3);
            menu.addCommand(new RunExampleCommand("3", program3.toString(), controller3));
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

        IStmt program4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                new CompStmt(new OpenRFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")),
                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        try {
            program4.typecheck(new MyDictionary<>());
            PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyBarrierTable(),program4);
            IRepository repo4 = new Repository(prg4, "log4.txt");
            Controller controller4 = new Controller(repo4);
            menu.addCommand(new RunExampleCommand("4", program4.toString(), controller4));
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }

        IStmt program5 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '-')))),
                                new PrintStmt(new VarExp("v")))));
        try {
            program5.typecheck(new MyDictionary<>());
            PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(),new MyBarrierTable(), program5);
            IRepository repo5 = new Repository(prg5, "log5.txt");
            Controller controller5 = new Controller(repo5);
            menu.addCommand(new RunExampleCommand("5", program5.toString(), controller5));
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }

        IStmt program6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAllocationStmt("a", new VarExp("v")), new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(30))), new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))), new PrintStmt(new ArithExp(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))), new ValueExp(new IntValue(5)), '+'))))))));
        try{
            program6.typecheck(new MyDictionary<>());
            PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyBarrierTable(),program6);
            IRepository repo6 = new Repository(prg6, "log6.txt");
            Controller controller6 = new Controller(repo6);
            menu.addCommand(new RunExampleCommand("6", program6.toString(), controller6));
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }

        IStmt program7 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new HeapAllocationStmt("a", new ValueExp(new IntValue(22))),
                new CompStmt(new ForkStmt(new CompStmt(new HeapWritingStmt("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));
        try {
            program7.typecheck(new MyDictionary<>());
            PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyBarrierTable(),program7);
            IRepository repo7 = new Repository(prg7, "log7.txt");
            Controller controller7 = new Controller(repo7);
            menu.addCommand(new RunExampleCommand("7", program7.toString(), controller7));
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }
        menu.show();
    }
}