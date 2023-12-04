package raf.dsw.classycraft.app.gui.swing.view.painters;

import raf.dsw.classycraft.app.classyRepository.implementation.subElements.Connection;

import java.awt.*;

public class KompozicijaPainter extends ConnectionPainter
{
    public KompozicijaPainter(Connection connectionElement)
    {
        super(connectionElement);
    }

    @Override
    public void paint(Graphics2D g)
    {
        if(connectionElement.getToElement() == null)
            this.setBounds();

        else
        {
            connectionElement.getStart().setFirst(connectionElement.getFromElement().getPosition().getFirst() + connectionElement.getFromElement().getSize().getFirst()/2);
            connectionElement.getStart().setSecond(connectionElement.getFromElement().getPosition().getSecond()+15);
            connectionElement.getEnd().setFirst(connectionElement.getToElement().getPosition().getFirst() + connectionElement.getToElement().getSize().getFirst()/2);
            connectionElement.getEnd().setSecond(connectionElement.getToElement().getPosition().getSecond()+15);
        }

        g.setPaint(connectionElement.getColor());
        g.setStroke(new BasicStroke(connectionElement.getStroke()));

        g.draw(shape);
        g.setPaint(connectionElement.getColor());
        g.fill(shape);
    }
}
