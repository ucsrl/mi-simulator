/**
 *
 */
package gui;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

/**
 * Die Klasse beinhaltet das ListModel für die Speicherdarstellung
 *
 * @author Matthias oehme
 */
public class MemoryTable implements ListModel {

    /** Daten einer Speicherzeile */
    private ArrayList<MemoryTableEntry> data;

    /**
     * Instantiates a new memory table.
     *
     * @param in
     *            the in
     */
    public MemoryTable(ArrayList<MemoryTableEntry> in) {
        data = in;

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#addListDataListener(javax.swing.event.
     * ListDataListener )
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public Object getElementAt(int index) {
        return data.get(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return data.size();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.
     * ListDataListener)
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub

    }

}
