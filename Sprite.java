//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism

//import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class Sprite
{
    int x, y, w, h;

    abstract void drawYourself(Graphics g, int scroll_x, int scroll_y);
    abstract void update();
    abstract Json marshall();

    abstract boolean isTile();

    abstract boolean isBoom();

    abstract boolean isPot();

    abstract boolean isLink();

    @Override 
    public String toString()
    {
	    return "Sprite (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }

}