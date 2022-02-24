package cli;

import simulator.Command;

interface IMachine {
    boolean hasHalted();

    Command executeNext();
}
