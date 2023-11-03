package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public class SimpleFactory {
    public static ProjectFactory pf = new ProjectFactory();
    public static DiagramFactory mmf = new DiagramFactory();
    public static PackageFactory ef = new PackageFactory();

    public static ClassyNodeFactory getFactory(ClassyNode parent){
        if(parent instanceof ProjectExplorer)
            return pf;
        if(parent instanceof Project)
            return mmf;
        if(parent instanceof Diagram)
            return ef;
        return null;
    }
}
