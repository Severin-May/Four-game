package assignment02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MenuWindow extends EntryWindow {
    
    private List<GameWindow> gameWindows = new ArrayList<>();
    
    public MenuWindow() {
        JButton small = new JButton();
        small.setText("3 x 3");
        small.addActionListener(getActionListener(3));
        
        JButton medium = new JButton();
        medium.setText("5 x 5");
        medium.addActionListener(getActionListener(5));
        
        JButton large = new JButton();
        large.setText("7 x 7");
        large.addActionListener(getActionListener(7));
        
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
    }   
    
    private ActionListener getActionListener(final int size) {
        return new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow gameWindow = new GameWindow(size, MenuWindow.this);
                gameWindow.setVisible(true);
                gameWindows.add(gameWindow);
            }
            
        };
    }
    
    public List<GameWindow> getWindowList() {
        return gameWindows;
    }
    
    @Override
    protected void doUponExit() {
        System.exit(0);
    }
    
}
