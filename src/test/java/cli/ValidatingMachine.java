package cli;

import simulator.Command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class ValidatingMachine implements IMachine {

    private final ByteArrayOutputStream result = new ByteArrayOutputStream();
    private final IMachine inner;
    private String[] referenceLines;
    private int referenceIndex = 0;

    ValidatingMachine(IMachine inner, String referenceLines) {
        this.inner = new PrintingMachine(inner, new PrintStream(result));
        this.referenceLines = referenceLines.split(System.lineSeparator(), -1);
    }

    @Override
    public boolean hasHalted() {
        return inner.hasHalted();
    }

    @Override
    public Command executeNext() {
        result.reset();
        Command command = inner.executeNext();
        String[] outputLines = result.toString().split(System.lineSeparator(), -1);
        for (int i = 0; i < outputLines.length - 1; i++) {
            String outputLine = outputLines[i];
            if (!outputLine.equals(referenceLines[referenceIndex])) {
                throw new IllegalStateException("Invalid output on line " + referenceIndex + ".\n" +
                        "Expected: " + referenceLines[referenceIndex] + "\n" +
                        "Actual: " + outputLine);
            }
            referenceIndex++;
        }
        return command;
    }
}
