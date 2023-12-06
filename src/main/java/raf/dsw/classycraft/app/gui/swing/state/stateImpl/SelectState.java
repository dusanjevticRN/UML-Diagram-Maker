package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;
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
            else if(elem instanceof Connection){
                Connection connection = (Connection) elem;
                if(isHitLine(connection, x, y)){
                    selectionModel.select(connection);
                    hit = true;
                    System.out.println("Hit: " + connection.getName());
                    break;
                }
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
            panel.setSelectedPainters(new ArrayList<>());
            panel.setSelectionModel(new UmlSelectionModel());
            panel.setSelectionModel(selectionModel);
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        hit = false;
        selectionModel.getSelected().clear();
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
        System.out.println("Released");
        String start = startX + "/" + startY;
        String end = x + "/" + y;
        String startEnd = start + "-" + end;

        UmlSelectionModel selectionModel = panel.getSelectionModel();
        List<InterClass> selectableElements = new ArrayList<>();
        for(DiagramElement elem : panel.getDiagram().getDiagramElements()){
            if(elem instanceof InterClass){
                InterClass interClass = (InterClass) elem;
                selectableElements.add(interClass);
            }
            else if(elem instanceof Connection){
                Connection connection = (Connection) elem;
                if(isHitLineDrag(connection, startX, startY, x, y)){
                    selectionModel.select(connection);
                    hit = true;
                    System.out.println("Hit: " + connection.getName());
                }
            }
        }
        for(InterClass ic : selectableElements){
            if(isHitDrag(ic,startX, startY, x, y)){
                selectionModel.select(ic);
                hit = true;
                System.out.println("Hit: " + ic.getName());
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
            panel.setSelectedPainters(new ArrayList<>());
            panel.setSelectionModel(new UmlSelectionModel());
            panel.setSelectionModel(selectionModel);
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        hit = false;
        selectionModel.getSelected().clear();

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
    private boolean isHitDrag(InterClass interClass, int sX, int sY, int x, int y) {
        // Uzimamo minimum i max izmedju pocetne i krajnje tacke
        int rect1TopLeftX = Math.min(sX, x);
        int rect1TopLeftY = Math.min(sY, y);
        int rect1BottomRightX = Math.max(sX, x);
        int rect1BottomRightY = Math.max(sY, y);

        int rect2TopLeftX = interClass.getPosition().getFirst();
        int rect2TopLeftY = interClass.getPosition().getSecond();
        int rect2BottomRightX = rect2TopLeftX + interClass.getSize().getFirst();
        int rect2BottomRightY = rect2TopLeftY + interClass.getSize().getSecond();

        if (rect1TopLeftX > rect2BottomRightX || rect2TopLeftX > rect1BottomRightX) {
            return false;
        }

        if (rect1TopLeftY > rect2BottomRightY || rect2TopLeftY > rect1BottomRightY) {
            return false;
        }

        return true;
    }
    private boolean isHitLine(Connection connection, int x, int y){
        int startX = connection.getStart().getFirst();
        int startY = connection.getStart().getSecond();
        int endX = connection.getEnd().getFirst();
        int endY = connection.getEnd().getSecond();

        if(x >= startX && x <= endX && y >= startY && y <= endY){
            return true;
        }
        return false;

    }

    private boolean isHitLineDrag(Connection connection, int sX, int sY, int x, int y) {
        // Uzimamo minimum i max izmedju pocetne i krajnje tacke
        int rect1TopLeftX = Math.min(sX, x);
        int rect1TopLeftY = Math.min(sY, y);
        int rect1BottomRightX = Math.max(sX, x);
        int rect1BottomRightY = Math.max(sY, y);

        int rect2TopLeftX = connection.getStart().getFirst();
        int rect2TopLeftY = connection.getStart().getSecond();
        int rect2BottomRightX = connection.getEnd().getFirst();
        int rect2BottomRightY = connection.getEnd().getSecond();

        if (rect1TopLeftX > rect2BottomRightX || rect2TopLeftX > rect1BottomRightX) {
            return false;
        }

        if (rect1TopLeftY > rect2BottomRightY || rect2TopLeftY > rect1BottomRightY) {
            return false;
        }

        return true;
    }

}
