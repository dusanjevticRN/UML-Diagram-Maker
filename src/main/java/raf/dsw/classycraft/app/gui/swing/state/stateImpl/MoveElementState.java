package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.implementation.subElements.UmlSelectionModel;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;

import java.awt.*;
import java.util.ArrayList;

public class MoveElementState implements State {
    private int startX;
    private int startY;

    ArrayList<DiagramElement> elements = new ArrayList<>();

    @Override
    public void execute(int x, int y, PackageView packageView) {

    }

    @Override
    public void stateMousePressed(int x, int y, PackageView packageView) {
        this.startX = x;
        this.startY = y;
        //dodamo trenutno selektovne elem u listu
        elements.clear();
        for(DiagramElement element : packageView.getCurrentDiagramPanel().getSelectedElements()){
            if(elements.contains(element) || !(element instanceof InterClass)){
                continue;
            }
            else {
                elements.add(element);
            }
        }
    }

    @Override
    public void stateMouseDragged(int x, int y, PackageView packageView) {
        //move slican kao i move po dijagram panelu
        int deltaX = x - startX;
        int deltaY = y - startY;
        for (DiagramElement element : elements) {
            if (element instanceof InterClass) {
                Pair<Integer, Integer> currentPosition = ((InterClass) element).getPosition();
                ((InterClass) element).setPosition(new Pair<>(currentPosition.getFirst() + deltaX, currentPosition.getSecond() + deltaY));
            }
        }
        packageView.setPanelPainters(new ArrayList<>());
        packageView.panelRepaint();
        packageView.panelOutsideRefresh();
        startX = x;
        startY = y;
    }

    @Override
    public void stateMouseReleased(int x, int y, PackageView packageView) {
        packageView.clearSelectedAll();
        elements.clear();
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

    // The other methods remain unchanged
}

