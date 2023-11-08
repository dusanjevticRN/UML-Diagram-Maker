package raf.dsw.classycraft.app.gui.swing.view.tabbedPane;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.core.eventHandler.EventType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClassyTabbedPane extends JTabbedPane implements ISubscriber {

        EventBus eventBus = EventBus.getInstance();
        List<Diagram> openDiagrams;
        public ClassyTabbedPane() {
            super();
            openDiagrams = new ArrayList<>();
            eventBus.subscribe(EventType.DIAGRAM_SELECTION, this);
            eventBus.subscribe(EventType.DIAGRAM_DELETION, this);
        }

    @Override
    public void update(Object notification, Object typeOfUpdate) {
        if (notification instanceof ClassyTreeItem) {
            ClassyTreeItem item = (ClassyTreeItem) notification;

            if (EventType.DIAGRAM_SELECTION.equals(typeOfUpdate)) {
                //System.out.println("SELECT");

                if(this.openDiagrams.contains((Diagram) item.getClassyNode())){
                    this.setSelectedIndex(this.indexOfTab(item.getClassyNode().getName()));
                    return;
                }

                this.openDiagrams.add((Diagram) item.getClassyNode());
                String title = item.getClassyNode().getName();
                this.addTab(title, new JPanel());
                int index = this.indexOfTab(title);
                this.getComponentAt(index).setName(item.getClassyNode().getUniqueId());

            } else if (EventType.DIAGRAM_DELETION.equals(typeOfUpdate)) {
                //System.out.println("DELETE");

                this.openDiagrams.remove((Diagram) ((ClassyTreeItem) notification).getClassyNode());
                String uniqueId = item.getClassyNode().getUniqueId();
                for (int i = 0; i < this.getTabCount(); i++) {
                    Component tabComponent = this.getComponentAt(i);
                    if (tabComponent.getName().equals(uniqueId)) {
                        this.removeTabAt(i);
                        break;
                    }
                }
            }
        }
        else if(notification instanceof ClassyNode){
            System.out.println("DELETE");
            if(EventType.DIAGRAM_DELETION.equals(typeOfUpdate)){
                this.openDiagrams.remove((Diagram) notification);
                String uniqueId = ((ClassyNode) notification).getUniqueId();
                for (int i = 0; i < this.getTabCount(); i++) {
                    Component tabComponent = this.getComponentAt(i);
                    if (tabComponent.getName().equals(uniqueId)) {
                        this.removeTabAt(i);
                        break;
                    }
                }
            }
        }
    }
}
