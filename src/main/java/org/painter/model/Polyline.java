/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Transient;
import org.painter.view.ElementHandler;

/**
 *
 * @author danecek
 */
@Entity
public class Polyline extends Element {

    @ElementCollection(fetch = FetchType.EAGER)
    private final List<Pos> points = new ArrayList<>();
    @Transient
    private java.awt.Rectangle outline;

    public Polyline() {
    }

    public Polyline(Pos ref) {
        super(ref);
        points.add(ref);
    }

    public void addPos(int x, int y) {
        points.add(new Pos(x, y));
    }

    @Override
    public int getWidth() {
        return getOutline().width;
    }

    @Override
    public int getHeight() {
        return getOutline().height;
    }

    @Override
    public Rectangle getOutline() {
        if (outline == null) {
            outline = new java.awt.Rectangle(0, 0, -1, -1);
            for (Pos p : points) {
                outline.add(p.getX(), p.getY());
            }
        }
        return outline;
    }

    @Override
    public void apply(ElementHandler u) {
        u.handle(this);
    }

    public Pos[] pointArray() {
        return points.toArray(new Pos[points.size()]);

    }

    @Override
    public String toString() {
        return "Polyline{" + "points=" + points + '}';
    }

}
