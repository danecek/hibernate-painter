/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.view;

import org.painter.model.Circle;
import org.painter.model.Polyline;
import org.painter.model.Rectangle;

/**
 *
 * @author danecek
 */
public interface ElementHandler {

    void handle(Circle c);

    void handle(Rectangle c);

    void handle(Polyline c);

}
