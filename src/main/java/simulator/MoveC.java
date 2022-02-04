/**
 *
 */
package simulator;

import codegenerator.LabelInUse;
import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;

import java.util.ArrayList;

/**
 * MoveC-Befehl
 *
 * @author Matthias Oehme
 */
public class MoveC extends Command {
    /**
     * erster Operand
     */
    Operand op1;

    /**
     * zweiter Operand
     */
    Operand op2;

    /**
     * Länge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     */
    int length = 0;

    /**
     * Konstruktor für einen MoveC-Befehl
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
    public MoveC(int line, int adress, int length, Operand op1, Operand op2, int beg, int end) {
        super(line, adress, beg, end);
        this.op1 = op1;
        this.op2 = op2;
        this.length = length;
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
        MyByte opcode = null;
        switch (length) {
            case 1:
                opcode = new MyByte("A8");
                break;
            case 2:
                opcode = new MyByte("A9");
                break;
            case 4:
                opcode = new MyByte("AA");
                break;
            default:
                System.out.println("Fehler beim berechnen des OPCodes");
        }
        int x = 1;
        MyByte[] opc1 = op1.getOpCode();
        MyByte[] opc2 = op2.getOpCode();

        MyByte[] ret = new MyByte[opc1.length + opc2.length + 1];
        ret[0] = opcode;
        for (int i = 0; i < opc1.length; i++) {
            ret[x++] = opc1[i];
        }
        for (int i = 0; i < opc2.length; i++) {
            ret[x++] = opc2[i];
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
                (op2 instanceof AbsAddress) && (((AbsAddress) op2).hasLabel()));
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Command#run()
     */
    @Override
    public synchronized void run() {
        super.run();
        op2.setContent(NumberConversion.complement(op1.getContent()), length);
        Enviroment.flags.setOverflow(false);

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
                bhwfd = "W";
                break;

        }
        return "MOVEC " + bhwfd + " " + op1.toString() + ", " + op2.toString();
    }

}
