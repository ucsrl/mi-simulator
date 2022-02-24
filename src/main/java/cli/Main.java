package cli;

import codegenerator.Program;
import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;
import enviroment.Register;
import gui.CONSTANTS;
import parser.Parser;
import scanner.Scanner;
import simulator.Command;
import simulator.Halt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: Main <Path to MI program> [state file]");
            System.exit(1);
        }

        // load the program text
        String programText = null;
        try {
            programText = new String(Files.readAllBytes(new File(args[0]).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // load and assemble the program
        boolean loaded = MachineUtils.assembleAndLoad(programText);
        if (!loaded) {
            System.exit(1);
        }

        // load the state file if any
        if (args.length > 1) {
            String stateText = null;
            try {
                stateText = new String(Files.readAllBytes(new File(args[1]).toPath()));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            MachineUtils.loadState(stateText);
        }

        runProgram();
        System.exit(0);
    }

    private static void runProgram() {
//        PrintingMachine machine = new PrintingMachine(new MIMachine(), System.out);
        IMachine machine = new MIMachine();
        while (!machine.hasHalted()) {
            machine.executeNext();
        }
    }
}
