package raf.dsw.classycraft.app.gui.swing.controller.diagramActions;

import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomOutAction extends AbstractClassyAction
{
    public ZoomOutAction()
    {
        this.putValue(SMALL_ICON, loadIcon("/images/ZoomOut.png"));
        this.putValue(SHORT_DESCRIPTION, "Zoom out");
        this.putValue(NAME, "Zoom out");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Ovime kursor pretvaramo u nesto drugo
        MainFrame.getInstance().getPackageView().getSelectedDiagramPanel().getStateManager().getCurrentState().execute(0, 0, MainFrame.getInstance().getPackageView().getSelectedDiagramPanel());
        EventBus.getInstance().notifySubscriber(this, EventType.ZOOM_OUT);
    }
}
