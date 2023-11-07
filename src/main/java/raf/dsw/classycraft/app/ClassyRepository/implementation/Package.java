package raf.dsw.classycraft.app.ClassyRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.observer.ISubscriber;


@Getter
@Setter
public class Package extends ClassyNodeComposite
{
    public Package(ClassyNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(ClassyNode nodeToAdd)
    {
        if(nodeToAdd != null && nodeToAdd instanceof Diagram)
        {
            Diagram diagram = (Diagram) nodeToAdd;

            if(!this.getChildren().contains(diagram))
                this.getChildren().add(diagram);

        }

        else if(nodeToAdd != null && nodeToAdd instanceof Package)
        {
            Package pack = (Package) nodeToAdd;

            if(!this.getChildren().contains(pack))
                this.getChildren().add(pack);

        }
    }

    @Override
    public void notifySubscriber(Object notification, Object typeOfUpdate) {

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

}
