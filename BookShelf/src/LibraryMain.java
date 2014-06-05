import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * The main class that will run the program.
 * @author Shi Kaiyuan
 * @version June 3, 2014
 *
 */
public final class LibraryMain {
    
    /**
     * Private constructor - guards against instantiation.
     */
    private LibraryMain() {
        
    }
    
    /**
     * Main method of a simulation driver.
     * @param aRgs command line arguments, assumes none
     */
    public static void main(String[] aRgs) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final LibraryFrame frame = new LibraryFrame();
                frame.setTitle("BookShelf Library Management System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}