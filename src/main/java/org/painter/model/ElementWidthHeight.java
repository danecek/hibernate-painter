/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.model;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author danecek
 */
@MappedSuperclass
public abstract class ElementWidthHeight extends Element {

    private int width;
    private int height;

    public ElementWidthHeight() {
    }

    public ElementWidthHeight(java.awt.Rectangle r) {
        super(new Pos(r.getLocation()));
        this.width = r.width;
        this.height = r.height;
    }

    /**
     * @return the width
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    @Override
    public int getHeight() {
        return height;
    }

}
