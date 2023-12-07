package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.UmlEnum;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import javax.swing.*;

public class AddEnumState implements State {
    @Override
    public void execute(int x, int y, DiagramPanel panel) {
        DiagramPanel diagramPanel = panel;
        System.out.println("TEST");
        String name = JOptionPane.showInputDialog("Enter enum name:");
        if(name == null) return;
        else if(name.isEmpty()){
            //ERROR
            System.out.println("Name cannot be empty");
            return;
        }
        else if(!diagramPanel.getDiagram().checkName(name)){
            //ERROR
            System.out.println("Name already exists");
            return;
        }

        UmlEnum enm = new UmlEnum(diagramPanel.getDiagram(), name, Visibility.PUBLIC, x, y);
        enm.setName("Enum:" + name);
        enm.setPosition(x, y);
        enm.setSize(new Pair(name.length()*8+120, 170));
        InterClassPainter painter = new InterClassPainter(enm);
        System.out.println("Dodajem klasu");
        panel.getPainters().add(painter);
        panel.repaint();
        panel.getDiagram().addDiagramElement(new Pair(x, y),enm);
        EventBus.getInstance().notifySubscriber(panel.getDiagram(), EventType.SET_PANEL);
        EventBus.getInstance().notifySubscriber(enm, EventType.ADD_ENUM_TO_TREE);

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
