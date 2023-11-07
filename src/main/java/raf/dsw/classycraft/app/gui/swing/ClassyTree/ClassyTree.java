package raf.dsw.classycraft.app.gui.swing.ClassyTree;

import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.gui.swing.controller.AddAction.AddType;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.ClassyRepository.implementation.ProjectExplorer;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent, AddType addType);
    ClassyTreeItem getSelectedNode();
}
