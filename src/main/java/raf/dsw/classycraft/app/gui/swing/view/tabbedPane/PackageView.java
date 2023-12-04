package raf.dsw.classycraft.app.gui.swing.view.tabbedPane;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PackageView extends CloseableTabbedPane implements ISubscriber {

        EventBus eventBus = EventBus.getInstance();

        List<Diagram> openDiagrams;
        List<Component> tabs;
        public PackageView()
        {
            super();
            openDiagrams = new ArrayList<>();
            tabs = new ArrayList<>();
            eventBus.subscribe(EventType.DIAGRAM_SELECTION, this);
            eventBus.subscribe(EventType.DIAGRAM_DELETION, this);
            eventBus.subscribe(EventType.DIAGRAM_RENAME, this);
            eventBus.subscribe(EventType.DIAGRAM_CLOSE, this);
            eventBus.subscribe(EventType.DIAGRAM_LIST_DELETION, this);
            eventBus.subscribe(EventType.CLOSE_TABS, this);
        }

    @Override
    public void update(Object notification, Object typeOfUpdate)
    {
        if(EventType.CLOSE_TABS.equals(typeOfUpdate))
        {
            this.closeAllTabs();
            return;
        }
        if (notification instanceof ClassyTreeItem)
        {
            ClassyTreeItem item = (ClassyTreeItem) notification;



            if (EventType.DIAGRAM_DELETION.equals(typeOfUpdate))
            {
                System.out.println("DELETE in tab");

                this.openDiagrams.remove((Diagram) ((ClassyTreeItem) notification).getClassyNode());
                String uniqueId = item.getClassyNode().getUniqueId();
                String name = "";
                for(char c: uniqueId.toCharArray()){
                    if(c == '_')
                        break;
                    name = name + c;
                }
                System.out.println(name);
                for (int i = 0; i < this.getTabCount(); i++)
                {
                    Component tabComponent = this.getComponentAt(i);
                    System.out.println(tabComponent.getName());
                    if (tabComponent.getName().equals(name))
                    {
                        this.removeTabAt(i);
                        break;
                    }
                }
            }
        }
        else if(notification instanceof ClassyNode)
        {
            if(EventType.DIAGRAM_DELETION.equals(typeOfUpdate))
            {
                this.openDiagrams.remove((Diagram) notification);
                String uniqueId = ((ClassyNode) notification).getUniqueId();

                for (int i = 0; i < this.getTabCount(); i++)
                {
                    Component tabComponent = this.getComponentAt(i);

                    if (tabComponent.getName().equals(uniqueId))
                    {
                        System.out.println("Deleting" + tabComponent.getName());
                        this.removeTabAt(i);
                        break;
                    }
                }
            }
            else if(EventType.DIAGRAM_SELECTION.equals(typeOfUpdate)){
                Diagram selectedDiagram = (Diagram) notification;
                if(this.openDiagrams.contains((Diagram) notification))
                {
                    this.setSelectedIndex(this.indexOfTab(((ClassyNode) notification).getName()));
                    return;
                }

                this.openDiagrams.add((Diagram) notification);
                String title = ((ClassyNode) notification).getName();
                DiagramPanel diagramPanel = new DiagramPanel(selectedDiagram);
                this.addTab(title, diagramPanel);
                int index = this.indexOfTab(title);
                this.getComponentAt(index).setName(((ClassyNode) notification).getUniqueId());

            }
        }
        else if(EventType.DIAGRAM_LIST_DELETION.equals(typeOfUpdate))
        {
            System.out.println("LIST DELETE1");

            if(notification instanceof List)
            {
                System.out.println("LIST DELETE2");
                List<ClassyNode> list = (List<ClassyNode>) notification;

                for(ClassyNode node : list)
                {
                    if(node instanceof Diagram)
                    {
                        this.openDiagrams.remove((Diagram) node);
                        String uniqueId = node.getUniqueId();

                        for (int i = 0; i < this.getTabCount(); i++)
                        {
                            Component tabComponent = this.getComponentAt(i);

                            if (tabComponent.getName().equals(uniqueId))
                            {
                                System.out.println("Deleting" + tabComponent.getName());
                                this.removeTabAt(i);
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(notification instanceof Component)
        {
            if(EventType.DIAGRAM_CLOSE.equals(typeOfUpdate))
            {
                JPanel temp = (JPanel) notification;
                String id = temp.getName();
                String name = "";

                for(char c: id.toCharArray())
                {
                    if(c == '_')
                        break;
                    name = name + c;
                }

                name.substring(0, name.length() - 1);
                for(Diagram d : openDiagrams){
                    System.out.println(d.getName() + " - " + name);
                    if(d.getName().equals(name))
                    {
                        //System.out.println("FOUND");
                        openDiagrams.remove(d);

                        for (int i = 0; i < this.getTabCount(); i++)
                        {
                            Component tabComponent = this.getComponentAt(i);
                            String tname = "";
                            for(char c: tabComponent.getName().toCharArray()){
                                if(c == '_')
                                    break;
                                tname = tname + c;
                            }

                            tname.substring(0, tname.length() - 1);
                            //System.out.println(tabComponent.getName() + " - " + name);
                            if (tname.equals(name))
                            {
                                //System.out.println("Deleting" + tabComponent.getName());
                                this.removeTabAt(i);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }


        if(EventType.DIAGRAM_RENAME.equals(typeOfUpdate))
        {
            System.out.println("RENAME");
            System.out.println(notification);
            String oldName = "";
            String noti = (String) notification;
            String split[] = noti.split("/");
            String newName = split[1];
            oldName = split[0];
            oldName.substring(0, oldName.length() - 1);

            for (int i = 0; i < this.getTabCount(); i++)
            {
                Component tabComponent = this.getComponentAt(i);
                String tname = "";
                for(char c: tabComponent.getName().toCharArray())
                {
                    if(c == '_')
                        break;
                    tname = tname + c;
                }

                System.out.println(tname);
                System.out.println(oldName);
                System.out.println(tname.equals(oldName));

                if (tname.equals(oldName))
                {
                    System.out.println("Renaming" + tabComponent.getName());
                    System.out.println(newName);
                    tabComponent.setName(newName);
                    this.setTitleAt(i, newName);
                    Component tempTab = this.getComponentAt(i);
                    this.remove(i);
                    this.add(tempTab);
                    this.revalidate();
                    this.repaint();
                    break;
                }
            }
        }


    }
    private void closeAllTabs() {
        while (this.getTabCount() > 0) {
            this.removeTabAt(0);
        }
        this.openDiagrams.clear();
    }

    public DiagramPanel getSelectedDiagramPanel()
    {
        return (DiagramPanel) this.getSelectedComponent();
    }



}
