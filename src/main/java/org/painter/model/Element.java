/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import org.painter.view.ElementHandler;

/**
 *
 * @author danecek
 */
@Entity
@NamedQuery(name = "orfans", query = "SELECT e FROM Element e WHERE e.elementGroup IS NULL")
public abstract class Element implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private java.sql.Time creationTime = new Time(new Date().getTime());
    @Embedded
    private Pos position;
    @ManyToOne
    private ElementGroup elementGroup;
    private int rgbColor = Color.BLACK.getRGB();

    public Element() {
    }

    public Element(Pos ref) {
        this.position = ref;
    }

    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public abstract int getHeight();

    /**
     * @return the width
     */
    public abstract int getWidth();

    public java.awt.Rectangle getOutline() {
        return new java.awt.Rectangle(position.getX(), position.getY(), getWidth(), getHeight());
    }

    /**
     * @return the position
     */
    public Pos getPosition() {
        return position;
    }

    public double distance(Point p) {
        return p.distance(getPosition().getX(), getPosition().getY());
    }

    public ElementGroup getElementGroup() {
        return elementGroup;
    }

    /**
     * @param elementGroup the elementGroup to set
     */
    public void setElementGroup(ElementGroup elementGroup) {
        this.elementGroup = elementGroup;
    }

    public void addtoGroup(ElementGroup group) {
        removeFromGroup();
        group.getElements().add(this);
        elementGroup = group;
    }

    public void removeFromGroup() {
        if (elementGroup != null) {
            elementGroup.getElements().remove(this);
        }
    }

    /**
     * @return the creationTime
     */
    public java.sql.Time getCreationTime() {
        return creationTime;
    }

    public abstract void apply(ElementHandler u);

    /**
     * @return the rgbColor
     */
    public Color getColor() {
        return new Color(rgbColor);
    }

}
