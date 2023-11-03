package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.*;
import raf.dsw.classycraft.app.controller.AddAction.AddDiagramAction;
import raf.dsw.classycraft.app.controller.AddAction.AddPackageAction;
import raf.dsw.classycraft.app.controller.AddAction.AddProjectAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar
{
    public MyMenyBar()
    {

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        ExitAction exitAction = new ExitAction();
        AddProjectAction addProjectAction = new AddProjectAction();
        AddPackageAction addPackageAction = new AddPackageAction();
        AddDiagramAction addDiagramAction = new AddDiagramAction();
        fileMenu.add(addDiagramAction);
        fileMenu.add(addPackageAction);
        fileMenu.add(addProjectAction);
        fileMenu.add(exitAction);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        AboutUsAction aboutUs = new AboutUsAction();
        editMenu.add(aboutUs);

        this.add(editMenu);
        this.add(fileMenu);
    }

}