/**
 *
 */
package codegenerator;

import simulator.AbsAddress;
import simulator.Command;

/**
 * Repr�sentiert einen Eintrag in der Symboltabelle f�r ein genutztes Label
 *
 * @author Cyberdyne
 */
public class LabelInUse {

    /** Name des Labels */
    private String name;

    /** Befehl in welchem das Label genutzt wird. */
    private Command command;

    /** Operand in welchem das Label genutzt wird */
    private AbsAddress op;

    /** Zugeh�riges definiertes Label */
    private Label label;

    /**
     * Instanziert ein genutzes Label
     *
     * @param com
     *            Befehl in welchem das Label genutzt wird
     * @param name
     *            name des Labels
     * @param op
     *            Operand in welchem das Label genutzt wird
     */
    public LabelInUse(Command com, String name, AbsAddress op) {
        command = com;
        this.name = name;
        this.op = op;
        this.label = null;
    }

    /**
     * Gibt die Adresse zur�ck an welcher das Label genutzt wird
     *
     * @return the adress
     */
    public int getAdress() {
        return op.getOrt();
    }

    /**
     * Gib den zugeh�rigen Befehl zur�ck
     *
     * @return zugeh�riger Befehl
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Gibt den Namen zur�ck
     *
     * @return Namen des Labels
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt den zugeh�rigen Operanden zur�ck
     *
     * @return zugeh�riger Operanden
     */
    public AbsAddress getOp() {
        return op;
    }

    /**
     * Vergleicht ein Labe mit einem LabelInUse auf Grundelage der Namen.
     *
     * @param label
     *            zu vergleichendes LabelInUse
     * @return ob die Namen gleich sind
     */
    @Override
    public boolean equals(Object label) {
        if (label instanceof Label)
            return name.equals(((Label) label).getName());
        return super.equals(label);
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }

}
