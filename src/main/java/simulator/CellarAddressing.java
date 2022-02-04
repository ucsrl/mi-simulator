/**
 *
 */
package simulator;

import enviroment.MyByte;
import enviroment.NumberConversion;

/**
 * Operandenspezifikation für die Kelleradressierung
 *
 * @author Matthias Oehme
 */
public class CellarAddressing implements Operand {

    /** Register */
    private RegisterAddressing register;

    /** Länge des Registerzugriffs */
    private int length;

    /** Offset für das Weiteschalten */
    private int offset;

    /** positiv weitschalten? */
    private boolean plus;

    /** Registernummer */
    private int nr;

    /**
     * Instantiates a new cellar addressing.
     *
     * @param register
     *            Registernummer
     * @param length
     *            Länge des Registerzugriffs
     * @param offset
     *            Offset für das Weiteschalten
     * @param plus
     *            positiv weitschalten
     */
    public CellarAddressing(int register, int length, int offset, boolean plus) {
        this.register = new RegisterAddressing(register, 4);
        nr = register;
        this.length = length;
        this.offset = offset;
        this.plus = plus;
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Operand#copy()
     */
    @Override
    public Operand copy() {
        return new CellarAddressing(nr, length, offset, plus);
    }

    /*
     * (non-Javadoc)
     *
     * @see interpreter.Operand#getAdress()
     */
    @Override
    public int getAdress() {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see interpreter.Operand#getContent()
     */
    @Override
    public MyByte[] getContent() {
        MyByte[] val = NumberConversion.add(register.getContent(),
                NumberConversion.intToByte(offset, 4));
        if (!plus)
            register.setContent(val, length);
        int adr = NumberConversion.myBytetoIntWithoutSign(register.getContent());
        MyByte[] ret = new AbsAddress(adr, length, 0).getContent();
        if (plus)
            register.setContent(val, length);
        return ret;
    }

    /**
     * Gibt den inhalt ohne Weiterschalten des Offsets zurück
     *
     * @return the content without offset
     */
    public MyByte[] getContentWithoutOffset() {

        MyByte[] ret = new AbsAddress(
                NumberConversion.myBytetoIntWithoutSign(register.getContent()), length,
                0).getContent();

        return ret;
    }

    /*
     * (non-Javadoc)
     *
     * @see interpreter.Operand#getOpCode()
     */
    @Override
    public MyByte[] getOpCode() {
        return new MyByte[]{plus ? new MyByte(8 * 16 + nr) : new MyByte(7 * 16 + nr)};
    }

    /*
     * (non-Javadoc)
     *
     * @see interpreter.Operand#setContent(interpreter.MyByte[], int)
     */
    @Override
    public void setContent(MyByte[] content, int length) {
        if (!plus) {
            register.setContent(NumberConversion.add(register.getContent(),
                            NumberConversion.intToByte(offset,
                                    4)),
                    length);
        }
        new AbsAddress(NumberConversion.myBytetoIntWithoutSign(register.getContent()), 4,
                0).setContent(content, length);
        if (plus) {
            register.setContent(NumberConversion.add(register.getContent(),
                            NumberConversion.intToByte(offset,
                                    4)),
                    length);
        }

    }

    /**
     * Sets the content without offset.
     *
     * @param content
     *            the content
     * @param length
     *            the length
     */
    public void setContentWithoutOffset(MyByte[] content, int length) {
        new AbsAddress(NumberConversion.myBytetoIntWithoutSign(register.getContent()), 4,
                0).setContent(content, length);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return plus ? ("!R" + Integer.toString(nr) + "+") : "-!R" + Integer.toString(nr);
    }

}
