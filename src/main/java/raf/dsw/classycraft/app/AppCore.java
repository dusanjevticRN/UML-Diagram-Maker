package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.core.Logger;
import raf.dsw.classycraft.app.core.MessageGenerator;
import raf.dsw.classycraft.app.gui.swing.SwingGui;
import raf.dsw.classycraft.app.logger.ConsoleLogger;
import raf.dsw.classycraft.app.logger.LoggerFactory;
import raf.dsw.classycraft.app.messageGenerator.MessageGeneratorClass;
import raf.dsw.classycraft.app.repository.ClassyRepository;

//Pitanje za vezbe, da li trebam da posaljem messageGenerator MainFrame-u posto je i on subscriber (pitaj detaljno)

public class AppCore
{
    public static void main(String[] args)
    {
        ClassyRepository classyRepository = new ClassyRepository();
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        MessageGenerator messageGenerator = new MessageGeneratorClass();

        LoggerFactory loggerFactory = new LoggerFactory();
        Logger consoleLogger = loggerFactory.getLogger("ConsoleLogger", messageGenerator);
        Logger fileLogger = loggerFactory.getLogger("FileLogger", messageGenerator);



        appCore.initialize(gui, classyRepository);
        appCore.run();
    }
}