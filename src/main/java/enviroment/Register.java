package enviroment;

import gui.CONSTANTS;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Diese Klasse repaesentier ein Register der MI
 */
public class Register {

    /**
     * Inhalt des Registers
     */
    private int content;

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
        content = 0;
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
        assert length == 1 || length == 2 || length == 4 || length == 8;
        ByteBuffer buffer = ByteBuffer.allocate(length).order(ByteOrder.BIG_ENDIAN);
        buffer.putInt(content);
        if (length == 8) {
            Register next = Enviroment.REGISTERS.getRegister(
                    (nr + 1) % CONSTANTS.NUMBER_OF_REGISTER);
            buffer.putInt(next.getContentAsNumber(4));
        }

        byte[] bytes = buffer.array();
        MyByte[] result = new MyByte[length];
        for (int i = 0; i < length; i++) {
            result[i] = new MyByte(bytes[i]);
        }

        return result;
    }

    /**
     * Gibt den Inhalt eines Registers als Integer zurueck
     *
     * @param length Länge des
     * @return Inhalt eines Register
     */
    public int getContentAsNumber(int length) {
        assert length > 0 && length <= 4;
        // TODO: strip to length
        return this.content;
    }

    public void setContentAsNumber(int value) {
        this.content = value;
    }

    /**
     * Setzt den Inhalt eines Registers
     *
     * @param content Inhalt
     */
    public void setContent(MyByte[] content) {
        int length = content.length;
        assert length == 1 || length == 2 || length == 4 || length == 8;

        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.BIG_ENDIAN);
        for (int i = 0; i < 4; i++) {
            buffer.put((byte) content[i].getContent());
        }
        this.content = buffer.rewind().getInt();
        if (content.length == 8) {
            Register next = Enviroment.REGISTERS.getRegister((nr + 1) % CONSTANTS.NUMBER_OF_REGISTER);
            next.setContent(new MyByte[]{content[4], content[5], content[6], content[7]});
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
