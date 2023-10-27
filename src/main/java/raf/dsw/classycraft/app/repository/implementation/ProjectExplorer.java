package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

import java.util.List;

public class ProjectExplorer extends ClassyNodeComposite {

    public ProjectExplorer(String name) {
        super(null, name);
        super.getChildren();
    }

    @Override
    public void addChild(ClassyNode node) {
        if(node instanceof Project && node != null){
            Project project = (Project) node;
            if(!this.getChildren().contains(project)){
                this.getChildren().add(project);
                this.setParent(this);
            }
        }
    }

    @Override
    public int getIndex(List<ClassyNode> children) {
        return super.getIndex(children);
    }

    @Override
    public void deleteChild(ClassyNode child) {
        if(!(child instanceof Project))
            return;
        super.deleteChild(child);
    }
}
