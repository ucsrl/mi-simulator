package simulator;

import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;
import gui.CONSTANTS;

import java.util.ArrayList;

import codegenerator.LabelInUse;

/**
 * Call-Befehl
 * 
 * @author Matthias Oehme
 */
public class Call extends Command {

    /** erster Operand */
    Operand op1;

    /** true, falls es sich um einen 3-Adressbefehl handelt */
    boolean three = false;

    /** true, falls es ein Gleitpunktzahlbefehl ist */
    boolean floating = false;

    /** L�nge Befehls B = 1, H = 2, W = 4, F = 4, D = 8 */
    int length = 0;

    /**
     * Konstruktor f�r einen Call-Befehl
     * 
     * @param line
     *            Zeile im Quelltext
     * @param adress
     *            Adresse des Befehls
     * @param length
     *            L�nge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     * @param op1
     *            erster Operand
     * @param beg
     *            Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end
     *            Zeichenposition - Ende des Befehlswortes im Quelltext
     */
    public Call(int line, int adress, int length, Operand op1, int beg, int end) {
	super(line, adress, beg, end);
	this.op1 = op1;
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
	opcode = new MyByte("F2");

	int x = 1;
	MyByte[] opc1 = op1.getOpCode();

	MyByte[] ret = new MyByte[opc1.length + 1];
	ret[0] = opcode;
	for (int i = 0; i < opc1.length; i++) {
	    ret[x + i] = opc1[i];
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
	return ((op1 instanceof AbsAddress) && (((AbsAddress) op1).hasLabel()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see compiler.Command#run()
     */
    @Override
    public synchronized void run() {
	int ziel = 0;
	if (op1 instanceof AbsAddress) {
	    ziel = ((AbsAddress) op1).getAdress();
	} else {
	    ziel = op1.getAdress();
	}
	CellarAddressing test = new CellarAddressing(CONSTANTS.SP_REGISTER, 4,
		-4, false);

	test.setContent(
		NumberConversion.intToByte(Enviroment.getPC()
			+ getOpCode().length, 4), 4);

	Enviroment.REGISTERS.getRegister(CONSTANTS.PC_REGISTER).setContent(
		NumberConversion.intToByte(ziel, 4));
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CALL " + op1.toString();
    }

}
