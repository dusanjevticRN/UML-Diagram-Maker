package raf.dsw.classycraft.app.controller.AddAction;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddProjectAction extends AbstractClassyAction {
    public AddProjectAction() {
        super("/images/addProject.png");

        this.putValue(SHORT_DESCRIPTION, "Add Project");
        this.putValue(NAME, "Add Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        MainFrame.getInstance().getClassyTree().addChild(selected, AddType.PROJECT_ADD);

    }
}
