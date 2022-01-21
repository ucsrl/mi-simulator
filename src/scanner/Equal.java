/**
 * 
 */
package scanner;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Diese Klasse wird f�r dier Verwaltung von der Compileroption Equal verwendet
 * 
 * @author Matthias Oehme
 */
public class Equal {

    /** Ersetzungsname */
    private String name;

    /** Tokenliste f�r die Ersetzung */
    private ArrayList<Token> makro = new ArrayList<Token>();

    /**
     * Konstruktor f�r ein neues Equal
     * 
     * @param name
     *            Ersetzungsname
     * @param makro
     *            Tokenliste f�r die Ersetzung
     */
    public Equal(String name, ArrayList<Token> makro) {
	this.name = name;
	this.makro = makro;
    }

    /**
     * gibt einen Iterator zur�ck
     * 
     * @return the iterator
     */
    public Iterator<Token> getIterator() {
	return makro.iterator();
    }

    /**
     * Gibt den Namen zur�ck
     * 
     * @return Name
     */
    public String getName() {
	return name;
    }
}
