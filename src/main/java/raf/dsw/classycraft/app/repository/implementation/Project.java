package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

import java.util.List;

public class Project extends ClassyNodeComposite {

    private String author, path;
    protected boolean changed = true;

    public Project( String name, ClassyNode parent, String author, String path) {
        super(parent, name);
        this.author = author;
        this.path = path;
    }

    public Project(ClassyNode parent, String name) {
        super(parent, name);
    }

    public void renameAuthor(String author){
        this.author = author;
        changed = true;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        changed = true;
    }

    @Override
    public int getIndex(List<ClassyNode> children) {
        return super.getIndex(children);
    }

    @Override
    public void deleteChild(ClassyNode child) {
        if(!(child instanceof ClassyNode))
            return;
        super.deleteChild(child);
        changed = true;
    }
    @Override
    public void addChild(ClassyNode node) {
        if(node instanceof ClassyNode && node != null){
            ClassyNode mapa = (ClassyNode) node;
            if(!this.getChildren().contains(mapa)){
                this.getChildren().add(mapa);
            }
            changed = true;
        }
    }
    public String getAuthor() {
        changed = true;
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
