package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Interfejs;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import javax.swing.*;
import java.awt.*;

public class AddInterfaceState implements State {
    @Override
    public void execute(int x, int y, PackageView packageView) {
        packageView.setPanelCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void stateMousePressed(int x, int y, PackageView packageView) {
        DiagramPanel diagramPanel = packageView.getCurrentDiagramPanel();
        System.out.println("TEST");
        String name = JOptionPane.showInputDialog("Enter interface name:");
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

        Interfejs inter = new Interfejs(diagramPanel.getDiagram(), name, Visibility.PUBLIC, x, y);
        inter.setName("Interface:" + name);
        inter.setPosition(x, y);
        inter.setSize(new Pair(name.length()*8+120, 170));
        InterClassPainter painter = new InterClassPainter(inter);
        System.out.println("Dodajem klasu");
        packageView.addPainter(painter);
        packageView.panelRepaint();
        packageView.addDiagramElement(new Pair(x, y),inter);
        EventBus.getInstance().notifySubscriber(packageView.getDiagram(), EventType.SET_PANEL);
        EventBus.getInstance().notifySubscriber(inter, EventType.ADD_INTERFACE_TO_TREE_S);

    }

    @Override
    public void stateMouseDragged(int x, int y, PackageView packageView) {

    }

    @Override
    public void stateMouseReleased(int x, int y, PackageView packageView) {

    }

    @Override
    public void stateRightMouseDragged(int x, int y, PackageView packageView) {

    }

    @Override
    public void stateRightMousePressed(int x, int y, PackageView packageView) {

    }

    @Override
    public void stateRightMouseReleased(int x, int y, PackageView packageView) {

    }
}
