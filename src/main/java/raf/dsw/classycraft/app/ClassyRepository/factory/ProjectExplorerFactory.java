package raf.dsw.classycraft.app.ClassyRepository.factory;

import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.implementation.ProjectExplorer;

public class ProjectExplorerFactory extends ClassyNodeFactory
{
    @Override
    public ClassyNode createFactory(ClassyNode node) {
        return new ProjectExplorer("Project Explorer");
    }
}
