package raf.dsw.classycraft.app.gui.swing.controller;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.controller.addAction.AddDiagramAction;
import raf.dsw.classycraft.app.gui.swing.controller.addAction.AddPackageAction;
import raf.dsw.classycraft.app.gui.swing.controller.addAction.AddProjectAction;
import raf.dsw.classycraft.app.gui.swing.controller.diagramActions.*;

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
    private AddClassAction addClassAction;
    private AddInterfaceAction addInterfaceAction;
    private AddEnumAction addEnumAction;
    private AddRelationshipAction addRelationshipAction;
    private AddContentAction addContentAction;
    private MoveElementAction moveElementAction;
    private SelectElementAction selectElementAction;
    private DeleteElementAction deleteElementAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
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
        this.addClassAction = new AddClassAction();
        this.addInterfaceAction = new AddInterfaceAction();
        this.addEnumAction = new AddEnumAction();
        this.addRelationshipAction = new AddRelationshipAction();
        this.addContentAction = new AddContentAction();
        this.moveElementAction = new MoveElementAction();
        this.selectElementAction = new SelectElementAction();
        this.deleteElementAction = new DeleteElementAction();
        this.zoomInAction = new ZoomInAction();
        this.zoomOutAction = new ZoomOutAction();
    }
}
