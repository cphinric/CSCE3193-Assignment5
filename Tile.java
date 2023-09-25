//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Iterator;

public class Tile extends Sprite{

    BufferedImage tile_image;
    
    Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.w = 50;
        this.h = 50;
        
        if(tile_image == null)
        {
            tile_image = View.loadImage("images/tile.png");
        }
    }
    
    //Unmarshaling constructor
    Tile(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 50;
        h = 50;  
        if(tile_image == null)
        {
            tile_image = View.loadImage("images/tile.png");
        }
    }

    @Override 
    public String toString()
    {
	    return "Tile (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }

    //Mashal constructor
    @Override
    Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("x", this.x);
        ob.add("y", this.y);
        ob.add("w", this.w);
        ob.add("h", this.h);
        return ob;
    }

    void drawYourself(Graphics g, int scroll_x, int scroll_y)
    {
        g.drawImage(tile_image, x + scroll_x, y + scroll_y, null);
        
    }

    void update()
    {

    }

    boolean isTile(int mouse_x, int mouse_y)
    {
        Iterator<Sprite> iter = Model.sprites.iterator();
        while (iter.hasNext()) {
            Sprite t = iter.next();
            if ((mouse_x < ((t.x + 50)) && mouse_x > (t.x - 50)) && (mouse_y < t.y + 50 && mouse_y > t.y - 50)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    boolean isLink()
    {
        return false;
    }

    boolean isPot()
    {
        return false;
    }

    boolean isBoom()
    {
        return false;
    }

    boolean isTile()
    {
        return true;
    }
}
