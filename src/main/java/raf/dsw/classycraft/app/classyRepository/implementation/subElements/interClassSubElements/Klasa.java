package raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;

import java.util.ArrayList;
import java.util.List;

public class Klasa extends InterClass
{
    private List<ClassContent> classContents;
    public Klasa(ClassyNode parent, String name)
    {
        super(parent, name);
        this.classContents = new ArrayList<>();
        this.type = "Klasa";
    }

    public Klasa(ClassyNode parent, String name, Visibility visibility, int x, int y)
    {
        super(parent, name, visibility, x, y);
        this.classContents = new ArrayList<>();
        this.type = "Klasa";
    }

    public void addClassContent(ClassContent classContent)
    {
        this.classContents.add(classContent);
    }

    public void deleteClassContent(ClassContent classContent) {this.classContents.remove(classContent);}
}
