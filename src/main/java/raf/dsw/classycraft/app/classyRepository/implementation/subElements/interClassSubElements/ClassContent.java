package raf.dsw.classycraft.app.classyRepository.implementation.subElements.interClassSubElements;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Visibility;

@Getter
@Setter
public abstract class ClassContent
{
    private String name;
    private Visibility visibility;
    private boolean isStatic;

    public ClassContent(String name, Visibility visibility, boolean isStatic)
    {
        this.name = name;
        this.visibility = visibility;
        this.isStatic = isStatic;
    }
}
