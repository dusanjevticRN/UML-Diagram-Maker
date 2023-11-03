package raf.dsw.classycraft.app.controller.AddAction;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddPackageAction extends AbstractClassyAction {
    public AddPackageAction() {
        super("/images/addPackageAction.png");

        this.putValue(SHORT_DESCRIPTION, "Add Package");
        this.putValue(NAME, "Add Package");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        MainFrame.getInstance().getClassyTree().addChild(selected, AddType.PACKAGE_ADD);
    }
}
