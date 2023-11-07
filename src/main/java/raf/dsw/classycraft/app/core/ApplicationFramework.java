package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.ClassyRepository.ClassyRepository;

@Getter
@Setter
public abstract class ApplicationFramework
{
    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected Logger consoleLogger;
    protected Logger fileLogger;

    public abstract void start();

    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator)
    {
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = messageGenerator;
    }

    public void initialiseLogger(Logger consoleLogger, Logger fileLogger)
    {
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
    }
}
