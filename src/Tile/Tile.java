/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tile;

import java.awt.Color;

/**
 *
 * Tile deal with the individual tiles, the smallest pieces of the Board.
 * Each title contain an integer and a color.
 * @author Legion
 */
public class Tile {
    private int value ;
    private Color tileColor;

    /* Constructs a basic tile with a value of 0 */
    public Tile() {
        this.value = 0;
    }

    /* Constructs a tile with a value of number */
    public Tile(int value) {
        this.value = value;
    }
    
    /* Gets the tile's value */
    public int getValue() {
        return value;
    }
    
    /* Sets the tile's value - used when adding two tiles together */
    public void setValue(int value) {
        this.value = value;
    }


    /* Sets the tile's color based on its value */
    public void setColor() {
        ///
        if ( this.value == 2 )
        {
            tileColor = new Color( 238, 228, 218 );
        }
        else if ( this.value == 4 )
        {
            tileColor = new Color( 237, 224, 200 );
        }
        else if ( this.value == 8 )
        {
            tileColor = new Color( 242, 177, 121 );
        }
        else if ( this.value == 16 )
        {
            tileColor = new Color( 245, 149, 99 );
        }
        else if ( this.value == 32 )
        {
            tileColor = new Color( 246, 124, 95 );
        }
        else if ( this.value == 64 )
        {
            tileColor = new Color( 246, 94, 59 );
        }
        else if ( this.value == 128 )
        {
            tileColor = new Color( 237, 207, 114 );
        }
        else if ( this.value == 256 )
        {
            tileColor = new Color( 237, 204, 97 );
        }
        else if ( this.value == 512 )
        {
            tileColor = new Color( 237, 200, 80 );
        }
        else if ( this.value == 1024 )
        {
            tileColor = new Color( 237, 197, 63 );
        }
        else
        {
            tileColor = new Color( 237, 194, 46 );
        }
        
        
    }

    /* Gets the tile's color */

    public Color getTileColor() {
        setColor();
        return tileColor;
    }
    
    /* Represents the tile as a String - used in the GUI */
    @Override
    public String toString() {
        return "" + value;
    }
    
 
}
