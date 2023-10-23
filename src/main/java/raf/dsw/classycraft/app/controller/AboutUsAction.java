package raf.dsw.classycraft.app.controller;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.net.URL;


public class AboutUsAction extends AbstractClassyAction
{

    public AboutUsAction()
    {
        super("/images/aboutUs.png");

        this.putValue(SHORT_DESCRIPTION, "About Us");
        this.putValue(NAME, "About Us");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JFrame aboutUsFrame = new JFrame("About Us");
        aboutUsFrame.setSize(500, 500);
        aboutUsFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        URL url1 = getClass().getResource("/images/vujavuja.jpg");
        URL url2 = getClass().getResource("/images/OpenHajniken.jpeg");

        JLabel name1 = new JLabel("Nemanja Vujic");

        if(url1 != null)
        {
            ImageIcon icon1 = new ImageIcon(url1);
            Image image1 = icon1.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel labelImage1 = new JLabel(new ImageIcon(image1));
            panel.add(name1);
            panel.add(labelImage1);
        }

        else
            System.err.println("File " + "/images/vujavuja.jpg" + " not found");


        if(url2 != null)
        {
            JLabel name2 = new JLabel("Dusan Jevtic");
            ImageIcon icon2 = new ImageIcon(url2);
            Image image2 = icon2.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel labelImage2 = new JLabel(new ImageIcon(image2));
            panel.add(name2);
            panel.add(labelImage2);
        }

        else
            System.err.println("File " + "/images/OpenHajniken.jpeg" + " not found");


        aboutUsFrame.add(panel);
        aboutUsFrame.setVisible(true);
    }
}
