package raf.dsw.classycraft.app.gui.swing.controller.diagramActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;

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

    }
}
