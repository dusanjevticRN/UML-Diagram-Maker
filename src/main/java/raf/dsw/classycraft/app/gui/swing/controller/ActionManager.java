package raf.dsw.classycraft.app.gui.swing.controller;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.controller.AddAction.AddDiagramAction;
import raf.dsw.classycraft.app.gui.swing.controller.AddAction.AddPackageAction;
import raf.dsw.classycraft.app.gui.swing.controller.AddAction.AddProjectAction;

@Getter
public class ActionManager
{
    private AboutUsAction aboutUsAction;
    private EditAction editAction;
    private ExitAction exitAction;
    private AddDiagramAction addDiagramAction;
    private AddPackageAction addPackageAction;
    private AddProjectAction addProjectAction;
    private DeleteAction deleteAction;

    public ActionManager()
    {
        initialiseAction();
    }

    private void initialiseAction()
    {
        this.aboutUsAction = new AboutUsAction();
        this.editAction = new EditAction();
        this.exitAction = new ExitAction();
        this.addDiagramAction = new AddDiagramAction();
        this.addPackageAction = new AddPackageAction();
        this.addProjectAction = new AddProjectAction();
        this.deleteAction = new DeleteAction();
    }
}
