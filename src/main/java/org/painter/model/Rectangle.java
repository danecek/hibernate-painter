/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.model;

import javax.persistence.Entity;
import org.painter.view.ElementHandler;

/**
 *
 * @author danecek
 */
@Entity
public class Rectangle extends ElementWidthHeight {

    public Rectangle() {
    }

    public Rectangle(java.awt.Rectangle r) {
        super(r);
    }

    @Override
    public void apply(ElementHandler u) {
        u.handle(this);
    }

}
