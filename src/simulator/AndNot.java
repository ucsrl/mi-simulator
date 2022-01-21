/**
 * 
 */
package simulator;

import java.util.ArrayList;

import codegenerator.LabelInUse;
import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;

/**
 * ANDNOT-Befehl
 * 
 * @author Matthias Oehme
 */
public class AndNot extends Command {

    /** erster Operand */
    Operand op1;

    /** zweiter Operand */
    Operand op2;

    /** dritter Operand */
    Operand op3;

    /** true, falls es sich um einen 3-Adressbefehl handelt */
    boolean three = false;

    /** Länge Befehls B = 1, H = 2, W = 4, F = 4, D = 8 */
    int length = 0;

    /**
     * Konstruktor für einen 2-Adress ANDNOT-Befehl
     * 
     * @param line
     *            Zeile im Quelltext
     * @param adress
     *            Adresse des Befehls
     * @param length
     *            Länge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     * @param op1
     *            erster Operand
     * @param op2
     *            zweiter Operand
     * @param beg
     *            Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end
     *            Zeichenposition - Ende des Befehlswortes im Quelltext
     */
    public AndNot(int line, int adress, int length, Operand op1, Operand op2,
	    int beg, int end) {
	super(line, adress, beg, end);
	this.op1 = op1;
	this.op2 = op2;
	this.length = length;
	setAdress(adress);
    }

    /**
     * Instantiates a new and not.
     * 
     * @param line
     *            the line
     * @param adress
     *            the adress
     * @param length
     *            the length
     * @param op1
     *            the op1
     * @param op2
     *            the op2
     * @param op3
     *            the op3
     * @param beg
     *            the beg
     * @param end
     *            the end
     */
    public AndNot(int line, int adress, int length, Operand op1, Operand op2,
	    Operand op3, int beg, int end) {
	this(line, adress, length, op1, op2, beg, end);
	this.op3 = op3;
	three = true;
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

	if (op3 instanceof AbsAddress && ((AbsAddress) op3).hasLabel()) {
	    ret.add(new LabelInUse(this, ((AbsAddress) op3).getLabel(),
		    (AbsAddress) op3));
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
	    opcode = three ? new MyByte("B6") : new MyByte("B3");
	    break;
	case 2:
	    opcode = three ? new MyByte("B7") : new MyByte("B4");
	    break;
	case 4:
	    opcode = three ? new MyByte("B8") : new MyByte("B5");
	    break;
	default:
	    System.out.println("Fehler beim berechnen des OPCodes");
	}
	int x = 1;
	MyByte[] opc1 = op1.getOpCode();
	MyByte[] opc2 = op2.getOpCode();
	MyByte[] opc3 = three ? op3.getOpCode() : null;
	MyByte[] ret = new MyByte[three ? opc1.length + opc2.length
		+ opc3.length + 1 : opc1.length + opc2.length + 1];
	ret[0] = opcode;
	for (MyByte element : opc1) {
	    ret[x] = element;
	    x++;
	}
	for (MyByte element : opc2) {
	    ret[x] = element;
	    x++;
	}
	if (three) {
	    for (MyByte element : opc3) {
		ret[x] = element;
		x++;
	    }
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
			.hasLabel()))
		|| ((op3 instanceof AbsAddress) && (((AbsAddress) op3)
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
	MyByte[] erg = new MyByte[length];
	MyByte[] ope1 = op1.getContent();
	MyByte[] ope2 = ((three == false) && (op2 instanceof CellarAddressing)) ? ((CellarAddressing) op2)
		.getContentWithoutOffset() : op2.getContent();
	ope1 = NumberConversion.complement(ope1);
	for (int i = length - 1; i >= 0; i--) {
	    erg[i] = new MyByte(ope1[i].getContent() & ope2[i].getContent());
	}
	if (!three) {
	    op2.setContent(erg, length);
	} else {
	    op3.setContent(erg, length);
	}
	Enviroment.flags.setCarry(false);
	if (NumberConversion.myBytetoIntWithoutSign(erg) == 0) {
	    Enviroment.flags.setZero(true);
	} else {
	    Enviroment.flags.setZero(false);
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

	if (op3 instanceof AbsAddress && ((AbsAddress) op3).hasLabel()) {
	    ((AbsAddress) op3).setOrt(adress + 1 + op1.getOpCode().length
		    + op2.getOpCode().length);
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
	    bhwfd = "W";
	    break;
	}
	return "ANDNOT " + bhwfd + " " + op1.toString() + ", " + op2.toString()
		+ (three ? ", " + op3.toString() : "");
    }

}
