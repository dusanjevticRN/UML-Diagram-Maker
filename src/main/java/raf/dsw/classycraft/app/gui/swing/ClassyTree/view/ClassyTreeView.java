package raf.dsw.classycraft.app.gui.swing.ClassyTree.view;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Editor;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Listener;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabView;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabbedPane;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

@Getter
public class ClassyTreeView extends JTree {

    public ClassyTreeView(DefaultTreeModel defaultTreeModel, ClassyTabView classyTabView, ClassyTabbedPane classyTabbedPane)
    {
        this.setModel(defaultTreeModel);
        ClassyTreeRenderer classyTreeCellRenderer = new ClassyTreeRenderer();
        Listener listener = new Listener();
        listener.addSubscriber(classyTabView);
        listener.addSubscriber(classyTabbedPane);
        this.addTreeSelectionListener(listener);
        this.setCellEditor(new Editor(this, classyTreeCellRenderer));
        this.setCellRenderer(classyTreeCellRenderer);
        this.setEditable(true);
    }
}
