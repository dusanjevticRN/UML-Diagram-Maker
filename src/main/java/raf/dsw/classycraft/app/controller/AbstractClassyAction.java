
package raf.dsw.classycraft.app.controller;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.Image;

public abstract class AbstractClassyAction extends AbstractAction
{
    public AbstractClassyAction(String iconPath)
    {
        loadIcon(iconPath);
    }

    protected void loadIcon(String iconPath)
    {
        if(iconPath != null)
        {
            //.class je class literal koji daje referencu prilozene klase sa kojom mozemo uzimati metaPodatke i ostale informacije
            ImageIcon originalIcon = new ImageIcon(AboutUsAction.class.getResource(iconPath)); //getResource traži resurs sa priloženim imenom i vraća URL objekat
            Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            this.putValue(SMALL_ICON, scaledIcon); // putValue(key, objekat) key (string) identifikuje storovani objekat
        }

        else
            System.err.println("File " + " images/aboutUs.png" + " not found");
    }
}
