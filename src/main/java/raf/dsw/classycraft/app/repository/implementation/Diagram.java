package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

import java.util.List;

public class Diagram extends ClassyNodeComposite {

    private boolean isPattern;
    private boolean hasCentralTopic;

    public Diagram(String name, ClassyNode parent, boolean isPattern) {
        super(parent, name + " ");
        this.isPattern = isPattern;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getIndex(List<ClassyNode> children) {
        return super.getIndex(children);
    }

    @Override
    public void deleteChild(ClassyNode child) {
        if(!(child instanceof Element))
            return;
        super.deleteChild(child);
    }

    @Override
    public void addChild(ClassyNode node) {
        if(node instanceof Element && node != null){
            Element e = (Element) node;
            if(!this.getChildren().contains(e)){
                this.getChildren().add(e);
            }
        }
    }

    public void setHasCentralTopic(boolean hasCentralTopic) {
        this.hasCentralTopic = hasCentralTopic;
    }
}
