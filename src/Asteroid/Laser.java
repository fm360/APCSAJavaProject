package Asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Laser extends MovingThing
{
	private int speed;
	private Image image;

	public Laser()
	{
		this(0,0,0);
	}

	public Laser(int x, int y)
	{
		this(x,y,0);
	}

	public Laser(int x, int y, int s)
	{
		super(x,y);
		speed = s;
		
		try
		{
			image = ImageIO.read(new File("laser.png"));			
		}
		catch(Exception e)
		{
			System.out.println("ERROR");
		}
	}

	public void setSpeed(int s)
	{
		speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),null);
		this.move("W");
	}

	public boolean clTop(Object obj){
		Asteroid a = (Asteroid)obj;
		return getY() + 20 >= a.getY() && getY() < a.getY() + a.getHeight() && 
				(getX() >= a.getX() && getX() + 10 <= a.getX() + a.getWidth());
	}
	
	public String toString()
	{
		return "";
	}
}