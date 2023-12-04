package raf.dsw.classycraft.app.classyRepository.implementation.subElements.connectionSubElements;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;

public class Kompozicija extends Connection
{
    public Kompozicija(ClassyNode parent, String name)
    {
        super(parent, name);
        this.type = "Kompozicija";
    }

    public Kompozicija(ClassyNode parent, String name, InterClass fromElement, int x, int y)
    {
        super(parent, name, fromElement, x, y);
        this.type = "Kompozicija";
    }
}
