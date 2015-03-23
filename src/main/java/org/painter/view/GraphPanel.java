/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.painter.view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.painter.model.Element;
import org.painter.model.Polyline;
import org.painter.App;
import org.painter.model.ElemEnum;
import org.painter.model.ElementGroup;
import org.painter.resource.AbstractFacade;
import org.painter.resource.PaintException;

/**
 *
 * @author danecek
 */
public class GraphPanel extends JPanel implements Observer {

    public static final Stroke GROUP_STROKE = new BasicStroke(1, 0, 0, 1, new float[]{10, 4}, 0);

    private Element elm;
    private Rectangle r;
    private Point start;

    public GraphPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        requestFocus();
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics2D g = (Graphics2D) GraphPanel.this.getGraphics();
                Painter p = new Painter(g);
                g.setXORMode(App.XOR_COLOR);
                ElemEnum mode = App.INST.getMode();
                switch (mode) {
                    case Group:
                        g.setStroke(GROUP_STROKE);
                        g.draw(r);
                        Rectangle r2 = new Rectangle(start);
                        r2.add(e.getPoint());
                        g.draw(r2);
                        r = r2;
                        break;
                    case Polyline:
                        Polyline pl = (Polyline) elm;
                        pl.addPos(e.getX(), e.getY());
                        //       elm.paint(g);
                        elm.apply(p);
                        break;
                    default:
                        elm.apply(p);
                        //      elm.paint(g);
                        elm = mode.create(r.getLocation(), e.getPoint());
//                        elm.paint(g);
                        elm.apply(p);
                }
            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
                r = new Rectangle(start);
                elm = App.INST.getMode().create(start);
                requestFocusInWindow();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    if (App.INST.getMode() == ElemEnum.Group) {
                        AbstractFacade.getInstance().createGroup(start, e.getPoint());
                    } else {
                        if (elm instanceof Polyline) {
                            Polyline pl = (Polyline) elm;
                            pl.addPos(e.getX(), e.getY());
                        } else {
                            elm = App.INST.getMode().create(start, e.getPoint());
                        }
                        AbstractFacade.getInstance().createElement(elm);
                    }
                    App.INST.notifyObservers();
                } catch (PaintException ex) {
                    App.INST.showError(ex);
                }
            }
        }
        );
        App.INST.addObserver(this);
    }

    @Override

    protected void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            Painter p = new Painter((Graphics2D) g);
            for (ElementGroup group : AbstractFacade.getInstance().groups()) {
                p.handle(group);
              //  group.paint((Graphics2D) g.create());
                for (Element elem : group.getElements()) {
                    elem.apply(p);
                    //    elem.paint((Graphics2D) g);
                }
            }
            for (Element e : AbstractFacade.getInstance().orfans()) {
                e.apply(p);//  e.paint((Graphics2D) g.create());
            }
        } catch (PaintException ex) {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}
