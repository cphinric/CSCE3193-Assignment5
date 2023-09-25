//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Link extends Sprite{
    
    int px, py;
    double speed = 4;
    View view;

    BufferedImage[] link_images;
	int numImages = 50;
	int currentImage;
    boolean linkR = false; 
	boolean linkL = false;
	boolean linkU = false; 
	boolean linkD = false;

    @Override 
    public String toString()
    {
	    return "Link (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }

    Link(int x, int y)
    {
        this.x = 100;
        this.y = 100;
        if (link_images == null) {
            link_images = new BufferedImage[numImages];

            for (int i = 0; i < numImages; i++) {
                link_images[i] = View.loadImage("images/link" + (i + 1) + ".png");
            }
            currentImage = 0;
            this.h = link_images[currentImage].getHeight();
            this.w = link_images[currentImage].getWidth();
        }
    }

    void update()
    {

    }

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

    public void setPreviousPosition()
    {
        px = x;
        py = y;
    }

    public void getOutOfSprite(Tile p)
    {
        if((px + w) <= p.x && (x + w) >= p.x) //LEFT COLLISION FIXING
        {
            x = p.x - w; 
        }
        if(px >= (p.x + p.w) && x <= (p.x + p.w)) //RIGHT COLLISION FIXING
        {
            x = p.x + p.w;
        }
        if((py + h) <= p.y && (y + h) >= p.y) //VERTICAL COLLISION FIXING (TOP)
        {
            y = p.y - h;
        }
        if(py >= (p.y + p.h)) //VERTICAL COLLISION FIXING (BOTTOM)
        {
            y = p.y + p.h;
        }
    }

    public void updateImageNum(int dir) //int dir
    {
        if(dir == 0)
        {
            if ((currentImage < 38) && (currentImage >= 29))
            {
                currentImage++;
            }
            else{
                currentImage = 29;
            }
            linkR = true; 
            linkL = false;
            linkU = false; 
            linkD = false;
        }
        if(dir == 1)
        {
            if ((currentImage < 22) && (currentImage >= 13))
            {
                currentImage++;
            }
            else{
                currentImage = 13;
            }
            linkR = false; 
            linkL = true;
            linkU = false; 
            linkD = false;
        }
        if(dir == 2)
        {
            if ((currentImage < 49) && (currentImage >= 40))
            {
                currentImage++;
            }
            else{
                currentImage = 40;
            }
            linkR = false; 
            linkL = false;
            linkU = true; 
            linkD = false;
        }
        if(dir == 3)
        {
            
            if ((currentImage < 12) && (currentImage >= 3))
            {
                currentImage++;
            }
            else{
                currentImage = 3;
            }
            linkR = false; 
            linkL = false;
            linkU = false; 
            linkD = true;
        }
    }

    void drawYourself(Graphics g, int scroll_x, int scroll_y)
    {
        g.drawImage(link_images[currentImage], x + scroll_x, y + scroll_y, null);
    }

    @Override
    boolean isLink()
    {
        return true;
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
        return false;
    }
}
