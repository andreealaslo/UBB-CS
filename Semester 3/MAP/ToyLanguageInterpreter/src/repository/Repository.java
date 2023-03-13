package repository;

import model.PrgState;
import Exception.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    List<PrgState> programStates;
    private final String logFilePath;
    private int currentPosition;

    public Repository(PrgState prg, String logFile_Path) throws IOException {
        logFilePath = logFile_Path;
        programStates = new ArrayList<>();
        this.currentPosition = 0;
        this.addProgram(prg);
        this.emptyLogFile();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    @Override
    public void logPrgStateExec(PrgState prgState) throws MyException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter((new BufferedWriter(new FileWriter(logFilePath, true))));
        logFile.println(prgState.programStateToString());
        logFile.close();

    }

    @Override
    public List<PrgState> getPrgList() {
        return this.programStates;
    }

    @Override
    public void setPrgList(List<PrgState> prgStates) {
        this.programStates = prgStates;
    }

    @Override
    public void addProgram(PrgState program) {
        this.programStates.add(program);
    }

    @Override
    public PrgState getCurrentState() {
        return this.programStates.get(this.currentPosition);
    }

    @Override
    public void emptyLogFile() throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }

}
