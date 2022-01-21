package enviroment;

import gui.CONSTANTS;
import gui.MemoryTable;
import gui.MemoryTableEntry;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 * Diese Klasse repraesentiert den Speicher der MI
 */
public class Memory {

    /** Speicher der MI */
    private MyByte[] memory = new MyByte[CONSTANTS.MEMORY_LENGTH];

    /** Darstellung des Speichers */
    private JList memoryJList;

    /** Haltung der Speicherdarstellung */
    private ArrayList<MemoryTableEntry> data;

    /** Liste der Speicherzellen, die rit gefärbt werden sollen */
    private ArrayList<MyByte> changed_list = new ArrayList<MyByte>();

    /** Liste geänderter Speicherzellen */
    private ArrayList<MyByte> new_list = new ArrayList<MyByte>();

    /**
     * Konstrktur für den Speicher.
     */
    public Memory() {
	for (int i = 0; i < CONSTANTS.MEMORY_LENGTH; i++) {
	    memory[i] = new MyByte(0);
	}
	createMemory();

    }

    /**
     * Initalisiert den Speicher und die Speicherdarstellung
     */
    public void createMemory() {
	data = new ArrayList<MemoryTableEntry>();
	int count = 0;
	String line = "000000";

	ArrayList<MyByte> list = new ArrayList<MyByte>();
	for (int i = 0; i < CONSTANTS.MEMORY_LENGTH; i++) {
	    list.add(memory[i]);
	    count++;
	    if (count == 8) {
		data.add(new MemoryTableEntry(list, line));

		line = "00000".substring(0, 6 - Integer.toHexString(i + 1)
			.length())
			+ Integer.toHexString(i + 1);
		line = line.toUpperCase();

		list = new ArrayList<MyByte>();
		count = 0;

	    }
	}
	memoryJList = new JList(new MemoryTable(data));

    }

    /**
     * Gibt die Liste der geaenderten Speicherzellen zurueck
     * 
     * @return Liste der geaenderten Speicherzellen
     */
    public ArrayList<MyByte> getChangedList() {
	return changed_list;
    }

    /**
     * Gibt den Inhalt des Speichers zurueck
     * 
     * @param begin
     *            Anfang des Speicherauszuges
     * @param length
     *            Laenge des Speicherauszuges
     * @return Speicherauszug
     */
    public MyByte[] getContent(int begin, int length) {
	if (begin < 0 || begin + length > CONSTANTS.MEMORY_LENGTH) {
	    Enviroment.frame.getRun().stopProgram();
	    JOptionPane.showMessageDialog(Enviroment.frame,
		    CONSTANTS.ERROR_MEMORY_TEXT + begin,
		    " CONSTANTS.ERROR_MEMORY_TITLE", JOptionPane.ERROR_MESSAGE);

	    return NumberConversion.intToByte(0, length);
	}
	MyByte[] ret = new MyByte[length];
	for (int i = 0; i < length; i++) {
	    ret[i] = new MyByte(memory[begin + i].getContent());
	}
	return ret;
    }

    /**
     * Gibt den Inhalt des Speichers zurueck als Integer
     * 
     * @param begin
     *            Anfang des Speicherauszuges
     * @param length
     *            Laenge des Speicherauszuges (maximal 4)
     * @return Speicherauszug
     */
    public int getContentAsInt(int begin, int length) {
	if (begin < 0 || begin + length > 1048576) {
	    Enviroment.frame.getRun().stopProgram();
	    JOptionPane.showMessageDialog(Enviroment.frame,
		    CONSTANTS.ERROR_MEMORY_TEXT + begin,
		    " CONSTANTS.ERROR_MEMORY_TITLE", JOptionPane.ERROR_MESSAGE);

	    return 0;
	}
	MyByte[] ret = new MyByte[length];
	for (int i = 0; i < length; i++) {
	    ret[i] = new MyByte(memory[begin + i].getContent());
	}
	return NumberConversion.myBytetoIntWithSign(ret);
    }

