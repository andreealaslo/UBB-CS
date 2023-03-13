package view;
import controller.Controller;
import model.ADTs.CyclicBarrier.MyBarrierTable;
import model.ADTs.CyclicBarrier.MyIBarrierTable;
import model.ADTs.Dictionary.MyDictionary;
import model.ADTs.Dictionary.MyIDictionary;
import model.ADTs.Heap.MyHeap;
import model.ADTs.Heap.MyIHeap;
import model.ADTs.List.MyIList;
import model.ADTs.List.MyList;
import model.ADTs.Stack.MyIStack;
import model.ADTs.Stack.MyStack;
import model.Expression.ArithExp;
import model.Expression.ValueExp;
import model.Expression.VarExp;
import model.PrgState;
import model.Statement.*;
import Exception.MyException;
import model.Type.BoolType;
import model.Type.IntType;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.Value;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class View {

    public void start(){
        boolean start = true;
        while(start){
            try{
                showMenu();
                Scanner reader = new Scanner(System.in);
                int option = reader.nextInt();
                if (option == 1){runProgram1();}
                else if (option == 2) {runProgram2();}
                else if (option == 3) {runProgram3();}
                else if (option == 0) {start = false;}
                else {System.out.println("Invalid input!");}
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

        }
    }

    private void runProgram1() throws MyException, IOException, InterruptedException {
        IStmt program1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v",
                new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        runPrg(program1);
    }

    private void runProgram2() throws MyException, IOException, InterruptedException {
        IStmt program2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)),
                        new ValueExp(new IntValue(5)),'*'),'+' )), new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"),
                        new ValueExp(new IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));
        runPrg(program2);
    }

    private void runProgram3() throws MyException, IOException, InterruptedException {
        IStmt program3 = new CompStmt(new VarDeclStmt("a", new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("a",new ValueExp(new BoolValue(false))),new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v",
                        new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        runPrg(program3);
    }

    private void runPrg(IStmt statement) throws MyException, IOException, InterruptedException {
        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<Integer, Value> hp = new MyHeap<>();
        MyIBarrierTable barrierTable = new MyBarrierTable();
        PrgState prg = new PrgState(exeStack, symTable, output, fileTable, hp,  barrierTable, statement);
        IRepository repo = new Repository(prg, "log.txt");
        Controller ctrl = new Controller(repo);
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        ctrl.allStep();
        System.out.println("Result is: " + prg.getOut().toString().replace('[', ' ').replace(']', ' '));
    }

    private void showMenu() {
        System.out.println("--------------Toy Language Interpreter--------------\n");
        System.out.println("1. Run program 1: \n\tint v;\n\t  v=2;\n\t    Print(v)\n");
        System.out.println("2. Run program 2: \n\tint a;\n\t  int b;\n\t    a=2+3*5;\n\t      b=a+1;\n\t        Print(b)\n");
        System.out.println("3. Run program 3: \n\tbool a;\n\t  int v;\n\t    a=false;\n\t      (If a Then v=2 Else v=3);\n\t         Print(v)\n");
        System.out.println("0. Exit.");
        System.out.println("Choose a program to run: ");
    }

}
