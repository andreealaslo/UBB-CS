package view.command;

import controller.Controller;
import Exception.MyException;

import java.io.IOException;
import java.util.Scanner;

public class RunExampleCommand extends Command {
    private final Controller ctrl;

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        ctrl = controller;

    }

    @Override
    public void execute() {
        try {
            ctrl.allStep();
        } catch (InterruptedException | IOException | MyException e) {
            throw new RuntimeException(e);
        }
    }
}

