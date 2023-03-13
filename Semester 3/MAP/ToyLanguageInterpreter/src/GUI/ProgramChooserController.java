package GUI;

import controller.Controller;
import Exception.MyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.ADTs.CyclicBarrier.MyBarrierTable;
import model.ADTs.Dictionary.MyDictionary;
import model.ADTs.Heap.MyHeap;
import model.ADTs.List.MyList;
import model.ADTs.Stack.MyStack;
import model.Expression.*;
import model.Statement.*;
import model.Type.IntType;
import model.Type.RefType;
import model.Type.StringType;
import model.Value.BoolValue;
import model.Value.IntValue;
import model.Value.StringValue;
import repository.IRepository;
import repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramChooserController {
    private ProgramExecutorController programExecutorController;

    public void setProgramExecutorController(ProgramExecutorController programExecutorController) {
        this.programExecutorController = programExecutorController;
    }

    @FXML
    private ListView<IStmt> programsListView;

    @FXML
    private Button displayButton;

    @FXML
    public void initialize() {
        programsListView.setItems(getAllStatements());
        programsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    @FXML
    private void displayProgram(ActionEvent actionEvent) {
        IStmt selectedStatement = programsListView.getSelectionModel().getSelectedItem();
        if (selectedStatement == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error encountered!");
            alert.setContentText("No statement selected!");
            alert.showAndWait();
        } else {
            int id = programsListView.getSelectionModel().getSelectedIndex();
            try {
                selectedStatement.typecheck(new MyDictionary<>());
                PrgState programState = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(),new MyBarrierTable(), selectedStatement);
                IRepository repository = new Repository(programState, "log" + (id + 1) + ".txt");
                Controller controller = new Controller(repository);
                programExecutorController.setController(controller);
            } catch (MyException | IOException exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error encountered!");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        }

    }
    @FXML
    private ObservableList<IStmt> getAllStatements() {
        List<IStmt> allStatements = new ArrayList<>();
        IStmt program1 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v",
                new ValueExp(new IntValue(7))), new PrintStmt(new VarExp("v"))));
        allStatements.add(program1);
        IStmt program2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)),
                        new ValueExp(new IntValue(5)), '*'), '+')), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"),
                        new ValueExp(new IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));
        allStatements.add(program2);
        IStmt program3 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(3))), (new IfStmt(new RelationalExp(new VarExp("a"), new VarExp("b"), ">="), new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))))))));
        allStatements.add(program3);
        IStmt program4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                new CompStmt(new OpenRFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")),
                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        allStatements.add(program4);
        IStmt program5 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '-')))),
                                new PrintStmt(new VarExp("v")))));
        allStatements.add(program5);
        IStmt program6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new HeapAllocationStmt("a", new VarExp("v")), new CompStmt(new HeapAllocationStmt("v", new ValueExp(new IntValue(30))), new CompStmt(new PrintStmt(new HeapReadingExp(new VarExp("v"))), new PrintStmt(new ArithExp(new HeapReadingExp(new HeapReadingExp(new VarExp("a"))), new ValueExp(new IntValue(5)), '+'))))))));
        allStatements.add(program6);
        IStmt program7 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new HeapAllocationStmt("a", new ValueExp(new IntValue(22))),
                new CompStmt(new ForkStmt(new CompStmt(new HeapWritingStmt("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadingExp(new VarExp("a")))))))));
        allStatements.add(program7);

        /*
        * int v; int x; int y; v=0;
        (repeat (fork(print(v);v=v-1);v=v+1) until v==3);
        x=1;nop;y=3;nop;
        print(v*10)
        The final Out should be {0,1,2,30}*/
        ///repeat...until statement
        IStmt program8 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("x", new IntType()), new CompStmt(new VarDeclStmt("y", new IntType()),
                new CompStmt(new RepeatUntilStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '-')))), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '+'))), new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), "==")),
                        new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))), new CompStmt(new NopStmt(), new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(3))), new CompStmt(new NopStmt(), new PrintStmt(new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), '*'))))))))));
        allStatements.add(program8);

        ////cyclic barrier
        /*Ref int v1; Ref int v2; Ref int v3; int cnt;
        new(v1,2);new(v2,3);new(v3,4);newBarrier(cnt,rH(v2));
        fork(await(cnt);wh(v1,rh(v1)*10);print(rh(v1)));
        fork(await(cnt);wh(v2,rh(v2)*10);wh(v2,rh(v2)*10);print(rh(v2)));
        await(cnt);
        print(rH(v3))
        The final Out should be {4,20,300}*/
        IStmt program9 = new CompStmt(
                new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())), new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())), new CompStmt(new VarDeclStmt("cnt", new IntType()), new CompStmt(new HeapAllocationStmt("v1", new ValueExp(new IntValue(2))),
                        new CompStmt(new HeapAllocationStmt("v2", new ValueExp(new IntValue(3))), new CompStmt(
                                new HeapAllocationStmt("v3", new ValueExp(new IntValue(4))),
                                new CompStmt(new NewBarrierStmt("cnt", new HeapReadingExp(new VarExp("v2"))), new CompStmt(new ForkStmt(new CompStmt(new AwaitStatement("cnt"),
                                        new CompStmt(new HeapWritingStmt("v1", new ArithExp(new HeapReadingExp(new VarExp("v1")), new ValueExp(new IntValue(10)), '*')),
                                                new PrintStmt(new HeapReadingExp(new VarExp("v1")))))), new CompStmt(new ForkStmt(
                                                        new CompStmt(new AwaitStatement("cnt"), new CompStmt(
                                                                new HeapWritingStmt("v2", new ArithExp(new HeapReadingExp(new VarExp("v2")), new ValueExp(new IntValue(10)), '*')),
                                                                new CompStmt(
                                                                        new HeapWritingStmt("v2", new ArithExp(new HeapReadingExp(new VarExp("v2")), new ValueExp(new IntValue(10)),'*')),
                                                                        new PrintStmt(new HeapReadingExp(new VarExp("v2"))))))), new CompStmt(
                                                                                                new AwaitStatement("cnt"),
                                                                                                new PrintStmt(new HeapReadingExp(new VarExp("v3"))))))))))))));
        allStatements.add(program9);
        return FXCollections.observableArrayList(allStatements);
    }
}