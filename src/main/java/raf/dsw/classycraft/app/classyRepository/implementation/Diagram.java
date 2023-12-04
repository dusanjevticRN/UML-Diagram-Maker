package raf.dsw.classycraft.app.classyRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNodeLeaf;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Pair;
import raf.dsw.classycraft.app.core.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Diagram extends ClassyNodeLeaf
{
    private List<String> classes = new ArrayList<>();
    private List<DiagramElement> diagramElements = new ArrayList<>();
    private Map<Pair, DiagramElement> mapaElemenata = new HashMap<>();
    public Diagram(String name, ClassyNode parent, boolean isPattern) {
        super(parent, name + " ");
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }


    @Override
    public void notifySubscriber(Object notification, Object typeOfUpdate) {

    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

    }

    public void addClass(String name)
    {
        classes.add(name);
    }
    public boolean checkName(String name)
    {
        for(String s : classes)
        {
            if(s.equals(name))
                return false;
        }
        return true;
    }

    public void addDiagramElement(Pair pair, DiagramElement diagramElement)
    {
        diagramElements.add(diagramElement);
        mapaElemenata.put(pair, diagramElement);
    }
}
