/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.controller;

import java.awt.event.ActionEvent;
import org.painter.App;

/**
 *
 * @author danecek
 */
public class ExitAction extends GraphAction {

    public static final ExitAction INST = new ExitAction();

    private ExitAction() {
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        App.INST.exit();
    }

}
