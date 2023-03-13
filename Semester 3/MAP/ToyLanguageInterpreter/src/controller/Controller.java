package controller;

import Exception.MyException;
import model.ADTs.Heap.MyIHeap;
import model.PrgState;
import model.Value.RefValue;
import model.Value.Value;
import repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Pair {
    final PrgState first;
    final MyException second;

    public Pair(PrgState first, MyException second) {
        this.first = first;
        this.second = second;
    }
}

public class Controller {
    private IRepository repo;
    boolean displayFlag = false;
    private ExecutorService executor;

    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrglist) {
        return inPrglist.stream()
                .filter(p -> !p.isNotComplete())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<PrgState> programStates) throws InterruptedException, IOException, MyException {
        programStates.forEach(programState -> {
            try {
                repo.logPrgStateExec(programState);
                display(programState);
            } catch (IOException | MyException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        });
        List<Callable<PrgState>> callList = programStates.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());
        List<Pair> newProgramList;
        newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return new Pair(future.get(), null);
                    } catch (ExecutionException | InterruptedException e) {
                        if (e.getCause() instanceof MyException)
                            return new Pair(null, (MyException) e.getCause());
                        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
                        return null;
                    }

                })
                .filter(Objects::nonNull)
                .filter(pair -> pair.first != null || pair.second != null)
                .collect(Collectors.toList());
        for (Pair error : newProgramList)
            if (error.second != null)
                throw error.second;
        programStates.addAll(newProgramList.stream().map(pair -> pair.first).collect(Collectors.toList()));

        programStates.forEach(programState -> {
            try {
                repo.logPrgStateExec(programState);
                display(programState);
            } catch (IOException | MyException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        });
        repo.setPrgList(programStates);
    }

    public void oneStep() throws MyException, InterruptedException, IOException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> programStates = removeCompletedPrg(repo.getPrgList());
        oneStepForAllPrograms(programStates);
        conservativeGarbageCollector(programStates);
        //programStates = removeCompletedPrg(repository.getProgramList());
        executor.shutdownNow();
        //repository.setProgramStates(programStates);
    }

    public void allStep() throws InterruptedException, IOException, MyException {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> programStates = removeCompletedPrg(repo.getPrgList());
        while (programStates.size() > 0) {
            conservativeGarbageCollector(programStates);
            oneStepForAllPrograms(programStates);
            //programStates = removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //repo.setPrgList(programStates);
    }

    public void conservativeGarbageCollector(List<PrgState> programStates) {
        List<Integer> symTableAddresses = Objects.requireNonNull(programStates.stream()
                        .map(p -> getAddrFromSymTable(p.getSymTable().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        programStates.forEach(p -> {
            p.getHeapTable().setContent((HashMap<Integer, Value>) safeGarbageCollector(symTableAddresses, getAddrFromHeap(p.getHeapTable().getContent().values()), p.getHeapTable().getContent()));
        });
    }

    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> (symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void display(PrgState programState) {
        if (displayFlag) {
            System.out.println(programState.toString());
        }
    }

    public void setProgramStates(List<PrgState> programStates) {
        this.repo.setPrgList(programStates);
    }

    public List<PrgState> getProgramStates() {
        return this.repo.getPrgList();
    }
}
