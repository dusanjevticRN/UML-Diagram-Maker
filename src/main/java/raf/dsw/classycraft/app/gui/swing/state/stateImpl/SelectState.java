package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.UmlSelectionModel;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import java.util.ArrayList;
import java.util.List;

public class SelectState implements State {
    private int startX;
    private int startY;
    private boolean hit = false;

    @Override
    public void execute(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateMousePressed(int x, int y, DiagramPanel panel) {
        System.out.println("Select state");
        this.startX = x;
        this.startY = y;
        UmlSelectionModel selectionModel = panel.getSelectionModel();
        List<InterClass> selectableElements = new ArrayList<>();
        for(DiagramElement elem : panel.getDiagram().getDiagramElements()){
            if(elem instanceof InterClass){
                InterClass interClass = (InterClass) elem;
                selectableElements.add(interClass);
            }
        }
        for(InterClass ic : selectableElements){
            if(isHit(ic, x, y)){
                selectionModel.select(ic);
                hit = true;
                System.out.println("Hit: " + ic.getName());
                break;
            }
        }
        if(!hit){
            System.out.println(hit);
            panel.setSelectionModel(new UmlSelectionModel());
            panel.setSelectedPainters(new ArrayList<>());
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        if(hit) {
            System.out.println(hit);
            System.out.println("Hit: " + selectionModel.getSelected().get(0).getName());
            panel.setSelectionModel(selectionModel);
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        hit = false;
    }

    @Override
    public void stateMouseDragged(int x, int y, DiagramPanel panel) {
        if(startX == 0 && startY == 0){
            startX = x;
            startY = y;
            EventBus.getInstance().notifySubscriber(this, EventType.START_DRAG);
        }
        String start = startX + "/" + startY;
        String end = x + "/" + y;
        System.out.println(start + "-" + end);
        String startEnd = start + "-" + end;
        EventBus.getInstance().notifySubscriber(startEnd, EventType.DRAG);
    }

    @Override
    public void stateMouseReleased(int x, int y, DiagramPanel panel) {
        String start = startX + "/" + startY;
        String end = x + "/" + y;
        String startEnd = start + "-" + end;

        EventBus.getInstance().notifySubscriber(startEnd, EventType.CLEAR_DRAG);
        startX = 0;
        startY = 0;
    }

    private boolean isHit(InterClass interClass, int x, int y){
        int startX = interClass.getPosition().getFirst();
        int startY = interClass.getPosition().getSecond();
        int endX = startX + interClass.getSize().getFirst();
        int endY = startY + interClass.getSize().getSecond();

        if(x >= startX && x <= endX && y >= startY && y <= endY){
            return true;
        }
        return false;

    }

}
