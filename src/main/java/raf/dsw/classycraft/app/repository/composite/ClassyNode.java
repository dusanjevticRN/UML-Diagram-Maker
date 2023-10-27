package raf.dsw.classycraft.app.repository.composite;

public abstract class ClassyNode {
    protected transient ClassyNode parent;
    protected String name;

    public ClassyNode(ClassyNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public ClassyNode getParent() {
        return parent;
    }
    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
