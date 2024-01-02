package raf.dsw.classycraft.app.classyRepository.commands;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeLeaf;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class AddClassCommand implements Command
{
    private ClassyNode parent;
    private ClassyNode child;

    public AddClassCommand(ClassyNode parent, ClassyNode child)
    {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand()
    {
        ((ClassyNodeComposite) parent).addChild(child);
    }

    @Override
    public void undoCommand()
    {
        //((ClassyNodeComposite) parent).removeChild(child);
        MainFrame.getInstance().getPackageView().getDiagram().getDiagramElements().remove(child);
        MainFrame.getInstance().getPackageView().panelOutsideRefresh();
        MainFrame.getInstance().repaint();
    }
}
