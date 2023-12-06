package raf.dsw.classycraft.app.gui.swing.ClassyTree;

import raf.dsw.classycraft.app.gui.swing.controller.addAction.AddType;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabView;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

public interface ClassyTree  {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer, ClassyTabView classyTabView, PackageView packageView);
    void addChild(ClassyTreeItem parent, AddType addType);
    void removeChild(ClassyTreeItem nodeToRemove);
    ClassyTreeItem getSelectedNode();
}
