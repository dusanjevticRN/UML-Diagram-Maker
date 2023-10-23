package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.AboutUsAction;
import raf.dsw.classycraft.app.controller.ExitAction;
import javax.swing.*;

public class MyToolBar extends JToolBar
{
    public MyToolBar()
    {
        super(HORIZONTAL);
        this.setFloatable(false);

        ExitAction exitAction = new ExitAction();
        AboutUsAction aboutUsAction = new AboutUsAction();

        this.add(exitAction); //exitAction ce se executovati automatski tako što će se pokrenuti actionPerformed metoda, klikom na odgovarajući GUI element (ikonica).
        this.add(aboutUsAction); // isto
    }
}
