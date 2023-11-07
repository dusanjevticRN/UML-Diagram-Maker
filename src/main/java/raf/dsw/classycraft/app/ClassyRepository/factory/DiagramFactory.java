package raf.dsw.classycraft.app.ClassyRepository.factory;

import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.ClassyRepository.implementation.Diagram;

public class DiagramFactory extends ClassyNodeFactory
{

    @Override
    public ClassyNode createFactory(ClassyNode node)
    {
        return new Diagram("Diagram " + ((ClassyNodeComposite)node).getIndex(((ClassyNodeComposite) node).getChildren()),node,false);
    }
}
