/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.view;

import java.awt.Color;
import java.awt.Graphics2D;
import org.painter.model.Circle;
import org.painter.model.Element;
import org.painter.model.ElementGroup;
import org.painter.model.Polyline;
import org.painter.model.Pos;
import org.painter.model.Rectangle;

/**
 *
 * @author danecek
 */
public class Painter implements ElementHandler {

    private final Graphics2D g;

    public Painter(Graphics2D g) {
        this.g = g;
    }

    @Override
    public void handle(Circle c) {
        g.setColor(c.getColor());
        g.drawOval(c.getPosition().getX(), c.getPosition().getY(),
                c.getWidth(), c.getHeight());
    }

    @Override
    public void handle(Rectangle c) {
         g.setColor(c.getColor());
        g.drawRect(c.getPosition().getX(), c.getPosition().getY(), c.getWidth(), c.getHeight());
    }

    @Override
    public void handle(Polyline c) {
         g.setColor(c.getColor());
        Pos[] pts = c.pointArray();
        Pos pos1 = c.getPosition();
        for (int j = 1; j < pts.length; j++) {
            Pos pos2 = pts[j];
            g.drawLine(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY());
            pos1 = pos2;
        }
    }

    public void handle(ElementGroup group) {
        java.awt.Rectangle outline = new java.awt.Rectangle(0, 0, -1, -1);
        for (Element e : group.getElements()) {
            outline.add(e.getOutline());
        }
        g.setColor(new Color(255, 0, 0, 30));
        g.fillRoundRect(outline.x - 10, outline.y - 10,
                outline.width + 20, outline.height + 20, 20, 20);
    }

}
