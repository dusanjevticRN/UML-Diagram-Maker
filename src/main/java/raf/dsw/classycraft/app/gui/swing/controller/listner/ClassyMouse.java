package raf.dsw.classycraft.app.gui.swing.controller.listner;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClassyMouse implements MouseListener, MouseMotionListener{

    private boolean rightMouseButtonPressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Cordinates: " + e.getX() + " " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //offset za y kordinatu je dodat jer iz nekog razloga nije lepo havatao poziciju misa idk
        MouseEvent correctedEvent = new MouseEvent(
                e.getComponent(),
                e.getID(),
                e.getWhen(),
                e.getModifiers(),
                e.getX(),
                e.getY() - 70,
                e.getClickCount(),
                e.isPopupTrigger(),
                //event se prati preko booleana za e a ne correctedEvent pa mozemo da stavimo nobutton
                MouseEvent.NOBUTTON
        );

        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseButtonPressed = true;
        }
        System.out.println("Cordinates: " + e.getX() + " " + e.getY());
        MainFrame.getInstance().getPackageView().handleMousePressed(correctedEvent, rightMouseButtonPressed);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseEvent correctedEvent = new MouseEvent(
                e.getComponent(),
                e.getID(),
                e.getWhen(),
                e.getModifiers(),
                e.getX(),
                e.getY() - 70,
                e.getClickCount(),
                e.isPopupTrigger(),
                MouseEvent.NOBUTTON
        );
        MainFrame.getInstance().getPackageView().handleMouseReleased(correctedEvent, rightMouseButtonPressed);
        if (e.getButton() == MouseEvent.BUTTON3) {
            rightMouseButtonPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MouseEvent correctedEvent = new MouseEvent(
                e.getComponent(),
                e.getID(),
                e.getWhen(),
                e.getModifiers(),
                e.getX(),
                e.getY() - 70,
                e.getClickCount(),
                e.isPopupTrigger(),
                MouseEvent.NOBUTTON
        );
        MainFrame.getInstance().getPackageView().handleMouseDragged(correctedEvent, rightMouseButtonPressed);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
