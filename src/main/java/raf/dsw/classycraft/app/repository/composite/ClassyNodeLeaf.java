package raf.dsw.classycraft.app.repository.composite;

public abstract class ClassyNodeLeaf extends ClassyNode {
    public ClassyNodeLeaf(ClassyNode parent, String name) {
        super(parent, name);
    }

    public abstract void delete();
}
