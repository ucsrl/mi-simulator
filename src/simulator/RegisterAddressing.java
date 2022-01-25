package simulator;

import enviroment.Enviroment;
import enviroment.MyByte;
import enviroment.NumberConversion;

/**
 * Operandenspezifikation für die Registeradressierung
 *
 * @author Matthias Oehme
 */
public class RegisterAddressing implements Operand {

    /**
     * Regsiternummer
     */
    int nr;

    /**
     * Länge des Registerzugriffs
     */
    int length;

    /**
     * Konstruktor für die Registeradressierung
     *
     * @param nr     Regsiternummer
     * @param length Länge des Registerzugriffs
     */
    public RegisterAddressing(int nr, int length) {
        this.nr = nr;
        this.length = length;
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Operand#copy()
     */
    @Override
    public Operand copy() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see interpreter.Operand#getAdress()
     */
    @Override
    public int getAdress() {
        return NumberConversion.myBytetoIntWithoutSign(
                Enviroment.REGISTERS.getRegister(nr, 4));
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Operand#getContent()
     */
    @Override
    public MyByte[] getContent() {
        return Enviroment.REGISTERS.getRegister(nr, length);
    }

    /*
     * (non-Javadoc)
     *
     * @see interpreter.Operand#getOpCode()
     */
    @Override
    public MyByte[] getOpCode() {
        return new MyByte[]{new MyByte(5 * 16 + nr)};
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Operand#setContent(compiler.MyByte[], int)
     */
    @Override
    public void setContent(MyByte[] content, int length) {
        Enviroment.REGISTERS.getRegister(nr).setContent(content);

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "R" + Integer.toString(nr);
    }

}
