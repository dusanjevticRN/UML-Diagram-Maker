package raf.dsw.classycraft.app.gui.swing.view.tabbedPane;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.AppCore;
import raf.dsw.classycraft.app.classyRepository.implementation.Diagram;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.*;
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
import raf.dsw.classycraft.app.gui.swing.controller.listner.ClassyMouse;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.painters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

@Getter
@Setter
public class DiagramPanel extends JPanel implements ISubscriber {

    private ArrayList<ElementPainter> painters = new ArrayList<>();
    private ArrayList<ElementPainter> tempPainters = new ArrayList<>();
    private ArrayList<ElementPainter> selectedPainters = new ArrayList<>();
    private ArrayList<DiagramElement> selectedElements = new ArrayList<>();
    private SelectionPainter selectionPainter = null;
    private StateManager stateManager = new StateManager();
    private Diagram diagram;
    int startDargX;
    int startDragY;
    private UmlSelectionModel selectionModel;
    private double scaleFactor;
    private double scale;
    private AffineTransform affineTransform;
    private boolean setScale;
    private int step;
    public DiagramPanel(Diagram diagram){

        super();
        ClassyMouse classyMouse = new ClassyMouse(this);
        this.diagram = diagram;
        init(diagram);
        selectionModel = new UmlSelectionModel();
        this.setFocusable(true);
        this.setScale = true;
        this.step = 5;
        this.affineTransform = new AffineTransform();

        this.setBackground(Color.WHITE);
        this.addMouseListener(classyMouse);
        this.addMouseMotionListener(classyMouse);
        setupDeleteKeyBinding();
        EventBus.getInstance().subscribe(EventType.ADD_CLASS, this);
        EventBus.getInstance().subscribe(EventType.ADD_METHOD, this);
        EventBus.getInstance().subscribe(EventType.ZOOM_IN_STATE, this);
        EventBus.getInstance().subscribe(EventType.ZOOM_IN, this);
        EventBus.getInstance().subscribe(EventType.ZOOM_OUT_STATE, this);
        EventBus.getInstance().subscribe(EventType.ZOOM_OUT, this);
        EventBus.getInstance().subscribe(EventType.ADD_INTERFACE, this);
        EventBus.getInstance().subscribe(EventType.ADD_ENUM, this);
        EventBus.getInstance().subscribe(EventType.SELECT_ELEMENT, this);
        EventBus.getInstance().subscribe(EventType.REFRESH, this);
        EventBus.getInstance().subscribe(EventType.DRAG, this);
        EventBus.getInstance().subscribe(EventType.CLEAR_DRAG, this);
        EventBus.getInstance().subscribe(EventType.START_DRAG, this);
        EventBus.getInstance().subscribe(EventType.ADD_GENERALIZATION, this);
        EventBus.getInstance().subscribe(EventType.ADD_AGGREGATION, this);
        EventBus.getInstance().subscribe(EventType.ADD_COMPOSITION, this);
        EventBus.getInstance().subscribe(EventType.ADD_DEPENDENCY, this);
        EventBus.getInstance().subscribe(EventType.MOVE, this);
        EventBus.getInstance().subscribe(EventType.DELETE_ELEMENTS, this);
        EventBus.getInstance().subscribe(EventType.CONTENT_STATE, this);
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
                else if(element instanceof Kompozicija){
                    Kompozicija kompozicija = (Kompozicija) element;
                    KompozicijaPainter painter = new KompozicijaPainter(kompozicija, 0);
                    painters.add(painter);
                    selectedPainters.add(painter);
                }
                else if(element instanceof Agregacija){
                    Agregacija agregacija = (Agregacija) element;
                    AgregacijaPainter painter = new AgregacijaPainter(agregacija, 0);
                    painters.add(painter);
                    selectedPainters.add(painter);
                }
                else if(element instanceof Zavisnost){
                    Zavisnost zavisnost = (Zavisnost) element;
                    ZavisnostPainter painter = new ZavisnostPainter(zavisnost, 0);
                    painters.add(painter);
                    selectedPainters.add(painter);
                }
                else if(element instanceof Generalizacija){
                    Generalizacija generalizacija = (Generalizacija) element;
                    GeneralizacijaPainter painter = new GeneralizacijaPainter(generalizacija, 0);
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
                    ZavisnostPainter painter = new ZavisnostPainter(zavisnost, 0);
                    painters.add(painter);
                } else if (element instanceof Agregacija && !flaggedConnections.contains(element)) {
                    Agregacija agregacija = (Agregacija) element;
                    AgregacijaPainter painter = new AgregacijaPainter(agregacija, 0);
                    painters.add(painter);
                } else if (element instanceof Kompozicija && !flaggedConnections.contains(element)) {
                    Kompozicija kompozicija = (Kompozicija) element;
                    KompozicijaPainter painter = new KompozicijaPainter(kompozicija, 0);
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
                        ZavisnostPainter painter = new ZavisnostPainter(zavisnost, 0);
                        painters.add(painter);
                    }
                    else if(element instanceof Agregacija){
                        Agregacija agregacija = (Agregacija) element;
                        AgregacijaPainter painter = new AgregacijaPainter(agregacija, 0);
                        painters.add(painter);
                    }
                    else if(element instanceof Kompozicija){
                        Kompozicija kompozicija = (Kompozicija) element;
                        KompozicijaPainter painter = new KompozicijaPainter(kompozicija, 0);
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
    public void startAgregationState()
    {
        this.stateManager.setAgregationState();
    }
    public void startKompozicijaState()
    {
        this.stateManager.setCompositionState();
    }
    public void startDependencyState()
    {
        this.stateManager.setAddDependancyState();
    }
    public void startZoomInState() {this.stateManager.setZoomInState();}
    public void startZoomOutState() {this.stateManager.setZoomOutState();}
    public void startAddFieldState() {this.stateManager.setAddFieldState();}

    @Override
    public void update(Object notification, Object typeOfUpdate) {
        if(EventType.MOVE.equals(typeOfUpdate)){
            if (notification instanceof Triple<?, ?, ?>) {
                Triple<DiagramPanel, Integer, Integer> data = (Triple<DiagramPanel, Integer, Integer>) notification;
                if (data.getFirst() == this) {
                    Pair<Integer, Integer> displacement = new Pair<>(data.getSecond(), data.getThird());
                    int deltaX = displacement.getFirst();
                    int deltaY = displacement.getSecond();
                    for (DiagramElement elem : diagram.getDiagramElements()) {
                        if (elem instanceof InterClass) {
                            ((InterClass) elem).getPosition().setFirst(((InterClass) elem).getPosition().getFirst() + deltaX);
                            ((InterClass) elem).getPosition().setSecond(((InterClass) elem).getPosition().getSecond() + deltaY);
                        }
                    }
                    painters.clear();
                    this.init(diagram);
                    this.repaint();
                }
            }
        }
        else if(EventType.ADD_CLASS.equals(typeOfUpdate))
            this.startAddClassState();
        else if(EventType.ADD_INTERFACE.equals(typeOfUpdate))
            this.startAddInterfaceState();

        else if(EventType.ZOOM_IN.equals(typeOfUpdate))
            this.startZoomInState();

        else if(EventType.ZOOM_OUT.equals(typeOfUpdate))
            this.startZoomOutState();

        else if(EventType.ZOOM_IN_STATE.equals(typeOfUpdate))
        {
            String stringX = notification.toString().split("/")[0];
            String stringY = notification.toString().split("/")[1];

            int x = Integer.parseInt(stringX);
            int y = Integer.parseInt(stringY);

            this.zoomIn(x, y);
        }

        else if(EventType.ZOOM_OUT_STATE.equals(typeOfUpdate))
        {
            String stringX = notification.toString().split("/")[0];
            String stringY = notification.toString().split("/")[1];

            int x = Integer.parseInt(stringX);
            int y = Integer.parseInt(stringY);

            this.zoomOut(x, y);
        }

        else if(EventType.CONTENT_STATE.equals(typeOfUpdate))
            this.startAddFieldState();

        else if(EventType.ADD_ENUM.equals(typeOfUpdate))
            this.startAddEnumState();
        else if(EventType.ADD_GENERALIZATION.equals(typeOfUpdate))
            this.startGeneralizationState();
        else if(EventType.ADD_AGGREGATION.equals(typeOfUpdate))
            this.startAgregationState();
        else if(EventType.ADD_COMPOSITION.equals(typeOfUpdate))
            this.startKompozicijaState();
        else if(EventType.ADD_DEPENDENCY.equals(typeOfUpdate))
            this.startDependencyState();

        else if(EventType.SELECT_ELEMENT.equals(typeOfUpdate))
            this.startSelectState();
        else if(EventType.DELETE_ELEMENTS.equals(typeOfUpdate)){
            Pair notif = (Pair) notification;
            if(notif.getFirst() == this) {
                ArrayList<DiagramElement> elementsForDeleting = (ArrayList<DiagramElement>) ((Pair<?, ?>) notification).getSecond();
                for (DiagramElement element : elementsForDeleting) {
                    System.out.println(element.getName());
                }
                this.init(diagram);
                this.repaint();
                for (DiagramElement element : elementsForDeleting) {
                    diagram.getDiagramElements().remove(element);
                }
                this.painters.clear();
                this.selectedPainters.clear();
                this.init(diagram);
                this.repaint();
            }
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
            selectedElements.add(p.getDiagramElement());
        }
        Graphics2D g2d = (Graphics2D) g;

        if (setScale)
        {
            affineTransform = g2d.getTransform();
            System.out.println(affineTransform.getScaleX());
            scale = affineTransform.getScaleX();
            step /= scale;
            setScale = false;
        }
        g2d.setTransform(affineTransform);

        for (ElementPainter painter : painters)
        {
            if(!selectedPainters.contains(painter))
            {
                if(painter instanceof GeneralizacijaPainter)
                    ((GeneralizacijaPainter) painter).setSelected(false);
                else if(painter instanceof AgregacijaPainter)
                    ((AgregacijaPainter) painter).setSelected(false);
                else if(painter instanceof KompozicijaPainter)
                    ((KompozicijaPainter) painter).setSelected(false);
                else if(painter instanceof ZavisnostPainter)
                    ((ZavisnostPainter) painter).setSelected(false);
                painter.paint(g2d);

            }
            else if(painter instanceof InterClassPainter){
                InterClassPainter interClassPainter = (InterClassPainter) painter;
                interClassPainter.paintSelected(g2d);
            }
            else if(painter instanceof GeneralizacijaPainter){
                //generalizacijaPainter.paintSelected(g2d);
                ((GeneralizacijaPainter) painter).setSelected(true);
                if(((GeneralizacijaPainter) painter).getSelected())
                    painter.paint(g2d);
            }
            else if(painter instanceof AgregacijaPainter){
                //agregacijaPainter.paintSelected(g2d);
                ((AgregacijaPainter) painter).setSelected(true);
                if(((AgregacijaPainter) painter).getSelected())
                    painter.paint(g2d);
            }
            else if(painter instanceof KompozicijaPainter){
                //kompozicijaPainter.paintSelected(g2d);
                ((KompozicijaPainter) painter).setSelected(true);
                if(((KompozicijaPainter) painter).getSelected())
                    painter.paint(g2d);
            }
            else if(painter instanceof ZavisnostPainter){
                //zavisnostPainter.paintSelected(g2d);
                ((ZavisnostPainter) painter).setSelected(true);
                if(((ZavisnostPainter) painter).getSelected())
                    painter.paint(g2d);
            }
        }
    }

    private void setUpZoomTransformation(int mouseX, int mouseY)
    {
        // Translate graphics context to position the mouse coordinates at the origin
        double translateX = affineTransform.getTranslateX();
        double translateY = affineTransform.getTranslateY();
        affineTransform.translate(
                (mouseX - translateX) * (1 - scaleFactor),
                (mouseY - translateY) * (1 - scaleFactor)
        );

        // Apply scale transformation
        this.affineTransform.scale(scaleFactor, scaleFactor);

        if(Math.abs(affineTransform.getScaleX() - scale) < 0.01) {
            AffineTransform newTransform = new AffineTransform();
            newTransform.translate(affineTransform.getTranslateX(), affineTransform.getTranslateY());
            newTransform.scale(scale, scale);
            this.affineTransform.setTransform(newTransform);
        }

        this.revalidate();
        this.repaint();
    }

    public void zoomIn(int mouseX, int mouseY)
    {
        this.scaleFactor = 1.09;

        if (affineTransform.getScaleX() >= 1.9)
        {
            AppCore.getInstance().getMessageGenerator().generate(EventType.MAX_ZOOM);
            return;
        }
        setUpZoomTransformation(mouseX, mouseY);
    }

    public void zoomOut(int mouseX, int mouseY)
    {
        scaleFactor = 1/ 1.09; //reciprocna vrednost zoomIna

        if (affineTransform.getScaleX() <= 0.4 * scale)
        {
            AppCore.getInstance().getMessageGenerator().generate(EventType.MIN_ZOOM);
            return;
        }
        setUpZoomTransformation(mouseX, mouseY);
    }

    private ArrayList<Connection> getConnections(Diagram diagram)
    {
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



    private void setupDeleteKeyBinding() {
        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteKeyPressed();
            }
        };

        String keyStrokeAndActionKey = "DELETE";
        KeyStroke deleteKeyStroke = KeyStroke.getKeyStroke("DELETE");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(deleteKeyStroke, keyStrokeAndActionKey);
        this.getActionMap().put(keyStrokeAndActionKey, deleteAction);
    }

    private void onDeleteKeyPressed() {
        System.out.println("DELETE PRESSED");
        EventBus.getInstance().notifySubscriber(this, EventType.DELETE_KEY);
    }
}
