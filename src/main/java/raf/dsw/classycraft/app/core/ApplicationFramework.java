package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.ClassyRepository;

public class ApplicationFramework
{
    protected ClassyRepository classyRepository;
    private static ApplicationFramework instance;

    //buduca polja za model celog projekta

    private ApplicationFramework() {}

    public void initialize(ClassyRepository classyRepository)
    {
        MainFrame.getInstance().setVisible(true);
        this.classyRepository = classyRepository;
    }

    public static ApplicationFramework getInstance()
    {
        if(instance==null)
            instance = new ApplicationFramework();

        return instance;
    }
}
