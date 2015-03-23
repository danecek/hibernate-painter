/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.controller;

import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author danecek
 */
public class ModeMenu extends JMenu {

    public ModeMenu() {
        super("Mode");
        for (Mode ma : Mode.MODES) {
            JRadioButtonMenuItem b = (JRadioButtonMenuItem) this.add(new JRadioButtonMenuItem(ma.toString()));
            b.setModel(ma.getButtonModel());
        }
    }

}
