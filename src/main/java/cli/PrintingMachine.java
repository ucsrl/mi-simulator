package cli;

import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.Register;
import gui.CONSTANTS;
import simulator.Command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

class PrintingMachine implements IMachine {
    private final IMachine inner;
    private final PrintStream out;

    private final int[] previousRegValues = new int[CONSTANTS.NUMBER_OF_REGISTER];
    private final Map<Integer, MyByte> previousMemValues = new HashMap<>();
    private final Map<String, Boolean> previousFlags = new HashMap<>();
    private boolean initialized;

    PrintingMachine(IMachine inner, PrintStream out) {
        this.inner = inner;
        this.out = out;
    }

    @Override
    public boolean hasHalted() {
        return inner.hasHalted();
    }

    @Override
    public Command executeNext() {
        if (!initialized) {
            Map<Integer, MyByte> changes = Enviroment.MEMORY.getChanges();
            for (Integer address : changes.keySet()) {
                previousMemValues.put(address, changes.get(address));
            }
            fillCurrentFlags(previousFlags);
        }

        Command command = inner.executeNext();
        out.println("INS: " + command);
        printRegisterValues(previousRegValues);
        out.println();
        printFlags(previousFlags);
        out.println();
        printMemoryValues(previousMemValues);
        out.println();
        out.println();
        return command;
    }

    private static void fillCurrentFlags(Map<String, Boolean> flags) {
        flags.put("C", Enviroment.flags.isCarry());
        flags.put("N", Enviroment.flags.isNegative());
        flags.put("V", Enviroment.flags.isOverflow());
        flags.put("Z", Enviroment.flags.isZero());
    }

    static class Separator {
        private boolean first;
        private final PrintStream out;

        Separator(PrintStream out) {
            this.out = out;
            this.first = true;
        }

        void printColumn(String column) {
            if (!first) {
                out.print("; ");
            }
            out.print(column);
            first = false;
        }
    }

    private void printFlags(Map<String, Boolean> previousFlags) {
        Map<String, Boolean> flags = new HashMap<>();
        fillCurrentFlags(flags);
        Separator separator = new Separator(out);
        for (String flag : flags.keySet()) {
            if (flags.get(flag) != previousFlags.get(flag)) {
                separator.printColumn(String.format("%s: %d -> %d", flag, asBit(previousFlags.get(flag)), asBit(flags.get(flag))));
            } else {
                separator.printColumn(String.format("%s: %d", flag, asBit(flags.get(flag))));
            }
        }
        fillCurrentFlags(previousFlags);
    }


    private void printMemoryValues(Map<Integer, MyByte> previousMemValues) {
        Map<Integer, MyByte> changes = Enviroment.MEMORY.getChanges();
        Separator separator = new Separator(out);

        for (Integer address : changes.keySet()) {
            int currentValue = changes.get(address).getContent();
            int previousValue = previousMemValues.containsKey(address) ? previousMemValues.get(address).getContent() : 0;

            if (previousValue == 0 && currentValue == 0)
                continue;

            previousMemValues.put(address, changes.get(address));
            if (previousValue != currentValue) {
                separator.printColumn(String.format("%d: %d -> %d", address, previousValue, currentValue));
            } else {
                separator.printColumn(String.format("%d: %d", address, currentValue));
            }
        }
    }

    private void printRegisterValues(int[] previousRegValues) {
        Separator separator = new Separator(out);
        for (int i = 0; i < CONSTANTS.NUMBER_OF_REGISTER; i++) {
            Register register = Enviroment.REGISTERS.getRegister(i);
            int regValue = register.getContentAsNumber(4);
            if (previousRegValues[i] == regValue && regValue == 0)
                continue;
            if (previousRegValues[i] != regValue) {
                separator.printColumn(String.format("R%s: %d -> %d", i, previousRegValues[i], regValue));
            } else {
                separator.printColumn(String.format("R%s: %d", i, regValue));
            }
            previousRegValues[i] = regValue;
            register.reset();
        }
    }

    private static int asBit(boolean value) {
        return value ? 1 : 0;
    }

}
