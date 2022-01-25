package gui;

import enviroment.Enviroment;
import parser.Parser;
import scanner.Scanner;
import simulator.Command;
import simulator.Halt;
import simulator.RunProgram;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Diese Klasse enthält die grafischen Elemente
 */
public class Window extends javax.swing.JFrame {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The j menu5.
     */
    private JMenu jMenu5;

    /**
     * The exit menu item.
     */
    private JMenuItem exitMenuItem;

    /**
     * The j separator2.
     */
    private JSeparator jSeparator2;

    /**
     * The new file.
     */
    private JMenuItem newFile;

    /**
     * The save as menu item.
     */
    private JMenuItem saveAsMenuItem;

    /**
     * The save menu item.
     */
    private JMenuItem saveMenuItem;

    /**
     * The open file menu item.
     */
    private JMenuItem openFileMenuItem;

    /**
     * The about menu item.
     */
    private JMenuItem aboutMenuItem;

    /**
     * The undo menu item.
     */
    private JMenuItem undoMenuItem;

    /**
     * The about menu item.
     */
    private JCheckBoxMenuItem shlMenuItem;

    /**
     * The about menu item.
     */
    private JCheckBoxMenuItem label_windowMenuItem;

    /**
     * Dateimenü
     */
    private JMenu file_menu;

    /**
     * Bearbeitenmenü
     */
    private JMenu edit_menu;

    /**
     * Einstellungsmenü
     */
    private JMenu settings_menu;

    /**
     * Menüleiste
     */
    private JMenuBar jMenuBar1;

    /**
     * Quelltextscanner
     */
    private Scanner scanner;

    /**
     * Anzeigebereich für den Speicher
     */
    private JScrollPane memory;

    /**
     * Anzeigebereich für die Register
     */
    private JScrollPane register;

    /**
     * Anzeige der Flags
     */
    private JPanel flag;

    /**
     * Anzeigebereich für den Kellerspeicher
     */
    private JScrollPane stack;

    /**
     * The menu_panel.
     */
    private JPanel button_panel;

    /**
     * Assemble-Button
     */
    private JButton btnAssemble;

    /**
     * Run-Button
     */
    private JButton btnRun;

    /**
     * Step-Button
     */
    private JButton btnStep;

    /**
     * Nächster befehl
     */
    private Command nex = null;

    /**
     * Übersetzungsstatus
     */
    private boolean compiled = false;

    /**
     * The btn stop.
     */
    private JButton btnStop;

    /**
     * The run.
     */
    private RunProgram run;

    /**
     * The btn restart.
     */
    private JButton btnRestart;

    /**
     * The file.
     */
    private File file;

    /**
     * The input_panel.
     */
    private JPanel input_panel;

    /**
     * The register_panel.
     */
    private JPanel register_panel;

    /**
     * The memory_panel.
     */
    private JPanel memory_panel;

    /**
     * The flag_panel.
     */
    private JPanel flag_panel;

    /**
     * The north_panel.
     */
    private JScrollPane north_panel;

    /**
     * The south_panel.
     */
    private JScrollPane south_panel;

    /**
     * The ober_panel.
     */
    private JPanel ober_panel;

    /**
     * The ober_panel2.
     */
    private JPanel ober_panel2;

    /**
     * The center_panel.
     */
    private JPanel center_panel;

    /**
     * Quelltexteingabefeld
     */
    private HighlightedJPane text;

    /**
     * Fehlerausgabe
     */
    private JTextPane error;

    /**
     * JTextField mit Zeilenummern
     */
    private NumberedPane numberedPane;

    /**
     * Mainpanel.
     */
    public JSplitPane main_panel;

    /**
     * Letzer Text der Quelltexteingabe
     */
    private String oldtext = "";

    /**
     * Dateiauswahl.
     */
    private final JFileChooser fc = new JFileChooser();

