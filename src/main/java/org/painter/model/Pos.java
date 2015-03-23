/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.model;

import java.awt.Point;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author danecek
 */
@Embeddable
public class Pos implements Serializable {

    private int x;
    private int y;

    public Pos() {
    }

    public Pos(Point p) {
        this(p.x, p.y);
    }

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", x, y);
    }
}
