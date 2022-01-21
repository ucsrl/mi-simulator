package codegenerator;

import java.util.ArrayList;

import Exceptions.NoCommandException;

import enviroment.MyByte;

import simulator.Command;

/**
 * Zwischencodedarstellung, die vom Parser generiert wird
 */
public class Program {

    /** Auflisteung des Programms */
    private ArrayList<Command> program = new ArrayList<Command>();

    /**
     * Konstruktor
     */
    public Program() {

    }

    /**
     * Fügt einen weiteren Befehl hinzu
     * 
     * @param comm
     *            der neue Befehl
     */
    public void add(Command comm) {
	program.add(comm);
    }

    /**
     * Gibt den ersten Befehl in einer Programmzeile wieder
     * 
     * @param line
     *            Programmzeile
     * @return erster Befehl in der Zeile oder null
     */
    public Command getCommandPerLine(int line) {
	for (Command com : program) {
	    if (com.getLine() == line) {
		return com;
	    }
	}

	return null;
    }

    /**
     * Generiert den Opcode des ganzen Programms
     * 
     * @return Opcode
     */
    public MyByte[] getOpCode() {

	ArrayList<MyByte> liste = new ArrayList<MyByte>();
	for (Command command : program) {
	    MyByte[] opc = command.getOpCode();
	    for (MyByte op : opc) {
		liste.add(op);
	    }
	}

	MyByte[] ret = new MyByte[liste.size()];
	for (int i = 0; i < liste.size(); i++) {
	    ret[i] = liste.get(i);
	}
	return ret;

    }

    /**
     * Gibt die Liste der Befehle zurück
     * 
     * @return Liste der Befehle
     */
    public ArrayList<Command> getCommands() {

	return program;
    }

    /**
     * Gibt den Bytecode aus
     */
    public void print() {
	int count = 0;
	for (Command command : program) {
	    MyByte[] opc = command.getOpCode();
	    for (MyByte op : opc) {
		System.out.print(op.toString().toUpperCase() + " ");
		count++;
		if (count == 8) {
		    count = 0;

		}
	    }
	}
    }

    /**
     * Setzt einen Breakpoint auf eine bestimmte Zeile
     * 
     * @param line
     *            Programmzeile
     * @param value
     *            true oder false
     */
    public void setBreakPoint(int line, boolean value)
	    throws NoCommandException {
	try {
	    getCommandPerLine(line).setBreakPoint(value);
	} catch (Exception e) {
	    throw new NoCommandException();
	}
    }

}
