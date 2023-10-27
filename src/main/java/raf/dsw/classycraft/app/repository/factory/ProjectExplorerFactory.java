package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public class ProjectExplorerFactory extends ClassyNodeFactory{

    @Override
    ClassyNode createFactory(ClassyNode node) {
        return new ProjectExplorer("Project Explorer");
    }
}
