package raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.Metod;

import java.util.ArrayList;
import java.util.List;

public class Interfejs extends InterClass
{
    private List<Metod> metods;
    public Interfejs(ClassyNode parent, String name)
    {
        super(parent, name);
        this.metods = new ArrayList<>();
        this.type = "Interfejs";
    }

    public Interfejs(ClassyNode parent, String name, Visibility visibility, int x, int y)
    {
        super(parent, name, visibility, x, y);
        this.metods = new ArrayList<>();
        this.type = "Interfejs";
    }

    public void addMetod(Metod metod)
    {
        this.metods.add(metod);
    }

    public void deleteMetod(Metod metod) {this.metods.remove(metod);}
}
