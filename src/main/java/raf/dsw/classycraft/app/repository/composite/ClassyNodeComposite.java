package raf.dsw.classycraft.app.repository.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode{

    private List<ClassyNode> children;
    public ClassyNodeComposite(ClassyNode parent, String name) {
        super(parent, name);
        this.children = new ArrayList<>();
    }

    public void addChild(ClassyNode nodeToAdd){
        if(nodeToAdd != null)
            this.children.add(nodeToAdd);
    }

    public void deleteChild(ClassyNode nodeToDelete){
        if(nodeToDelete == null || this.children == null || this.children.isEmpty() || !this.children.contains(nodeToDelete)){
            return;
        }
        nodeToDelete.setParent(null);
        this.children.remove(nodeToDelete);
    }

    public int getIndex(List<ClassyNode> children){
        return children.size() + 1;
    }

    public List<ClassyNode> getChildren() {
        return children;
    }

}
