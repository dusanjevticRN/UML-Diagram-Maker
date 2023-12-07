package raf.dsw.classycraft.app.gui.swing.state.stateImpl;

import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.connectionSubElements.Zavisnost;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Klasa;
import raf.dsw.classycraft.app.gui.swing.state.State;
import raf.dsw.classycraft.app.gui.swing.view.painters.ZavisnostPainter;
import raf.dsw.classycraft.app.gui.swing.view.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.view.tabbedPane.DiagramPanel;

import java.awt.*;
import java.util.ArrayList;

public class AddDependancyState implements State {
    private Zavisnost dependancy = null;
    private Connection dep = null;
    private ConnectionPainter dependancyPainter = null;
    private int startX= 0;
    private int startY= 0;
    @Override
    public void execute(int x, int y, DiagramPanel panel) {
        panel.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void stateMousePressed(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateMouseDragged(int x, int y, DiagramPanel panel) {


        if(startX == 0 && startY == 0) {
            Connection connection = new Zavisnost(null, "name");
            connection.setStart(new Pair<>(0, 0));
            System.out.println("Start: " + x + " " + y);
            startX = x;
            startY = y;
            connection.setStart(new Pair<>(startX, startY));
            dep = connection;
            dep.setEnd(new Pair<>(x, y));
            dependancy = (Zavisnost) dep;
            if (dependancy.getToElement() == null) {
                for (DiagramElement diagramElement : panel.getDiagram().getDiagramElements()) {
                    if (diagramElement instanceof InterClass) {
                        if (isHit((InterClass) diagramElement, x, y)) {
                            dependancy.setFromElement(((InterClass) diagramElement));
                            break;
                        }
                    }
                }
            }
            if (dependancy.getToElement() == null) {
                dependancy.setToElement(new Klasa(panel.getDiagram(), "Klasa", null, x, y));
            }
            dependancyPainter = new ZavisnostPainter(dependancy,1);
            dependancyPainter.setConnectionElement(dependancy);
            dependancyPainter.setColor(Color.BLACK);
            panel.getPainters().add(dependancyPainter);
        }

        panel.getPainters().remove(dependancyPainter);
        System.out.println("PAINT");
        dep.setEnd(new Pair<>(x, y));
        dependancy.setToElement(new Klasa(panel.getDiagram(), "Klasa", null, x, y));
        dependancyPainter = new ZavisnostPainter(dependancy,1);
        panel.getPainters().add(dependancyPainter);

        panel.repaint();

    }

    @Override
    public void stateMouseReleased(int x, int y, DiagramPanel panel) {
        panel.setPainters(new ArrayList<>());
        panel.repaint();
        panel.outsideRefresh();
        startX = 0;
        startY = 0;
        for(DiagramElement diagramElement : panel.getDiagram().getDiagramElements()){
            if(diagramElement instanceof InterClass){
                if(isHit((InterClass) diagramElement, x, y)){
                    panel.setPainters(new ArrayList<>());
                    panel.repaint();
                    panel.outsideRefresh();
                    dependancy.setToElement(((InterClass) diagramElement));
                    setEnd(dependancy, (InterClass) diagramElement);
                    dependancyPainter.setConnectionElement(dependancy);
                    dependancyPainter = new ZavisnostPainter(dependancy, 0);
                    panel.getDiagram().addDiagramElement(new Pair<>(x, y), dependancy);
                    panel.setPainters(new ArrayList<>());
                    panel.repaint();
                    panel.outsideRefresh();
                    break;
                }
                else {
                    panel.setPainters(new ArrayList<>());
                    panel.repaint();
                    panel.outsideRefresh();
                }
            }
        }

        dep = null;
        dependancy = null;

    }

    @Override
    public void stateRightMouseDragged(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateRightMousePressed(int x, int y, DiagramPanel panel) {

    }

    @Override
    public void stateRightMouseReleased(int x, int y, DiagramPanel panel) {

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
    private void setEnd(Connection connection, InterClass interClass){
        int startX = connection.getFromElement().getPosition().getFirst();
        int startY = connection.getFromElement().getPosition().getSecond();
        int endXofStart = startX + connection.getFromElement().getSize().getFirst();
        int endYofStart = startY + connection.getFromElement().getSize().getSecond();

        int endStartX = interClass.getPosition().getFirst();
        int endStartY = interClass.getPosition().getSecond();
        int endXofEnd = endStartX + interClass.getSize().getFirst();
        int endYofEnd = endStartY + interClass.getSize().getSecond();

        int endMidCordX = (endStartX + endXofEnd)/2;
        int endMidCordY = (endStartY + endYofEnd)/2;

        if (endMidCordX < startX) {
            // To levo od from
            connection.getStart().setFirst(startX);
            connection.getStart().setSecond((startY + endYofStart)/2);
            connection.getEnd().setFirst(endXofEnd);
            connection.getEnd().setSecond(endMidCordY);
        }  else if (endMidCordY < startY - 10) {
            // To iznad from
            connection.getStart().setFirst((startX + endXofStart)/2);
            connection.getStart().setSecond(startY);
            connection.getEnd().setFirst(endMidCordX);
            connection.getEnd().setSecond(endYofEnd);
        }  else if (endMidCordX > endXofStart) {
            // To desno od from
            connection.getStart().setFirst(endXofStart);
            connection.getStart().setSecond((startY + endYofStart)/2);
            connection.getEnd().setFirst(endStartX);
            connection.getEnd().setSecond(endMidCordY);
        }  else {
            // To ispod from
            connection.getStart().setFirst((startX + endXofStart)/2);
            connection.getStart().setSecond(endYofStart);
            connection.getEnd().setFirst(endMidCordX);
            connection.getEnd().setSecond(endStartY);
        }
    }
}
