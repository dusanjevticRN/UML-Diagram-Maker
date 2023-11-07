package raf.dsw.classycraft.app;

import lombok.Getter;
import raf.dsw.classycraft.app.core.*;
import raf.dsw.classycraft.app.gui.swing.SwingGui;
import raf.dsw.classycraft.app.logger.LoggerFactory;
import raf.dsw.classycraft.app.messageGenerator.MessageGeneratorClass;
import raf.dsw.classycraft.app.ClassyRepository.ClassyRepository;

@Getter
public class AppCore extends ApplicationFramework
{
    private static AppCore instance;
    private AppCore() {}
    @Override
    public void start()
    {
        this.gui.start();
    }
    public static AppCore getInstance()
    {
        if(instance == null)
            instance = new AppCore();

        return instance;
    }
    public static void main(String[] args)
    {
        ApplicationFramework appCore = AppCore.getInstance();
        Gui gui = new SwingGui();
        MapRepository mapRepository = new ClassyRepository();
        MessageGenerator messageGenerator = new MessageGeneratorClass();

        LoggerFactory loggerFactory = new LoggerFactory();
        Logger consoleLogger = loggerFactory.getLogger("ConsoleLogger", messageGenerator);
        Logger fileLogger = loggerFactory.getLogger("FileLogger", messageGenerator);

        appCore.initialise(gui, mapRepository, messageGenerator);
        appCore.initialiseLogger(consoleLogger, fileLogger);

        appCore.start();
    }
}