package raf.dsw.classycraft.app.repository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeLeaf;


@Getter
@Setter
public class Package extends ClassyNodeComposite {


    public Package(ClassyNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(ClassyNode nodeToAdd) {
        if(nodeToAdd != null && nodeToAdd instanceof Diagram){
            Diagram diagram = (Diagram) nodeToAdd;
            if(!this.getChildren().contains(diagram)){
                this.getChildren().add(diagram);
            }
        }
        else if(nodeToAdd != null && nodeToAdd instanceof Package){
            Package pack = (Package) nodeToAdd;
            if(!this.getChildren().contains(pack)){
                this.getChildren().add(pack);
            }
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void notifySubscriber(Object notification, Object typeOfUpdate) {

    }
}
