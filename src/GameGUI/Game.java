/**
 *
 * @author Legion
 */
package GameGUI;
import controller.Board;
import Tile.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener
{
    Board game = new Board(4);
    static Game newGame = new Game();

    static JFrame frame = new JFrame( "2048" );

    static Color green;

    String gameBoard = game.toString();
    private boolean firstTime = true;
    private boolean gameOver = false;
    private boolean seeFinalBoard = false;
    

    /**
     * 
     * sets up the GUI with appropriate sizes and adds a Key Listener
     */
    public static void setUpGUI()
    {
        frame.addKeyListener( newGame );
        frame.getContentPane().add( newGame );
        frame.setSize( 750, 750 );
        frame.setVisible( true );
        frame.setResizable( false );
    }


    /**
     * Checks to see whether wasd or arrow keys are pressed and performs the
     * appropriate actions - updates the JFrame with every move
     * 
     * @param e
     *            KeyEvent to check
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        if (gameOver){
            if (e.getKeyCode() == KeyEvent.VK_ENTER )
            {
                game = new Board(4);
                game.spawn();
                game.spawn();
                frame.repaint();
                gameOver = false;
            } 
        } else if (firstTime) {
            e.getKeyChar();
            game = new Board(4);
            game.spawn();
            game.spawn();
            frame.repaint();
            firstTime = false;
        } else if ( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP )
            {
                game.up();
                game.spawn();
                gameBoard = game.toString();
                frame.repaint();
            }
            else if ( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN )
            {
                game.down();
                game.spawn();
                gameBoard = game.toString();
                frame.repaint();
            }
            else if ( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT )
            {
                game.left();
                game.spawn();
                gameBoard = game.toString();
                frame.repaint();
            }
            else if ( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                game.right();
                game.spawn();
                gameBoard = game.toString();
                frame.repaint();
            }
            else if ( e.getKeyCode() == KeyEvent.VK_ENTER )
            {
                game = new Board(4);
                game.spawn();
                game.spawn();
                frame.repaint();
            }
    }


    @Override
    public void keyReleased( KeyEvent e )
    {
        // Not Used
        
        

    }


    @Override
    public void keyTyped( KeyEvent e )
    {
        // Not Used

    }


    /**
     * Paints the GUI with a series of strings, the board, the tiles and ensures
     * they are repainted when the game is over
     * 
     * @param g
     *            Graphics parameter
     */
    @Override
    public void paint( Graphics g )
    {
        super.paint(g);
        
        Graphics2D g2 = (Graphics2D)g;
        g2.scale(1.5, 1.5);
        g2.drawString( "GAME 2048", 230, 25 );
        
        g2.drawString( "Score: " + game.getScore(),
            200 - 4 * String.valueOf( game.getScore() ).length(),
            40 );
        g2.drawString( "Highest Tile: " + game.getHighTile(),
            280 - 4 * String.valueOf( game.getHighTile() ).length(),
            40 );
        g2.drawString( "Press Enter to Start New Game", 200, 315 );
        g2.drawString( "Use Arrow Keys to move", 205, 335 );
        g2.drawString("@author: Le Thanh Binh", 205, 390 );
//        if ( game.blackOut() )
//        {
//            g2.drawString( "Press 'Enter' to restart", 200, 355 );
//        }
        g2.setColor( new Color(255,237,205));
        g2.fillRect( 140, 50, 250, 250 );
        for ( int i = 0; i < 4; i++ )
        {
            for ( int j = 0; j < 4; j++ )
            {
                drawTiles( g, game.board[i][j], j * 60 + 150, i * 60 + 60 );
            }
        }
        if (game.getHighTile() == 2048)
        {
            g2.setColor( Color.gray );
            g2.fillRect( 140, 50, 250, 250 );
            for ( int i = 0; i < 4; i++ )
            {
                for ( int j = 0; j < 4; j++ )
                {
                    g2.setColor( Color.DARK_GRAY );
                    g2.fillRoundRect( j * 60 + 150, i * 60 + 60, 50, 50, 5, 5 );
                    g2.setColor( Color.WHITE );
                    g.drawString( "WINNING", j * 60 + 160, i * 60 + 75 );
                }
            }
        }
        if (game.gameOver())
        {
            g2.drawString( "GAME OVER!", 230, 355 );
            gameOver = true;   
        }
    }

    /**
     * 
     * draws an individual tile - called from the paint method
     * 
     * @param g
     *            Graphics parameter
     * @param tile
     *            Tile to draw
     * @param x
     *            X coordinate to draw at
     * @param y
     *            Y coordinate to draw at
     */
    public void drawTiles( Graphics g, Tile tile, int x, int y )
    {
        int tileValue = tile.getValue();
        int length = String.valueOf(tileValue).length();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor( Color.white);
        g2.fillRoundRect( x, y, 50, 50, 5, 5 );
        g2.setColor( Color.PINK );
        if ( tileValue > 0 )
        {
            g2.setColor( tile.getTileColor());
            g2.fillRoundRect( x, y, 50, 50, 5, 5 );
            g2.setColor( Color.black );
            g.drawString( tile.toString(), x + 25 - 3 * length, y + 25 );
        }
    }
    public static void main (String[] args) {
        setUpGUI();
    }
}