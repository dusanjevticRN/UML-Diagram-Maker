package raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements;

import raf.dsw.classycraft.app.classyRepository.composite.ClassyNode;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;

public class UmlEnum extends InterClass
{
    public UmlEnum(ClassyNode parent, String name)
    {
        super(parent, name);
        this.type = "Enum";
    }

    public UmlEnum(ClassyNode parent, String name, Visibility visibility, int x, int y)
    {
        super(parent, name, visibility, x, y);
        this.type = "Enum";
    }
}
