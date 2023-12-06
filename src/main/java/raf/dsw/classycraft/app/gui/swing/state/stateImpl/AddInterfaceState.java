package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Interfejs;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import javax.swing.*;

public class AddInterfaceState implements State {
    @Override
    public void execute(int x, int y, DiagramPanel panel) {
        DiagramPanel diagramPanel = panel;
        System.out.println("TEST");
        String name = JOptionPane.showInputDialog("Enter interface name:");
        if(name == null) return;
        else if(name.isEmpty()){
            //ERROR
            System.out.println("Name cannot be empty");
        }
        else if(!diagramPanel.getDiagram().checkName(name)){
            //ERROR
            System.out.println("Name already exists");
        }

        Interfejs inter = new Interfejs(diagramPanel.getDiagram(), name, Visibility.PUBLIC, x, y);
        inter.setName("Interface:" + name);
        inter.setPosition(x, y);
        inter.setSize(new Pair(name.length()*8+120, 170));
        InterClassPainter painter = new InterClassPainter(inter);
        System.out.println("Dodajem klasu");
        panel.getPainters().add(painter);
        panel.repaint();
        panel.getDiagram().addDiagramElement(new Pair(x, y),inter);
    }

    @Override
    public void stateMousePressed(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateMouseDragged(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateMouseReleased(int x, int y, DiagramPanel panel) {

    }
}
