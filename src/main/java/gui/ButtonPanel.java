package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonPanel extends JPanel {

    private final JButton btnAssemble;
    private final JButton btnRun;
    private final JButton btnStep;
    private final JButton btnStop;
    private final JButton btnRestart;

    public ButtonPanel(boolean compiled) {

        this.btnAssemble = new JButton("Assemble");
        btnStop = new JButton("Stop");
        btnRun = new JButton("Run");
        btnRun.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F5) {
                    ((JButton) e.getSource()).doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        btnStep = new JButton("Step");
        btnRun.setEnabled(compiled);
        btnStep.setEnabled(compiled);
        btnStop.setEnabled(false);
        this.add(btnAssemble);
        this.add(btnRun);
        this.add(btnStep);
        this.add(btnStop);

        btnRestart = new JButton("Restart");
        btnRestart.setEnabled(compiled);
        this.add(btnRestart);

    }

    // TODO: remove these getters
    public JButton getBtnAssemble() {
        return btnAssemble;
    }

    public JButton getBtnRun() {
        return btnRun;
    }

    public JButton getBtnStep() {
        return btnStep;
    }

    public JButton getBtnStop() {
        return btnStop;
    }

    public JButton getBtnRestart() {
        return btnRestart;
    }

    public void setFontSize(int size) {
        GuiUtils.changeFont(this, size);
    }

}