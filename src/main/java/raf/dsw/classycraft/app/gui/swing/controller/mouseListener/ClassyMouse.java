package raf.dsw.classycraft.app.gui.swing.controller.mouseListener;

import com.sun.tools.javac.Main;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClassyMouse implements MouseListener, MouseMotionListener {

    private DiagramPanel currentPanel;

    public ClassyMouse(DiagramPanel currentPanel){
        this.currentPanel = currentPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().execute(e.getX(), e.getY(), currentPanel);
        MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateMousePressed(e.getX(), e.getY(), currentPanel);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateMouseReleased(e.getX(), e.getY(), currentPanel);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateMouseDragged(e.getX(), e.getY(), currentPanel);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
