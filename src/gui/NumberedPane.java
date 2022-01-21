package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import enviroment.Enviroment;

/**
 * Die Klasse ist für das Anzeigen eines JTextPane mit Zeilennummern
 * 
 * @author Matthias Oehme
 */
public class NumberedPane extends JPanel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** JTextPane */
    private HighlightedJPane pane;

    /** JScrollPane auf dem das ganze angezeigt wird */
    JScrollPane scrollPane;

    /** Array mit Labeln */
    private MyLabel[] label = new MyLabel[10000000];

    /** The max. */
    private int max = 20;

    /** The icon1. */
    private ImageIcon icon1;

    /** The icon2. */
    private ImageIcon icon2;

    /**
     * Instanziiert ein NumberedPane
     */
    public NumberedPane() {
	super();

	URL resource1 = getClass().getResource("/images/icon2.gif");
	URL resource2 = getClass().getResource("/images/icon1.gif");
	icon1 = new ImageIcon(resource1);
	icon2 = new ImageIcon(resource2);

	setMinimumSize(new Dimension(50, 40));
	setPreferredSize(new Dimension(50, 40));

	pane = new HighlightedJPane() {
	    /**
			 * 
			 */
	    private static final long serialVersionUID = 1L;

	    @Override
	    public void paint(Graphics g) {
		super.paint(g);
		NumberedPane.this.repaint();
	    }

	    @Override
	    public void paintComponent(Graphics g) {
		setAntiAliasEnabled(g);
		super.paintComponent(g);
	    }
	};

	scrollPane = new JScrollPane(pane);

	scrollPane.addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(ComponentEvent e) {
		pane.setSize(new Dimension(scrollPane.getWidth() - 20,
			scrollPane.getHeight() - 20));
	    }
	});

	scrollPane.setMinimumSize(new Dimension(50, 40));
	scrollPane.setPreferredSize(new Dimension(50, 40));
	scrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    }

    /**
     * Gibt das HiglightedJPane zurueck
     * 
     * @return the text pane
     */
    public HighlightedJPane getTextPane() {
	return pane;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
	super.paint(g);

	// We need to properly convert the points to match the view port
	// Read docs for view port
	int start = pane
		.viewToModel(scrollPane.getViewport().getViewPosition());
	// starting pos in document
	int end = pane.viewToModel(new Point(scrollPane.getViewport()
		.getViewPosition().x + pane.getWidth(), scrollPane
		.getViewport().getViewPosition().y + pane.getHeight()));
	// end pos in doc

	// translate offsets to lines
	Document doc = pane.getDocument();
	int startline = doc.getDefaultRootElement().getElementIndex(start) + 1;
	int endline = doc.getDefaultRootElement().getElementIndex(end) + 1;

	int fontHeight = g.getFontMetrics(pane.getFont()).getHeight();
	int fontDesc = g.getFontMetrics(pane.getFont()).getDescent();
	int starting_y = -1;

	try {
	    starting_y = pane.modelToView(start).y
		    - scrollPane.getViewport().getViewPosition().y + fontHeight
		    - fontDesc;
	} catch (BadLocationException e1) {
	    e1.printStackTrace();
	}
	int merke = 0;
	for (int i = 1; i < max; i++) {
	    try {
		this.remove(label[i]);
	    } catch (Exception e) {

	    }
	}
	for (int line = startline, y = starting_y; line <= endline; y += fontHeight, line++) {
	    if (label[line] == null) {
		MyLabel bla = new MyLabel(Integer.toString(line), icon1, icon2);
		label[line] = bla;
		label[line].addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent arg0) {
			if (arg0.getClickCount() == 2) {

			    if (Enviroment.compiled) {
				MyLabel inp = (MyLabel) arg0.getSource();
				if (inp.getStatus()) {
				    inp.setStatus(false);
				} else {
				    inp.setStatus(true);
				}

			    } else {
				JOptionPane
					.showMessageDialog(
						Enviroment.frame,
						CONSTANTS.ERROR_SETBREAKPOINT_NOT_ASSEMBLED,
						CONSTANTS.ERROR_SETBREAKPOINT_TITEL,
						JOptionPane.ERROR_MESSAGE);
			    }

			}
		    }
		});
	    }
	    this.add(label[line]);
	    label[line].setBounds(0, y - 15, 40, 20);
	    merke++;
	}

	max = merke + startline;

    }

    /**
     * Reset.
     */
    public void reset() {
	for (MyLabel lab : label) {
	    if (lab != null) {
		lab.reset();
	    }
	}
    }

    private void setAntiAliasEnabled(Graphics g) {

	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_OFF);
	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
    }

}
