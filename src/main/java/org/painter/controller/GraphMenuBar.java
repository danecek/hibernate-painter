/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.controller;

import javax.swing.JMenuBar;

/**
 *
 * @author danecek
 */
public class GraphMenuBar extends JMenuBar {

    public GraphMenuBar() {
        add(new FileMenu());
        add(new ModeMenu());
    }
}
