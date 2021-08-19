/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Random;
import Tile.Tile;

/**
 *
 * @author Legion
 */
public class Board {

    final static int ROW = 4;
    final static int COL = 4;
    public int grids;
    private int border;
    public Tile[][] board;
    private int score;

    /* Default constructor for the Board - sets up a 4x4 matrix */
    public Board() {
        this.board = new Tile[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                    this.board[i][j] = new Tile(0);
            }
        }
    }

    /* Constructor for the Board - sets up a matrix with specified board*/
    public Board(Tile[][] board) {
        this.board = new Tile[ROW][COL];
        for (int i = 0; i < grids; i++) {
            for (int j = 0; j < grids; j++) {
                    board[i][j] = new Tile(0);
            }
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                try{
                    this.board[i][j].setValue(board[i][j].getValue());
                } catch (Exception e){
                    this.board[i][j].setValue(0);
                }
                    
            }
        }
        
    }

    /* Constructor for the Board - sets up a matrix with specified grid size */
    public Board(int grids) {
        this.grids = grids;
        this.board = new Tile[ROW][COL];
        for (int i = 0; i < grids; i++) {
            for (int j = 0; j < grids; j++) {
                    board[i][j] = new Tile(0);
            }
        }
    }
        /* Getter method that returns the board */
    public Tile[][] getBoard() {
        return this.board;
    }

    /* Getter method that returns the score */
    public int getScore() {
        return this.score;
    }

    /* Finds the highest tile on the board and returns it */
    public int getHighTile() {
        int max = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (max < board[i][j].getValue()) {
                    max = board[i][j].getValue();
                }
            }
        }
        return max;
    }

    /* Prints out the board onto the console - for testing purposes */
    public void print() {
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                System.out.print(board[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }

    /* Returns the board as a String - used in the GUI */
    @Override
    public String toString() {
        String s = "";
        for ( int i = 0; i < ROW; i++ )
        {
            for ( int j = 0; j < COL; j++ )
            {
                s += board[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    /* Spawns a 2 (or 4) at an empty space very time a move is made */
    public void spawn() {
        Random rd = new Random();
        int number = rd.nextInt(10);
        if (number <= 1) {
            number = 4;
        } else {
            number = 2;
        }
        int countEmpty = tileEmpty();
        if (countEmpty > 0) {
            int index = rd.nextInt(countEmpty) + 1;
            int indexCount = 0;
            for (int i = 0; i < COL; i++) {
                for (int j = 0; j < ROW; j++) {
                    if (board[i][j].getValue() == 0) {
                        indexCount++;
                        if (indexCount == index) {
                            board[i][j].setValue(number);
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * @return
     */
    public int tileEmpty() {
        int count = 0;
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++) {
                if (board[i][j].getValue() == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /* Checks to see if the board is completely blacked out and if it is, it will nudge the players to restart */
    public boolean blackOut() {
        int count = tileEmpty();
        if (count == 0) {
            return true;
        }
        return false;
    }

    /* Checks to see if the game is over - when the board is blacked out and none of the tiles can combine */
    public boolean gameOver() {
        int count = 0;
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                if ( board[i][j].getValue() > 0 )
                {
                    if ( i == 0 && j == 0 )
                    {
                        if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 0 && j == 3 )
                    {
                        if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 3 )
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 0 )
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 0 && ( j == 1 || j == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && ( j == 1 || j == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( j == 0 && ( i == 1 || i == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( j == 3 && ( i == 1 || i == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i][j - 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                        {
                            count++;
                        }
                    }
                    else
                    {
                        if ( board[i][j].getValue() != board[i][j - 1].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                        {
                            count++;
                        }
                    }
                }
            }
        }
        if ( count == 16 )
        {
            return true;
        }
        return false;

    }
    /*Check if 2 board are equals or not - will be used in gameOver method*/
    public boolean compare(Tile[][] board1){
        for (int i = 0; i < COL; i++) {
            for (int j = 0; j < ROW; j++){
                if (board[i][j].getValue() != board1[i][j].getValue()){
                    return false;
                    }
                }
            }
       return true; 
    }    
    /* Called when 'w' or up arrow is pressed - calls 'verticalMove' for every tile on the board with parameter 'up' */
    public void up() {
        
        for (int col = 0; col < COL; col++) {
            border = 0;
            for (int row = 0; row < ROW; row++) {
                if (board[row][col].getValue() != 0) {
                    if (border <= row) {
                        verticalMove(row, col, "up");
                    }
                }
            }
        }
    }

    /* Called when 's' or down arrow is pressed - calls 'verticalMove' for every tile on the board with parameter 'down' */
    public void down() {
        for (int col = 0; col < COL; col++) {
            border = ROW - 1;
            for (int row = ROW - 1; row >= 0; row--) {
                if (board[row][col].getValue() != 0) {
                    if (border >= row) {
                        verticalMove(row, col, "down");
                    }
                }
            }
        }
    }

    /* Called when 'd' or right arrow is pressed - calls 'horizontalMove' for every tile on the board with parameter 'right' */
    public void right() {
        for (int row = 0; row < ROW; row++) {
            border = COL - 1;
            for (int col = COL - 1; col >= 0; col--) {
                if (board[row][col].getValue() != 0) {
                    if (border >= col) {
                        horizontalMove(row, col, "right");
                    }
                }

            }
        }

    }

    /* Called when 'a' or left arrow is pressed - calls 'horizontalMove' for every tile on the board with parameter 'left' */
    public void left() {
        for (int row = 0; row < ROW; row++) {
            border = 0;
            for (int col = 0; col < COL; col++) {
                if (board[row][col].getValue() != 0) {
                    if (border <= col) {
                        horizontalMove(row, col, "left");
                    }
                }

            }
        }

    }

    /* Compares two tile's values together and if they are the same or if one is equal to 0 (plain tile) - their values are added (provided that the tiles we are comparing are two different tiles and they are moving towards the appropriate direction) - recursively moves through the row */
    public void horizontalMove(int row, int col, String direction) {
        Tile initial = board[row][border];
        Tile compare = board[row][col];

        if (initial.getValue() == 0 || initial.getValue() == compare.getValue()) {
            if (border < col || (border > col && direction.equals("right"))) {
                int addScore = initial.getValue() + compare.getValue();
                if (initial.getValue() != 0) {
                    this.score += addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        } else {
            if (direction.equals("right")) {
                border--;
            } else {
                border++;
            }
            horizontalMove(row, col, direction);
        }

    }

    /* Compares two tile's values together and if they are the same or if one is equal to 0 (plain tile) - their values are added (provided that the tiles we are comparing are two different tiles and they are moving towards the appropriate direction) - recursively moves through the column */
    public void verticalMove(int row, int col, String direction) {
        Tile initial = board[border][col];
        Tile compare = board[row][col];

        if (initial.getValue() == 0 || initial.getValue() == compare.getValue()) {
            if (row > border || (direction.equals("down") && (row < border))) {
                int addScore = initial.getValue() + compare.getValue();
                if (initial.getValue() != 0) {
                    this.score += addScore;
                }
                initial.setValue(addScore);
                compare.setValue(0);
            }
        } else {
            if (direction.equals("down")) {
                border--;
            } else {
                border++;
            }
            verticalMove(row, col, direction);
        }

    }

}
