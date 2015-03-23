/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.resource.hibernate;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import javax.persistence.EntityManager;
import org.painter.App;
import org.painter.resource.AbstractFacade;
import org.painter.model.Element;
import org.painter.model.ElementGroup;

/**
 *
 * @author daneelemsmceelemsmk
 */
public class JPAFacade extends AbstractFacade {

    public JPAFacade() {
    }

    @Override
    public void clearAll() {
        EntityManager em = App.EMF.createEntityManager();
        em.getTransaction().begin();
        List<Element> elems = em.createQuery("SELECT e FROM Element e", Element.class).getResultList();
        for (Element elm : elems) {
            em.remove(elm);
        }
        em.flush();
        em.createQuery("DELETE FROM ElementGroup").executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<ElementGroup> groups() {
        EntityManager em = App.EMF.createEntityManager();
        em.getTransaction().begin();
        List<ElementGroup> l = em.createQuery("SELECT g FROM ElementGroup g",
                ElementGroup.class).getResultList();
        em.getTransaction().commit();
        return l;
    }

    @Override
    public List<Element> elements() {
        EntityManager em = App.EMF.createEntityManager();
        em.getTransaction().begin();
        List<Element> l = em.createQuery("SELECT e FROM Element e",
                Element.class).getResultList();

        em.getTransaction().commit();
        return l;
    }

    @Override
    public void createElement(Element elm) {
        EntityManager em = App.EMF.createEntityManager();
        em.getTransaction().begin();
        em.persist(elm);
        em.getTransaction().commit();
    }

    @Override
    public void createGroup(Point ref, Point point) {
        Rectangle r = new Rectangle(ref);
        r.add(point);
        ElementGroup newGroup = new ElementGroup();
        EntityManager em = App.EMF.createEntityManager();
        em.getTransaction().begin();
        List<Element> elems = em.createQuery("SELECT e FROM Element e",
                Element.class).getResultList();
        for (Element elm : elems) {
            if (r.contains(elm.getOutline())) {
                ElementGroup oldGroup = elm.getElementGroup();
                if (oldGroup != null) {
                    //   em.merge(oldGroup);
                    oldGroup.getElements().remove(elm);
                    if (oldGroup.getElements().isEmpty()) {
                        em.remove(oldGroup);
                    }
                }
                elm.setElementGroup(newGroup);
                newGroup.getElements().add(elm);
            }
        }
        if (!newGroup.getElements().isEmpty()) {
            em.persist(newGroup);
        }
        em.getTransaction().commit();
    }

    @Override
    public List orfans() {
        EntityManager em = App.EMF.createEntityManager();
        em.getTransaction().begin();
        List<Element> l = em.createNamedQuery("orfans", Element.class).getResultList();
        em.getTransaction().commit();
        return l;
    }

}
