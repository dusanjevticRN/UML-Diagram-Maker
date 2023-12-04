package raf.dsw.classycraft.app.classyRepository.implementation.subElements;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;

import java.awt.*;

@Getter
@Setter
public abstract class InterClass extends DiagramElement
{
    private Visibility visibility;
    private String name;
    private Pair<Integer, Integer> size;
    private Pair<Integer, Integer> position;
    public InterClass(ClassyNode parent, String name)
    {
        super(parent, name);
        this.visibility = Visibility.PUBLIC;
        //this.type = "InterClass";
    }

    public InterClass(ClassyNode parent, String name, Visibility visibility, int x, int y)
    {   //size za x osu odnosno za sirinu ima formulu jer se ono prilagodjava duzini imena
        super(parent, name);
        this.visibility = visibility;
        this.position = new Pair(x, y);
        this.size = new Pair(this.getName().length()*8+100, 220); //Ciljana velicina je negde oko x = 150 i y = 200 u zavisnosti od velicine imena
        //this.type = "InterClass";
    }

    public void setPosition(Integer x, Integer y)
    {
        this.position.setFirst(x);
        this.position.setSecond(y);
        this.notifySubscriber(this, null); //Za typeOfUpdate prosledjujemo null jer cemo u notify odredjivati na osnovu instanceof
    }

    public void setPosition(Pair<Integer, Integer> position)
    {
        this.size = position;
        this.notifySubscriber(this, null); //Za typeOfUpdate prosledjujemo null jer cemo u notify odredjivati na osnovu instanceof
    }

    public void setSize(Integer width, Integer height)
    {
        this.size.setFirst(width);
        this.size.setSecond(height);
        this.notifySubscriber(this, null); //Za typeOfUpdate prosledjujemo null jer cemo u notify odredjivati na osnovu instanceof
    }

    public void setSize(Pair<Integer, Integer> size)
    {
        this.size = size;
        this.notifySubscriber(this, null); //Za typeOfUpdate prosledjujemo null jer cemo u notify odredjivati na osnovu instanceof
    }
}
