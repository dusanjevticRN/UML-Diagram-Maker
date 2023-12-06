package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

public class ZoomOutState implements State
{
    @Override
    public void execute(int x, int y, DiagramPanel panel)
    {

    }

    @Override
    public void stateMousePressed(int x, int y, DiagramPanel panel)
    {
        String notification = x + "/" + y;
        EventBus.getInstance().notifySubscriber(notification, EventType.ZOOM_OUT_STATE);
    }

    @Override
    public void stateMouseDragged(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateMouseReleased(int x, int y, DiagramPanel panel) {

    }
}
