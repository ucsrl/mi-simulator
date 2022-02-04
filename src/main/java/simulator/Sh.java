package simulator;

import codegenerator.LabelInUse;
import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;

import java.util.ArrayList;

/**
 * Sh-Befehl
 *
 * @author Matthias Oehme
 */
public class Sh extends Command {

    /**
     * erster Operand
     */
    Operand op1;

    /**
     * zweiter Operand
     */
    Operand op2;

    /**
     * dritter Operand
     */
    Operand op3;

    /**
     * Konstruktor für einen Sh-Befehl
     *
     * @param line   Zeile im Quelltext
     * @param adress Adresse des Befehls
     * @param op1    erster Operand
     * @param op2    zweiter Operand
     * @param op3    dritter Operand
     * @param beg    Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end    Zeichenposition - Ende des Befehlswortes im Quelltext
     */
    public Sh(int line, int adress, Operand op1, Operand op2, Operand op3, int beg, int end) {
        super(line, adress, beg, end);
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
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
        opcode = new MyByte("E7");

        int x = 1;
        MyByte[] opc1 = op1.getOpCode();
        MyByte[] opc2 = op2.getOpCode();
        MyByte[] opc3 = op3.getOpCode();
        MyByte[] ret = new MyByte[opc1.length + opc2.length + opc3.length + 1];
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

        return ret;

    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Command#hasLabel()
     */
    @Override
    public boolean hasLabel() {
        return ((op1 instanceof AbsAddress) && (((AbsAddress) op1).hasLabel())) || (
                (op2 instanceof AbsAddress) && (((AbsAddress) op2).hasLabel())) || (
                (op3 instanceof AbsAddress) && (((AbsAddress) op3).hasLabel()));
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Command#run()
     */
    @Override
    public synchronized void run() {
        super.run();
        MyByte[] ope2 = op2.getContent();
        int ope1 = NumberConversion.myBytetoIntWithSign(op1.getContent());
        MyByte[] erg = NumberConversion.intToByte(ope1 < 0 ?
                NumberConversion.myBytetoIntWithSign(
                        ope2) >>> -ope1 :
                NumberConversion.myBytetoIntWithSign(
                        ope2) << ope1, 4);

        op3.setContent(erg, 4);

        Enviroment.flags.setCarry(false);

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
            ((AbsAddress) op3).setOrt(
                    adress + 1 + op1.getOpCode().length + op2.getOpCode().length);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return "SH " + op1.toString() + ", " + op2.toString() + ", " + op3.toString();
    }

}
