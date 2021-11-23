package assignment02;

import java.util.ArrayList;

public class Model {
    
    private int size;
    private Player actualPlayer;
    private Cell[][] table;

    public Model(int size) {
        this.size = size;
        actualPlayer = Player.RED;

        table = new Cell[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                Cell cell = new Cell(0, Player.NOONE);
                table[i][j] = cell;
                //System.out.print(table[i][j].counter);
            }
        }
    }
    
    public Cell step(int row, int column) {
        rangeChecker(row, column);
        if(row+1 < size) {rangeChecker(row+1, column);}
        if(row-1 >= 0) {rangeChecker(row-1, column);}
        if(column+1 < size) rangeChecker(row, column+1);
        if(column-1 >= 0) rangeChecker(row, column-1);
                
        if (actualPlayer == Player.RED) {
            actualPlayer = Player.BLUE;
        } else {
            actualPlayer = Player.RED;
        }
        return table[row][column];
        
    }
    
    public Player findWinner() {
        int redCounter = 0;
        int blueCounter = 0;
      
        if(isOverGame()) {
            for (int i = 0; i < table.length; i++) {
                for(int j = 0; j < table.length; j++) {
                    if(table[i][j].player.name() == "RED") redCounter++;
                    else blueCounter++;
                }
            }
        }    
        if(redCounter == blueCounter) return Player.NOONE; 
        else return blueCounter > redCounter ? Player.BLUE : Player.RED;    
    }
    
    public boolean isOverGame() {
        boolean areAllNotNoone = true;
        for (int i = 0; i < table.length; i++) {
            for(int j = 0; j < table.length; j++) {
                if(table[i][j].player == Player.NOONE) {
                    areAllNotNoone = false;
                }
            }
        }
        return areAllNotNoone;
    }
    
    public Player getActualPlayer() {
        return actualPlayer;
    }
    
    public void rangeChecker(int row, int column) {
        
        if(table[row][column].counter < 4) {
            table[row][column].counter++;
        }
        if(table[row][column].counter == 4 && table[row][column].player.name() == "NOONE") {
            //System.out.println("wewhewhe");
            table[row][column].player = actualPlayer;
        }
        
    }
    
    public Cell getCell(int y, int x) {
//        for (int i = 0; i < size; ++i) {
//            for (int j = 0; j < size; ++j) {
//                System.out.print(table[i][j].counter);
//            }
//            System.out.println();
//        }
        return table[y][x];
        //System.out.print(table);
    }
    
}

