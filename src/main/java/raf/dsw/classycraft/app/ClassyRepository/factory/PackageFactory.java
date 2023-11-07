package raf.dsw.classycraft.app.ClassyRepository.factory;

import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.ClassyRepository.implementation.Package;

public class PackageFactory extends ClassyNodeFactory
{
    @Override
    public ClassyNode createFactory(ClassyNode node)
    {
        return new Package(node, "Package " + ((ClassyNodeComposite)node).getIndex(((ClassyNodeComposite) node).getChildren()));
    }
}
