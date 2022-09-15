package cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: Main <Path to MI program> [state file]");
            System.exit(1);
        }

        // TODO: use proper commandline parsing
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
        boolean useHex = false;
        if (args.length > 2) {
            if (args[2].equals("-useHex")) {
                useHex = true;
            } else {
                System.err.println("Unknown argument " + args[2]);
                System.exit(1);
            }
        }

        if (args.length > 1) {
            String stateText = null;

            if (args[1].equals("-useHex")) {
                useHex = true;
            } else {
                try {
                    stateText = new String(Files.readAllBytes(new File(args[1]).toPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

                MachineUtils.loadState(stateText, useHex);
            }
        }



        runProgram(useHex);
        System.exit(0);
    }

    private static void runProgram(boolean useHex) {
        PrintingMachine machine = new PrintingMachine(new MIMachine(), System.out, useHex);
        while (!machine.hasHalted()) {
            machine.executeNext();
        }
    }
}
