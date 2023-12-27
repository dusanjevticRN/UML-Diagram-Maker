package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.Project;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SaveAction extends AbstractClassyAction{
public SaveAction() {
        this.putValue(SMALL_ICON, loadIcon("/images/save.png"));
        this.putValue(SHORT_DESCRIPTION, "Save");
        this.putValue(NAME, "Save");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save action");

        if (!(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project)) {
            System.out.println("Not a project");
            return;
        }

        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();

        if (!project.isChanged()) {
            return;
        }

        //project.setChanged(false);
        String defaultPath = "saved_projects/";
        project.setPath(defaultPath + project.getName() + ".json");

        try {
            System.out.println("Saving project" + project.getName());
            AppCore.getInstance().getSerializer()
                    .saveProject(project.getPath(), project);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
