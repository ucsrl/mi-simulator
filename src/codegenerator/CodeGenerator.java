/**
 *
 */
package codegenerator;

import enviroment.Enviroment;
import parser.Parser;
import simulator.Command;

import java.util.ArrayList;

/**
 * Diese Klasse bereitet die Befehle f�r die Generierung des Maschinencodes vor.
 *
 * @author Matthias Oehme
 */
public class CodeGenerator {

    /** Liste der definierten Label */
    private ArrayList<Label> defindedLabel = new ArrayList<Label>();

    /** Liste der genutzten Label */
    private ArrayList<LabelInUse> usedLabel = new ArrayList<LabelInUse>();

    /** Zugeh�riges Programm im Zwischencode */
    private Program program;

    /** Zugeh�riger Parser */
    private Parser parser;

    /**
     * Instaziiert einen neuen Codegenerator
     *
     * @param parser
     *            Parser, welcher den Generator nutzt
     */
    public CodeGenerator(Parser parser) {
        this.parser = parser;
    }

    /**
     * F�gt ein weiteres Label zur Liste der definierten Label hinzu
     *
     * @param lab
     *            definiertes Label
     */
    public void addLabel(Label lab) {
        for (Label label : defindedLabel) {
            if (label.getName().equals(lab.getName())) {
                parser.getErrorMeassge().append("Das Label " + lab.getName()
                        + " wurde mehrfach definiert");
            }
        }
        defindedLabel.add(lab);
    }

    /**
     * F�gt ein weiteres Label zur Liste der genutzten Label hinzu
     *
     * @param label
     *            genutztes label
     */
    public void addLabelInUse(LabelInUse label) {
        usedLabel.add(label);
    }

    /**
     * Verschiebt die Befehle an einer bestimmten Adresse um das �bergegebene
     * Offset
     *
     * @param begin
     *            Adresse bei der der Algorithmus beginnt
     * @param offset
     *            Offset um welches der Befehle verschoben werden sollen
     */
    private void aktAddress(int begin, int offset) {
        Command last = null;
        for (Command comm : program.getCommands()) {
            if (comm.getAdress() >= begin) {
                comm.setAdress(last == null ?
                        comm.getAdress() + offset :
                        last.getAdress() + last.getOpCode().length);
                last = comm;
            }
        }
    }

    /**
     * Pr�ft ob alle Label, die genutzt werden auch definiert sind.
     *
     * @return true, wenn alle Label definiert sind
     */
    public boolean precheckLabel() {
        for (LabelInUse label_used : usedLabel) {
            if (!defindedLabel.contains(label_used)) {
                parser.getErrorMeassge()
                        .append("Label nicht gefunden:" + label_used.getName());
                return false;
            } else {
                label_used.setLabel(
                        defindedLabel.get(defindedLabel.indexOf(label_used)));
            }
        }
        return true;
    }

    /**
     * �berpr�ft, ob alle Label richtig gesetzt wurden
     *
     * @return true, wenn alle Label auf die richtigen Speicherstellen zeigen
     */
    private boolean checkLabels() {
        for (Command comm : program.getCommands()) {
            if (comm.hasLabel()) {
                ArrayList<LabelInUse> liste = comm.getLabel();
                for (LabelInUse label_used : liste) {
                    String name = label_used.getName();
                    for (Label label_defined : defindedLabel) {
                        if (label_defined.getName().equals(name)
                                && label_defined.getAdress()
                                != (label_used.getOp().getAdress())) {
                            return false; // falls Labeladressen nicht
                            // �bereinstimmen, false
                        }

                    }
                }
            }
        }
        return true; // alle Labeladressen stimmen �berein
    }

    /**
     * Labelberechnung
     *
     * @param program
     *            Programm f�r welches die Label berechnet werden sollen
     */
    public void evalLabel(Program program) {
        this.program = program;
        boolean fertig = false;
        while (!fertig) { // Schritt 1
            for (LabelInUse label_used : usedLabel) {
                Label label_defined = defindedLabel.get(
                        defindedLabel.indexOf(label_used));
                int opcode_length_old = label_used.getOp().getOpCode().length;
                int adress = label_used.getAdress() + label_used.getOp()
                        .getOpCode().length;

                // Adresse des definierten Label stimmt nicht
                // mit der des genutzten Label �berein
                if (label_defined.getAdress() != label_used.getOp().getAdress()) {

                    // Adress neusetzen
                    label_used.getOp().setAdress(label_defined.getAdress());

                    // Adressen im Befehl setzen, der das Label
                    // nutzt
                    label_used.getCommand()
                            .setAdress(label_used.getCommand().getAdress());
                }

                // Folgebefehle um Offset verschieben
                aktAddress(adress, label_used.getOp().getOpCode().length
                        - opcode_length_old);
            }

            // Pr�fen ob alle Label richtig gesetzt sind
            fertig = checkLabels();
        }

        // F�r alle Befehle die Adressen aktualisieren
        aktAddress(0, 0);

        // Anzeige einer Tabelle mit Labeln und den zugeh�rigen Adressen
        Enviroment.getLabelWindow().setContent(defindedLabel);
        Enviroment.getLabelWindow().setVisible(Enviroment.label_Window);
        Enviroment.getLabelWindow().pack();
    }

    public ArrayList<Label> getLabels() {
        return defindedLabel;
    }
}
