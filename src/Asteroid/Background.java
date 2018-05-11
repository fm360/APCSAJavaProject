package Asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Background
{
	private Image image;

	public Background(int x)
	{
		try
		{
			if(x == 0){ 
				image = ImageIO.read(new File("maxresdefault.jpg"));			
			}
			if(x == 1){ 
				image = ImageIO.read(new File("maxresdefault.jpg"));			
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR");
		}
	}


	public void draw( Graphics window )
	{
		window.drawImage(image,0,0,null);
	}

	public String toString()
	{
		return "";
	}
}