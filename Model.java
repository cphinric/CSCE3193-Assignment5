//Parker Hinrichs (03.31.2023) Assignment 5: Polymorphism

import java.util.ArrayList;

class Model
{
	int mouse_x;
	int mouse_y;
	Link link;
	static boolean editMode = false;
	static boolean potMode = false;
	static ArrayList<Sprite> sprites;
	boolean Right;
	boolean Left;
	boolean Up;
	boolean Down;


	//Constructor
	Model()
	{
		sprites = new ArrayList<Sprite>();
		link = new Link(0, 0);
	}

	//Unmarshaling constructor
	public void unmarshal(Json ob)
	{
		sprites = new ArrayList<Sprite>();
		sprites.add(link);
		Json tmpList = ob.get ("sprites");
			for(int i = 0; i < tmpList.size(); i++)
			{
				sprites.add(new Tile(tmpList.get(i)));
			}
		Json tmpListP = ob.get ("pots");
			for(int i = 0; i < tmpListP.size(); i++)
			{
				sprites.add(new Pot(tmpListP.get(i)));
			}
	}

	//Marshal Constructor
	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpList = Json.newList();
		ob.add("sprites", tmpList);
		Json tmpListPots = Json.newList();
		ob.add("pots", tmpListPots);
		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isTile())
			{
				tmpList.add (((Tile)sprites.get(i)).marshall());
			}
			if(sprites.get(i).isPot())
			{
				tmpListPots.add (((Pot)sprites.get(i)).marshall());
			}
		}
		return ob;
	}
	
	public void addBoom(int x, int y)
	{
		if(link.linkR == true)
		{
			sprites.add(new Boom((x + link.w), y + (link.h/2), 0));
			Right = true;

		}
		if(link.linkL == true)
		{
			sprites.add(new Boom(x, y + (link.h/2), 1));
			Left = true;
		}
		if(link.linkU == true)
		{
			sprites.add(new Boom(x + (link.w/2), y, 2));
			Up = true;
		}
		if(link.linkD == true)
		{
			sprites.add(new Boom(x + (link.w/2), (y + link.h), 3));
			Down = true;
		}
	}

	public void update()
	{
		for(int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).update();
			if (isCollision(sprites.get(i), link))
			{
				if(sprites.get(i).isTile())
				{
					link.getOutOfSprite((Tile)sprites.get(i));
				}
				if(sprites.get(i).isPot())
				{
					if(((Pot)sprites.get(i)).isBroken == false)
					{
						if(link.linkR == true)
						{
							((Pot)sprites.get(i)).x = (link.x + link.w);
						}
						if(link.linkL == true)
						{
							((Pot)sprites.get(i)).x = link.x - ((Pot)sprites.get(i)).w;
						}
						if(link.linkU == true)
						{
							((Pot)sprites.get(i)).y = link.y - ((Pot)sprites.get(i)).h;
						}
						if(link.linkD == true)
						{
							((Pot)sprites.get(i)).y = (link.y + link.h);
						}
					}
					
				}
			}
			if (sprites.get(i).isPot())
			{
				for(int j = 0; j < sprites.size(); j++)
				{
					if(isCollision(sprites.get(i), sprites.get(j)))
					{
						if(sprites.get(j).isTile())
						{
							((Pot)sprites.get(i)).Break();
							break;
						}
						if(sprites.get(j).isBoom())
						{
							((Pot)sprites.get(i)).Break();
							sprites.remove(j);
							break;
						}
					}
				}
				if(((Pot)sprites.get(i)).numFrames >= 20)
				{
					sprites.remove(i);
					break;
				}
			}
			if(sprites.get(i).isBoom())
			{
				for(int j = 0; j < sprites.size(); j++)
				{
					if(isCollision(sprites.get(i), sprites.get(j)))
					{
						if(sprites.get(j).isTile())
						{
							sprites.remove(i);
							break;
						}
					}
				}
			}
		}
		
	}

	boolean isCollision(Sprite t, Sprite b)
	{
		if((b.x + b.w) <= t.x)
		{
			return false;
		}
		if(b.x >= (t.x + t.w))
		{
			return false;
		}
		if((b.y + b.h) <= t.y)
		{
			return false;
		}
		if(b.y >= (t.y + t.h))
		{
			return false;
		}
		return true;
		
	}

	public void setPosition(int x, int y)
	{
		mouse_x = x - ((x % 50 + 50) % 50);
		mouse_y = y - ((y % 50 + 50) % 50);
		boolean tileFound = false;
		Tile t = new Tile(mouse_x, mouse_y);
		Pot p = new Pot(mouse_x, mouse_y);

		if (editMode == true && potMode == false)
		{
			for (int i = 0; i < sprites.size(); i++)
			{
				if(sprites.get(i).isTile())
				{
					if (((Tile)sprites.get(i)).isTile(mouse_x, mouse_y) == true)
					{
						tileFound = true;
						break;
					}
				}
			}
			if (!tileFound)
			{
				sprites.add(t);
			}
		}
		if (potMode == true)
			{
				sprites.add(p);
			}
	}
}