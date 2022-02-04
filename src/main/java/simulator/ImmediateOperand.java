package simulator;

import enviroment.MyByte;
import enviroment.NumberConversion;

/**
 * Operandenspezifikation für Direkte Operanden
 */
public class ImmediateOperand implements Operand {

    /**
     * Speicherdarstellung des Operanden
     */
    MyByte[] content = new MyByte[8];

    /**
     * Länge der Operanden
     */
    int length;

    /**
     * Konstruktor für Direkte Operanden
     *
     * @param content Speicherdarstellung des Operanden
     * @param length  Länge der Operanden
     */
    public ImmediateOperand(MyByte[] content, int length) {

        for (int i = (8 - length); i < 8; i++) {
            this.content[i] = content[i - (8 - length)];
        }

        this.length = length;
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Operand#copy()
     */
    @Override
    public Operand copy() {
        return new ImmediateOperand(getContent(), length);
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
     * @see compiler.Operand#getContent()
     */
    @Override
    public MyByte[] getContent() {
        switch (length) {
            case 1:
                return new MyByte[]{content[7]};
            case 2:
                return new MyByte[]{content[6], content[7]};
            case 4:
                return new MyByte[]{content[4], content[5], content[6], content[7]};
            case 8:
                return content;
        }
        return content;
    }

    /*
     * (non-Javadoc)
     *
     * @see interpreter.Operand#getOpCode()
     */
    @Override
    public MyByte[] getOpCode() {
        int merke = NumberConversion.myBytetoIntWithoutSign(getContent());
        if (merke >= 0 && merke < 64 && length != 8) {
            return new MyByte[]{content[7]};
        }

        MyByte[] ret = new MyByte[length + 1];
        ret[0] = new MyByte("8F");
        int x = 1;
        for (int i = (8 - length); i < 8; i++) {
            ret[x++] = content[i];
        }
        return ret;
    }

    /**
     * Gets the op code2.
     *
     * @return the op code2
     */
    public MyByte[] getOpCode2() {
        switch (length) {
            case 1:
                return new MyByte[]{content[7]};
            case 2:
                return new MyByte[]{content[6], content[7]};
            case 4:
                return new MyByte[]{content[4], content[5], content[6], content[7]};
            case 8:
                return content;
        }
        return content;
    }

    /*
     * (non-Javadoc)
     *
     * @see compiler.Operand#setContent(compiler.MyByte[], int)
     */
    @Override
    public void setContent(MyByte[] content, int length) {
        // not Implementet
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "I H'" + NumberConversion.myBytetoHex(getContent()) + "'";
    }

}
