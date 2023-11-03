package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeLeaf;


public class Package extends ClassyNodeLeaf {


    public Package(ClassyNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
