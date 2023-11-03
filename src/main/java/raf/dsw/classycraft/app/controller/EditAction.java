package raf.dsw.classycraft.app.controller;

import raf.dsw.classycraft.app.gui.swing.ClassyTree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAction extends AbstractClassyAction {
    public EditAction() {
        super("/images/edit.png");

        this.putValue(SHORT_DESCRIPTION, "Edit");
        this.putValue(NAME, "Edit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = (ClassyTreeItem) MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Please select an item to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(selected.getClassyNode().getName().equals("Project Explorer")) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "You cannot edit the Project Explorer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(!(selected.getClassyNode() instanceof Project)){
            JDialog editDialog = new JDialog();
            editDialog.setSize(500, 750);
            editDialog.setAlwaysOnTop(true);
            editDialog.setIconImage(new ImageIcon(getClass().getResource("/images/edit.png")).getImage());
            editDialog.setResizable(false);
            editDialog.setTitle("Edit: " + selected.getClassyNode().getName());
            editDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            editDialog.setLayout(new BorderLayout());


            JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


            JLabel nameLabel = new JLabel("Name:");
            JTextField nameTextField = new JTextField(20);
            nameTextField.setText(selected.getClassyNode().getName());

            mainPanel.add(nameLabel);
            mainPanel.add(nameTextField);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Add padding

            JButton applyButton = new JButton("Apply");
            JButton cancelButton = new JButton("Cancel");

            buttonPanel.add(applyButton);
            buttonPanel.add(cancelButton);

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editDialog.dispose();
                }
            });

            applyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameTextField.getText();
                    selected.setName(name);

                    editDialog.dispose();
                }
            });


            editDialog.add(mainPanel, BorderLayout.CENTER);
            editDialog.add(buttonPanel, BorderLayout.SOUTH);

            editDialog.setModal(true);
            editDialog.setLocationRelativeTo(null);
            editDialog.pack();
            editDialog.setVisible(true);

            return;
        }
        JDialog editDialog = new JDialog();
        editDialog.setSize(500, 750);
        editDialog.setAlwaysOnTop(true);
        editDialog.setIconImage(new ImageIcon(getClass().getResource("/images/edit.png")).getImage());
        editDialog.setResizable(false);
        editDialog.setTitle("Edit: " + selected.getClassyNode().getName());
        editDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        editDialog.setLayout(new BorderLayout());


        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField(20);
        nameTextField.setText(selected.getClassyNode().getName());
        JLabel authorLabel = new JLabel("Author:");
        JTextField authorTextField = new JTextField(20);
        String author;
        ClassyNodeComposite cnc = (ClassyNodeComposite) selected.getClassyNode();
        Project project = (Project) cnc;
        author = project.getAuthor();
        authorTextField.setText(author);

        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);
        mainPanel.add(authorLabel);
        mainPanel.add(authorTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Add padding

        JButton applyButton = new JButton("Apply");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(applyButton);
        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog.dispose();
            }
        });

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                selected.setName(name);
                String author = authorTextField.getText();
                project.renameAuthor(author);
                editDialog.dispose();
            }
        });


        editDialog.add(mainPanel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);

        editDialog.setModal(true);
        editDialog.setLocationRelativeTo(null);
        editDialog.pack();
        editDialog.setVisible(true);

        return;
    }
}

