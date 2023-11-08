package raf.dsw.classycraft.app.classyRepository.implementation;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeLeaf;
import raf.dsw.classycraft.app.core.observer.ISubscriber;

public class Diagram extends ClassyNodeLeaf
{
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
