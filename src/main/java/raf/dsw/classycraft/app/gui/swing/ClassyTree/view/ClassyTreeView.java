package raf.dsw.classycraft.app.gui.swing.ClassyTree.view;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Editor;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Listener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

@Getter
public class ClassyTreeView extends JTree {

    public ClassyTreeView(DefaultTreeModel defaultTreeModel)
    {
        this.setModel(defaultTreeModel);
        ClassyTreeRenderer classyTreeCellRenderer = new ClassyTreeRenderer();
        this.addTreeSelectionListener(new Listener());
        this.setCellEditor(new Editor(this, classyTreeCellRenderer));
        this.setCellRenderer(classyTreeCellRenderer);
        this.setEditable(true);
    }
}
