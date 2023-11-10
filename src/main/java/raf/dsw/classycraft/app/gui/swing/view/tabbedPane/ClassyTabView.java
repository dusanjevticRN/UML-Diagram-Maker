package raf.dsw.classycraft.app.gui.swing.view.tabbedPane;

import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.classyRepository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.core.observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.core.eventHandler.EventType;

import javax.swing.*;


public class ClassyTabView extends JPanel implements ISubscriber {

    private JLabel projectNameLabel;
    private JLabel authorNameLabel;
    private JLabel pathLabel;
    private JLabel diagramLabel;
    private JTabbedPane tabbedPane;

    public ClassyTabView() {
        projectNameLabel = new JLabel("Select a project...");
        this.add(projectNameLabel); // Add the label to the panel
        authorNameLabel = new JLabel("");
        this.add(authorNameLabel);
    }

    // Existing methods...

    // ISubscriber interface method
    @Override
    public void update(Object notification, Object typeOfUpdate) {
        if (notification instanceof ClassyTreeItem && typeOfUpdate.equals(EventType.PROJECT_SELECTION)) {
            ClassyTreeItem item = (ClassyTreeItem) notification;
            Project newItem = null;
            if(item.getClassyNode() instanceof ProjectExplorer){
                projectNameLabel.setText("Select a project...");
                authorNameLabel.setText("");
                return;
            }
            else if(!(item.getClassyNode() instanceof Project)){
                newItem = getProject(item);
                if(newItem != null) {
                    projectNameLabel.setText("Project: " + newItem.getName());
                    authorNameLabel.setText("Author: " + newItem.getAuthor());
                }
            }
            else {
                Project classyNode = (Project) item.getClassyNode(); // Assuming ClassyNode has a method to get the project name
                projectNameLabel.setText("Project: " + classyNode.getName()); // Update the label with the project name
                authorNameLabel.setText("Author: " + classyNode.getAuthor()); // Update the label with the author name
            }
        }
    }

    private Project getProject(ClassyTreeItem item) {
        if(item == null)
            return null;
        if(item.getClassyNode() instanceof Project)
            return (Project) item.getClassyNode();
        if(item.getParent() instanceof Project)
            return (Project) item.getParent();
        return getProject((ClassyTreeItem) item.getParent());
    }
}


