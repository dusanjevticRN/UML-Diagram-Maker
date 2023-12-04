package raf.dsw.classycraft.app.gui.swing.view.painters;

import raf.dsw.classycraft.app.classyRepository.implementation.DiagramElement;
import raf.dsw.classycraft.app.classyRepository.implementation.subElements.InterClass;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InterClassPainter extends ElementPainter
{
    private InterClass interClassElement;

    public InterClassPainter(DiagramElement diagramElement)
    {
        super(diagramElement);
        this.interClassElement = (InterClass) diagramElement;
        this.shape = new Rectangle2D.Float(interClassElement.getPosition().getFirst(), interClassElement.getPosition().getSecond(), interClassElement.getSize().getFirst(), interClassElement.getSize().getSecond());
    }

    @Override
    public void paint(Graphics2D g)
    {
        //setFrame postavlja poziciju i velicinu Rectangle-a
        ((Rectangle2D)shape).setFrame(interClassElement.getPosition().getFirst(), interClassElement.getPosition().getSecond(), interClassElement.getSize().getFirst(), interClassElement.getSize().getSecond());

        g.setPaint(Color.BLACK);
        g.setStroke(new BasicStroke(interClassElement.getStroke()));
        g.draw(shape);
        g.setPaint(interClassElement.getColor());
        g.fill(shape);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        //drawString renderuje tekst na grafickoj povrsini (argumenti su tekst, x koordinata i y koordinata)
        g.drawString(interClassElement.getName(),
                ((InterClass)interClassElement).getPosition().getFirst()+((InterClass)interClassElement).getSize().getFirst()/2-interClassElement.getName().length()*3-6,
                ((InterClass)interClassElement).getPosition().getSecond()+((InterClass)interClassElement).getSize().getSecond()/2+5);
    }

}
