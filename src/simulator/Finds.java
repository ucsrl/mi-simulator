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
 * FindS-Befehl
 * @author Matthias Oehme
 * 
 */
public class Finds extends Command {

    /** erster Operand */
    Operand op1;

    /** zweiter Operand */
    Operand op2;

    /** dritter Operand */
    Operand op3;

    /** dritter Operand */
    Operand op4;

    /**
     * Konstruktor für einen Findc-Befehl
     * 
     * @param line
     *            Zeile im Quelltext
     * @param adress
     *            Adresse des Befehls
     * @param op1
     *            erster Operand
     * @param op2
     *            zweiter Operand
     * @param op3
     *            dritter Operand
     * @param op4
     *            vierter Operand
     * @param beg
     *            Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end
     *            Zeichenposition - Ende des Befehlswortes im Quelltext
     */
    public Finds(int line, int adress, Operand op1, Operand op2, Operand op3,
	    Operand op4, int beg, int end) {
	super(line, adress, beg, end);
	this.op1 = op1;
	this.op2 = op2;
	this.op3 = op3;
	this.op4 = op4;
	setAdress(adress);
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

	if (op4 instanceof AbsAddress && ((AbsAddress) op4).hasLabel()) {
	    ret.add(new LabelInUse(this, ((AbsAddress) op4).getLabel(),
		    (AbsAddress) op4));
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
	MyByte opcode = new MyByte("F9");

	int x = 1;
	MyByte[] opc1 = op1.getOpCode();
	MyByte[] opc2 = op2.getOpCode();
	MyByte[] opc3 = op3.getOpCode();
	MyByte[] opc4 = op4.getOpCode();
	MyByte[] ret = new MyByte[opc1.length + opc2.length + opc3.length
		+ opc4.length + 1];
	ret[0] = opcode;
	for (MyByte element : opc1) {
	    ret[x] = element;
	    x++;
	}
	for (MyByte element : opc2) {
	    ret[x] = element;
	    x++;
	}

	for (MyByte element : opc3) {
	    ret[x] = element;
	    x++;
	}

	for (MyByte element : opc4) {
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
			.hasLabel()))
		|| ((op3 instanceof AbsAddress) && (((AbsAddress) op3)
			.hasLabel()))
		|| ((op4 instanceof AbsAddress) && (((AbsAddress) op4)
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
	int p = NumberConversion.myBytetoIntWithSign(op1.getContent());
	int s = NumberConversion.myBytetoIntWithSign(op2.getContent());
	int a = op3.getAdress();
	int beg = (a * 8 + p) / 8;
	int end = beg + 5;
	long bitfield = Enviroment.MEMORY.getContentAsLong(beg, end - beg);

	int sh_re = 40 - s - ((p < 0) ? (p % 8) + 8 : p % 8);
	int sh_li = 64 - s;

	bitfield >>>= sh_re;
	bitfield <<= sh_li;
	bitfield >>= sh_li;

	String string = Long.toBinaryString(bitfield);
	int zahl = string.indexOf("1") == -1 ? p + s : string.indexOf("1");

	MyByte[] ret = NumberConversion.intToByte(zahl, 4);
	op4.setContent(ret, 4);

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
	if (op4 instanceof AbsAddress && ((AbsAddress) op4).hasLabel()) {
	    ((AbsAddress) op4).setOrt(adress + 1 + op1.getOpCode().length
		    + op2.getOpCode().length + op3.getOpCode().length);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

	return "FINDS " + op1.toString() + ", " + op2.toString() + ", "
		+ op3.toString() + ", " + op4.toString();
    }
}
