package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Logger;
import raf.dsw.classycraft.app.core.MessageGenerator;
import raf.dsw.classycraft.app.logger.ConsoleLogger;
import raf.dsw.classycraft.app.logger.LoggerFactory;
import raf.dsw.classycraft.app.messageGenerator.MessageGeneratorClass;

//Pitanje za vezbe, da li trebam da posaljem messageGenerator MainFrame-u posto je i on subscriber (pitaj detaljno)

public class AppCore
{
    public static void main(String[] args)
    {
        ApplicationFramework appCore = ApplicationFramework.getInstance();

        MessageGenerator messageGenerator = new MessageGeneratorClass();

        LoggerFactory loggerFactory = new LoggerFactory();
        Logger consoleLogger = loggerFactory.getLogger("ConsoleLogger", messageGenerator);
        Logger fileLogger = loggerFactory.getLogger("FileLogger", messageGenerator);

        appCore.initialize();
    }
}