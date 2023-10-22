package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.AboutUsAction;
import raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction ea = new ExitAction();
        fileMenu.add(ea);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        AboutUsAction aboutUs = new AboutUsAction();
        editMenu.add(aboutUs);
        add(editMenu);

        add(fileMenu);

    }

}