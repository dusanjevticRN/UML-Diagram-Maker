package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.ClassyRepository;

@Getter
@Setter
public class ApplicationFramework
{
    protected Gui gui;
    protected ClassyRepository classyRepository;
    private static ApplicationFramework instance;

    //buduca polja za model celog projekta

    private ApplicationFramework() {}

    public void initialize(Gui gui,ClassyRepository classyRepository)
    {
        this.gui = gui;
        this.classyRepository = classyRepository;
    }

    public static ApplicationFramework getInstance()
    {
        if(instance==null)
            instance = new ApplicationFramework();

        return instance;
    }

    public void run() {
        this.gui.start();
    }
}
