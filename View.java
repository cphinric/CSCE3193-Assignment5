//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

class View extends JPanel
{
	Model model;
	int scroll_x = 0;
	int scroll_y = 0;
	boolean printToScreen;
	
	//Constructor
	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		printToScreen = false;
	}

	boolean isRoom(int x, int y)
	{
		if ((x == 0) && (y == 0))
		{
			//Room 1
			return true;
		}
		if ((x == -700) && (y == 0))
		{
			//Room 2
			return true;
		}
		if ((x == -700) && (y == -500))
		{
			//Room 3
			return true;
		}
		if ((x == 0) && (y == -500))
		{
			//Room 4
			return true;
		}
		return false;
	}

	static BufferedImage loadImage(String filename) {
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File(filename));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		System.out.println("Successfully loaded " + filename + " image.");
		return im;
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < Model.sprites.size(); i++)
		{
			Model.sprites.get(i).drawYourself(g, scroll_x, scroll_y);
		}
		
		if(printToScreen)
		{
			g.setColor(new Color(255,0,0));
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString("EDIT MODE", 550, 430);
		}
	}
}

