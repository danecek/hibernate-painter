/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.view;

import java.awt.BorderLayout;
import org.painter.controller.GraphMenuBar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import org.painter.App;
import org.painter.controller.ToolBar;

/**
 *
 * @author danecek
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super("Graph");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new ToolBar(), BorderLayout.NORTH);

        setJMenuBar(new GraphMenuBar());
        JSplitPane js = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new GraphPanel(800, 600), new TabbedPane());
        add(js);
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                App.INST.exit();
            }

        });

    }

}
