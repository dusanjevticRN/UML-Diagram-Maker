package raf.dsw.classycraft.app.gui.swing.ClassyTree.controller;

import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.ClassyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.ClassyRepository.implementation.Package;
import raf.dsw.classycraft.app.ClassyRepository.implementation.Project;
import raf.dsw.classycraft.app.ClassyRepository.implementation.ProjectExplorer;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class Listener implements TreeSelectionListener {


    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem)path.getLastPathComponent();
        System.out.println("Selektovan cvor:"+ treeItemSelected.getClassyNode().getName());
        System.out.println("getPath: "+e.getPath());
        if(treeItemSelected.getClassyNode() instanceof ProjectExplorer){
            System.out.println("Tip node-a: ProjectExplorer");
        }
        else if(treeItemSelected.getClassyNode() instanceof Package){
            System.out.println("Tip node-a: Package");
        }
        else if(treeItemSelected.getClassyNode() instanceof Diagram){
            System.out.println("Tip node-a: Diagram");
        }
        else if(treeItemSelected.getClassyNode() instanceof Project){
            System.out.println("Tip node-a: Project");
        }
        else{
            System.out.println("Tip node-a: Nista od navedenog");
        }
    }
}
