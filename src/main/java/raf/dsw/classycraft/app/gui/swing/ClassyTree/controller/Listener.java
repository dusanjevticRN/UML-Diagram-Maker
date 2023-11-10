package raf.dsw.classycraft.app.gui.swing.ClassyTree.controller;

import raf.dsw.classycraft.app.core.observer.IPublisher;
import raf.dsw.classycraft.app.core.observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.core.eventHandler.EventType;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

public class Listener implements TreeSelectionListener, IPublisher {

    List<ISubscriber> subscribers = new ArrayList<>();

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if (!e.isAddedPath()) return;

        TreePath path = e.getPath();
        if (path == null) return;
        ClassyTreeItem item = (ClassyTreeItem) path.getLastPathComponent();
        if (item == null) return;

        notifySubscriber(item, EventType.PROJECT_SELECTION);

        if (item.getClassyNode() instanceof Diagram) {
            notifySubscriber(item, EventType.DIAGRAM_SELECTION);
        }
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber(Object notification, Object typeOfUpdate) {
        for(ISubscriber subscriber : subscribers)
            subscriber.update(notification, typeOfUpdate);
    }
}
