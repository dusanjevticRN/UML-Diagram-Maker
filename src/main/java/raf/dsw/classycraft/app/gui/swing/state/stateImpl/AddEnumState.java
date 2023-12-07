package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.UmlEnum;
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

        UmlEnum enm = new UmlEnum(diagramPanel.getDiagram(), name, Visibility.PUBLIC, x, y);
        enm.setName("Enum:" + name);
        enm.setPosition(x, y);
        enm.setSize(new Pair(name.length()*8+120, 170));
        InterClassPainter painter = new InterClassPainter(enm);
        System.out.println("Dodajem klasu");
        panel.getPainters().add(painter);
        panel.repaint();
        panel.getDiagram().addDiagramElement(new Pair(x, y),enm);
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
