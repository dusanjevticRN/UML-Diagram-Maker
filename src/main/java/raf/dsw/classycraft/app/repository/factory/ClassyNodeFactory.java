package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public abstract class ClassyNodeFactory {

    public ClassyNode getNode(ClassyNode node){
        ClassyNode newNode = createFactory(node);
        return newNode;
    }

    public abstract ClassyNode createFactory(ClassyNode node);


}

