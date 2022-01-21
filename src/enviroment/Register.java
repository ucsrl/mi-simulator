package enviroment;

import java.awt.Color;

import gui.CONSTANTS;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Diese Klasse repaesentier ein Register der MI
 */
public class Register {

    /** Inhalt des Registers */
    MyByte[] content = new MyByte[4];

    /** Stackregister? */
    private boolean isStack = false;

    /** Darstellung des Registers */
    private JTextField tfield = new JTextField("0", 15);

    /** Registernr */
    private int nr;

    /** geandert? für die Rotfaerbung erforderlich */
    private boolean changed = false;

    /**
     * Instanziiert ein Register
     * 
     * @param nr
     *            Registerbummer
     */
    public Register(int nr) {
	content = NumberConversion.intToByte(0, 4);
	tfield.setFont(CONSTANTS.FONT);
	tfield.setHorizontalAlignment(SwingConstants.RIGHT);
	tfield.setFocusable(false);
	this.nr = nr;
    }

    /**
     * Instanziiert ein Register
     * 
     * @param isStack
     *            Stackregister
     */
    public Register(int nr, boolean isStack) {
	this(nr);
	this.isStack = isStack;
    }

    /**
     * Gibt den Inhalt des Registers zurueck
     * 
     * @param length
     *            Länge des Registerzurgriffs
     * @return Inhalt des Registers
     */
    public MyByte[] getContent(int length) {
	MyByte[] ret;
	switch (length) {
	case 1:
	    return new MyByte[] { new MyByte(content[3].getContent()) };
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
     * @param length
     *            Länge des 
     * @return Inhalt eines Register
     */
    public int getContentAsInt(int length) {
	return NumberConversion.myBytetoIntWithSign(getContent(length));
    }

    /**
     * Gibt die Registerdarstellung zurueck
     * 
     * @param regview
     *            Art der Darstellung
     * @return Registerdarstellung
     */
    public JTextField getTextField(int regview) {
	String text = "";
	switch (regview) {
	case 0:
	    text = Integer.toString(NumberConversion
		    .myBytetoIntWithSign(content));
	    break;
	case 1:
	    text = NumberConversion.myBytetoBin(content);
	    break;
	case 2:
	    text = NumberConversion.myBytetoHex(content);
	    break;
	case 3:

	    Float zahl = Float.intBitsToFloat(NumberConversion
		    .myBytetoIntWithoutSign(content));
	    text = Float.toString(Float.intBitsToFloat(NumberConversion
		    .myBytetoIntWithoutSign(content)));
	    if (zahl.isNaN()) {
		text = "NaN";
	    }
	    break;
	}
	tfield.setText(text);
	tfield.setForeground(changed ? Color.red : Color.black);
	if (text.length() > 10) {
	    tfield.setToolTipText(text);
	}
	return tfield;
    }

    /**
     * Setzt den Inhalt eines Registers
     * 
     * @param content
     *            Inhalt
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
	    Enviroment.REGISTERS.getRegister(
		    (nr + 1) % CONSTANTS.NUMBER_OF_REGISTER).setContent(
		    new MyByte[] { content[4], content[5], content[6],
			    content[7] });
	    break;
	}
	if (isStack && Enviroment.STACKBEGIN == 0) {
	    Enviroment.STACKBEGIN = NumberConversion
		    .myBytetoIntWithSign(content);
	}

	changed = true;

    }

    /**
     * Aktualisiert die Registerdarstellung
     * 
     * @param regview
     *            Registerdarstellung
     */
    public void update(int regview) {
	String text = "";
	switch (regview) {
	case 0:
	    text = Integer.toString(NumberConversion
		    .myBytetoIntWithSign(content));
	    break;
	case 1:
	    text = NumberConversion.myBytetoBin(content);
	    break;
	case 2:
	    text = NumberConversion.myBytetoHex(content);
	    break;
	}
	tfield.setText(text);
	tfield.setToolTipText(text.length() > 15 ? text : "");

    }
    
    /**
     * Setzt den Änderungsstatus eines Register zurück
     * 
     */
    public void reset() {
	changed = false;
    }

}
