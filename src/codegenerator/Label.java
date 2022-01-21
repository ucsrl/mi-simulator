package codegenerator;

import simulator.Command;

/**
 * Diese Klasse repr�sentiert ein definiertes Label f�r die Symboltabelle -
 * CodeGenerator
 * 
 * @author Matthias Oehme
 */
public class Label {

    /** Name des Labels */
    private String name;

    /** Befehl auf welches das Label zeigt */
    private Command ziel;

    /**
     * Instanziiert ein neues label.
     * 
     * @param name
     *            Name des Labels
     * @param ziel
     *            Befehl auf welches das Label zeigt
     */
    public Label(String name, Command ziel) {
	this.name = name;
	this.ziel = ziel;
    }

    /**
     * Gibt die Adresse des zugeh�rigen Befehls zur�ck
     * 
     * @return Adresse des zugeh�rigen Befehls zur�ck
     */
    public int getAdress() {
	return ziel.getAdress();
    }

    /**
     * Gibt den Namen des Labels zur�ck.
     * 
     * @return Name des Labels
     */
    public String getName() {
	return name;
    }

    /**
     * Vergleicht ein LabelInUse mit einem Label auf Grundelage der Namen.
     * 
     * @param label
     *            zu vergleichendes Label
     * @return ob die Namen gleich sind
     */
    @Override
    public boolean equals(Object label) {
	if (label instanceof LabelInUse)
	    return name.equals(((LabelInUse) label).getName());
	return super.equals(label);
    }

    
    public String toString() {
	return name + "   " + Integer.toHexString(ziel.getAdress());
    }
}
