package raf.dsw.classycraft.app.gui.swing.ClassyTree.view;

import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Editor;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Listener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree {

    public ClassyTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        ClassyTreeRenderer classyTreeCellRenderer = new ClassyTreeRenderer();
        addTreeSelectionListener(new Listener());
        setCellEditor(new Editor(this, classyTreeCellRenderer));
        setCellRenderer(classyTreeCellRenderer);
        setEditable(true);
    }
}
