//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Pot extends Sprite
{
    BufferedImage image, brokenImage;
    int px, py, burnTime;
    boolean isBroken = false;
    int numFrames = 0;

    public Pot(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.isBroken = false;
        this.burnTime = 0;
        this.w = 48;
        this.h = 48;
        if(image == null)
        {
            image = View.loadImage("images/pot.png");
        }
    }

    //Unmarshaling constructor
    Pot(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 48;
        h = 48;  
        if(image == null)
        {
            image = View.loadImage("images/pot.png");
        }
    }

    @Override 
    public String toString()
    {
	    return "Pot (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
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

    void Break()
    {
        isBroken = true;
        numFrames++;
        if(brokenImage == null)
        {
            brokenImage = View.loadImage("images/pot_broken.png");   
        }
        if(numFrames > 20)
        {
            numFrames = 0;
        }
    }

    void update()
    {
        if(isBroken == true)
        {
            Break();
        }
    }

    void drawYourself(Graphics g, int scroll_x, int scroll_y)
    {
        if(isBroken == true)
        {
            g.drawImage(brokenImage, x + scroll_x, y + scroll_y, null);
        }
        else
        {
            g.drawImage(image, x + scroll_x, y + scroll_y, null);
        }
    }

    @Override
    boolean isLink()
    {
        return false;
    }

    boolean isPot()
    {
        return true;
    }

    boolean isBoom()
    {
        return false;
    }

    boolean isTile()
    {
        return false;
    }
}
