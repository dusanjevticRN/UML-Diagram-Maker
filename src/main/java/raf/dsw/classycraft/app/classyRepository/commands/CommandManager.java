package raf.dsw.classycraft.app.classyRepository.commands;

import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import java.util.ArrayList;
import java.util.List;

public class CommandManager
{
    private int currentCommand;
    private List<Command> commands;

    public CommandManager()
    {
        currentCommand = 0;
        commands = new ArrayList<>();
    }

    public void addCommand(Command command)
    {
        while(currentCommand < commands.size())
            commands.remove(currentCommand);

        commands.add(command);
        this.doCommand();
    }

    public void enableBoth(){
        if(currentCommand < commands.size())
        {
            commands.get(currentCommand++);
            enableUndo(true);
        }

        if(currentCommand == commands.size())
            enableRedo(false);
    }
    public void doCommand() {
        Diagram diagram = MainFrame.getInstance().getPackageView().getDiagram();
        diagram.getProject(diagram).setChanged(true);
        System.out.println("Changed: " + diagram.getProject(diagram).isChanged());
        this.enableBoth();
    }

    public void redoCommand()
    {
        if(currentCommand < commands.size())
        {
            commands.get(currentCommand++).redoCommand();
            enableUndo(true);
        }

        if(currentCommand == commands.size())
            enableRedo(false);
    }

    public void undoCommand()
    {
        if(currentCommand > 0)
        {
            commands.get(--currentCommand).undoCommand();
            enableRedo(true);
        }

        if(currentCommand == 0)
            enableUndo(false);
    }

    public void enableUndo(boolean bool)
    {
        AppCore.getInstance().getGui().enableUndoAction(bool);
    }

    public void enableRedo(boolean bool)
    {
        AppCore.getInstance().getGui().enableRedoAction(bool);
    }
}
