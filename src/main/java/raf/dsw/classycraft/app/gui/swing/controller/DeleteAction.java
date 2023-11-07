package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.ClassyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messageGenerator.EventType;

import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractClassyAction
{
    public DeleteAction()
    {
        this.putValue(SMALL_ICON, loadIcon("/images/deleteAction.png"));
        this.putValue(SHORT_DESCRIPTION, "Delete");
        this.putValue(NAME, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (MainFrame.getInstance().getClassyTree().getSelectedNode() == null)
        {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NO_ITEM_TO_DELETE);
            return;
        }

        else if (MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof ProjectExplorer)
        {
            AppCore.getInstance().getMessageGenerator().generate(EventType.CANT_DELETE_PROJECT_EXPLORER);
            return;
        }

        else
        {

        }
    }

}
