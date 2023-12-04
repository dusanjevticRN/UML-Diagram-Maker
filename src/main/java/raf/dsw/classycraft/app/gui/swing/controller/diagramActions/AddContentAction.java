package raf.dsw.classycraft.app.gui.swing.controller.diagramActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;

import java.awt.event.ActionEvent;

public class AddContentAction extends AbstractClassyAction
{
    public AddContentAction()
    {
        this.putValue(SMALL_ICON, loadIcon("/images/Content.png"));
        this.putValue(SHORT_DESCRIPTION, "Add Content");
        this.putValue(NAME, "Add Content");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
