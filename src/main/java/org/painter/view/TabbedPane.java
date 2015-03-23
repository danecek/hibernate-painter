/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.view;

import javax.swing.JTabbedPane;

/**
 *
 * @author danecek
 */
public class TabbedPane extends JTabbedPane {

    public TabbedPane() {
        add("Elements", new ElementTable());
        add("Groups", new GroupTable());
    }

}
