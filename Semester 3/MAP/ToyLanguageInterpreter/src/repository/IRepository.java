package repository;
import Exception.MyException;
import model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    void logPrgStateExec(PrgState prgState) throws MyException, IOException;

    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> prgStates);

    void addProgram(PrgState program);

    PrgState getCurrentState();

    void emptyLogFile() throws IOException;
}
