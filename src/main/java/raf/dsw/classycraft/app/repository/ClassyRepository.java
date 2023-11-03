package raf.dsw.classycraft.app.repository;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.MapRepository;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

@Setter
@Getter
public class ClassyRepository implements MapRepository {
    private ProjectExplorer projectExplorer;

    public ClassyRepository() {
        projectExplorer = new ProjectExplorer("Project Explorer:");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }
    @Override
    public void addChild(ClassyNodeComposite parent, ClassyNode child) {
        //TODO: implement add Child method
    }
}
