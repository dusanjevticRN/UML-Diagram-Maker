package raf.dsw.classycraft.app.controller.AddAction;

import raf.dsw.classycraft.app.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddDiagramAction extends AbstractClassyAction {
    public AddDiagramAction() {
        super("/images/addDiagram.png");

        this.putValue(SHORT_DESCRIPTION, "Add Diagram");
        this.putValue(NAME, "Add Diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        MainFrame.getInstance().getClassyTree().addChild(selected, AddType.DIAGRAM_ADD);
    }
}
