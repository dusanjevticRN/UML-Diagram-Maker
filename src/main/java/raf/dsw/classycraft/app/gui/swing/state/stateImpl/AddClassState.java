package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import javax.swing.*;

public class AddClassState implements State {

    @Override
    public void execute(int x, int y, DiagramPanel panel) {
        DiagramPanel diagramPanel = panel;
        System.out.println("TEST");
        String name = JOptionPane.showInputDialog("Enter class name:");

        if(name == null) return;

        else if(name.isEmpty())
        {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NAME_CANNOT_BE_EMPTY);
            return;
        }

        else if(!diagramPanel.getDiagram().checkName(name))
        {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NAME_ALREADY_EXISTS);
            return;
        }

        Klasa klas = new Klasa(diagramPanel.getDiagram(), name, Visibility.PUBLIC, x, y);
        klas.setName("Class:" + name);
        klas.setPosition(x, y);
        klas.setSize(new Pair(name.length()*8+120, 170));
        InterClassPainter painter = new InterClassPainter(klas);
        System.out.println("Dodajem klasu");
        panel.getPainters().add(painter);
        panel.repaint();
        panel.getDiagram().addDiagramElement(new Pair(x, y),klas);
        EventBus.getInstance().notifySubscriber(panel.getDiagram(), EventType.SET_PANEL);
        EventBus.getInstance().notifySubscriber(klas, EventType.ADD_CLASS_TO_TREE);

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
