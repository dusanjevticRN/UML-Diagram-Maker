package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

public class DiagramFactory extends ClassyNodeFactory{

    @Override
    public ClassyNode createFactory(ClassyNode node) {
        return new Diagram("Diagram " + ((ClassyNodeComposite)node).getIndex(((ClassyNodeComposite) node).getChildren()),node,false);
    }
}
