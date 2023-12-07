package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Atribut;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.ClassContent;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Interfejs;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddFieldState implements State
{
    private int startX;
    private int startY;
    private Klasa klasa;
    private Interfejs interfejs;
    private List<String> classAtributes = new ArrayList<>();
    private boolean hit = false;

    @Override
    public void execute(int x, int y, DiagramPanel panel) {
        panel.setCursor(Cursor.getDefaultCursor());
    }

    private boolean isHit(InterClass interClass, int x, int y) {
        int startX = interClass.getPosition().getFirst(); //Gornja leva x koordinata
        int startY = interClass.getPosition().getSecond(); //Gornja leva y koordinata
        int endX = startX + interClass.getSize().getFirst(); //Donja desna x koordinata
        int endY = startY + interClass.getSize().getSecond(); //Donja desna y koordinata

        if(x >= startX && x <= endX && y >= startY && y <= endY){
            return true;
        }
        return false;

    }

    @Override
    public void stateMousePressed(int x, int y, DiagramPanel panel)
    {
        System.out.println("Add Field state");
        this.startX = x;
        this.startY = y;


        List<InterClass> selectableElements = new ArrayList<>();

        for (DiagramElement elem : panel.getDiagram().getDiagramElements())
        {
            if (elem instanceof InterClass)
            {
                InterClass interClass = (InterClass) elem;
                selectableElements.add(interClass);
            }
        }

        for(InterClass ic : selectableElements)
        {
            if(isHit(ic, x, y))
            {
                hit = true;
                System.out.println("Hit: " + ic.getName());

                if(ic.getType() == "Klasa")
                {
                    this.klasa = (Klasa) ic;
                    this.interfejs = null;
                }

                else
                {
                    this.interfejs = (Interfejs) ic;
                    this.klasa = null;
                }

                break;
            }
        }

        if(!hit) {
            System.out.println(hit);
        }

        if(hit) {
            System.out.println(hit);

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Add Field Dialog");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(600, 400);
                frame.setLocationRelativeTo(null);

                JPanel mainPanel = new JPanel(new BorderLayout());

                // Left Panel
                JPanel leftPanel = new JPanel(new GridBagLayout());
                leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel fieldNameLabel = new JLabel("Field name:");
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(150, 25)); // Set preferred size

                JLabel accessLevelLabel = new JLabel("Access Level:");
                // ChoiceBox for access level
                String[] accessLevels = {"Private", "Protected", "Public", "Package private"};
                JComboBox<String> accessLevelChoiceBox = new JComboBox<>(accessLevels);
                accessLevelChoiceBox.setSelectedIndex(0); // Set the default selection

                JLabel dataTypeLabel = new JLabel("Data Type:");
                // ChoiceBox for data types
                String[] dataTypes = {"int", "double", "String", "boolean", "char", "float", "long", "short", "byte"};
                JComboBox<String> dataTypeChoiceBox = new JComboBox<>(dataTypes);
                dataTypeChoiceBox.setSelectedIndex(0); // Set the default selection

                JLabel staticLabel = new JLabel("Static:");
                // ChoiceBox for static/non-static
                String[] staticOptions = {"Non-Static", "Static"};
                JComboBox<String> staticChoiceBox = new JComboBox<>(staticOptions);
                staticChoiceBox.setSelectedIndex(0); // Set the default selection

                JButton addButton = new JButton("Add");
                JButton cancelButton = new JButton("Cancel");

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;

                // Adding components to the left panel with spacing
                leftPanel.add(fieldNameLabel, gbc);
                gbc.gridy++;
                leftPanel.add(textField, gbc);

                // Add spacing
                gbc.gridy++;
                gbc.insets = new Insets(10, 0, 0, 0); // Top spacing
                leftPanel.add(accessLevelLabel, gbc);
                gbc.gridy++;
                leftPanel.add(accessLevelChoiceBox, gbc);

                // Add more spacing
                gbc.gridy++;
                gbc.insets = new Insets(10, 0, 0, 0); // Top spacing
                leftPanel.add(dataTypeLabel, gbc);
                gbc.gridy++;
                leftPanel.add(dataTypeChoiceBox, gbc);

                // Add even more spacing
                gbc.gridy++;
                gbc.insets = new Insets(10, 0, 0, 0); // Top spacing
                leftPanel.add(staticLabel, gbc);
                gbc.gridy++;
                leftPanel.add(staticChoiceBox, gbc);

                // Add even more spacing
                gbc.gridy++;
                gbc.insets = new Insets(20, 0, 0, 0); // Top spacing
                leftPanel.add(addButton, gbc);
                gbc.gridx = 0; // Reset column
                gbc.gridy++;
                leftPanel.add(cancelButton, gbc);

                // Right Panel
                JPanel rightPanel = new JPanel(new BorderLayout());

                // JList for displaying class fields
                JList<String> classFieldsList = new JList<>();
                classFieldsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                classFieldsList.setLayoutOrientation(JList.VERTICAL);
                classFieldsList.setVisibleRowCount(-1);

                DefaultListModel<String> listModel = new DefaultListModel<>(); //Cuvamo podatke u listi
                classFieldsList.setModel(listModel); //Povezujemo listu sa modelom

                rightPanel.add(new JScrollPane(classFieldsList), BorderLayout.CENTER);

                // Edit and Remove buttons
                JPanel buttonsPanel = new JPanel();
                JButton updateButton = new JButton("Update");
                JButton removeButton = new JButton("Remove");
                buttonsPanel.add(updateButton);
                buttonsPanel.add(removeButton);
                rightPanel.add(buttonsPanel, BorderLayout.SOUTH);

                // Adding the left and right panels to the main panel
                mainPanel.add(leftPanel, BorderLayout.WEST);
                mainPanel.add(rightPanel, BorderLayout.CENTER);

                // Adding the main panel to the frame
                frame.add(mainPanel);

                // Display the frame
                frame.setVisible(true);

                //Ako lista classContents klase nije prazna, dodajemo sve atribute u listModel
                if(!this.klasa.getClassContents().isEmpty())
                {
                    for(ClassContent cc : this.klasa.getClassContents())
                    {
                        if(cc instanceof Atribut)
                        {
                            Atribut atribut = (Atribut) cc;
                            StringBuilder sb = new StringBuilder();

                            if(atribut.getVisibility() == Visibility.PRIVATE)
                                sb.append("- ");

                            else if(atribut.getVisibility() == Visibility.PROTECTED)
                                sb.append("# ");

                            else if(atribut.getVisibility() == Visibility.PUBLIC)
                                sb.append("+ ");

                            else if(atribut.getVisibility() == Visibility.PACKAGE_PRIVATE)
                                sb.append("~ ");

                            sb.append(atribut.getName() + ": ");

                            if(atribut.isStatic())
                                sb.append("static ");

                            sb.append(atribut.getDataType());

                            listModel.addElement(sb.toString());
                        }
                    }
                }

                cancelButton.addActionListener(e -> {
                    System.out.println("Cancel button clicked");
                    frame.dispose();
                });

                addButton.addActionListener(e -> {
                    StringBuilder sb = new StringBuilder(); //string builder za listModel

                    //parametri za konstruktor atributa
                    String nameOfField = textField.getText();
                    Visibility visibility = Visibility.PACKAGE_PRIVATE; //defaultna vrednost
                    Boolean isStatic = false;
                    String dataType = "int"; //defaultna vrednost

                    if(accessLevelChoiceBox.getSelectedItem().equals("Private"))
                    {
                        visibility = Visibility.PRIVATE;
                        sb.append("- ");
                    }

                    else if(accessLevelChoiceBox.getSelectedItem().equals("Protected"))
                    {
                        sb.append("# ");
                        visibility = Visibility.PROTECTED;
                    }

                    else if(accessLevelChoiceBox.getSelectedItem().equals("Public"))
                    {
                        sb.append("+ ");
                        visibility = Visibility.PUBLIC;
                    }

                    else if(accessLevelChoiceBox.getSelectedItem().equals("Package private"))
                    {
                        sb.append("~ ");
                        visibility = Visibility.PACKAGE_PRIVATE;
                    }

                    sb.append(textField.getText() + ": ");

                    if(staticChoiceBox.getSelectedItem().equals("Static"))
                    {
                        isStatic = true;
                        sb.append("static ");
                    }

                    if(dataTypeChoiceBox.getSelectedItem().equals("int"))
                    {
                        dataType = "int";
                        sb.append("int");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("double"))
                    {
                        dataType = "double";
                        sb.append("double");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("String"))
                    {
                        dataType = "String";
                        sb.append("String");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("boolean"))
                    {
                        dataType = "boolean";
                        sb.append("boolean");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("char"))
                    {
                        dataType = "char";
                        sb.append("char");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("float"))
                    {
                        dataType = "float";
                        sb.append("float");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("long"))
                    {
                        dataType = "long";
                        sb.append("long");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("short"))
                    {
                        dataType = "short";
                        sb.append("short");
                    }

                    else if(dataTypeChoiceBox.getSelectedItem().equals("byte"))
                    {
                        dataType = "byte";
                        sb.append("byte");
                    }

                    ClassContent atribut = new Atribut(nameOfField, visibility, isStatic, dataType);

                    if(this.klasa.getClassContents().isEmpty())
                    {
                        this.klasa.addClassContent(atribut); //selektovanoj klasi dodajemo atribut
                        listModel.addElement(sb.toString()); //dodajemo atribut u listu
                        System.out.println("Dodat atribut u listu classContents klase (if)");
                    }

                    else if(this.klasa.getClassContents().contains(atribut))
                        AppCore.getInstance().getMessageGenerator().generate(EventType.FIELD_ALREADY_EXISTS);


                    else
                    {
                        this.klasa.addClassContent(atribut); //selektovanoj klasi dodajemo atribut
                        listModel.addElement(sb.toString()); //dodajemo atribut u listu
                        System.out.println("Dodat atribut u listu classContents klase (else)");
                    }

                    System.out.println("Dodat novi atribut" + sb.toString());
                });

                classFieldsList.getSelectionModel().addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {
                        int selectedIndex = classFieldsList.getSelectedIndex();

                        if (selectedIndex != -1)
                        {
                            String selectedData = listModel.getElementAt(selectedIndex);
                            System.out.println(selectedData);

                            String[] parts = selectedData.split(": ");

                            if (parts.length == 2)
                            {
                                String[] atributeDetails1 = parts[0].split(" ");
                                String visibility = atributeDetails1[0];
                                String nameOfAtribute = atributeDetails1[1];

                                String[] atributeDetails2 = parts[1].split(" ");
                                String dataType = atributeDetails2[0];
                                boolean isStatic = false;

                                //atributeDetails2 ce imati duzinu = 2 kada je atribut static
                                if(atributeDetails2.length == 2)
                                {
                                    isStatic = true;
                                    dataType = atributeDetails2[1]; //ako je static, onda je drugi element u nizu dataType
                                }

                                //Podesavamo vrednost visibility choice box-a
                                if(visibility.equals("-"))
                                    accessLevelChoiceBox.setSelectedItem("Private");

                                else if(visibility.equals("#"))
                                    accessLevelChoiceBox.setSelectedItem("Protected");

                                else if(visibility.equals("+"))
                                    accessLevelChoiceBox.setSelectedItem("Public");

                                else if(visibility.equals("~"))
                                    accessLevelChoiceBox.setSelectedItem("Package private");

                                //Podesavamo vrednost dataType choice box-a
                                if(dataType.equals("int"))
                                    dataTypeChoiceBox.setSelectedItem("int");

                                else if(dataType.equals("double"))
                                    dataTypeChoiceBox.setSelectedItem("double");

                                else if(dataType.equals("String"))
                                    dataTypeChoiceBox.setSelectedItem("String");

                                else if(dataType.equals("boolean"))
                                    dataTypeChoiceBox.setSelectedItem("boolean");

                                else if(dataType.equals("char"))
                                    dataTypeChoiceBox.setSelectedItem("char");

                                else if(dataType.equals("float"))
                                    dataTypeChoiceBox.setSelectedItem("float");

                                else if(dataType.equals("long"))
                                    dataTypeChoiceBox.setSelectedItem("long");

                                else if(dataType.equals("short"))
                                    dataTypeChoiceBox.setSelectedItem("short");

                                else if(dataType.equals("byte"))
                                    dataTypeChoiceBox.setSelectedItem("byte");

                                //Podesavamo vrednost textField-a
                                textField.setText(nameOfAtribute);

                                //Podesavamo vrednost static choice box-a
                                if(isStatic)
                                    staticChoiceBox.setSelectedItem("Static");

                                else
                                    staticChoiceBox.setSelectedItem("Non-Static");
                            }
                        }
                    }
                });

                updateButton.addActionListener(e -> {
                    int selectedIndex = classFieldsList.getSelectedIndex();

                    if (selectedIndex != -1) {
                        // Uzmi selektovani element iz liste
                        String selectedData = listModel.getElementAt(selectedIndex);

                        // Uzmi nove vrednosti iz komponenti
                        String newName = textField.getText();
                        String newVisibility = (String) accessLevelChoiceBox.getSelectedItem();
                        Visibility newVisibility1 = Visibility.PACKAGE_PRIVATE; //visibility koji cu proslediti konstruktoru atributa
                        String newDataType = (String) dataTypeChoiceBox.getSelectedItem();
                        boolean newIsStatic = staticChoiceBox.getSelectedItem().equals("Static");

                        if(newVisibility.equals("Private"))
                        {
                            newVisibility = "-";
                            newVisibility1 = Visibility.PRIVATE;
                        }

                        else if(newVisibility.equals("Protected"))
                        {
                            newVisibility = "#";
                            newVisibility1 = Visibility.PROTECTED;
                        }

                        else if(newVisibility.equals("Public"))
                        {
                            newVisibility = "+";
                            newVisibility1 = Visibility.PUBLIC;
                        }

                        else if(newVisibility.equals("Package private"))
                        {
                            newVisibility = "~";
                            newVisibility1 = Visibility.PACKAGE_PRIVATE;
                        }

                        //Pravimo novi string koji ce biti ubacen u listu
                        StringBuilder updatedString = new StringBuilder();
                        updatedString.append(newVisibility).append(" ");
                        updatedString.append(newName).append(": ");

                        if (newIsStatic)
                            updatedString.append("static ");

                        updatedString.append(newDataType);

                        //Updejtujemo listu
                        listModel.setElementAt(updatedString.toString(), selectedIndex);

                        //Updejtujemo listu classContents klase
                        this.klasa.updateClassContent(this.klasa.getClassContents().get(selectedIndex), new Atribut(newName, newVisibility1, newIsStatic, newDataType));
                    }
                    System.out.println("Update button clicked");
                });

                removeButton.addActionListener(e -> {

                    int selectedIndex = classFieldsList.getSelectedIndex();

                    if (selectedIndex != -1)
                    {
                        listModel.remove(selectedIndex);
                        this.klasa.deleteClassContent(this.klasa.getClassContents().get(selectedIndex));
                    }
                });
            });



            hit = false;
        }
    }

    @Override
    public void stateMouseDragged(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateMouseReleased(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateRightMouseDragged(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateRightMousePressed(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateRightMouseReleased(int x, int y, DiagramPanel panel) {

    }
}
