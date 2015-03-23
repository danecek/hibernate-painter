/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.resource.hibernate;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import static org.painter.App.SF;
import org.painter.resource.AbstractFacade;
import org.painter.model.Element;
import org.painter.model.ElementGroup;
import org.painter.resource.PaintException;

/**
 *
 * @author daneelemsmceelemsmk
 */
public class HibernateFacade extends AbstractFacade {

    public HibernateFacade() {
    }

    @Override
    public void clearAll() throws PaintException {
        Session s = SF.getCurrentSession();
        try {
            s.beginTransaction();
            List<Element> elems = s.createQuery("SELECT e FROM Element e").list();
            for (Element elm : elems) {
                s.delete(elm);
            }
            s.flush();
            s.createQuery("DELETE FROM ElementGroup").executeUpdate();
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            s.getTransaction().rollback();
            throw new PaintException(ex);
        }
    }

    @Override
    public List<ElementGroup> groups() throws PaintException {
        Session s = SF.getCurrentSession();
        try {
            s.beginTransaction();
            List<ElementGroup> l = s.createQuery("SELECT g FROM ElementGroup g").list();
            s.getTransaction().commit();
            return l;
        } catch (HibernateException ex) {
            s.getTransaction().rollback();
            throw new PaintException(ex);
        }
    }

    @Override
    public List<Element> elements() throws PaintException {
        Session s = SF.getCurrentSession();
        try {
            s.beginTransaction();
            List<Element> l = s.createQuery("SELECT e FROM Element e").list();
            s.getTransaction().commit();
            return l;
        } catch (HibernateException ex) {
            s.getTransaction().rollback();
            throw new PaintException(ex);
        }
    }

    @Override
    public void createElement(Element elm) throws PaintException {
        Session s = SF.getCurrentSession();
        try {
            s.beginTransaction();
//            System.out.println(elm);
            s.save(elm);
//            System.out.println(elm);
            s.getTransaction().commit();
//            System.out.println(elm);
        } catch (HibernateException ex) {
            s.getTransaction().rollback();
            throw new PaintException(ex);
        }
    }

    @Override
    public void createGroup(Point ref, Point point) throws PaintException {
        Rectangle r = new Rectangle(ref);
        r.add(point);
        ElementGroup newGroup = new ElementGroup();
        Session s = SF.getCurrentSession();
        try {
            s.beginTransaction();
            List<Element> elems = s.createQuery("SELECT e FROM Element e").list();
            for (Element elm : elems) {
                if (r.contains(elm.getOutline())) {
                    ElementGroup oldGroup = elm.getElementGroup();
                    if (oldGroup != null) {
                        oldGroup.getElements().remove(elm);
                        if (oldGroup.getElements().isEmpty()) {
                            s.delete(oldGroup);
                        }
                    }
                    elm.setElementGroup(newGroup);
                    newGroup.getElements().add(elm);
                }
            }
            if (!newGroup.getElements().isEmpty()) {
                s.save(newGroup);
            }
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            s.getTransaction().rollback();
            throw new PaintException(ex);
        }
    }

    @Override
    public List orfans() throws PaintException {
        Session s = SF.getCurrentSession();
        try {
            s.beginTransaction();
            List<Element> l = s.getNamedQuery("orfans").list();
            s.getTransaction().commit();
            return l;
        } catch (HibernateException ex) {
            s.getTransaction().rollback();
            throw new PaintException(ex);
        }
    }

}
