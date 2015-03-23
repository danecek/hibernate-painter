/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.resource;

import java.awt.Point;
import java.util.List;
import org.painter.model.Element;
import org.painter.model.ElementGroup;
import org.painter.resource.hibernate.HibernateFacade;

/**
 *
 * @author danecek
 */
public abstract class AbstractFacade {

    private static AbstractFacade instance = new HibernateFacade();

    /**
     * @return the instance
     */
    public static AbstractFacade getInstance() {
        return instance;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(AbstractFacade aInstance) {
        instance = aInstance;
    }

    public abstract void clearAll() throws PaintException;

    public abstract List<ElementGroup> groups() throws PaintException;

    public abstract List<Element> elements() throws PaintException;

    public abstract void createElement(Element elm) throws PaintException;

    public abstract void createGroup(Point ref, Point point) throws PaintException;

    public abstract List<Element> orfans() throws PaintException;

}
