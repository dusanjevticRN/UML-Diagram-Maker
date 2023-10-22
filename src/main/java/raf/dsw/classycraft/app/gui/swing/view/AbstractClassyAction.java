
package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.AboutUsAction;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import java.awt.Image;

public abstract class AbstractClassyAction extends AbstractAction {
    public AbstractClassyAction(String name, String iconPath) {
        ImageIcon originalIcon = new ImageIcon(AboutUsAction.class.getResource(iconPath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        putValue(NAME, name);
        putValue(SMALL_ICON, scaledIcon);
    }

    protected void loadIcon(String path) {
        putValue(SMALL_ICON, new ImageIcon(path));
    }
}
