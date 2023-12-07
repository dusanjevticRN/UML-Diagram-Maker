package raf.dsw.classycraft.app.gui.swing.controller.listner;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClassyMouse implements MouseListener, MouseMotionListener{

    private DiagramPanel currentPanel;
    private boolean rightMouseButtonPressed = false;
    public ClassyMouse(DiagramPanel currentPanel){
        this.currentPanel = currentPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Cordinates: " + e.getX() + " " + e.getY());
        MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().execute(e.getX(), e.getY(), currentPanel);
        MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateMousePressed(e.getX(), e.getY(), currentPanel);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseButtonPressed = true;
            MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateRightMousePressed(e.getX(), e.getY(), currentPanel);
        }
        else
            MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateMousePressed(e.getX(), e.getY(), currentPanel);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseButtonPressed = false;
            MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateRightMouseReleased(e.getX(), e.getY(), currentPanel);
        }
        else
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
        if(rightMouseButtonPressed)
        {
            System.out.println("Right drag cords: " + e.getX() + " " + e.getY());
            MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateRightMouseDragged(e.getX(), e.getY(), currentPanel);
        }
        else
            MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().stateMouseDragged(e.getX(), e.getY(), currentPanel);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
