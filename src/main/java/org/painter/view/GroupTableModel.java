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
import org.painter.App;
import org.painter.model.ElementGroup;
import org.painter.resource.AbstractFacade;
import org.painter.resource.PaintException;

/**
 *
 * @author danegcegk
 */
public class GroupTableModel extends AbstractTableModel implements Observer {

    List<ElementGroup> groups = new ArrayList<>();

    public GroupTableModel() {
        App.INST.addObserver(this);
    }

    @Override
    public int getRowCount() {
        return groups.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "Size";

        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ElementGroup eg = groups.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return eg.getId().toString();
            case 1:
                return eg.getElements().size();

        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            groups = new ArrayList<>(AbstractFacade.getInstance().groups());

            Collections.sort(groups, new Comparator<ElementGroup>() {

                @Override
                public int compare(ElementGroup o1, ElementGroup o2) {
                    return o1.getId().compareTo(o2.getId());

                }
            });
            fireTableDataChanged();
        } catch (PaintException ex) {
            Logger.getLogger(GroupTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
