package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeLeaf;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Element extends ClassyNodeLeaf {
    private transient Color color;

    private int stroke;

    private int colorSeter;

    public Element(ClassyNode parent, String name) {
        super(parent, name);
        setColor();
        this.stroke = 2;
    }
    public Element(String name,ClassyNode parent, int stroke, int colorSeter) {
        super(parent, name);
        this.color = new Color(colorSeter,true);
        System.out.println(colorSeter);
        this.stroke = stroke;
    }

    private void setColor() {
        color = new Color(25,189,255);
        colorSeter = color.getRGB();
    }
    public void setStroke(int stroke){
        this.stroke = stroke;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
