package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeLeaf;

import java.util.List;

public class Diagram extends ClassyNodeLeaf {



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
}
