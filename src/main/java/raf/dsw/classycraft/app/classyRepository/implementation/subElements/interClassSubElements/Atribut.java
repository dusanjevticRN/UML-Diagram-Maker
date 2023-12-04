package raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements.ClassContent;

import java.lang.reflect.Type;

@Getter
@Setter
public class Atribut extends ClassContent
{
    private Type type;

    public Atribut(String name, Visibility visibility, boolean isStatic, Type type)
    {
        super(name, visibility, isStatic);
        this.type = type;
    }
}
