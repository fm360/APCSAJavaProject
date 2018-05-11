package Asteroid;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;

public class PowerUp extends MovingThing
{
	private int speed;
	private Image image;

	public PowerUp()
	{
		this(0,0,0);
	}

	public PowerUp(int x, int y)
	{
		this(x,y,0);
	}

	public PowerUp(int x, int y, int s)
	{
		super(x, y);
		speed=s;
		
		try
		{
			image = ImageIO.read(new File("powerup.png"));			
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
	
	public int getWidth(){
		return 15;
	}
	public int getHeight(){
		return 24;
	}

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),null);
		this.move("S");
	}

	public String toString()
	{
		return "";
	}
}