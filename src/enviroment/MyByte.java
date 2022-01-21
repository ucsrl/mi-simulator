package enviroment;

/**
 * Diese Klasse repraesentier ein Byte
 */
public class MyByte {

    /** Wert den Bytes als Integer */
    private int content;

    /** Carryflag */
    private boolean carry = false;

    /**
     * Konstruktor
     * 
     * @param content
     *           Wert den Bytes
     */
    public MyByte(int content) {
	this.content = content % 256;
	if (content > 255) {
	    carry = true;
	}
    }

    /**
     * Konstruktor
     * 
     * @param hex
     *            Wert den Bytes als Hexzahl
     */
    public MyByte(String hex) {
	int content = Integer.parseInt(hex, 16);
	this.content = content % 256;
	if (content > 255) {
	    carry = true;
	}
    }

    /**
     * Addiert zwei MyBytes
     * 
     * @param opp
     *            MyBytes
     * @return Ergbenis als MyByte
     */
    public MyByte add(MyByte opp) {
	return new MyByte((content + opp.getContent()));
    }

    /**
     * Erstellt eine Kopie des Objekts
     * 
     * @return Kopie des Objekts
     */
    public MyByte copy() {
	return new MyByte(getCarry() ? content + 256 : content);
    }

    /**
     * Vergleicht zwei MyByte
     * 
     * @param one
     *           MyByte
     * @return true, if successful
     */
    public boolean equal(MyByte one) {
	return getContent() == one.getContent();
    }

    /**
     * Gibt das Carry zurueck
     * 
     * @return Carry 
     */
    public boolean getCarry() {
	return carry;
    }

    /**
     * Gibt den Inhalt als Integer zurueck
     * 
     * @return Inhalt als Integer
     */
    public int getContent() {
	return content;
    }

    /**
     * Negiert ein MyBYte
     * 
     * @return zu negierendes MyByte
     */
    public MyByte negate() {
	StringBuffer neu = new StringBuffer();
	String bin = Integer.toBinaryString(content);
	for (int i = 0; i < bin.length(); i++) {
	    switch (bin.getBytes()[i]) {
	    case '0':
		neu.append("1");
		break;
	    case '1':
		neu.append("0");
		break;
	    }

	}
	int x = neu.length();
	for (int i = 0; i < 8 - x; i++) {
	    neu = neu.reverse().append("1").reverse();
	}
	return new MyByte(Integer.parseInt(neu.toString(), 2));

    }

    /**
     * Setzt den Inhalt des MyByte
     * 
     * @param con
     *            neuer Inhalt
     */
    public void setContent(int con) {
	content = con;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	String ret = Integer.toString(content, 16).toUpperCase();

	return (ret.length() == 1) ? "0" + ret : ret;
    }
}
