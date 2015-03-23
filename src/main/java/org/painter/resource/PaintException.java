/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.resource;

/**
 *
 * @author danecek
 */
public class PaintException extends Exception {

    public PaintException() {
    }

    public PaintException(String message) {
        super(message);
    }

    public PaintException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaintException(Throwable cause) {
        super(cause);
    }

}
