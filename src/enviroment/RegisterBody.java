package enviroment;

import gui.CONSTANTS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Diese Klasse repraesentiert den Registerkoerper der MI
 */
public class RegisterBody {

    /** Array mit den Registern */
    private Register[] content = new Register[CONSTANTS.NUMBER_OF_REGISTER];

    /**
     * Instaziiert den RegisterKoerper
     */
    public RegisterBody() {
	for (int i = 0; i < CONSTANTS.NUMBER_OF_REGISTER; i++) {
	    content[i] = (i == CONSTANTS.SP_REGISTER) ? new Register(i, true)
		    : new Register(i);
	}
    }

    /**
     * Gibt die Darstellung des Registerkörpers auf einem JScrollPane zurueck
     * 
     * @return Darstellung des Registerkörpers auf einem JScrollPane
     */
    public JScrollPane getJScrollPane() {
	JPanel ret2 = new JPanel();
	JPanel ret = new JPanel();
	JComboBox chooser = new JComboBox();
	chooser.addItem("Dezimal");
	chooser.addItem("Binär");
	chooser.addItem("Hexadezimal");
	chooser.addItem("Float");
	chooser.setSelectedIndex(Enviroment.REGVIEW);
	chooser.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent evt) {
		JComboBox cb = (JComboBox) evt.getSource();

		int newItem = cb.getSelectedIndex();
		Enviroment.setRegView(newItem);
		Enviroment.frame.updateUI();
	    }
	});

	ret.add(chooser);
	ret2.add(ret);

	for (int i = 0; i < CONSTANTS.NUMBER_OF_REGISTER; i++) {
	    ret = new JPanel();
	    ret.add(new JLabel("R" + ((i < 10) ? i + "  " : i)));
	    ret.add(content[i].getTextField(Enviroment.REGVIEW));
	    ret2.add(ret);
	}

	ret2.setLayout(new BoxLayout(ret2, BoxLayout.Y_AXIS));

	return new JScrollPane(ret2);
    }

    /**
     * Gibt das Register mit der Nummer nr zurueck
     * 
     * @param nr
     *            Registernummer
     * @return Register mit der Nummer nr
     */
    public Register getRegister(int nr) {
	return content[nr];
    }

    /**
     * Gibt das Register mit der Nummer nr zurueck
     * 
     * @param nr
     *            Registernummer
     *            
     * @param length
     *            Länge des Registerzugriffs
     *                        
     * @return Register mit der Nummer nr
     */
    public MyByte[] getRegister(int nr, int length) {
	    return content[nr].getContent(length);
    }
    

    /**
     * Setzt den Inhalt eines Registers
     * 
     * @param nr
     *            Registernummer
     * @param content
     *            Inhalt eines Registers
     */
    public void setContent(int nr, MyByte[] content) {
	
	    this.content[nr].setContent(content);

    }

    /**
     * Aktualisiert die Registerdarstellung
     */
    public void update() {
	for (Register reg : content) {
	    reg.update(Enviroment.REGVIEW);
	}
    }

    public void reset() {
	for (Register reg : content) {
	    reg.reset();
	}
    }

}
