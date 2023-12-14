package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;

public class MoveElementState implements State {
    private int initialX, initialY;
    private boolean isDragging = false;

    @Override
    public void execute(int x, int y, PackageView packageView) {

    }

    @Override
    public void stateMousePressed(int x, int y, PackageView packageView) {
        // Record initial mouse coordinates and set dragging to false
        initialX = x;
        initialY = y;
        isDragging = false;
    }

    @Override
    public void stateMouseDragged(int x, int y, PackageView packageView) {
        // Only move elements if there is actual dragging (i.e., a change in mouse position)
        if (!isDragging) {
            if (x != initialX || y != initialY) {
                isDragging = true;
            } else {
                return; // Exit if there is no movement
            }
        }

        // Calculate the movement delta
        int deltaX = x - initialX;
        int deltaY = y - initialY;

        // Move each selected element
        for (DiagramElement element : packageView.getSelectionModel().getSelected()) {
            if (element instanceof InterClass) {
                InterClass interClass = (InterClass) element;
                Pair<Integer, Integer> currentPosition = interClass.getPosition();
                interClass.setPosition(currentPosition.getFirst() + deltaX, currentPosition.getSecond() + deltaY);
            }
            // Similar logic for other DiagramElement types if necessary
        }

        // Update the view to reflect the new positions
        packageView.repaint();

        // Update initial coordinates for continuous dragging
        initialX = x;
        initialY = y;
    }

    @Override
    public void stateMouseReleased(int x, int y, PackageView packageView) {
        // Reset dragging state
        isDragging = false;
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

    // The other methods (execute, stateRightMouseDragged, stateRightMousePressed, stateRightMouseReleased) can remain unchanged
}
