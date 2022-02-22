package gui;

import javax.swing.*;
import java.awt.*;

class GuiUtils {
    static void changeFont(Component component, int size) {
        component.setFont(component.getFont().deriveFont((float) size));
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                changeFont(child, size);
            }
        }
    }
}
