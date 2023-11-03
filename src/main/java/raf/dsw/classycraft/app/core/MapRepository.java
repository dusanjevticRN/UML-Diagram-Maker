package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();

    void addChild(ClassyNodeComposite parent, ClassyNode child);
}
