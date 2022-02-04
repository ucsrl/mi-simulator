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
 * Clear-Befehl
 *
 * @author Matthias Oehme
 */
public class Clear extends Command {

    /**
     * erster Operand
     */
    Operand op1;

    /**
     * Länge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     */
    int length = 0;

    /**
     * true, falls es ein Gleitpunktzahlbefehl ist
     */
    boolean floating = false;

    /**
     * Konstruktor für einen Clear-Befehl
     *
     * @param line
     *            Zeile im Quelltext
     * @param adress
     *            Adresse des Befehls
     * @param length
     *            Länge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     * @param op1
     *            erster Operand
     * @param beg
     *            Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end
     *            Zeichenposition - Ende des Befehlswortes im Quelltext
     * @param floating
     *            true, falls es ein Gleitpunktzahlbefehl ist
     */
    public Clear(int line, int adress, int length, Operand op1, int beg, int end,
                 boolean floating) {
        super(line, adress, beg, end);
        this.floating = floating;
        this.op1 = op1;
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
                opcode = new MyByte("99");
                break;
            case 2:
                opcode = new MyByte("9A");
                break;
            case 4:
                opcode = new MyByte(floating ? "9C" : "9B");
                break;
            case 8:
                opcode = new MyByte("9D");
                break;
            default:
                System.out.println("Fehler beim berechnen des OPCodes");
        }
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
    public void run() {
        super.run();
        op1.setContent(NumberConversion.intToByte(0, length), length);
        Enviroment.flags.setOverflow(false);
        Enviroment.flags.setNegative(false);
        Enviroment.flags.setZero(true);
        super.run();

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
        return "CLEAR " + bhwfd + " " + op1.toString();
    }

}
