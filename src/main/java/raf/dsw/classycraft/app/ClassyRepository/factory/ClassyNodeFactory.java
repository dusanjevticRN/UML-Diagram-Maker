package raf.dsw.classycraft.app.ClassyRepository.factory;

import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;

public abstract class ClassyNodeFactory
{
    public ClassyNode getNode(ClassyNode node)
    {
        ClassyNode newNode = createFactory(node);
        return newNode;
    }

    public abstract ClassyNode createFactory(ClassyNode node);

}

