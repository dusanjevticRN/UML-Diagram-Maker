package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import java.awt.*;

public interface State {
    void execute(int x, int y, DiagramPanel panel);
    void stateMousePressed(int x, int y, DiagramPanel panel);
    void stateMouseDragged(int x, int y, DiagramPanel panel);
    void stateMouseReleased(int x, int y, DiagramPanel panel);
    void stateRightMouseDragged(int x, int y, DiagramPanel panel);
    void stateRightMousePressed(int x, int y, DiagramPanel panel);
    void stateRightMouseReleased(int x, int y, DiagramPanel panel);

}
