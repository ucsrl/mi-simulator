package cli;

import enviroment.Enviroment;
import simulator.Command;
import simulator.Halt;

class MIMachine implements IMachine {
    private boolean halted;

    @Override
    public boolean hasHalted() {
        return halted;
    }

    @Override
    public Command executeNext() {
        if (!hasHalted()) {
            Command next = Enviroment.readNextCommand();
            next.run();
            if (next instanceof Halt) {
                halted = true;
            }
            return next;
        }
        return null;
    }
}

