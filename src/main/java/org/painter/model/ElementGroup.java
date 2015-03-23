/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author danecek
 */
@Entity
public class ElementGroup implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "elementGroup", fetch = FetchType.EAGER)
    private Set<Element> elements = new HashSet<>();

    public ElementGroup() {
    }

    public Long getId() {
        return id;
    }

    /**
     * @return the elements
     */
    public Set<Element> getElements() {
        return elements;
    }

    public int size() {
        return elements.size();
    }

//    public void paint(Graphics2D g) {
//        java.awt.Rectangle outline = new java.awt.Rectangle(0, 0, -1, -1);
//        for (Element e : elements) {
//            outline.add(e.getOutline());
//        }
//        g.setColor(new Color(255, 0, 0, 30));
//        g.fillRoundRect(outline.x - 10, outline.y - 10,
//                outline.width + 20, outline.height + 20, 20, 20);
//    }

    @Override
    public String toString() {
        return "ElementGroup{" + "id=" + id + ", elements=" + elements + '}';
    }

}
