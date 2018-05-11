package Asteroid;

public interface Collidable
{
	boolean cLeft(Object obj);
	boolean cRight(Object obj);
	boolean cTop(Object obj);
	boolean cBottom(Object obj);
}