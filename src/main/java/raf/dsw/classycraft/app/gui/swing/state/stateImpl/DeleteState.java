package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Triple;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.UmlSelectionModel;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.PackageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State {
    //kod bukv iz selecta samo promanjen malo da radi za delete sa hitom bez movea
    private int startXRight;
    private int startYRight;
    private int startX;
    private int startY;
    private boolean hit = false;
    private List<DiagramElement> selecteElements = new ArrayList<>();

    @Override
    public void execute(int x, int y, PackageView packageView) {
        packageView.setPanelCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        System.out.println("Delete state");
        packageView.clearSelectedElements();
        this.selecteElements.clear();
    }

    @Override
    public void stateMousePressed(int x, int y, PackageView packageView) {

        this.selecteElements.clear();
        packageView.setSelectionModel(new UmlSelectionModel());
        System.out.println("Select state");
        this.startX = x;
        this.startY = y;
        UmlSelectionModel selectionModel = packageView.getSelectionModel();
        List<InterClass> selectableElements = new ArrayList<>();
        for(DiagramElement elem : packageView.currentDiagramElements()){
            if(elem instanceof InterClass){
                InterClass interClass = (InterClass) elem;
                selectableElements.add(interClass);
                selecteElements.add(interClass);
            }
            else if(elem instanceof Connection){
                Connection connection = (Connection) elem;
                if(isHitLine(connection, x, y)){
                    selectionModel.select(connection);
                    hit = true;
                    selecteElements.add(connection);
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
            packageView.setSelectionModel(new UmlSelectionModel());
            packageView.setPanelSelectionPainters(new ArrayList<>());
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        if(hit) {
            System.out.println(hit);
            System.out.println("Hit: " + selectionModel.getSelected().get(0).getName());
            selecteElements.add(selectionModel.getSelected().get(0));
            packageView.setPanelSelectionPainters(new ArrayList<>());
            packageView.setSelectionModel(new UmlSelectionModel());
            packageView.setSelectionModel(selectionModel);
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        hit = false;
        selectionModel.getSelected().clear();
        deleteSelectedElements(packageView);
    }

    @Override
    public void stateMouseDragged(int x, int y, PackageView packageView) {
        if (startX == 0 && startY == 0) {
            packageView.clearSelectedElements();
            this.selecteElements.clear();
            startX = x;
            startY = y;
            EventBus.getInstance().notifySubscriber(this, EventType.START_DRAG_DEL);
        }

        //stara selekcija (on release koja je bila)
        String start = startX + "/" + startY;
        String end = x + "/" + y;
        String startEnd = start + "-" + end;
        EventBus.getInstance().notifySubscriber(startEnd, EventType.DRAG_DEL);

        // dinamicka selekcija
        UmlSelectionModel selectionModel = packageView.getSelectionModel();
        List<DiagramElement> newSelectedElements = new ArrayList<>();

        // prolazimo kroz elemente i proveravamo da li su hitovani
        for (DiagramElement elem : packageView.currentDiagramElements()) {
            if (elem instanceof InterClass) {
                InterClass interClass = (InterClass) elem;
                if (isHitDrag(interClass, startX, startY, x, y)) {
                    newSelectedElements.add(interClass);
                    if (!selecteElements.contains(interClass)) {
                        //selektujemo elemente koji su hitovani u dragu
                        selectionModel.select(interClass);
                    }
                }
            } else if (elem instanceof Connection) {
                Connection connection = (Connection) elem;
                if (isHitLineDrag(connection, startX, startY, x, y)) {
                    newSelectedElements.add(connection);
                    if (!selecteElements.contains(connection)) {
                        //selektuiju se konekcije koje su hitovane u dragu
                        selectionModel.select(connection);
                    }
                }
            }
        }

        //dinamicki unselektrujemo elemente
        for (DiagramElement previouslySelected : selecteElements) {
            if (!newSelectedElements.contains(previouslySelected)) {
                selectionModel.unselect(previouslySelected);
            }
        }

        selecteElements = newSelectedElements;

        EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
    }

    @Override
    public void stateMouseReleased(int x, int y, PackageView packageView) {
        System.out.println("Released");
        String start = startX + "/" + startY;
        String end = x + "/" + y;
        String startEnd = start + "-" + end;

        UmlSelectionModel selectionModel = packageView.getSelectionModel();
        List<InterClass> selectableElements = new ArrayList<>();
        for(DiagramElement elem : packageView.currentDiagramElements()){
            if(elem instanceof InterClass){
                InterClass interClass = (InterClass) elem;
                selectableElements.add(interClass);
                selecteElements.add(interClass);
            }
            else if(elem instanceof Connection){
                Connection connection = (Connection) elem;
                if(isHitLineDrag(connection, startX, startY, x, y)){
                    selectionModel.select(connection);
                    hit = true;
                    selecteElements.add(connection);
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
            packageView.setSelectionModel(new UmlSelectionModel());
            packageView.setPanelSelectionPainters(new ArrayList<>());
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        if(hit) {
            System.out.println(hit);
            System.out.println("Hit: " + selectionModel.getSelected().get(0).getName());
            packageView.setSelectionModel(new UmlSelectionModel());
            packageView.setPanelSelectionPainters(new ArrayList<>());
            packageView.setSelectionModel(selectionModel);
            EventBus.getInstance().notifySubscriber(this, EventType.REFRESH);
        }
        hit = false;
        selectionModel.getSelected().clear();

        EventBus.getInstance().notifySubscriber(startEnd, EventType.CLEAR_DRAG_DEL);
        startXRight = 0;
        startYRight = 0;

        deleteSelectedElements(packageView);
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

    private boolean isHit(InterClass interClass, int x, int y){
        int startX = interClass.getPosition().getFirst(); //Gornja leva x koordinata
        int startY = interClass.getPosition().getSecond(); //Gornja leva y koordinata
        int endX = startX + interClass.getSize().getFirst(); //Donja desna x koordinata
        int endY = startY + interClass.getSize().getSecond(); //Donja desna y koordinata

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

    private void deleteSelectedElements(PackageView packageView) {

        ArrayList<DiagramElement> selectedElements = new ArrayList<>();
        selectedElements.clear();
        for(DiagramElement element : packageView.getSelectedElementsDel()){
            if(selectedElements.contains(element) || !(element instanceof InterClass)){
                continue;
            }
            else {
                selectedElements.add(element);
            }
        }
        for(DiagramElement element : packageView.getSelectedElementsDel()){
            if(element instanceof Connection){
                Connection connection = (Connection) element;
                if(selectedElements.contains(connection.getToElement()) || selectedElements.contains(connection.getFromElement())){
                    selectedElements.add(connection);
                }
            }
        }
        EventBus.getInstance().notifySubscriber(selectedElements, EventType.DELETE);
        if (!selectedElements.isEmpty()) {
            packageView.deleteElements(selectedElements);
        }
        packageView.setPanelPainters(new ArrayList<>());
        packageView.setPanelSelectionPainters(new ArrayList<>());
        packageView.panelRepaint();
        packageView.panelOutsideRefresh();
    }

}
