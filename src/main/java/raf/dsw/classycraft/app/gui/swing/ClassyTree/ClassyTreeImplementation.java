package raf.dsw.classycraft.app.gui.swing.ClassyTree;

import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.core.observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.controller.addAction.AddType;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.factory.DiagramFactory;
import raf.dsw.classycraft.app.classyRepository.factory.PackageFactory;
import raf.dsw.classycraft.app.classyRepository.factory.ProjectFactory;
import raf.dsw.classycraft.app.classyRepository.implementation.Package;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.ClassyTabView;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeImplementation implements ClassyTree, ISubscriber {

    private ClassyTreeView classyTreeView;
    private DefaultTreeModel defaultTreeModel;

    public ClassyTreeImplementation() {
        classyTreeView = null;
        defaultTreeModel = null;
        EventBus.getInstance().subscribe(EventType.DIAGRAM_RENAME, this);
        EventBus.getInstance().subscribe(EventType.PACKAGE_RENAME, this);
        EventBus.getInstance().subscribe(EventType.PROJECT_RENAME, this);
    }
    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer, ClassyTabView classyTabView, PackageView packageView){
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        defaultTreeModel = new DefaultTreeModel(root);
        classyTreeView = new ClassyTreeView(defaultTreeModel, classyTabView, packageView);
        return classyTreeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent, AddType addType) {
        if(parent == null) {
            System.out.println("Parent is null");
            return;
        }
        if(!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;
        ClassyNode child =  createChild(parent.getClassyNode(), addType);
        if(child == null)
            return;
        parent.add(new ClassyTreeItem(child));
        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        classyTreeView.expandPath(classyTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(classyTreeView);
    }

    @Override
    public void removeChild(ClassyTreeItem nodeToRemove) {
        ClassyNodeComposite parent = (ClassyNodeComposite) nodeToRemove.getClassyNode().getParent();
        parent.removeChild(nodeToRemove.getClassyNode());
        defaultTreeModel.removeNodeFromParent(nodeToRemove);
        SwingUtilities.updateComponentTreeUI(classyTreeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
    }

    private ClassyNode createChild(ClassyNode classyNode, AddType addType)
    {
        if (classyNode instanceof ProjectExplorer && addType == AddType.PROJECT_ADD)
        {
            ProjectFactory projectFactory = new ProjectFactory();
            return projectFactory.createFactory(classyNode);
        }

        if(classyNode instanceof Project && addType == AddType.PACKAGE_ADD)
        {
            PackageFactory packageFactory = new PackageFactory();
            return packageFactory.createFactory(classyNode);
        }
        if(classyNode instanceof Project && addType == AddType.DIAGRAM_ADD)
        {
            DiagramFactory diagramFactory = new DiagramFactory();
            return diagramFactory.createFactory(classyNode);
        }

        if(classyNode instanceof Package && addType == AddType.PACKAGE_ADD)
        {
            PackageFactory packageFactory = new PackageFactory();
            return packageFactory.createFactory(classyNode);
        }
        if(classyNode instanceof Package && addType == AddType.DIAGRAM_ADD)
        {
            DiagramFactory diagramFactory = new DiagramFactory();
            return diagramFactory.createFactory(classyNode);
        }

        return null;
    }


    @Override
    public void update(Object notification, Object typeOfUpdate) {
        if (notification instanceof String){
            if(typeOfUpdate.equals(EventType.DIAGRAM_RENAME)){
                notification = ((String) notification).split("/")[1];
                ClassyTreeItem selected = (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
                selected.getClassyNode().setName((String) notification);
                classyTreeView.updateUI();
                System.out.println("UPDATE");
                SwingUtilities.updateComponentTreeUI(classyTreeView);
            }
            else if(typeOfUpdate.equals(EventType.PACKAGE_RENAME)){
                notification = ((String) notification).split("/")[1];
                ClassyTreeItem selected = (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
                selected.getClassyNode().setName((String) notification);
                classyTreeView.updateUI();
                System.out.println("UPDATE");
                SwingUtilities.updateComponentTreeUI(classyTreeView);
            }
            else if(typeOfUpdate.equals(EventType.PROJECT_RENAME)){
                notification = ((String) notification).split("/")[1];
                ClassyTreeItem selected = (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
                selected.getClassyNode().setName((String) notification);
                classyTreeView.updateUI();
                System.out.println("UPDATE");
                SwingUtilities.updateComponentTreeUI(classyTreeView);
            }
        }
    }
}
