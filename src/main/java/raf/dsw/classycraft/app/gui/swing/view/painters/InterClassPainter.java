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

    public void paint(Graphics2D g) {
        // Set the position and size of the Rectangle
        ((Rectangle2D)shape).setFrame(interClassElement.getPosition().getFirst(), interClassElement.getPosition().getSecond(), interClassElement.getSize().getFirst(), interClassElement.getSize().getSecond());

        g.setPaint(Color.BLACK);
        g.setStroke(new BasicStroke(interClassElement.getStroke()));
        g.draw(shape);
        g.setPaint(interClassElement.getColor());
        g.fill(shape);
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        g.setPaint(Color.BLACK);

        // Centering the text at the top of the rectangle
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(interClassElement.getName());
        int x = (int) (interClassElement.getPosition().getFirst() + (interClassElement.getSize().getFirst() - stringWidth) / 2);
        int y = (int) (interClassElement.getPosition().getSecond() + fm.getAscent());
        g.drawString(interClassElement.getName(), x, y);



        // Drawing a bottom border beneath the string
        int borderY = y + fm.getDescent();
        g.drawLine((int) interClassElement.getPosition().getFirst(), borderY, (int) (interClassElement.getPosition().getFirst() + interClassElement.getSize().getFirst()), borderY);
    }

}
