package raf.dsw.classycraft.app.gui.swing.view.tabbedPane;

import jdk.jshell.Diag;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.UmlSelectionModel;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.connectionSubElements.Agregacija;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.connectionSubElements.Generalizacija;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.connectionSubElements.Kompozicija;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.connectionSubElements.Zavisnost;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Interfejs;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.UmlEnum;
import raf.dsw.classycraft.app.core.eventHandler.EventBus;
import raf.dsw.classycraft.app.core.eventHandler.EventType;
import raf.dsw.classycraft.app.core.observer.ISubscriber;
import raf.dsw.classycraft.app.gui.swing.controller.mouseListener.ClassyMouse;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.painters.*;

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
        EventBus.getInstance().subscribe(EventType.ADD_GENERALIZATION, this);
    }

    private void init(Diagram diagram){
        ArrayList<Connection> connectionList = getConnections(diagram);
        ArrayList<Connection> flaggedConnections = new ArrayList<>();
        ArrayList<Connection> doubleCheck = new ArrayList<>();
        //Provera da li dve klase imaju vise od 1 zajednicke konekcije
        //TODO: Implementirati logiku za vise od 1 zajednicke konekcije
        //TODO: FLAGGED ili slicno
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
                } else if (element instanceof Generalizacija && !flaggedConnections.contains(element)) {
                    Generalizacija gen = (Generalizacija) element;
                    System.out.println(gen.getFromElement().getName() + " " + gen.getToElement().getName());
                    Connection con = gen;
                    Generalizacija generalizacija = (Generalizacija) element;
                    GeneralizacijaPainter painter = new GeneralizacijaPainter(generalizacija,0);
                    painters.add(painter);
                } else if (element instanceof Zavisnost && !flaggedConnections.contains(element)) {
                    Zavisnost zavisnost = (Zavisnost) element;
                    ZavisnostPainter painter = new ZavisnostPainter(zavisnost);
                    painters.add(painter);
                } else if (element instanceof Agregacija && !flaggedConnections.contains(element)) {
                    Agregacija agregacija = (Agregacija) element;
                    AgregacijaPainter painter = new AgregacijaPainter(agregacija);
                    painters.add(painter);
                } else if (element instanceof Kompozicija && !flaggedConnections.contains(element)) {
                    Kompozicija kompozicija = (Kompozicija) element;
                    KompozicijaPainter painter = new KompozicijaPainter(kompozicija);
                    painters.add(painter);
                }
                else if(element instanceof Connection && flaggedConnections.contains(element)){
                    if(element instanceof Generalizacija){
                        System.out.println("FLAGGED");
                        Generalizacija gen = (Generalizacija) element;
                        System.out.println(gen.getFromElement().getName() + " " + gen.getToElement().getName());
                        Connection con = gen;
                        Generalizacija generalizacija = (Generalizacija) element;
                        generalizacija.getFromElement().getPosition().setSecond(generalizacija.getFromElement().getPosition().getSecond() + 20);
                        generalizacija.getToElement().getPosition().setSecond(generalizacija.getToElement().getPosition().getSecond() + 20);
                        GeneralizacijaPainter painter = new GeneralizacijaPainter(generalizacija, 0);
                        painters.add(painter);
                    }
                    else if(element instanceof Zavisnost){
                        Zavisnost zavisnost = (Zavisnost) element;
                        ZavisnostPainter painter = new ZavisnostPainter(zavisnost);
                        painters.add(painter);
                    }
                    else if(element instanceof Agregacija){
                        Agregacija agregacija = (Agregacija) element;
                        AgregacijaPainter painter = new AgregacijaPainter(agregacija);
                        painters.add(painter);
                    }
                    else if(element instanceof Kompozicija){
                        Kompozicija kompozicija = (Kompozicija) element;
                        KompozicijaPainter painter = new KompozicijaPainter(kompozicija);
                        painters.add(painter);
                    }
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
    public void startGeneralizationState()
    {
        this.stateManager.setGeneralizationState();
    }

    @Override
    public void update(Object notification, Object typeOfUpdate) {
        if(EventType.ADD_CLASS.equals(typeOfUpdate))
            this.startAddClassState();
        else if(EventType.ADD_INTERFACE.equals(typeOfUpdate))
            this.startAddInterfaceState();
        else if(EventType.ADD_ENUM.equals(typeOfUpdate))
            this.startAddEnumState();
        else if(EventType.ADD_GENERALIZATION.equals(typeOfUpdate))
            this.startGeneralizationState();
        else if(EventType.SELECT_ELEMENT.equals(typeOfUpdate)) {
            this.startSelectState();
        }
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

    private ArrayList<Connection> getConnections(Diagram diagram) {
        ArrayList<Connection> connectionList = new ArrayList<>();
        for (DiagramElement element : diagram.getDiagramElements()) {
            if (element instanceof Connection) {
                Connection connection = (Connection) element;
                connectionList.add(connection);
            }
        }
        return connectionList;
    }

    public void outsideRefresh(){
        this.init(diagram);
        this.repaint();
    }
}
