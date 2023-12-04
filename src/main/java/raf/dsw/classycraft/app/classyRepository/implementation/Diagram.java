package raf.dsw.classycraft.app.classyRepository.implementation;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeLeaf;
import raf.dsw.classycraft.app.core.observer.ISubscriber;

import java.util.List;

public class Diagram extends ClassyNodeLeaf
{
    private List<DiagramElement> diagramElements;
    public Diagram(String name, ClassyNode parent, boolean isPattern) {
        super(parent, name + " ");
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }


    @Override
    public void notifySubscriber(Object notification, Object typeOfUpdate) {

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

    }
}
