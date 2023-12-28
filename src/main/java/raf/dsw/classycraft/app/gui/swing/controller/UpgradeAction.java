package raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpgradeAction extends AbstractClassyAction{
    public UpgradeAction() {
        this.putValue(SHORT_DESCRIPTION, "Upgrade to premium");
        this.putValue(NAME, "Upgrade to premium");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Enter Credit Card Details");
        dialog.setLayout(new GridLayout(5, 2));
        dialog.add(new JLabel("Card Number:"));
        dialog.add(new JTextField(20));
        dialog.add(new JLabel("Expiry Date (MM/YY):"));
        dialog.add(new JTextField(5));
        dialog.add(new JLabel("CVV:"));
        dialog.add(new JTextField(3));
        dialog.add(new JLabel("Cardholder Name:"));
        dialog.add(new JTextField(20));
        JButton submitButton = new JButton("Submit");
        dialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dialog, "Thank you for upgrading to premium!");
                dialog.dispose();
            }
        });

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
