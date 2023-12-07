package raf.dsw.classycraft.app.gui.swing.ClassyTree;

import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
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
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class ClassyTreeImplementation implements ClassyTree, ISubscriber {

    private ClassyTreeView classyTreeView;
    private DefaultTreeModel defaultTreeModel;
    private Diagram currentDiagram;
    public ClassyTreeImplementation() {
        classyTreeView = null;
        defaultTreeModel = null;
        EventBus.getInstance().subscribe(EventType.DIAGRAM_RENAME, this);
        EventBus.getInstance().subscribe(EventType.PACKAGE_RENAME, this);
        EventBus.getInstance().subscribe(EventType.PROJECT_RENAME, this);
        EventBus.getInstance().subscribe(EventType.ADD_CLASS_TO_TREE, this);
        EventBus.getInstance().subscribe(EventType.ADD_INTERFACE_TO_TREE, this);
        EventBus.getInstance().subscribe(EventType.ADD_ENUM_TO_TREE, this);
        EventBus.getInstance().subscribe(EventType.SET_PANEL, this);
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
        ClassyTreeItem toAdd = new ClassyTreeItem(child);
        toAdd.setParent(parent);
        parent.getChildren().add(toAdd);
        parent.add(new ClassyTreeItem(child));
        parent.getChildren().add(new ClassyTreeItem(child));
        child.setParent(parent.getClassyNode());
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
        if(typeOfUpdate.equals(EventType.SET_PANEL)){
            currentDiagram = (Diagram) notification;
        }
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
        else if(typeOfUpdate.equals(EventType.ADD_CLASS_TO_TREE)){
            System.out.println("ADD CLASS TO TREE");
            ClassyNode classToAdd = (ClassyNode) notification;

            ClassyTreeItem root = (ClassyTreeItem) classyTreeView.getModel().getRoot();

            ClassyTreeItem targetParent = findNodeWithClassyNode(root, currentDiagram);

            if (targetParent != null) {
                ClassyTreeItem newItem = new ClassyTreeItem(classToAdd);
                targetParent.add(newItem);

                ((DefaultTreeModel) classyTreeView.getModel()).reload(targetParent);
            } else {
                System.out.println("Nema ga");
            }
        }

        else if(typeOfUpdate.equals(EventType.ADD_INTERFACE_TO_TREE)){
            ClassyNode interfaceToAdd = (ClassyNode) notification;

            ClassyTreeItem root = (ClassyTreeItem) classyTreeView.getModel().getRoot();

            ClassyTreeItem targetParent = findNodeWithClassyNode(root, currentDiagram);

            if (targetParent != null) {
                ClassyTreeItem newItem = new ClassyTreeItem(interfaceToAdd);
                targetParent.add(newItem);

                ((DefaultTreeModel) classyTreeView.getModel()).reload(targetParent);
            } else {
                System.out.println("Nema ga");
            }
        }
        else if(typeOfUpdate.equals(EventType.ADD_ENUM_TO_TREE)){
            ClassyNode enumToAdd = (ClassyNode) notification;

            ClassyTreeItem root = (ClassyTreeItem) classyTreeView.getModel().getRoot();

            ClassyTreeItem targetParent = findNodeWithClassyNode(root, currentDiagram);

            if (targetParent != null) {
                ClassyTreeItem newItem = new ClassyTreeItem(enumToAdd);
                targetParent.add(newItem);

                ((DefaultTreeModel) classyTreeView.getModel()).reload(targetParent);
            } else {
                System.out.println("Nema ga");
            }
        }
        else if(typeOfUpdate.equals(EventType.DELETE_ELEMENTS)){
            Pair pair = (Pair) notification;
            ClassyTreeItem root = (ClassyTreeItem) classyTreeView.getModel().getRoot();
            DiagramPanel pnl = (DiagramPanel) pair.getFirst();
            ClassyTreeItem targetParent = findNodeWithClassyNode(root, pnl.getDiagram());
            if (targetParent != null) {
                List<ClassyTreeItem> toRemove = new ArrayList<>();
                for (int i = 0; i < targetParent.getChildCount(); i++) {
                    ClassyTreeItem child = (ClassyTreeItem) targetParent.getChildAt(i);
                    if(child.getClassyNode() instanceof Diagram){
                        toRemove.add(child);
                    }
                }
                for(ClassyTreeItem item: toRemove){
                    targetParent.remove(item);
                }
                ((DefaultTreeModel) classyTreeView.getModel()).reload(targetParent);
            } else {
                System.out.println("Nema ga");
            }
        }
    }


    private ClassyTreeItem findNodeWithClassyNode(ClassyTreeItem node, ClassyNode classyNode) {
        if (node.getClassyNode().equals(classyNode)) {
            return node;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            ClassyTreeItem foundNode = findNodeWithClassyNode((ClassyTreeItem) node.getChildAt(i), classyNode);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }

}
