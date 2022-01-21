package enviroment;

import gui.CONSTANTS;
import gui.HighlightedJPane;
import gui.LabelWindow;
import gui.Window;

import interpreter.Interpreter;

import java.util.ArrayList;

import codegenerator.Program;

import simulator.Command;

/**
 * The Class Enviroment.
 */
public class Enviroment {

    /** Der Speicher der MI */
    public static Memory MEMORY;

    /** Der Registerkoerper der MI */
    public static RegisterBody REGISTERS;

    /** Die Statusflags der MI */
    public static Flags flags;

    /** Das zugehörige Programm im Zwischencode */
    private static Program program;

    /** Das Quelltexteingabefeld */
    public static HighlightedJPane text;

    /** Art der Registerdarstellung */
    public static int REGVIEW = 0;

    /** Das Anzeigefenster */
    public static Window frame;

    /** Beginn des kellerspeichers */
    public static int STACKBEGIN = 0;

    /** Übersetzungszustand des Programms */
    public static boolean compiled = false;
    
    public static boolean shl = true;
    
    public static boolean label_Window= false;

    /** Der nächste Befehl */
    private static Command next;
    
    private static LabelWindow label_window=new LabelWindow();

    /**
     * Addiert value auf den Befehlszaehler
     * 
     * @param value
     *            Wert der addiert wird
     */
    public static void addToPC(int value) {
	REGISTERS.getRegister(CONSTANTS.PC_REGISTER).setContent(
		NumberConversion.intToByte(
			REGISTERS.getRegister(CONSTANTS.PC_REGISTER)
				.getContentAsInt(4) + value, 4));
    }

    /**
     * Gibt den Befehl an einer Adresse zurück
     * 
     * @param address
     *            Adresse
     * @return Befehl oder null
     */
    public static Command getCommandperAddress(int address) {
	ArrayList<Command> prog = program.getCommands();
	for (Command com : prog) {
	    if (com.getAdress() == address) {
		return com;
	    }
	}
	return null;
    }

    /**
     * Gibt den naechsten Befehl zurueck
     * 
     * @return naechster Befehl
     */
    public static Command getNextCommand() {
	return next;
    }

    /**
     * Gibt den aktuellen Befehlszzaehler zurueck
     * 
     * @return Befehlszzaehler
     */
    public static int getPC() {
	return REGISTERS.getRegister(CONSTANTS.PC_REGISTER).getContentAsInt(4);
    }

    /**
     * Gibt das Byte aus dem Speicher zurueck, auf welches des PC zeigt
     * 
     * @return Byte aus dem Speicher
     */
    public static int getPCFromMemory() {
	return NumberConversion.myBytetoIntWithoutSign(MEMORY
		.getContent(REGISTERS.getRegister(CONSTANTS.PC_REGISTER)
			.getContentAsInt(4), 1));
    }

    /**
     * Gibt die Zwischencodedarstellung des Programms zurueck
     * 
     * @return Zwischencodedarstellung des Programms
     */
    public static Program getProgram() {
	return program;
    }

    /**
     * Gibt das Quelltextanzeigefeld zurueck
     * 
     * @return Quelltextanzeigefeld
     */
    public static HighlightedJPane getText() {
	return text;
    }

    /**
     * Initalisiert die Laufzeitumgebung
     */
    public static void init() {
	if (MEMORY == null) {
	    MEMORY = new Memory();
	} else {
	    MEMORY.reset();
	}
	REGISTERS = new RegisterBody();
	flags = new Flags();
	STACKBEGIN = 0;
	next = null;
    }

    /**
     * Liest den naechsten Befehl im Speicher
     * 
     * @return naechster Befehl im Speicher
     */
    public static Command readNextCommand() {

	next = Interpreter.getNextCommand();
	Command nex2 = next != null ? getCommandperAddress(next.getAdress())
		: null;
	MyByte[] bef1 = next != null ? next.getOpCode() : new MyByte[] {};
	MyByte[] bef2 = nex2 != null ? nex2.getOpCode() : new MyByte[] {};

	if (bef1.length != bef2.length) {
	    Enviroment.frame.setErrorText(CONSTANTS.ERROR_MANIPULATION_MEMORY);

	} else {
	    for (int i = 0; i < bef1.length; i++) {
		if (bef1[i].getContent() != bef2[i].getContent()) {
		    Enviroment.frame
			    .setErrorText(CONSTANTS.ERROR_MANIPULATION_MEMORY);
		}
	    }

	}
	if (next != null) {
	    REGISTERS.getRegister(CONSTANTS.PC_REGISTER).setContent(
		    NumberConversion.intToByte(next.getAdress(), 4));
	}
	return next;
    }

    /**
     * Setzt den Uebersetzungsstatus des Programm
     * 
     * @param compiled
     *            Uebersetzungsstatus
     */
    public static void setCompiled(boolean compiled) {
	Enviroment.compiled = compiled;
    }

    /**
     * Setzt das darstellende Fenster
     * 
     * @param window
     *            darstellendes Fenster
     */
    public static void setJFrame(Window window) {
	frame = window;
    }

    /**
     * Setzt die Zwischencodedarstellung
     * 
     * @param program
     *            Zwischencodedarstellung
     */
    public static void setProgram(Program program) {
	Enviroment.program = program;
    }

    /**
     * Setzt die Registerdarstellung
     * 
     * @param regview
     *            Registerdarstellung
     */
    public static void setRegView(int regview) {
	REGVIEW = regview;
    }

    /**
     * Setzt das Quelltexteingabefeld
     * 
     * @param text
     *            Quelltexteingabefeld
     */
    public static void setText(HighlightedJPane text) {
	Enviroment.text = text;
    }
    
    public static void setLabelWindow(LabelWindow window) {
	Enviroment.label_window=window;
    }
    
    public static LabelWindow getLabelWindow() {
	return label_window;
    }
}
