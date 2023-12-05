package raf.dsw.classycraft.app.gui.swing.view.tabbedPane;

import jdk.jshell.Diag;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.UmlSelectionModel;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Interfejs;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.UmlEnum;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.core.observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.controller.mouseListener.ClassyMouse;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.InterClassPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.SelectionPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
public class DiagramPanel extends JPanel implements ISubscriber {

    ArrayList<ElementPainter> painters = new ArrayList<>();
    ArrayList<ElementPainter> tempPainters = new ArrayList<>();
    ArrayList<ElementPainter> selectedPainters = new ArrayList<>();
    private SelectionPainter selectionPainter = null;
    StateManager stateManager = new StateManager();
    Diagram diagram;
    int startDargX;
    int startDragY;
    private UmlSelectionModel selectionModel;
    public DiagramPanel(Diagram diagram){

        super();
        ClassyMouse classyMouse = new ClassyMouse(this);
        this.diagram = diagram;
        init(diagram);
        selectionModel = new UmlSelectionModel();

        this.setBackground(Color.WHITE);
        this.addMouseListener(classyMouse);
        this.addMouseMotionListener(classyMouse);
        EventBus.getInstance().subscribe(EventType.ADD_CLASS, this);
        EventBus.getInstance().subscribe(EventType.ADD_INTERFACE, this);
        EventBus.getInstance().subscribe(EventType.ADD_ENUM, this);
        EventBus.getInstance().subscribe(EventType.SELECT_ELEMENT, this);
        EventBus.getInstance().subscribe(EventType.REFRESH, this);
        EventBus.getInstance().subscribe(EventType.DRAG, this);
        EventBus.getInstance().subscribe(EventType.CLEAR_DRAG, this);
        EventBus.getInstance().subscribe(EventType.START_DRAG, this);
    }

    private void init(Diagram diagram){
        for (DiagramElement element : diagram.getDiagramElements()) {
            if(selectionModel != null && selectionModel.getSelected().contains(element)){
                if (element instanceof Klasa) {
                    Klasa klasa = (Klasa) element;
                    InterClassPainter painter = new InterClassPainter(klasa);
                    painters.add(painter);
                    selectedPainters.add(painter);
                } else if (element instanceof UmlEnum) {
                    UmlEnum umlEnum = (UmlEnum) element;
                    InterClassPainter painter = new InterClassPainter(umlEnum);
                    painters.add(painter);
                    selectedPainters.add(painter);
                } else if (element instanceof Interfejs) {
                    Interfejs interfejs = (Interfejs) element;
                    InterClassPainter painter = new InterClassPainter(interfejs);
                    painters.add(painter);
                    selectedPainters.add(painter);
                }
            }
            else {
                if (element instanceof Klasa) {
                    Klasa klasa = (Klasa) element;
                    InterClassPainter painter = new InterClassPainter(klasa);
                    painters.add(painter);
                } else if (element instanceof UmlEnum) {
                    UmlEnum umlEnum = (UmlEnum) element;
                    InterClassPainter painter = new InterClassPainter(umlEnum);
                    painters.add(painter);
                } else if (element instanceof Interfejs) {
                    Interfejs interfejs = (Interfejs) element;
                    InterClassPainter painter = new InterClassPainter(interfejs);
                    painters.add(painter);
                }
            }
        }

    }

    public void startAddClassState()
    {
        this.stateManager.setAddClassState();
    }
    public void startAddInterfaceState()
    {
        this.stateManager.setAddInterfaceState();
    }
    public void startAddEnumState()
    {
        this.stateManager.setAddEnumState();
    }
    public void startSelectState()
    {
        this.stateManager.setSelectState();
    }

    @Override
    public void update(Object notification, Object typeOfUpdate) {
        if(EventType.ADD_CLASS.equals(typeOfUpdate))
            this.startAddClassState();
        else if(EventType.ADD_INTERFACE.equals(typeOfUpdate))
            this.startAddInterfaceState();
        else if(EventType.ADD_ENUM.equals(typeOfUpdate))
            this.startAddEnumState();
        else if(EventType.SELECT_ELEMENT.equals(typeOfUpdate))
            this.startSelectState();
        else if(EventType.REFRESH.equals(typeOfUpdate)){
            System.out.println("REFRESH");
            this.init(diagram);
            this.repaint();
        }
        else if(EventType.START_DRAG.equals(typeOfUpdate)){
            tempPainters.addAll(painters);
        }
        if(EventType.DRAG.equals(typeOfUpdate)) {
            if(selectionPainter == null) {
                InterClass temp = new Klasa(null, "temp");
                selectionPainter = new SelectionPainter(temp);
                painters.add(selectionPainter);
            }

            String startStr[] = notification.toString().split("-");
            String start[] = startStr[0].split("/");
            String end[] = startStr[1].split("/");
            int startX = Integer.parseInt(start[0]);
            int startY = Integer.parseInt(start[1]);
            int endX = Integer.parseInt(end[0]);
            int endY = Integer.parseInt(end[1]);

            int rectX = Math.min(startX, endX);
            int rectY = Math.min(startY, endY);
            int rectWidth = Math.abs(endX - startX);
            int rectHeight = Math.abs(endY - startY);

            InterClass interClassElement = (InterClass) selectionPainter.getDiagramElement();
            interClassElement.getPosition().setFirst(rectX);
            interClassElement.getPosition().setSecond(rectY);
            interClassElement.getSize().setFirst(rectWidth);
            interClassElement.getSize().setSecond(rectHeight);

            this.repaint();
        }
        else if(EventType.CLEAR_DRAG.equals(typeOfUpdate)) {
            painters.remove(selectionPainter);
            selectionPainter = null;
            this.repaint();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ElementPainter p : selectedPainters){
            System.out.println("Selected: " + p);
        }
        Graphics2D g2d = (Graphics2D) g;
        for (ElementPainter painter : painters) {
            if(!selectedPainters.contains(painter))
                painter.paint(g2d);
            else {
                InterClassPainter interClassPainter = (InterClassPainter) painter;
                interClassPainter.paintSelected(g2d);
            }
        }
    }


}
