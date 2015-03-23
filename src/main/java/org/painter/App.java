package org.painter;

import org.painter.resource.AbstractFacade;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Arrays;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.painter.model.ElemEnum;
import org.painter.resource.PaintException;
import org.painter.resource.hibernate.HibernateFacade;
import org.painter.resource.hibernate.JPAFacade;
import org.painter.view.MainFrame;

/**
 * Hello world!
 *
 */
public class App extends Observable {

    public static final Color XOR_COLOR = Color.RED;
    public static final BasicStroke BASIC_STROKE = new BasicStroke(1, 0, 0, 1, new float[]{2, 2}, 0);
    public static final App INST = new App();
    private ElemEnum mode = ElemEnum.Circle;
    private MainFrame mainFrame;
    public static final EntityManagerFactory EMF
            = Persistence.createEntityManagerFactory("PaintHibernate");
    public static final SessionFactory SF;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            SF = new Configuration().configure().
                    buildSessionFactory();
        } catch (HibernateException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE,
                    "Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private App() {
    }

    public static void main(String[] args) {
        
        ArgEnum argEnum = ArgEnum.HIBERNATE;
        try {
            if (args.length > 0) {
                argEnum = ArgEnum.valueOf(args[0].toUpperCase());
            }
            switch (argEnum) {
                case JPA:
                    AbstractFacade.setInstance(new JPAFacade());
                    break;
                case HIBERNATE:
                    AbstractFacade.setInstance(new HibernateFacade());
            }
            INST.setMainFrame(
                    new MainFrame());
            INST.notifyObservers();
        } catch (IllegalArgumentException ex) {
            System.out.println("arguments: " + Arrays.toString(ArgEnum.values()));
            System.exit(0);
        }

    }

    /**
     * @return the mainFrame
     */
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    /**
     * @param mainFrame the mainFrame to set
     */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void exit() {
        mainFrame.dispose();
    }

    public ElemEnum getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(ElemEnum mode) {
        this.mode = mode;
    }

    public void showError(Exception ex) {
        JOptionPane.showMessageDialog(mainFrame, ex);
    }

}
