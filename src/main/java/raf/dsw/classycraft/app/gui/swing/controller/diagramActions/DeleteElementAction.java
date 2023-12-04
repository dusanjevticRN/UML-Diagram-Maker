package raf.dsw.classycraft.app.gui.swing.controller.diagramActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class DeleteElementAction extends AbstractClassyAction
{
    public DeleteElementAction()
    {
        this.putValue(SMALL_ICON, loadIcon("/images/deleteAction.png"));
        this.putValue(SHORT_DESCRIPTION, "Delete element");
        this.putValue(NAME, "Delete element");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
