/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.controller;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 *
 * @author danecek
 */
public class ToolBar extends Box {

    public ToolBar() {
        super(BoxLayout.LINE_AXIS);
        add(new JButton(ExitAction.INST));
        add(Box.createHorizontalStrut(3));
        add(new JButton(ClearAllAction.INST));
        add(Box.createHorizontalStrut(5));
        ButtonGroup bg = new ButtonGroup();
        for (Mode ma : Mode.MODES) {
            JToggleButton b = (JToggleButton) this.add(new JToggleButton(ma.toString()));
            b.setModel(ma.getButtonModel());
            bg.add(b);
        }
    }

}
