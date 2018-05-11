package Asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Spaceship extends MovingThing implements Collidable
{
	private int speed;
	private Image image;

	public Spaceship()
	{
		this(0,0,0);
	}

	public Spaceship(int x, int y)
	{
		this(x,y,0);
	}

	public Spaceship(int x, int y, int s)
	{
		super(x, y);
		speed=s;
		try
		{
			image = ImageIO.read(new File("ship.png"));
		}
		catch(Exception e)
		{
			System.out.println("ERROR");
		}
	}

	public void explode(){
		speed = 0;
		try
		{
			image = ImageIO.read(new File("explode.png"));
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
	}
	
	//COLLIDABLE METHODS
	public boolean cRight(Object obj){
		Asteroid a = (Asteroid)obj;
		return getX() > a.getX() && getX() <= a.getX() + a.getWidth() && 
				(getY() >= a.getY() && getY() + 50 <= a.getY() + a.getHeight());
	}
	public boolean cLeft(Object obj){
		Asteroid a = (Asteroid)obj;
		return getX() < a.getX() && getX() + 40 >= a.getX() && 
				(getY() >= a.getY() && getY() + 50 <= a.getY() + a.getHeight());
	}
	public boolean cTop(Object obj){
		Asteroid a = (Asteroid)obj;
		return getY() + 50 >= a.getY() && getY() < a.getY() + a.getHeight() && 
				(getX() >= a.getX() && getX() + 40 <= a.getX() + a.getWidth());
	}
	public boolean cBottom(Object obj){
		Asteroid a = (Asteroid)obj;
		return getY() <= a.getY() + a.getHeight() && getY() + 50 > a.getY() && 
				(getX() >= a.getX() - 30 && getX() + 40 <= a.getX() + a.getWidth() + 30);
	}
	public boolean cpBottom(Object obj){
		PowerUp p = (PowerUp)obj;
		return getY() <= p.getY() + p.getHeight() && getY() + 50 > p.getY() && 
				(getX() >= p.getX() - 30 && getX() + 40 <= p.getX() + p.getWidth() + 30);
	}
	public boolean cpTop(Object obj){
		PowerUp p = (PowerUp)obj;
		return getY() + 50 >= p.getY() && getY() < p.getY() + p.getHeight() && 
				(getX() >= p.getX() - 30 && getX() + 40 <= p.getX() + p.getWidth() + 30);
	}
	
	public boolean chBottom(Object obj){
		Heart p = (Heart)obj;
		return getY() <= p.getY() + p.getHeight() && getY() + 50 > p.getY() && 
				(getX() >= p.getX() - 30 && getX() + 40 <= p.getX() + p.getWidth() + 30);
	}
	public boolean chTop(Object obj){
		Heart p = (Heart)obj;
		return getY() + 50 >= p.getY() && getY() < p.getY() + p.getHeight() && 
				(getX() >= p.getX() - 30 && getX() + 40 <= p.getX() + p.getWidth() + 30);
	}
	

	public String toString()
	{
		return super.toString() + getSpeed();
	}
}