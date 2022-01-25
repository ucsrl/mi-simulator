package simulator;

import codegenerator.LabelInUse;
import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;

import java.util.ArrayList;

/**
 * Repr�sentation eines Div-Befehls
 *
 * @author Matthias Oehme
 */
public class Div extends Command {

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
     * true, falls es sich um einen 3-Adressbefehl handelt
     */
    boolean three = false;

    /**
     * true, falls es ein Gleitpunktzahlbefehl ist
     */
    boolean floating = false;

    /**
     * L�nge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     */
    int length = 0;

    /**
     * Konstruktor f�r einen 2-Adress DIV-Befehl
     *
     * @param line     Zeile im Quelltext
     * @param adress   Adresse des Befehls
     * @param length   L�nge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     * @param op1      erster Operand
     * @param op2      zweiter Operand
     * @param beg      Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end      Zeichenposition - Ende des Befehlswortes im Quelltext
     * @param floating true, falls es ein Gleitpunktzahlbefehl ist
     */
    public Div(int line, int adress, int length, Operand op1, Operand op2, int beg, int end,
               boolean floating) {
        super(line, adress, beg, end);
        this.op1 = op1;
        this.op2 = op2;
        this.length = length;
        setAdress(adress);
        this.floating = floating;
    }

    /**
     * Konstruktor f�r einen 3-Adress DIV-Befehl
     *
     * @param line     Zeile im Quelltext
     * @param adress   Adresse des Befehls
     * @param length   L�nge Befehls B = 1, H = 2, W = 4, F = 4, D = 8
     * @param op1      erster Operand
     * @param op2      zweiter Operand
     * @param op3      dritter Operand
     * @param beg      Zeichenposition - Beginn des Befehlswortes im Quelltext
     * @param end      Zeichenposition - Ende des Befehlswortes im Quelltext
     * @param floating true, falls es ein Gleitpunktzahlbefehl ist
     */

    public Div(int line, int adress, int length, Operand op1, Operand op2, Operand op3, int beg,
               int end, boolean floating) {
        this(line, adress, length, op1, op2, beg, end, floating);
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
                opcode = three ? new MyByte("E2") : new MyByte("DD");
                break;
            case 2:
                opcode = three ? new MyByte("E3") : new MyByte("DE");
                break;
            case 4:
                opcode = three ?
                        (floating ? new MyByte("E5") : new MyByte("E4")) :
                        (floating ? new MyByte("E0") : new MyByte("DF"));
                break;
            case 8:
                opcode = three ? new MyByte("E6") : new MyByte("E1");
                break;
            default:
                System.out.println("Fehler beim berechnen des OPCodes");
        }
        int x = 1;
        MyByte[] opc1 = op1.getOpCode();
        MyByte[] opc2 = op2.getOpCode();
        MyByte[] opc3 = three ? op3.getOpCode() : null;
        MyByte[] ret = new MyByte[three ?
                opc1.length + opc2.length + opc3.length + 1 :
                opc1.length + opc2.length + 1];
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
        MyByte[] erg = new MyByte[length];
        if (floating) {
            switch (length) {
                case 4:

                    Float ope1 = Float.intBitsToFloat(
                            NumberConversion.myBytetoIntWithoutSign(op1.getContent()));
                    Float ope2 = Float.intBitsToFloat(
                            NumberConversion.myBytetoIntWithoutSign(((three == false)
                                    && (op2 instanceof CellarAddressing)) ?
                                    ((CellarAddressing) op2).getContentWithoutOffset() :
                                    op2.getContent()));
                    Float erg_1 = ope2 / ope1;
                    erg = NumberConversion.intToByte(Float.floatToIntBits(erg_1),
                            length);

                    Enviroment.flags.setZero(erg_1 == 0);

                    Enviroment.flags.setNegative(erg_1 < 0);

                    Enviroment.flags.setOverflow(Float.isInfinite(erg_1)
                            || erg_1 == 0 && ope1 != 0
                            && ope2 != 0);

                    break;
                case 8:

                    Double ope11 = Double.longBitsToDouble(
                            NumberConversion.myBytetoLongWithoutSign(op1.getContent()));
                    Double ope22 = Double.longBitsToDouble(
                            NumberConversion.myBytetoLongWithoutSign(((three == false)
                                    && (op2 instanceof CellarAddressing)) ?
                                    ((CellarAddressing) op2).getContentWithoutOffset() :
                                    op2.getContent()));
                    Double erg_11 = ope22 / ope11;
                    erg = NumberConversion.intToByte(Double.doubleToLongBits(erg_11),
                            length);

                    Enviroment.flags.setZero(erg_11 == 0);

                    Enviroment.flags.setNegative(erg_11 < 0);

                    Enviroment.flags.setOverflow(Double.isInfinite(erg_11)
                            || erg_11 == 0 && ope11 != 0
                            && ope22 != 0);

                    break;
            }

        } else {
            MyByte[] ope1 = op1.getContent();
            MyByte[] ope2 = ((three == false) && (op2 instanceof CellarAddressing)) ?
                    ((CellarAddressing) op2).getContentWithoutOffset() :
                    op2.getContent();

            long erg1 = NumberConversion.myBytetoLongWithSign(ope2)
                    / NumberConversion.myBytetoLongWithSign(ope1);

            erg = NumberConversion.intToByte((int) erg1, length);

            Enviroment.flags.setZero(NumberConversion.myBytetoIntWithoutSign(erg) == 0);

            Enviroment.flags.setNegative(
                    NumberConversion.myBytetoIntWithoutSign(erg) < 0);

            Enviroment.flags.setOverflow(!NumberConversion.valid_number(erg1, length));

        }

        Enviroment.flags.setCarry(false);

        if (!three) {
            op2.setContent(erg, length);
        } else {
            op3.setContent(erg, length);
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
        return "DIV " + bhwfd + " " + op1.toString() + ", " + op2.toString() + (three ?
                ", " + op3.toString() :
                "");
    }

}
