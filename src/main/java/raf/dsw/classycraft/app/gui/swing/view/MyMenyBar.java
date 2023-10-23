package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.AboutUsAction;
import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar
{
    public MyMenyBar()
    {

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        ExitAction exitAction = new ExitAction();
        fileMenu.add(exitAction);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        AboutUsAction aboutUs = new AboutUsAction();
        editMenu.add(aboutUs);

        this.add(editMenu);
        this.add(fileMenu);
    }

}