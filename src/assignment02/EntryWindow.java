
package assignment02;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EntryWindow extends JFrame {
    
    public EntryWindow() {
        setTitle("My game");
        setSize(400, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }

        });
        
        URL url = Window.class.getResource("icon.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));
    }
    
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?",
                "Quit game", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            doUponExit();
        }
    }
        
    protected void doUponExit() {
        this.dispose();
    }
}
