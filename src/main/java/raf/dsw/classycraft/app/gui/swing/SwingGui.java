package raf.dsw.classycraft.app.gui.swing;

import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class SwingGui implements Gui
{
    @Override
    public void start()
    {
        MainFrame.getInstance().setVisible(true);
    }

}
