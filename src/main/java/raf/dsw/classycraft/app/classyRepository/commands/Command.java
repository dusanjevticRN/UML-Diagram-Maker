package raf.dsw.classycraft.app.classyRepository.commands;

public interface Command
{
    public void doCommand();
    public void undoCommand();
}
