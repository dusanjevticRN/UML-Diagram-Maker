package raf.dsw.classycraft.app.serializer;

import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageExporter {


    public static void exportPanelToImage(DiagramPanel panel, String filePath) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);


        panel.paint(image.getGraphics());

        try {
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
