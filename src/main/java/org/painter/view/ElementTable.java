/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.painter.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author danecek
 */
public class ElementTable extends JScrollPane {

    public ElementTable() {
        super(new JTable(new ElementTableModel()));
    }    
    
}
