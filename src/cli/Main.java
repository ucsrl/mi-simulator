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
import java.util.ArrayList;
import java.util.HashMap;
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
        boolean loaded = assembleAndLoad(programText);
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
            loadState(stateText);
        }

        runProgram();
        System.exit(0);
    }

    private static void runProgram() {
        Command next = Enviroment.readNextCommand();
        System.out.println();

        int[] previousRegValues = new int[CONSTANTS.NUMBER_OF_REGISTER];
        Map<Integer, MyByte> previousMemValues = Enviroment.MEMORY.getChanges();
        Map<String, Boolean> previousFlags = new HashMap<>();
        fillCurrentFlags(previousFlags);
        while (next != null) {
            System.out.println("INS: " + next);
            next.run();
            printRegisterValues(previousRegValues);
            System.out.println();
            printFlags(previousFlags);
            System.out.println();
            printMemoryValues(previousMemValues);
            System.out.println();
            System.out.println();
            if (next instanceof Halt) {
                break; // Programmende erreicht
            }
            next = Enviroment.readNextCommand();
        }
    }

    private static void printMemoryValues(Map<Integer, MyByte> previousMemValues) {
        Map<Integer, MyByte> changes = Enviroment.MEMORY.getChanges();
        for (Integer address : changes.keySet()) {
            int currentValue = changes.get(address).getContent();
            int previousValue = previousMemValues.containsKey(address) ? previousMemValues.get(address).getContent() : 0;

            if (previousValue == 0 && currentValue == 0)
                continue;

            previousMemValues.put(address, changes.get(address));
            if (previousValue != currentValue) {
                System.out.printf("%d: %d -> %d; ", address, previousValue, currentValue);
            } else {
                System.out.printf("%d: %d; ", address, currentValue);
            }
        }
    }

    private static void printRegisterValues(int[] previousRegValues) {
        for (int i = 0; i < CONSTANTS.NUMBER_OF_REGISTER; i++) {
            Register register = Enviroment.REGISTERS.getRegister(i);
            int regValue = register.getContentAsInt(4);
            if (previousRegValues[i] == regValue && regValue == 0)
                continue;
            if (previousRegValues[i] != regValue) {
                System.out.printf("R%s: %d -> %d; ", i, previousRegValues[i], regValue);
            } else {
                System.out.printf("R%s: %d; ", i, regValue);
            }
            previousRegValues[i] = regValue;
            register.reset();
        }
    }

    private static void printFlags(Map<String, Boolean> previousFlags) {
        Map<String, Boolean> flags = new HashMap<>();
        fillCurrentFlags(flags);
        for (String flag : flags.keySet()) {
            if (flags.get(flag) != previousFlags.get(flag)) {
                System.out.printf("%s: %d -> %d; ", flag, asBit(previousFlags.get(flag)), asBit(flags.get(flag)));
            } else {
                System.out.printf("%s: %d; ", flag, asBit(flags.get(flag)));
            }
        }
        fillCurrentFlags(previousFlags);
    }

    private static int asBit(boolean value) {
        return value ? 1 : 0;
    }

    private static boolean assembleAndLoad(String programText) {
        Enviroment.init();
        Scanner scanner = new Scanner(false);
        scanner.init(programText);
        Parser parser = new Parser(scanner);
        parser.start();
        if (!parser.eval()) {
            System.err.println(parser.getErrorMeassge().getErrorMessage());
            return false;
        }
        Program program = parser.getProgramm();
        ArrayList<Command> commands = program.getCommands();
        if (commands.isEmpty()) {
            System.err.println(CONSTANTS.ASSEMBLE_UNSUCCESSFUL);
            return false;
        }
        System.out.println(CONSTANTS.ASSEMBLE_SUCCESSFUL);
        Enviroment.setProgram(program);
        Enviroment.MEMORY.setContent(0, program.getOpCode());
        return true;
    }

    private static void fillCurrentFlags(Map<String, Boolean> flags) {
        flags.put("C", Enviroment.flags.isCarry());
        flags.put("N", Enviroment.flags.isNegative());
        flags.put("V", Enviroment.flags.isOverflow());
        flags.put("Z", Enviroment.flags.isZero());
    }

    private static void loadState(String state) {
        String[] components = state.split("[\\n;]");

        for (String component : components) {
            String trimmed = component.trim();
            if (trimmed.isEmpty()) continue;

            String[] split = trimmed.split(":");
            if (split.length != 2) {
                throw new IllegalArgumentException("Invalid state file entry: " + trimmed);
            }

            String key = split[0].trim();
            String value = split[1].trim();

            if (value.contains("->")) {
                String[] valueParts = value.split("->");
                if (valueParts.length != 2) {
                    throw new IllegalArgumentException("Invalid state file entry: " + trimmed);
                }
                value = valueParts[1].trim();
            }

            if (key.startsWith("R")) {
                int regNum = Integer.parseInt(key.replace("R", ""));
                Enviroment.REGISTERS.getRegister(regNum).setContent(NumberConversion.intToByte(Integer.parseInt(value), 4));
                continue;
            }

            if (key.startsWith("C")) {
                Enviroment.flags.setCarry(Integer.parseInt(value) == 1);
                continue;
            }

            if (key.startsWith("V")) {
                Enviroment.flags.setOverflow(Integer.parseInt(value) == 1);
                continue;
            }

            if (key.startsWith("Z")) {
                Enviroment.flags.setZero(Integer.parseInt(value) == 1);
                continue;
            }

            if (key.startsWith("N")) {
                Enviroment.flags.setNegative(Integer.parseInt(value) == 1);
                continue;
            }

            int address = Integer.parseInt(key);
            int memoryValue = Integer.parseInt(value);
            Enviroment.MEMORY.setContent(address, new MyByte[]{new MyByte(memoryValue)});
        }
    }
}
