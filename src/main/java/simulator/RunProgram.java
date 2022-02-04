/**
 *
 */
package simulator;

import enviroment.Enviroment;
import enviroment.NumberConversion;

import javax.swing.*;

/**
 * Klaase für die Programmausführung.
 *
 * @author Matthias Oehme
 */
public class RunProgram extends Thread {

    /**
     * nächster Befehl
     */
    private Command next;

    /**
     * Run-button
     */
    private JButton button_run;

    /**
     * Restart-Button
     */
    private JButton button_restart;

    /**
     * Stop-Button
     */
    private JButton button_stop;

    /** Step-Button */
    private JButton button_step;

    /** The runs. */
    private boolean stop_program = false;

    /**
     * Konstrktor für einen Programmablauf
     *
     * @param btnRun
     *            Run-button
     * @param btnStop
     *            Stop-Button
     * @param btnStep
     *            Step-Button
     * @param btnRestart
     *            Restart-Button
     */
    public RunProgram(JButton btnRun, JButton btnStop, JButton btnStep, JButton btnRestart) {
        button_run = btnRun;
        button_stop = btnStop;
        button_step = btnStep;
        button_restart = btnRestart;

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        boolean breakPoint = false;
        button_stop.setEnabled(true);
        button_run.setEnabled(false);
        button_step.setEnabled(false);
        button_restart.setEnabled(false);
        Enviroment.frame.updateUI();
        next = Enviroment.getNextCommand();
        while (next != null && !stop_program && !breakPoint) {
            next.run();
            if (next instanceof Halt) {
                break; // Programmende erreicht
            }
            next = Enviroment.readNextCommand();
            if (next != null) {
                Command next_precode = Enviroment.getCommandperAddress(
                        next.getAdress());
                if (next_precode != null && (NumberConversion.myByteEqual(
                        next.getOpCode(), next_precode.getOpCode()))) {
                    breakPoint = next_precode.hasBreakPoint();
                }
            }
        }
        Enviroment.getText().highlightNextCommand();
        button_stop.setEnabled(false);
        button_restart.setEnabled(true);
        button_run.setEnabled(!(next == null || next instanceof Halt));
        button_step.setEnabled(!(next == null || next instanceof Halt));
        Enviroment.frame.updateUI();

    }

    /**
     * Stoppt den Programmablauf
     *
     */
    public void stopProgram() {
        stop_program = true;
    }

}
