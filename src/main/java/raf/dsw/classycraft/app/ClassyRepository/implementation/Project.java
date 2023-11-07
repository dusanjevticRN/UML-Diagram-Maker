package raf.dsw.classycraft.app.ClassyRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.core.observer.ISubscriber;

import java.util.List;

@Getter
@Setter
public class Project extends ClassyNodeComposite
{
    private String author, path;

    public Project( String name, ClassyNode parent, String author, String path)
    {
        super(parent, name);
        this.author = author;
        this.path = path;
    }

    public Project(ClassyNode parent, String name) {
        super(parent, name);
    }

    public void renameAuthor(String author){
        this.author = author;
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
    public void addChild(ClassyNode node)
    {
        if(node != null && node instanceof Diagram)
        {
            Diagram diagram = (Diagram) node;

            if(!this.getChildren().contains(diagram))
                this.getChildren().add(diagram);

        }

        else if(node != null && node instanceof Package)
        {
            Package pack = (Package) node;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
