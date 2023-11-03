package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.repository.ClassyRepository;

public class AppCore
{
    public static void main(String[] args)
    {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        ClassyRepository classyRepository = new ClassyRepository();
        appCore.initialize(classyRepository);
    }

}