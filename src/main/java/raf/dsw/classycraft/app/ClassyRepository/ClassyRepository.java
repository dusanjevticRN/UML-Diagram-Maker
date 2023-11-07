package raf.dsw.classycraft.app.ClassyRepository;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.MapRepository;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.ClassyRepository.implementation.ProjectExplorer;

@Setter
@Getter
public class ClassyRepository implements MapRepository
{
    private ProjectExplorer projectExplorer;

    public ClassyRepository()
    {
        projectExplorer = new ProjectExplorer("Project Explorer:");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }
    @Override
    public void addChild(ClassyNodeComposite parent, ClassyNode child)
    {
        //TODO: implement add Child method
    }
}
