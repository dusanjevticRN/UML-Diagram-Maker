package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.core.MessageGenerator;
import raf.dsw.classycraft.app.messageGenerator.EventType;
import raf.dsw.classycraft.app.messageGenerator.Message;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber
{
    private static MainFrame instance;
    //private MessageGenerator messageGenerator;

    //buduca polja za sve komponente view-a na glavnom prozoru

    private MainFrame()
    {

    }

    private void initialize()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("ClassyCrafT");

        MyMenyBar menu = new MyMenyBar();
        this.setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        this.add(toolBar, BorderLayout.NORTH);
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void update(Object notification, Object typeOfUpdate)
    {
        Message msg = (Message) notification;
        JOptionPane.showMessageDialog(MainFrame.getInstance(), msg.getContent(), msg.getType(), 1);
    }
}
