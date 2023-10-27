package raf.dsw.classycraft.app.repository;

import raf.dsw.classycraft.app.core.MapRepository;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public class ClassyRepository implements MapRepository {
    private ProjectExplorer projectExplorer;

    public ClassyRepository() {
        projectExplorer = new ProjectExplorer("Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }
}
