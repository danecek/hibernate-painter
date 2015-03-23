/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.controller;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.painter.App;
import org.painter.resource.AbstractFacade;
import org.painter.resource.PaintException;

/**
 *
 * @author danecek
 */
public class ClearAllAction extends GraphAction {

    public static ClearAllAction INST = new ClearAllAction();

    public ClearAllAction() {
        super("Clear All");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            AbstractFacade.getInstance().clearAll();
            App.INST.notifyObservers();
        } catch (PaintException ex) {
            App.INST.showError(ex);
            Logger.getLogger(ClearAllAction.class.getName()).log(Level.INFO, null, ex);
        }
    }

}
