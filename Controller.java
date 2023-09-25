//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	Json json;
	boolean leftPressed, rightPressed, upPressed, downPressed;
	boolean arrowRight;
	boolean arrowLeft;
	boolean arrowUp;
	boolean arrowDown;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyEsc;
	boolean keyQ;
	boolean keyS;
	boolean keyControl;

	Controller(Model m)
	{
		model = m;
		Json lJson = Json.load("map.json");
			model.unmarshal(lJson);
	}

	public void editString()
	{
		
	}

	void setView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
		model.setPosition(e.getX() - view.scroll_x, e.getY() - view.scroll_y);
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_D: keyRight = true; break;
			case KeyEvent.VK_A: keyLeft = true; break; 
			case KeyEvent.VK_W: keyUp = true; break;
			case KeyEvent.VK_X: keyDown = true; break;
			case KeyEvent.VK_ESCAPE: keyEsc = true; break;
			case KeyEvent.VK_Q: keyQ = true; break;
			case KeyEvent.VK_RIGHT: arrowRight = true; break;
			case KeyEvent.VK_LEFT: arrowLeft = true; break;
			case KeyEvent.VK_UP: arrowUp = true; break;
			case KeyEvent.VK_DOWN: arrowDown = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_ESCAPE: keyEsc = false; break;
			case KeyEvent.VK_Q: keyQ = false; break;
			case KeyEvent.VK_RIGHT: arrowRight = false; break;
			case KeyEvent.VK_LEFT: arrowLeft = false; break;
			case KeyEvent.VK_UP: arrowUp = false; break;
			case KeyEvent.VK_DOWN: arrowDown = false; break;
			case KeyEvent.VK_CONTROL: keyControl = false;model.addBoom(model.link.x, model.link.y);break;
		}
		char l = Character.toLowerCase(e.getKeyChar());

		if(l == 's')
		{
			Json sJson = model.marshal();
			sJson.save("map.json");
			System.out.println("Saving...");
		}

		if(l == 'l')
		{
			Json lJson = Json.load("map.json");
			model.unmarshal(lJson);
			System.out.println("Loading...");
		}
		if(l == 'e')
		{
			view.printToScreen = !view.printToScreen;

			if (Model.editMode == false)
			{
				Model.editMode = true;
				System.out.println("EDIT MODE");
			}
			else
			{
				Model.editMode = false;
				
			}
		}
		if(l == 'p')
		{
			if(Model.editMode == true)
			{
				if (Model.potMode == false)
				{
					Model.potMode = true;
					System.out.println("Pot Mode Enabled");
				}
				else
				{
					Model.potMode = false;
					System.out.println("Pot Mode Disabled");
				}
			}
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(model.link.x > 650)
		{
			view.scroll_x = -700;
		}
		if(model.link.x < 650)
		{
			view.scroll_x = 0;
		}
		if(model.link.y > 450)
		{
			view.scroll_y = -500;
		}
		if(model.link.y < 450)
		{
			view.scroll_y = 0;
		}
		model.link.setPreviousPosition();
		if(arrowRight)
		{
			if(!arrowUp && !arrowDown)
			{
				model.link.updateImageNum(0);
				model.link.x += model.link.speed;
			}

		}
		if(arrowLeft)
		{
			if(!arrowUp && !arrowDown)
			{
				model.link.updateImageNum(1);
				model.link.x -= model.link.speed;
			}
		}
		if(arrowUp)
		{
			model.link.updateImageNum(2);
			model.link.y -= model.link.speed;
		}
		if(arrowDown)
		{
			model.link.updateImageNum(3);
			model.link.y += model.link.speed;
		}
		if(keyEsc) System.exit(0);
		if(keyQ) System.exit(0);
	}
}
