package model.ADTs.CyclicBarrier;
import Exception.MyException;
import javafx.util.Pair;
import java.util.HashMap;
import java.util.List;

public interface MyIBarrierTable {
    void put(int key, Pair<Integer, List<Integer>> value) throws MyException;
    Pair<Integer, List<Integer>> get(int key) throws MyException;
    boolean containsKey(int key);
    int getFreeAddress();
    void setFreeAddress(int freeAddress);
    void update(int key, Pair<Integer, List<Integer>> value) throws MyException;
    HashMap<Integer, Pair<Integer, List<Integer>>> getBarrierTable();
    void setBarrierTable(HashMap<Integer, Pair<Integer, List<Integer>>> newBarrierTable);
}