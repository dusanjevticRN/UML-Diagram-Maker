package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Package;

public class PackageFactory extends ClassyNodeFactory{


    @Override
    public ClassyNode createFactory(ClassyNode node) {
        return new Package(node, "Package " + ((ClassyNodeComposite)node).getIndex(((ClassyNodeComposite) node).getChildren()));

    }
}
