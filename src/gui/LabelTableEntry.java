/**
 *
 */
package gui;

import enviroment.Enviroment;
import enviroment.MyByte;

import java.util.ArrayList;

/**
 * @author Cyberdyne
 *
 */
public class LabelTableEntry {

    /** The data. */
    ArrayList<MyByte> data;

    /** The adr. */
    String adr;

    /**
     * Konstruktor für einen Speicherzelleneintrag
     *
     * @param data
     *            Daten der Speicherzelle
     * @param adr
     *            the adr
     */
    public LabelTableEntry(ArrayList<MyByte> data, String adr) {
        this.data = data;
        this.adr = adr;
    }

    /**
     * Setzt die Daten der Speicherzellen
     *
     * @param data
     *            geänderte Daten
     */
    public void setData(ArrayList<MyByte> data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuffer ret2 = new StringBuffer(adr + " ");
        boolean html = false;
        for (MyByte in : data) {
            if (Enviroment.MEMORY.getChangedList().contains(in)) {
                ret2.append(
                        "<font color=\"#ff0000\">" + in.toString() + " </font>");
                html = true;
            } else {
                ret2.append(in.toString() + " ");
            }
        }
        return html ? "<html>" + ret2.toString() + "</html>" : ret2.toString();
    }

}
