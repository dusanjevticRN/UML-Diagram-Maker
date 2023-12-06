package raf.dsw.classycraft.app.gui.swing.ClassyTree.view;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Editor;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.controller.Listener;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabView;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

@Getter
public class ClassyTreeView extends JTree {

    public ClassyTreeView(DefaultTreeModel defaultTreeModel, ClassyTabView classyTabView, PackageView packageView)
    {
        this.setToggleClickCount(0);
        this.setModel(defaultTreeModel);
        ClassyTreeRenderer classyTreeCellRenderer = new ClassyTreeRenderer();
        Listener listener = new Listener();
        listener.addSubscriber(classyTabView);
        listener.addSubscriber(packageView);
        this.addTreeSelectionListener(listener);
        this.addMouseListener(listener);
        this.setCellEditor(new Editor(this, classyTreeCellRenderer));
        this.setCellRenderer(classyTreeCellRenderer);
        this.setEditable(true);
    }
}
