package gui;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Diese Klasse wird für die Darstellung der Flags genutzt
 */
public class FixedStateCheckBox extends JCheckBox implements ItemListener {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Status der JCkeckBox
     */
    private boolean state;

    /**
     * Instaziiert eine FixedCheckBox
     *
     * @param text the text
     */
    public FixedStateCheckBox(String text) {
        setForeground(CONSTANTS.DARK_GREY);
        setText(text);
        addItemListener(this);
        setFocusable(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        setSelected(state);
    }

    /**
     * Setzt den Status der JCheckBox
     *
     * @param state neuer Status
     */
    public void setFixedState(boolean state) {
        this.state = state;
        setSelected(state);
    }

}