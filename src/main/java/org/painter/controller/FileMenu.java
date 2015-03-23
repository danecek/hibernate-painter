/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.controller;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JSeparator;

/**
 *
 * @author danecek
 */
public class FileMenu extends JMenu {

    ButtonGroup bg = new ButtonGroup();

    public FileMenu() {
        super("File");
        add(ClearAllAction.INST);
        add(new JSeparator());
        add(ExitAction.INST);
    }

}
