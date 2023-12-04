package raf.dsw.classycraft.app.gui.swing.controller.diagramActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddRelationshipAction extends AbstractClassyAction
{
    public AddRelationshipAction()
    {
        this.putValue(SMALL_ICON, loadIcon("/images/Relationship.png"));
        this.putValue(SHORT_DESCRIPTION, "Add Relationship");
        this.putValue(NAME, "Add Relationship");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(new JButton("Add Aggregation"));
        toolBar.add(new JButton("Add Composition"));
        toolBar.add(new JButton("Add Dependency"));
        toolBar.add(new JButton("Add Generalization"));

        JOptionPane.showMessageDialog(MainFrame.getInstance(), toolBar, "Add Relationship", JOptionPane.PLAIN_MESSAGE);
    }
}