    /**
     * Gibt den Inhalt des Speichers zurueck als LONG
     * 
     * @param begin
     *            Anfang des Speicherauszuges
     * @param length
     *            Laenge des Speicherauszuges (maximal 8)
     * @return Speicherauszug
     */
    public long getContentAsLong(int begin, int length) {
	if (begin < 0 || begin + length > 1048576) {
	    Enviroment.frame.getRun().stopProgram();
	    JOptionPane.showMessageDialog(Enviroment.frame,
		    CONSTANTS.ERROR_MEMORY_TEXT + begin,
		    " CONSTANTS.ERROR_MEMORY_TITLE", JOptionPane.ERROR_MESSAGE);
	    return 0;
	}
	MyByte[] ret = new MyByte[length];
	for (int i = 0; i < length; i++) {
	    ret[i] = new MyByte(memory[begin + i].getContent());
	}
	return NumberConversion.myBytetoLongWithoutSign(ret);
    }

    /**
     * Gibt die Speicherdarstellung auf einer JScrollPane zurueck
     * 
     * @return Speicherdarstellung auf einer JScrollPane
     */
    public JScrollPane getMemoryTable() {
	memoryJList.setFont(CONSTANTS.FONT);
	JScrollPane ret = new JScrollPane(memoryJList);

	return ret;
    }

    /**
     * Gibt die Kellerdarstellung auf einer JScrollPane zurueck
     * 
     * @return Kellerdarstellung auf einer JScrollPane
     */
    public JScrollPane getStackTable() {

	int count = 0;
	int merke = 0;
	int ende = Enviroment.STACKBEGIN;
	String[] liste = null;

	int beg = NumberConversion.myBytetoIntWithSign(Enviroment.REGISTERS
		.getRegister(CONSTANTS.SP_REGISTER, 4));
	beg = beg / 8 * 8;
	if (beg <= ende && ende != 0) {
	    String line = "00000".substring(0, 6 - Integer.toHexString(beg)
		    .length())
		    + Integer.toHexString(beg);
	    line = line.toUpperCase();
	    liste = new String[(Enviroment.STACKBEGIN - beg) / 4 + 1];

	    for (int i = beg; i <= Enviroment.STACKBEGIN + 8; i++) {

		if (Enviroment.MEMORY.getChangedList().contains(memory[i])) {
		    line += "<font color=\"#ff0000\"> " + memory[i].toString()
			    + "</font>";

		} else {
		    line += " " + memory[i].toString();
		}

		// line += " " + memory[i].toString();
		count++;
		if (count == 8) {
		    liste[merke++] = "<html>" + line + "</html>";
		    line = "00000".substring(0, 6 - Integer.toHexString(i + 1)
			    .length())
			    + Integer.toHexString(i + 1);
		    line = line.toUpperCase();
		    count = 0;

		}
	    }
	}
	JList stackJList = new JList(liste == null ? new String[] {} : liste);
	stackJList.setFont(CONSTANTS.FONT);

	JScrollPane ret = new JScrollPane(stackJList);

	return ret;
    }

    /**
     * Setzt den Speicher zurueck auf Grundlage der geaenderten Speicherzellen
     */
    public void reset() {
	for (MyByte in : new_list) {
	    in.setContent(0);
	}
	new_list = new ArrayList<MyByte>();
    }

    /**
     * Schreibt in den Speicher
     * 
     * @param begin
     *            Anfangsadresse
     * @param content
     *            Inhalt der in den Speicher geschrieben werden soll
     */
    public void setContent(int begin, MyByte[] content) {

	if (begin < 0 || begin + content.length > CONSTANTS.MEMORY_LENGTH) {
	    Enviroment.frame.getRun().stopProgram();
	    JOptionPane.showMessageDialog(Enviroment.frame,
		    CONSTANTS.ERROR_MEMORY_TEXT + begin,
		    " CONSTANTS.ERROR_MEMORY_TITLE", JOptionPane.ERROR_MESSAGE);
	    return;
	}

	for (int i = 0; i < content.length; i++) {
	    memory[begin + i].setContent(content[i].getContent()); // new
								   // MyByte(content[i].getContent());
	    changed_list.add(memory[begin + i]);
	    new_list.add(memory[begin + i]);
	}

    }

    /**
     * Loescht die Liste der Adressen, die rot dargestellt werden sollen
     * 
     */
    public void reset_changedList() {
	changed_list = new ArrayList<MyByte>();
    }

}
