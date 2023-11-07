package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.ClassyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.ClassyRepository.implementation.ProjectExplorer;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();

    void addChild(ClassyNodeComposite parent, ClassyNode child);
}
