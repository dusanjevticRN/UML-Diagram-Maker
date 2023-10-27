package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Element;

public class ElementFactory extends ClassyNodeFactory{


    @Override
    ClassyNode createFactory(ClassyNode node) {
        return new Element(node, "Element " + ((ClassyNodeComposite)node).getIndex(((ClassyNodeComposite) node).getChildren()));

    }
}
