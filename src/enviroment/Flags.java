package enviroment;

import gui.FixedStateCheckBox;

import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * Diese Klasse repraesentiert die Flags der MI
 */
public class Flags {

    /** Carry-Flag */
    private boolean carry = false;

    /** Zero-Flag. */
    private boolean zero = false;

    /** Overflow-Flag */
    private boolean overflow = false;

    /** Negative-Flag */
    private boolean negative = false;

    /** CheckBox für die Darstellung des Carry-Flag */
    private FixedStateCheckBox carry_cb = new FixedStateCheckBox("Carry");

    /** CheckBox für die Darstellung des Zero-Flag */
    private FixedStateCheckBox zero_cb = new FixedStateCheckBox("Zero");;

    /** CheckBox für die Darstellung des Overflow-Flag */
    private FixedStateCheckBox overflow_cb = new FixedStateCheckBox("Overflow");

    /** CheckBox für die Darstellung des Negative-Flag */
    private FixedStateCheckBox negative_cb = new FixedStateCheckBox("Negative");

    /**
     * Gibt eine JPanel mit den Flags zurueck
     * 
     * @return JPanel mit den Flags
     */
    public JPanel getFlags() {
	update();

	JPanel ret_inner = new JPanel();
	JPanel ret_outer = new JPanel();
	ret_inner.setLayout(new FlowLayout(10));
	ret_outer.setLayout(new FlowLayout(FlowLayout.RIGHT));

	ret_inner.add(zero_cb);

	ret_inner.add(overflow_cb);

	ret_inner.add(carry_cb);

	ret_inner.add(negative_cb);

	ret_outer.add(ret_inner);

	return ret_outer;
    }

    /**
     * Prueft ob das Carry-Flag gesetzt ist
     * 
     * @return Carry-Flag gesetzt
     */
    public boolean isCarry() {
	return carry;
    }

    /**
     * Prueft ob das Negative-Flag gesetzt ist
     * 
     * @return Negative-Flag gesetzt
     */
    public boolean isNegative() {
	return negative;
    }

    /**
     * Prueft ob das Overflow-Flag gesetzt ist
     * 
     * @return Overflow-Flag gesetzt
     */
    public boolean isOverflow() {
	return overflow;
    }

    /**
     * Prueft ob das Zero-Flag gesetzt ist
     * 
     * @return Zero-Flag gesetzt
     */
    public boolean isZero() {
	return zero;
    }

    /**
     * Setzt das Carry-Flag
     * 
     * @param carry
     *            Carry-Flag
     */

    public void setCarry(boolean carry) {
	this.carry = carry;
    }

    /**
     * Setzt das Negative-Flag
     * 
     * @param negative
     *            Negative-Flag
     */
    public void setNegative(boolean negative) {
	this.negative = negative;
    }

    /**
     * Setzt das Overflow-Flag
     * 
     * @param overflow
     *            Overflow-Flag
     */
    public void setOverflow(boolean overflow) {
	this.overflow = overflow;
    }

    /**
     * Setzt das Zero-Flag
     * 
     * @param zero
     *            Zero-Flag
     */
    public void setZero(boolean zero) {
	this.zero = zero;
    }

    /**
     * Aktualisiert die Flagdarstellung
     */
    public void update() {
	overflow_cb.setFixedState(overflow);
	// overflow_cb.paintImmediately(overflow_cb.getBounds());
	negative_cb.setFixedState(negative);
	// negative_cb.paintImmediately(negative_cb.getBounds());
	zero_cb.setFixedState(zero);
	// zero_cb.paintImmediately(zero_cb.getBounds());
	carry_cb.setFixedState(carry);
	// carry_cb.paintImmediately(carry_cb.getBounds());
    }

}
