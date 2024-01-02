package raf.dsw.classycraft.app.classyRepository.commands;

import raf.dsw.classycraft.app.AppCore;

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

    public void doCommand()
    {
        if(currentCommand < commands.size())
        {
            commands.get(currentCommand++).doCommand();
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

    public void permanentUndoCommand()
    {
        commands.get(--currentCommand).undoCommand();
        commands.remove(currentCommand);
    }

    public void revalidateActions()
    {
        if(currentCommand == 0) enableUndo(false);
        else enableUndo(true);

        if(currentCommand < commands.size()) enableRedo(true);
        else enableRedo(false);
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
