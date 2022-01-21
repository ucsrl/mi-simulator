package simulator;

import java.util.ArrayList;

import codegenerator.LabelInUse;
import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;

/**
 * CMP-Befehl
 * 
 * @author Matthias Oehme
 */
public class Cmp extends Command {
    /** erster Operand */
    Operand op1;

    /** zweiter Operand */
    Operand op2;

    /** true, falls es sich um einen 3-Adressbefehl handelt */
    boolean three = false;

    /** true, falls es ein Gleitpunktzahlbefehl ist */
    boolean floating = false;

    /** L�nge Befehls B = 1, H = 2, W = 4, F = 4, D = 8 */
    int length = 0;

    /**
     * Konstruktor f�r einen 2-Adress CMP-Befehl
     * 
     * @param line
     *            Zeile im Quelltext
     * @param adress
     *            Adresse des Befehls
     * @param length
     *            L�nge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     * @param op1
     *            erster Operand
     * @param op2
     *            zweiter Operand
     * @param beg
     *            Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end
     *            Zeichenposition - Ende des Befehlswortes im Quelltext
     * @param floating
     *            true, falls es ein Gleitpunktzahlbefehl ist
     */
    public Cmp(int line, int adress, int length, Operand op1, Operand op2,
	    int beg, int end, boolean floating) {
	super(line, adress, beg, end);
	this.op1 = op1;
	this.op2 = op2;
	this.length = length;
	setAdress(adress);
	this.floating = floating;
    }

    /*
     * (non-Javadoc)
     * 
     * @see compiler.Command#getLabel()
     */
    @Override
    public ArrayList<LabelInUse> getLabel() {
	ArrayList<LabelInUse> ret = new ArrayList<LabelInUse>();
	if (op1 instanceof AbsAddress && ((AbsAddress) op1).hasLabel()) {
	    ret.add(new LabelInUse(this, ((AbsAddress) op1).getLabel(),
		    (AbsAddress) op1));
	}
	if (op2 instanceof AbsAddress && ((AbsAddress) op2).hasLabel()) {
	    ret.add(new LabelInUse(this, ((AbsAddress) op2).getLabel(),
		    (AbsAddress) op2));
	}

	return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see compiler.Command#getOpCode()
     */
    @Override
    public MyByte[] getOpCode() {
	MyByte opcode = null;
	switch (length) {
	case 1:
	    opcode = new MyByte("92");
	    break;
	case 2:
	    opcode = new MyByte("93");
	    break;
	case 4:
	    opcode = new MyByte(floating ? "95" : "94");
	    break;
	case 8:
	    opcode = new MyByte("96");
	    break;
	default:
	    System.out.println("Fehler beim berechnen des OPCodes");
	}
	int x = 1;
	MyByte[] opc1 = op1.getOpCode();
	MyByte[] opc2 = op2.getOpCode();

	MyByte[] ret = new MyByte[opc1.length + opc2.length + 1];
	ret[0] = opcode;
	for (MyByte element : opc1) {
	    ret[x] = element;
	    x++;
	}
	for (MyByte element : opc2) {
	    ret[x] = element;
	    x++;
	}

	return ret;

    }

    /*
     * (non-Javadoc)
     * 
     * @see compiler.Command#hasLabel()
     */
    @Override
    public boolean hasLabel() {
	return ((op1 instanceof AbsAddress) && (((AbsAddress) op1).hasLabel()))
		|| ((op2 instanceof AbsAddress) && (((AbsAddress) op2)
			.hasLabel()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see compiler.Command#run()
     */
    @Override
    public synchronized void run() {

	super.run();
	if (floating) {

	    switch (length) {
	    case 4:
		Float zahl1 = Float.intBitsToFloat(NumberConversion
			.myBytetoIntWithSign(op1.getContent()));
		Float zahl2 = Float.intBitsToFloat(NumberConversion
			.myBytetoIntWithSign(op2.getContent()));
		Enviroment.flags.setZero(zahl1.compareTo(zahl2) == 0);
		Enviroment.flags.setNegative(zahl1.compareTo(zahl2) < 0);
		break;
	    case 8:
		Double zahl11 = Double.longBitsToDouble(NumberConversion
			.myBytetoLongWithoutSign(op1.getContent()));
		Double zahl22 = Double.longBitsToDouble(NumberConversion
			.myBytetoLongWithoutSign(op2.getContent()));
		Enviroment.flags.setZero(zahl11.compareTo(zahl22) == 0);
		Enviroment.flags.setNegative(zahl11.compareTo(zahl22) < 0);
		break;

	    }

	} else {

	    int zahl1 = NumberConversion.myBytetoIntWithSign(op1.getContent());
	    int zahl2 = NumberConversion.myBytetoIntWithSign(op2.getContent());
	    Enviroment.flags.setZero(zahl1 == zahl2);
	    Enviroment.flags.setNegative(zahl1 < zahl2);
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see compiler.Command#setAdress(int)
     */
    @Override
    public void setAdress(int adress) {
	this.adress = adress;
	if (op1 instanceof AbsAddress && ((AbsAddress) op1).hasLabel()) {
	    ((AbsAddress) op1).setOrt(adress + 1);
	}
	if (op2 instanceof AbsAddress && ((AbsAddress) op2).hasLabel()) {
	    ((AbsAddress) op2).setOrt(adress + 1 + op1.getOpCode().length);
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	String bhwfd = "";
	switch (length) {
	case 1:
	    bhwfd = "B";
	    break;
	case 2:
	    bhwfd = "H";
	    break;
	case 4:
	    bhwfd = floating ? "F" : "W";
	    break;
	case 8:
	    bhwfd = "D";
	    break;
	}
	return "CMP " + bhwfd + " " + op1.toString() + ", " + op2.toString();
    }

}
