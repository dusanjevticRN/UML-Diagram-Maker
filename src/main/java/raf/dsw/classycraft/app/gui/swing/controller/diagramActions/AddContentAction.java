package raf.dsw.classycraft.app.gui.swing.controller.diagramActions;

import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContentAction extends AbstractClassyAction
{
    public AddContentAction()
    {
        this.putValue(SMALL_ICON, loadIcon("/images/Content.png"));
        this.putValue(SHORT_DESCRIPTION, "Add Content");
        this.putValue(NAME, "Add Content");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnAddField = new JButton("Add Field");
        JButton btnAddMethod = new JButton("Add Method");

        btnAddField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Add Field button pressed");
                EventBus.getInstance().notifySubscriber(this, EventType.ADD_FIELD);
            }
        });

        btnAddMethod.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Add Method button pressed");
            }
        });

        // Add glue to push buttons to the left
        toolBar.add(Box.createHorizontalGlue());

        // Add the "Add Field" button to the center
        toolBar.add(btnAddField);

        // Add a separator between the buttons
        toolBar.addSeparator();

        // Add the "Add Method" button to the center
        toolBar.add(btnAddMethod);

        // Add glue to push buttons to the right
        toolBar.add(Box.createHorizontalGlue());

        JOptionPane.showMessageDialog(MainFrame.getInstance(), toolBar, "Add Content", JOptionPane.PLAIN_MESSAGE);
    }
}
