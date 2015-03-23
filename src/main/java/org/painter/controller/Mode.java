/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import org.painter.App;
import org.painter.model.ElemEnum;

/**
 *
 * @author danecek
 */
public class Mode {

    public static final Mode[] MODES = {
        new Mode(ElemEnum.Circle),
        new Mode(ElemEnum.Rectangle),
        new Mode(ElemEnum.Polyline),
        new Mode(ElemEnum.Group),};

    private final ElemEnum ee;
    private final ButtonModel buttonModel;

    public Mode(final ElemEnum ee) {
        this.ee = ee;
        buttonModel = new DefaultButtonModel();
        buttonModel.setSelected(ee == ElemEnum.Circle);
        buttonModel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                App.INST.setMode(ee);
                for (Mode m : MODES) {
                    m.getButtonModel().setSelected(m.getButtonModel() == getButtonModel());
                }
            }
        });
    }

    @Override
    public String toString() {
        return ee.toString();
    }

    /**
     * @return the buttonModel
     */
    public ButtonModel getButtonModel() {
        return buttonModel;
    }

}
