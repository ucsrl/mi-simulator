package simulator;

import enviroment.MyByte;

/**
 * Interface für eine Operandenspezifikation
 */
public interface Operand {

    public Operand copy();

    /**
     * Gibt des Adresse des Operanden zurück
     *
     * @return Adresse des Operanden
     */
    public int getAdress();

    /**
     * Gibt den Inhalt des Operanden zurück
     *
     * @return Inhalt als MyByte-Array
     */
    public MyByte[] getContent();

    /**
     * Generiert den Opcode des Operanden und gibt diesen als MyByte-Array
     * zurück
     *
     * @return Opcode des Operanden
     */
    public MyByte[] getOpCode();

    /**
     * Setzt den Inhalt des Operanden.
     *
     * @param content neues Inhalt als MyByte-Array
     * @param length  Anzahl der Bytes die neugesetzt werden
     */
    public void setContent(MyByte[] content, int length);
}
