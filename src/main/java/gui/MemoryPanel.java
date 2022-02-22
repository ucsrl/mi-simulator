package gui;

import javax.swing.*;
import java.awt.*;

public class MemoryPanel extends JScrollPane {
    public MemoryPanel(Component view) {
        super(view);
    }

    public MemoryPanel() {
    }

    public void setFontSize(int size) {
        GuiUtils.changeFont(this, size);
    }
}
