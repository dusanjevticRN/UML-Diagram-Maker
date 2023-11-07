package raf.dsw.classycraft.app.ClassyRepository.factory;

import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.ClassyRepository.implementation.Project;

public class ProjectFactory extends ClassyNodeFactory
{

    @Override
    public ClassyNode createFactory(ClassyNode node)
    {
        String userName = System.getProperty("user.name");
        Project newProject = new Project("Project " + ((ClassyNodeComposite)node).getIndex(((ClassyNodeComposite) node).getChildren()), node, userName, null);

        return newProject;
    }
}
