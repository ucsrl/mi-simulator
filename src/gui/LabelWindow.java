/**
 * 
 */
package gui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import codegenerator.Label;

import enviroment.Enviroment;

/**
 * @author Cyberdyne
 *
 */
public class LabelWindow extends JFrame {
    private JTable table=new JTable();
    public LabelWindow() {
	super();
	this.add(new JScrollPane(table));
    }
    public void setContent(ArrayList<Label> list){
	table.setModel(new LabelTableModel(list));
	
    }

    
    

}