    /**
     * Konstruktor für das Fenster
     */
    public Window() {
        super();
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (saveOld()) {
                    System.exit(0);
                }

            }

        });
        initGUI();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Window.this.updateUI();
            }
        });

    }

    /**
     * Gibt das akutell ablaufende Programm zurueck
     *
     * @return akutell ablaufende Programm
     */
    public RunProgram getRun() {
        return run;
    }

    /**
     * Gibt das Quelltexteinhabefeld zurueck
     *
     * @return Quelltexteinhabefeld
     */
    public HighlightedJPane getTextPane() {
        return text;
    }

    /**
     * initalisiert die grafische Oberfläche
     */
    private void initGUI() {
        // Diese Methode sollte unbedingt in de nächsten Version auf mehrere
        // Klassen aufgeteilt werden.
        fc.setFileFilter(new FileNameExtensionFilter("MI-File", "mi"));

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        center_panel = new JPanel();
        button_panel = new JPanel();
        button_panel.setLayout(new FlowLayout(10));
        ober_panel = new JPanel();
        ober_panel.setLayout(new BoxLayout(ober_panel, BoxLayout.X_AXIS));
        ober_panel2 = new JPanel();
        ober_panel2.setLayout(new BoxLayout(ober_panel2, BoxLayout.Y_AXIS));
        ober_panel2.add(button_panel);
        ober_panel2.add(ober_panel);
        north_panel = new JScrollPane(ober_panel2);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));
        setTitle(CONSTANTS.TITLE + " - unbenannt.mi");
        Enviroment.setJFrame(this);
        Enviroment.init();

        {
            memory_panel = new JPanel();
            memory_panel.setLayout(new BoxLayout(memory_panel, BoxLayout.Y_AXIS));

        }

        {
            input_panel = new JPanel();
            input_panel.setLayout(new BorderLayout());
            numberedPane = new NumberedPane();
            input_panel.add(numberedPane, BorderLayout.WEST);
            input_panel.add(numberedPane.scrollPane, BorderLayout.CENTER);
            center_panel.add(input_panel);
            text = numberedPane.getTextPane();
            Enviroment.setText(text);

            DropTarget target = new DropTarget(text, new DropTargetAdapter() {

                public void drop(DropTargetDropEvent dtde) {
                    if (dtde.isDataFlavorSupported(
                            DataFlavor.javaFileListFlavor)) {
                        dtde.acceptDrop(dtde.getDropAction());
                        try {
                            List<File> list = (List<File>) dtde.getTransferable()
                                    .getTransferData(
                                            DataFlavor.javaFileListFlavor);
                            for (File file1 : list) {
                                if (saveOld()) {
                                    file = file1;
                                    int ch;
                                    StringBuffer strContent = new StringBuffer(
                                            "");

                                    FileInputStream in = new FileInputStream(
                                            file);
                                    while ((ch = in.read())
                                            != -1) {
                                        strContent.append(
                                                (char) ch);
                                    }

                                    in.close();
                                    text.setText(
                                            strContent.toString());
                                    text.setCaretPosition(0);
                                    oldtext = text.getText();
                                    btnRestart.setEnabled(
                                            false);
                                    btnRun.setEnabled(false);
                                    btnStep.setEnabled(false);
                                    btnStop.setEnabled(false);
                                    updateUI();
                                    Enviroment.init();
                                    reset_Title();

                                }

                            }

                        } catch (Exception E) {
                            error.setText(CONSTANTS.ERROR_OPENFILE);

                        }

                    }
                }
            });

        }
        {
            register_panel = new JPanel();
            register_panel.setLayout(new BoxLayout(register_panel, BoxLayout.Y_AXIS));
            register = Enviroment.REGISTERS.getJScrollPane();
            register_panel.add(register);
            ober_panel.add(register_panel);
            ober_panel.add(center_panel);
            ober_panel.add(memory_panel);

        }
        {
            south_panel = new JScrollPane();
            south_panel.setVerticalScrollBarPolicy(
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            error = new JTextPane();
            error.setEditable(false);
            south_panel.setViewportView(error);
            main_panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, north_panel,
                    south_panel);
            main_panel.setOneTouchExpandable(true);
            main_panel.setResizeWeight(0.75);
        }

        {

            {
                btnAssemble = new JButton("Assemble");
                btnStop = new JButton("Stop");
                btnRun = new JButton("Run");
                btnRun.addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_F5) {
                            ((JButton) e.getSource()).doClick();
                            System.out.println("click1");

                        }
                        System.out.println("click1");

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        // TODO Auto-generated method stub
                        System.out.println("click2");

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        // TODO Auto-generated method stub
                        System.out.println("click3");

                    }
                });

                btnAssemble.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        numberedPane.reset();
                        Enviroment.init();
                        scanner = new Scanner(false);
                        scanner.init(text.getText());

                        Parser p = new Parser(scanner);
                        p.start();

                        if (p.eval()) {

                            if (p.getProgramm().getCommands().size()
                                    > 0) {
                                Enviroment.setProgram(
                                        p.getProgramm());
                                Enviroment.MEMORY.setContent(0,
                                        p.getProgramm()
                                                .getOpCode());

                                nex = Enviroment.readNextCommand();
                                text.highlightNextCommand();

                                run = new RunProgram(btnRun,
                                        btnStop,
                                        btnStep,
                                        btnRestart);
                                error.setText(
                                        CONSTANTS.ASSEMBLE_SUCCESSFUL);
                                compiled = true;
                            } else {
                                error.setText(
                                        CONSTANTS.ASSEMBLE_UNSUCCESSFUL);
                                compiled = false;
                            }
                        } else {
                            error.setText(
                                    p.getErrorMeassge().toString());
                            compiled = false;
                        }

                        updateUI();
                        if (p.getErrorMeassge().getErrorMessage().length()
                                > 0) {

                            error.setText(p.getErrorMeassge()
                                    .getErrorMessage());
                            compiled = false;
                        }
                        Enviroment.setCompiled(compiled);
                        text.setCompiled(compiled);
                        btnRun.setEnabled(compiled);
                        btnStep.setEnabled(compiled);
                        btnRestart.setEnabled(compiled);

                    }
                });
                button_panel.add(btnAssemble);
            }
            {

                btnRun.setEnabled(compiled);
                btnRun.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnRun.setEnabled(false);
                        btnStop.setEnabled(true);
                        btnStep.setEnabled(false);
                        Enviroment.REGISTERS.reset();
                        Enviroment.MEMORY.reset_changedList();
                        run = new RunProgram(btnRun, btnStop, btnStep,
                                btnRestart);
                        run.start();

                        register = Enviroment.REGISTERS.getJScrollPane();
                        memory = Enviroment.MEMORY.getMemoryTable();
                    }
                });
                button_panel.add(btnRun);
            }
            {
                btnStep = new JButton("Step");
                btnStep.setEnabled(compiled);
                btnStep.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Enviroment.REGISTERS.reset();
                        Enviroment.MEMORY.reset_changedList();
                        nex = Enviroment.getNextCommand();

                        if (nex != null && !(nex instanceof Halt)) {

                            nex.run();

                        } else {

                            btnRun.setEnabled(false);
                            btnStep.setEnabled(false);
                            error.setText("Programmende");
                        }
                        Enviroment.readNextCommand();
                        text.highlightNextCommand();

                        updateUI();

                    }
                });
                button_panel.add(btnStep);
            }
            {
                btnStop.setEnabled(false);
                btnStop.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        run.stopProgram();
                        text.highlightNextCommand();
                        text.highlightNextCommand();

                        btnStop.setEnabled(false);
                    }
                });
                button_panel.add(btnStop);
            }
            {
                btnRestart = new JButton("Restart");
                btnRestart.setEnabled(compiled);
                btnRestart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        run.stopProgram();
                        Enviroment.init();
                        Enviroment.MEMORY.setContent(0,
                                Enviroment.getProgram()
                                        .getOpCode());
                        nex = Enviroment.readNextCommand();
                        text.highlightNextCommand();
                        error.setText(CONSTANTS.ASSEMBLE_SUCCESSFUL);
                        btnRun.setEnabled(true);
                        btnStep.setEnabled(true);
                        btnStop.setEnabled(false);
                        updateUI();
                    }
                });
                button_panel.add(btnRestart);
            }
        }

        flag_panel = new JPanel();
        center_panel.add(flag_panel);
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        jMenuBar1 = new JMenuBar();
        setJMenuBar(jMenuBar1);
        {
            file_menu = new JMenu();
            jMenuBar1.add(file_menu);
            file_menu.setText("Datei");
            {
                newFile = new JMenuItem();
                newFile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!text.getText().equals(oldtext)) {
                            saveOld();
                        }
                        file = null;
                        fc.setSelectedFile(new File(""));
                        reset_Title();
                        text.setText("");
                        oldtext = "";
                        Enviroment.init();
                        btnRestart.setEnabled(false);
                        btnRun.setEnabled(false);
                        btnStep.setEnabled(false);
                        btnStop.setEnabled(false);
                        updateUI();

                    }
                });

                file_menu.add(newFile);
                newFile.setText("Neu");
            }
            {
                openFileMenuItem = new JMenuItem();
                openFileMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (saveOld()) {
                            int returnVal = fc.showOpenDialog(
                                    Window.this);
                            if (returnVal
                                    == JFileChooser.APPROVE_OPTION) {
                                file = fc.getSelectedFile();
                                int ch;
                                StringBuffer strContent = new StringBuffer(
                                        "");
                                try {
                                    FileInputStream in = new FileInputStream(
                                            file);
                                    while ((ch = in.read())
                                            != -1) {
                                        strContent.append(
                                                (char) ch);
                                    }

                                    in.close();
                                    text.setText(
                                            strContent.toString());
                                    text.setCaretPosition(0);
                                    oldtext = text.getText();
                                    btnRestart.setEnabled(
                                            false);
                                    btnRun.setEnabled(false);
                                    btnStep.setEnabled(false);
                                    btnStop.setEnabled(false);
                                    updateUI();
                                    Enviroment.init();
                                    reset_Title();

                                } catch (Exception E) {
                                    error.setText(
                                            CONSTANTS.ERROR_OPENFILE);

                                }
                            }

                        }
                    }
                });

                file_menu.add(openFileMenuItem);
                openFileMenuItem.setText("Öffnen");
                openFileMenuItem.setAccelerator(
                        KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
                                java.awt.Event.CTRL_MASK));
            }

            {
                saveMenuItem = new JMenuItem();
                saveMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (file == null) {
                            int returnVal = fc.showSaveDialog(
                                    Window.this);
                            if (returnVal
                                    == JFileChooser.APPROVE_OPTION) {
                                file = fc.getSelectedFile();
                            }
                        }
                        if (file != null) {
                            try {
                                FileOutputStream in = new FileOutputStream(
                                        file);

                                in.write(text.getText().getBytes());
                                oldtext = text.getText();
                                in.close();
                                reset_Title();

                            } catch (Exception E) {
                                error.setText(
                                        CONSTANTS.ERROR_SAVEFILE);

                            }

                        }

                    }

                });
                file_menu.add(saveMenuItem);
                saveMenuItem.setText("Speichern");
                saveMenuItem.setAccelerator(
                        KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                                java.awt.Event.CTRL_MASK));
            }
            {
                saveAsMenuItem = new JMenuItem();
                saveAsMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int returnVal = fc.showSaveDialog(Window.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            file = fc.getSelectedFile();
                            try {
                                FileOutputStream in = new FileOutputStream(
                                        file);

                                in.write(text.getText().getBytes());
                                oldtext = text.getText();
                                in.close();
                                reset_Title();
                            } catch (Exception E) {
                                error.setText(
                                        CONSTANTS.ERROR_SAVEFILE);
                            }

                        }

                    }
                });
                file_menu.add(saveAsMenuItem);
                saveAsMenuItem.setText("Speichern unter ...");
            }
            {
                jSeparator2 = new JSeparator();
                file_menu.add(jSeparator2);
            }
            {
                exitMenuItem = new JMenuItem();
                exitMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (saveOld()) {
                            System.exit(0);
                        }
                    }
                });
                file_menu.add(exitMenuItem);

                exitMenuItem.setText("Beenden");
            }
        }

        {
            edit_menu = new JMenu();
            jMenuBar1.add(edit_menu);
            edit_menu.setText("Bearbeiten");
            {

                undoMenuItem = new JMenuItem();
                undoMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // text.getUndoManager().end();

                        if (text.getUndoManager().canUndo()) {
                            text.getUndoManager().undo();
                            Window.this.textChanged();
                        }
                        text.requestFocus();
                    }
                });
                edit_menu.add(undoMenuItem);
                undoMenuItem.setText("Rückgängig");
                undoMenuItem.setAccelerator(
                        KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z,
                                java.awt.Event.CTRL_MASK));

            }
        }

        {
            settings_menu = new JMenu();
            jMenuBar1.add(settings_menu);
            settings_menu.setText("Einstellungen");
            {

                shlMenuItem = new JCheckBoxMenuItem();
                shlMenuItem.setSelected(true);
                shlMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Enviroment.shl = ((JCheckBoxMenuItem) e.getSource()).getState();
                        Enviroment.text.doHighLighting();
                    }
                });
                settings_menu.add(shlMenuItem);
                shlMenuItem.setText("Syntaxhighlighting");

                label_windowMenuItem = new JCheckBoxMenuItem();
                label_windowMenuItem.setSelected(false);

                label_windowMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Enviroment.label_Window = ((JCheckBoxMenuItem) e.getSource()).getState();
                        if (Enviroment.compiled) {
                            Enviroment.getLabelWindow().setVisible(
                                    Enviroment.label_Window);
                        }
                    }
                });
                settings_menu.add(label_windowMenuItem);
                label_windowMenuItem.setText("Fenster mit Labeladressen");
            }
        }

        {
            jMenu5 = new JMenu();
            jMenuBar1.add(jMenu5);
            jMenu5.setText("Über");
            {

                aboutMenuItem = new JMenuItem();
                aboutMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        JOptionPane.showMessageDialog(Window.this,
                                CONSTANTS.INFO_TEXT,
                                CONSTANTS.INFO_TITLE,
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                jMenu5.add(aboutMenuItem);
                aboutMenuItem.setText("Über");
            }
        }
        getContentPane().add(main_panel);
        updateUI();

    }

    /**
     * Setzt den Titel des JFrames neu
     */
    public void reset_Title() {
        setTitle(
                CONSTANTS.TITLE + " - " + (file != null ? file.getName() : "unbenannt.mi"));
    }

    /**
     * Zeigt den Speicherdialog, wenn sich der Inhalt geändert hat
     *
     * @return true, if successful
     */
    private boolean saveOld() {
        if (text.getText().equals(oldtext)) {
            return true; // keine Änderungen
        }
        int option = JOptionPane.showConfirmDialog(Window.this,
                "Möchten Sie die Änderungen in " + (
                        file != null ?
                                file.getName() :
                                "unbenannt.mi")
                        + " speichern?",
                "Änderungen speichern?",
                JOptionPane.YES_NO_CANCEL_OPTION);
        switch (option) {
            case JOptionPane.YES_OPTION:
                if (file == null) {
                    int returnVal = fc.showSaveDialog(Window.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fc.getSelectedFile();
                    }
                }
                if (file != null) {
                    try {
                        FileOutputStream in = new FileOutputStream(file);

                        in.write(text.getText().getBytes());
                        oldtext = text.getText();
                        in.close();

                    } catch (Exception E) {
                        error.setText(CONSTANTS.ERROR_SAVEFILE);
                    }
                } else {
                    return false;
                }
                return true;

            case JOptionPane.NO_OPTION:
                return true;

            case JOptionPane.CANCEL_OPTION:
                return false;

            default:
                return false;
        }

    }

    /**
     * Setzt den Fehlertext
     *
     * @param text Fehlertext
     */
    public void setErrorText(String text) {
        error.setText(text);
    }

    /**
     * Wird ausgefuehrt, wenn eine Veränderung des Quelltextes festgestellt wird
     */
    public void textChanged() {
        if (run != null) {
            run.stopProgram();
            run = null;
        }

        numberedPane.reset();
        Enviroment.setCompiled(false);
        Enviroment.getLabelWindow().setVisible(false);
        error.setText("");
        text.setCompiled(false);
        btnRun.setEnabled(false);
        btnStep.setEnabled(false);
        btnStop.setEnabled(false);
        btnRestart.setEnabled(false);
        Enviroment.init();
        updateUI();
    }

    /**
     * Aktualisiert die grafische Oberflaeche
     */
    public void updateUI() {
        if (Enviroment.getNextCommand() != null
                && Enviroment.getNextCommand() instanceof Halt) {
            error.setText(CONSTANTS.PROGRAM_END);
            btnRun.setEnabled(false);
            btnStep.setEnabled(false);
        }

        Enviroment.REGISTERS.update();
        Enviroment.flags.update();

        int val = (memory == null) ? 0 : memory.getVerticalScrollBar().getValue();
        int val2 = (stack == null) ? 0 : stack.getVerticalScrollBar().getValue();
        register_panel.removeAll();
        register = Enviroment.REGISTERS.getJScrollPane();
        register_panel.add(register);
        flag = Enviroment.flags.getFlags();

        JPanel instr_panel = new JPanel();
        if (compiled) {
            JLabel instr = new JLabel(" " + (Enviroment.getNextCommand() != null ?
                    Enviroment.getNextCommand().toString() :
                    "no Instr"));
            instr.setFont(CONSTANTS.FONT);
            instr.setForeground(CONSTANTS.DARK_GREY);
            instr_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            instr_panel.add(instr);
        }
        flag.setLayout(new FlowLayout(FlowLayout.CENTER));
        flag_panel.setLayout(new BoxLayout(flag_panel, BoxLayout.Y_AXIS));

        flag_panel.removeAll();

        flag_panel.add(flag);
        flag_panel.add(instr_panel);

        text.highlightNextCommand();

        memory_panel.removeAll();
        memory = Enviroment.MEMORY.getMemoryTable();
        memory.setSize(CONSTANTS.MEMORY_WIDTH, CONSTANTS.MEMORY_HEIGHT);
        memory.getVerticalScrollBar().setValue(val);
        memory.getVerticalScrollBar().setValue(val);
        stack = Enviroment.MEMORY.getStackTable();
        stack.setSize(CONSTANTS.STACK_WIDTH, CONSTANTS.STACK_HEIGHT);
        stack.getVerticalScrollBar().setValue(val2);
        stack.getVerticalScrollBar().setValue(val2);
        memory_panel.add("Speicher", memory);
        memory_panel.add("Stack", stack);
        validate();
        this.repaint();

    }

    /**
     * Setzt die Größe des Fensters
     */
    public void resetSize() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        int y = this.getHeight();
        int x = this.getWidth();
        setExtendedState(JFrame.NORMAL);
        setSize(Math.min(CONSTANTS.WINDOW_WIDTH, x), Math.min(CONSTANTS.WINDOW_HEIGHT, y));
    }

}
