/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.view;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.painter.model.Element;
import org.painter.App;
import org.painter.resource.AbstractFacade;
import org.painter.resource.PaintException;

/**
 *
 * @author danecek
 */
public class ElementTableModel extends AbstractTableModel implements Observer {

    List<Element> elems = new ArrayList<>();

    public ElementTableModel() {
        App.INST.addObserver(this);
    }

    @Override
    public int getRowCount() {
        return elems.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "X";
            case 2:
                return "Y";
            case 3:
                return "Discriminant";
            case 4:
                return "Width";
            case 5:
                return "Height";

        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Element e = elems.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return e.getId();
            case 1:
                return e.getPosition().getX();
            case 2:
                return e.getPosition().getY();
            case 3:
                return e.getClass().getSimpleName();
            case 4:
                return e.getWidth();
            case 5:
                return e.getHeight();

        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            elems = new ArrayList<>(AbstractFacade.getInstance().elements());
            
            Collections.sort(elems, new Comparator<Element>() {
                
                @Override
                public int compare(Element o1, Element o2) {
                    return o1.getCreationTime().compareTo(o2.getCreationTime());
                    
                }
            });
            fireTableDataChanged();
        } catch (PaintException ex) {
            Logger.getLogger(ElementTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
