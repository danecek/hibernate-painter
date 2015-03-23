/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.model;

import java.awt.Point;

/**
 *
 * @author danecek
 */
public enum ElemEnum {

    Circle,
    Rectangle,
    Polyline,
    Group;

    public Element create(Point ref, Point point) {
        java.awt.Rectangle r = new java.awt.Rectangle(ref);
        r.add(point);
        Pos pos = new Pos(ref);
        switch (this) {
            case Circle:
                return new Circle(r);//pos, r.width, r.height);
            case Rectangle:
                return new Rectangle(r);//pos, r.width, r.height);
            case Polyline:
                return new Polyline(pos);
        }
        return null;

    }

    public Element create(Point ref) {
        return create(ref, ref);

    }

}
