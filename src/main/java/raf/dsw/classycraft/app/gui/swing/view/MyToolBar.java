package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.*;
import raf.dsw.classycraft.app.controller.AddAction.AddDiagramAction;
import raf.dsw.classycraft.app.controller.AddAction.AddPackageAction;
import raf.dsw.classycraft.app.controller.AddAction.AddProjectAction;

import javax.swing.*;

public class MyToolBar extends JToolBar
{
    public MyToolBar()
    {
        super(HORIZONTAL);
        this.setFloatable(false);

        ExitAction exitAction = new ExitAction();
        AddPackageAction addPackageAction = new AddPackageAction();
        AboutUsAction aboutUsAction = new AboutUsAction();
        AddProjectAction addProjectAction = new AddProjectAction();
        AddDiagramAction addDiagramAction = new AddDiagramAction();
        EditAction editAction = new EditAction();
        DeleteAction deleteAction = new DeleteAction();

        this.add(exitAction); //exitAction ce se executovati automatski tako što će se pokrenuti actionPerformed metoda, klikom na odgovarajući GUI element (ikonica).
        this.add(aboutUsAction); // isto
        this.add(addProjectAction); // isto
        this.add(addPackageAction); // isto
        this.add(addDiagramAction); // isto
        this.add(editAction); // isto
        this.add(deleteAction); // isto
    }
}
