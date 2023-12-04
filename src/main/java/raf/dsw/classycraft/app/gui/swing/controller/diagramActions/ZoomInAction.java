package raf.dsw.classycraft.app.gui.swing.controller.diagramActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class ZoomInAction extends AbstractClassyAction
{
    public ZoomInAction()
    {
        this.putValue(SMALL_ICON, loadIcon("/images/ZoomIn.png"));
        this.putValue(SHORT_DESCRIPTION, "Zoom in");
        this.putValue(NAME, "Zoom in");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
