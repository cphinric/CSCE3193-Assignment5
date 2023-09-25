//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Boom extends Sprite{
    BufferedImage[] boom_image;
    double hor_velocity = 0;
    double vert_velocity = 0;
    int numImages = 4;
    Link link;
    int d;
    int currentImage;

    public Boom (int x, int y, int d)
    {
        this.x = x;
        this.y = y;
        this.d = d;
        if (boom_image == null) {
            boom_image = new BufferedImage[numImages];

            for (int i = 0; i < numImages; i++) {
                boom_image[i] = View.loadImage("images/boomerang" + (i + 1) + ".png");
            }
            currentImage = 0;
            this.h = boom_image[currentImage].getHeight();
            this.w = boom_image[currentImage].getWidth();
        }
    }

    void update()
    {
        x += hor_velocity;
        y += vert_velocity;
        if(d == 0)
        {
            hor_velocity = 3;
        }
        if(d == 1)
        {
            hor_velocity = -3;
        }
        if(d == 2)
        {
            vert_velocity = -3;
        }
        if(d == 3)
        {
            vert_velocity = 3;
        }
        updateImageNum();
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

    @Override 
    public String toString()
    {
	    return "Boomerang (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }

    void drawYourself(Graphics g, int scroll_x, int scroll_y)
    {
        g.drawImage(boom_image[currentImage], x + scroll_x, y + scroll_y, null);
    }

    public void updateImageNum()
    {
        if (currentImage < 3)
        {
            currentImage++;
        }
        else{
            currentImage = 0;
            }
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
        return true;
    }

    boolean isTile()
    {
        return false;
    }
}
