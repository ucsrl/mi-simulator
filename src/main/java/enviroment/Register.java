package enviroment;

import gui.CONSTANTS;

/**
 * Diese Klasse repaesentier ein Register der MI
 */
public class Register {

    /**
     * Inhalt des Registers
     */
    MyByte[] content = new MyByte[4];

    /**
     * Stackregister?
     */
    private boolean isStack = false;

    /**
     * Registernr
     */
    private int nr;

    /**
     * geandert? für die Rotfaerbung erforderlich
     */
    private boolean changed = false;

    /**
     * Instanziiert ein Register
     *
     * @param nr Registerbummer
     */
    public Register(int nr) {
        content = NumberConversion.intToByte(0, 4);
        this.nr = nr;
    }

    /**
     * Instanziiert ein Register
     *
     * @param isStack Stackregister
     */
    public Register(int nr, boolean isStack) {
        this(nr);
        this.isStack = isStack;
    }

    /**
     * Gibt den Inhalt des Registers zurueck
     *
     * @param length Länge des Registerzurgriffs
     * @return Inhalt des Registers
     */
    public MyByte[] getContent(int length) {
        MyByte[] ret;
        switch (length) {
            case 1:
                return new MyByte[]{new MyByte(content[3].getContent())};
            case 2:
                ret = new MyByte[2];
                ret[0] = new MyByte(content[2].getContent());
                ret[1] = new MyByte(content[3].getContent());
                return ret;
            case 4:
                ret = new MyByte[4];
                ret[0] = new MyByte(content[0].getContent());
                ret[1] = new MyByte(content[1].getContent());
                ret[2] = new MyByte(content[2].getContent());
                ret[3] = new MyByte(content[3].getContent());
                return ret;
            case 8:
                ret = new MyByte[8];
                ret[0] = new MyByte(content[0].getContent());
                ret[1] = new MyByte(content[1].getContent());
                ret[2] = new MyByte(content[2].getContent());
                ret[3] = new MyByte(content[3].getContent());
                ret[4] = Enviroment.REGISTERS.getRegister(
                        (nr + 1) % CONSTANTS.NUMBER_OF_REGISTER).getContent(4)[0];
                ret[5] = Enviroment.REGISTERS.getRegister(
                        (nr + 1) % CONSTANTS.NUMBER_OF_REGISTER).getContent(4)[1];
                ret[6] = Enviroment.REGISTERS.getRegister(
                        (nr + 1) % CONSTANTS.NUMBER_OF_REGISTER).getContent(4)[2];
                ret[7] = Enviroment.REGISTERS.getRegister(
                        (nr + 1) % CONSTANTS.NUMBER_OF_REGISTER).getContent(4)[3];
                return ret;
            default:
                System.out.println("Fehlerhafte Länge bei Registerzugriff");
                return null;
        }

    }

    /**
     * Gibt den Inhalt eines Registers als Integer zurueck
     *
     * @param length Länge des
     * @return Inhalt eines Register
     */
    public int getContentAsInt(int length) {
        return NumberConversion.myBytetoIntWithSign(getContent(length));
    }

    /**
     * Setzt den Inhalt eines Registers
     *
     * @param content Inhalt
     */
    public void setContent(MyByte[] content) {
        switch (content.length) {
            case 1:
                this.content[3] = new MyByte(content[0].getContent());
                break;
            case 2:
                this.content[2] = new MyByte(content[0].getContent());
                this.content[3] = new MyByte(content[1].getContent());
                break;
            case 4:
                this.content[0] = new MyByte(content[0].getContent());
                this.content[1] = new MyByte(content[1].getContent());
                this.content[2] = new MyByte(content[2].getContent());
                this.content[3] = new MyByte(content[3].getContent());
                break;
            case 8:
                this.content[0] = new MyByte(content[0].getContent());
                this.content[1] = new MyByte(content[1].getContent());
                this.content[2] = new MyByte(content[2].getContent());
                this.content[3] = new MyByte(content[3].getContent());
                Enviroment.REGISTERS.getRegister((nr + 1) % CONSTANTS.NUMBER_OF_REGISTER)
                        .setContent(new MyByte[]{content[4], content[5],
                                content[6], content[7]});
                break;
        }
        if (isStack && Enviroment.STACKBEGIN == 0) {
            Enviroment.STACKBEGIN = NumberConversion.myBytetoIntWithSign(content);
        }

        changed = true;

    }

    /**
     * Setzt den Änderungsstatus eines Register zurück
     */
    public void reset() {
        changed = false;
    }

    public boolean isChanged() {
        return changed;
    }
}
