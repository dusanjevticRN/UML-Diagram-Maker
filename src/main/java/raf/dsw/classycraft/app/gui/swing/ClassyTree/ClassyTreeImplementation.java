package raf.dsw.classycraft.app.gui.swing.ClassyTree;

import raf.dsw.classycraft.app.controller.AddAction.AddType;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.view.ClassyTreeView;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeLeaf;
import raf.dsw.classycraft.app.repository.factory.DiagramFactory;
import raf.dsw.classycraft.app.repository.factory.PackageFactory;
import raf.dsw.classycraft.app.repository.factory.ProjectFactory;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class ClassyTreeImplementation implements ClassyTree{

    private ClassyTreeView classyTreeView;
    private DefaultTreeModel defaultTreeModel;


    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
       ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
       defaultTreeModel = new DefaultTreeModel(root);
       classyTreeView = new ClassyTreeView(defaultTreeModel);
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
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) classyTreeView.getLastSelectedPathComponent();
    }

    private ClassyNode createChild(ClassyNode classyNode, AddType addType) {
        if (classyNode instanceof ProjectExplorer && addType == AddType.PROJECT_ADD) {
            ProjectFactory projectFactory = new ProjectFactory();
            return projectFactory.createFactory(classyNode);
        }
        if(classyNode instanceof  ProjectExplorer && addType == AddType.PACKAGE_ADD){
            System.out.println("Ne moze paket u projekat");
        }
        if(classyNode instanceof Project && addType == AddType.PROJECT_ADD) {
            System.out.println("Ne moze projekat u projekat");
        }
        if(classyNode instanceof Project && addType == AddType.PACKAGE_ADD){
            PackageFactory packageFactory = new PackageFactory();
            return packageFactory.createFactory(classyNode);
        }
        if(classyNode instanceof Project && addType == AddType.DIAGRAM_ADD){
            DiagramFactory diagramFactory = new DiagramFactory();
            return diagramFactory.createFactory(classyNode);
        }
        if(classyNode instanceof Package && addType == AddType.PROJECT_ADD){
            System.out.println("Ne moze projekat u paket");
        }
        if(classyNode instanceof Package && addType == AddType.PACKAGE_ADD){
            PackageFactory packageFactory = new PackageFactory();
            return packageFactory.createFactory(classyNode);
        }
        if(classyNode instanceof Package && addType == AddType.DIAGRAM_ADD){
            DiagramFactory diagramFactory = new DiagramFactory();
            return diagramFactory.createFactory(classyNode);
        }
        if(classyNode instanceof Diagram){
            System.out.println("Ne moze nista u dijagram");
        }
        return null;
    }

}
