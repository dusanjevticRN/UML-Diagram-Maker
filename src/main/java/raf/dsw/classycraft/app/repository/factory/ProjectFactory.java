package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Project;

public class ProjectFactory extends ClassyNodeFactory{

    @Override
    public ClassyNode createFactory(ClassyNode node) {
        String userName = System.getProperty("user.name");
        Project newProject = new Project("Project " + ((ClassyNodeComposite)node).getIndex(((ClassyNodeComposite) node).getChildren()), node, userName, null);

        return newProject;
    }
}
