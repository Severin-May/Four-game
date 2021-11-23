package assignment02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameWindow extends EntryWindow {
    
    private final int size;
    private final Model model;
    private final JLabel label;
    private final MenuWindow menuWindow;
    private final JButton[][] buttons; 
    
    public GameWindow(int size, MenuWindow menuWindow) {
        this.size = size;
        this.menuWindow = menuWindow;
        menuWindow.getWindowList().add(this);
        model = new Model(size);

        JPanel top = new JPanel();
        
        label = new JLabel();
        updateLabelText();
        
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());
        
        top.add(label);
        top.add(newGameButton);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
        buttons = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               addButton(mainPanel, i, j);   
            }
        }
        
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }
    
    public void addButton(JPanel panel, final int i, final int j) {
                final JButton newButton = new JButton();
                newButton.setText("0");
                newButton.setFocusable(false);
                buttons[i][j] = newButton;
                actionHandler(newButton, i, j);
                panel.add(newButton);
    }
      
    private void actionHandler(JButton button, final int i, final int j) {
            
            button.addActionListener(e -> {
            
            Cell newValue = model.step(i, j); 
            button.setText(String.valueOf(newValue.counter));
            if(newValue.counter < 4) 
            {
                 increaseNeighbours(i,j);
            }
            if(newValue.counter == 4) {
                 buttons[i][j].setBackground(newValue.player == Player.RED ? Color.red : Color.blue);
                 increaseNeighbours(i,j); 
                 //buttons[i][j].setEnabled(false);
            }
           
            
            updateLabelText();
            
            Player winner = model.findWinner();
            if (model.isOverGame()) {
                showGameOverMessage(winner);
                newGame();
            }
            
        });

    }
    
    private void showGameOverMessage(Player winner) {
        JOptionPane.showMessageDialog(this,
                "Game is over. Winner: " + winner.name());
        newGame();
    }
    
    private void newGame() {
        GameWindow newWindow = new GameWindow(size, menuWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        menuWindow.getWindowList().remove(this);
    }
    
    private void updateLabelText() {
        label.setText("Current player: "
                + model.getActualPlayer().name());
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        menuWindow.getWindowList().remove(this);
    }
    
    public void increaseNeighbours(final int row, final int column) {
      
        if(row+1 < size) {  
            setColor(row+1, column); 
        }
        if(row-1 >= 0) {  
            setColor(row-1, column);      
        }
        if(column+1 < size) {  
            setColor(row, column+1);         
        }
        if(column-1 >= 0) {  
            setColor(row, column-1); 
        }
   }
    
   public void setColor(int row, int column) {
       Cell newValue = model.getCell(row, column); 
       buttons[row][column].setText(String.valueOf(newValue.counter));            
       if(newValue.counter == 4) {buttons[row][column].setBackground(newValue.player.name() == "RED" ? Color.red : Color.blue); }
          
   }
}
