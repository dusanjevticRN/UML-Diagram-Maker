package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.connectionSubElements.Generalizacija;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

public class AddGeneralizationState implements State {
    private ElementPainter source;
    private ElementPainter destination;
    private DiagramElement sourceElement;
    private DiagramElement destinationElement;
    private ClassyNode sourceNode;
    @Override
    public void execute(int x, int y, DiagramPanel panel) {

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
