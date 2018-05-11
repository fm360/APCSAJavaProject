package Asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;

public class Asteroid extends MovingThing
{
	private int speed;
	private Image image;
	private int size;

	public Asteroid()
	{
		this(0,0,0);
		size = 0;
	}

	public Asteroid(int x, int y, int sz)
	{
		this(x,y,0,0);
	}

	public Asteroid(int x, int y, int s, int sz)
	{
		super(x, y);
		speed=s;
		size = sz;
		
		try
		{
			if (size == 0){
				image = ImageIO.read(new File("sast.png"));			
			}
			if (size == 1){
				image = ImageIO.read(new File("bast.png"));		
			}
			if (size == 2) {
				image = ImageIO.read(new File("hast.png"));
			}
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
		if (size == 0){
			return 40;
		}
		else if (size == 1){
			return 60;
		}
		else {
			return 75;
		}
	}
	public int getHeight(){
		if (size == 0){
			return 29;
		}
		else if (size == 1){
			return 51;
		}
		else {
			return 72;
		}
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
