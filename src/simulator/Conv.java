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
 * Conv-Befehl
 * @author Matthias Oehme
 * 
 */
public class Conv extends Command {
    /** erster Operand */
    Operand op1;

    /** zweiter Operand */
    Operand op2;

    /** Länge Befehls B = 1, H = 2, W = 4, F = 4, D = 8 */
    int length = 0;

    /**
     * Konstruktor für einen 2-Adress ADD-Befehl
     * 
     * @param line
     *            Zeile im Quelltext
     * @param adress
     *            Adresse des Befehls
     * @param op1
     *            erster Operand
     * @param op2
     *            zweiter Operand
     * @param beg
     *            Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end
     *            Zeichenposition - Ende des Befehlswortes im Quelltext
     */
    public Conv(int line, int adress, Operand op1, Operand op2, int beg, int end) {
	super(line, adress, beg, end);
	this.op1 = op1;
	this.op2 = op2;
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

	return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see compiler.Command#getOpCode()
     */
    @Override
    public MyByte[] getOpCode() {
	MyByte opcode = new MyByte("AC");

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
	MyByte[] neu = NumberConversion.intToByte(
		NumberConversion.myBytetoIntWithSign(op1.getContent()), 4);
	op2.setContent(neu, 4);

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

	return "CONV " + op1.toString() + ", " + op2.toString();
    }

}
