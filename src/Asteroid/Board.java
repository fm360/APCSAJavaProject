package Asteroid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;
import javax.imageio.ImageIO;

public class Board extends Canvas implements KeyListener, Runnable
{

	private Spaceship ship;
	private ArrayList <Asteroid> ast;
	private int chance = 980000;
	private ArrayList <PowerUp> pu;
	private ArrayList <Laser> l;
	private Laser laser;
	private ArrayList<Heart> hrt;
	
	private Random rand;
	private int lives;
	private int ammocnt;
	private int score;
	private int state = 1;
	
	private Background b;
	private int game;
	
	private long start;

	private boolean[] keys;
	private BufferedImage back;
	
	public Board()
	{
		setBackground(Color.black);

		keys = new boolean[6];
		
		rand = new Random();
		ship = new Spaceship(500,400,5);
		ast = new ArrayList<Asteroid>();
		pu = new ArrayList <PowerUp>();
		l = new ArrayList <Laser>();
		laser = new Laser (ship.getX(), ship.getY(), 3);
		hrt = new ArrayList<Heart>();
		
		lives = 3;
		ammocnt = 0;
		score = 0;
		b = new Background(0);
		game = 0;
		start = System.currentTimeMillis();

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

 public void update(Graphics window)
 {
	   paint(window);
 }

	public void paint( Graphics window )
	{
		Graphics2D twoDGraph = (Graphics2D)window;
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));
		Graphics graphToBack = back.createGraphics();
		
		
		if(game == 0){
			b.draw(graphToBack);
			graphToBack.setColor(Color.WHITE);
			graphToBack.setFont(new Font("Helvetica", Font.PLAIN, 45));
			graphToBack.drawString("ASTEROIDS",300,240);
			graphToBack.setFont(new Font("Helvetica", Font.PLAIN, 15));
			graphToBack.drawString("Instructions:", 250, 290);
			graphToBack.drawString("Use the arrow keys to move", 260, 320);
			graphToBack.drawString("Press Z to shoot lasers, if you have ammo", 260, 340);
			graphToBack.drawString("Don't collide with the asteroids", 260, 360);
			graphToBack.drawString("PRESS SPACE", 260, 380);

			if (state == 1){
				if (keys[5] == true) {
					state = 0;
					game = 1;
				}
			}
		}

		else if (game == 1){
			b = new Background(1);
			b.draw(graphToBack);
			graphToBack.setColor(Color.WHITE);
			graphToBack.drawString("LIVES: " + lives,25,50);
			graphToBack.drawString("AMMO: " + ammocnt,25,65);
			graphToBack.drawString("SCORE: " + score,25,85);

			
			ship.draw(graphToBack);
			
			if (state == 2){
				graphToBack.setColor(Color.RED);
				graphToBack.setFont(new Font("Helvetica", Font.PLAIN, 60));
				graphToBack.drawString("GAME OVER",220,240);
				graphToBack.setColor(Color.WHITE);
				graphToBack.setFont(new Font("Helvetica", Font.PLAIN, 45));
				graphToBack.drawString("Final Score: " + score,260,290);
			}
			
			//ASTEROID RNG
			long current = System.currentTimeMillis();
			
			if(((current - start) % 1000 == 1) && chance > 500000){
				chance = chance - 500;
			}
			
			int r = (int)(Math.random() * 800);
			int sz = rand.nextInt(3);
			int go = rand.nextInt(1000000);
			
			int aspd = 2 + rand.nextInt(2);
			if (go > chance){
				ast.add(new Asteroid(r,0,aspd,sz));
			}		

			for(int i = 0; i < ast.size(); i++){
				if (lives == 0){
					break;
				}
				ast.get(i).draw(graphToBack);
				if (ship.cTop(ast.get(i)) || ship.cBottom(ast.get(i)) || ship.cRight(ast.get(i)) || ship.cLeft(ast.get(i))){
					ast.get(i).setPos(1000, 1000);
					lives--;
				}
			}
			
			//POWERUP RNG
			int s = (int)(Math.random() * 800);
			int gopu = rand.nextInt(1000000);
			if (ammocnt < 5){
				if (gopu > 998500){
					pu.add(new PowerUp(s,0,2));
				}
			}
			for(int i = 0; i < pu.size(); i++){
				if (lives == 0){
					break;
				}
				pu.get(i).draw(graphToBack);
				if (ship.cpTop(pu.get(i)) || ship.cpBottom(pu.get(i))){
					pu.get(i).setPos(1000, 1000);
					ammocnt+= 5;
				}
			}
			
			//HEART RNG
			int t = (int)(Math.random() * 800);
			int gohrt = rand.nextInt(1000000);
			if (gohrt > 999000){
				hrt.add(new Heart(t,0,5));
			}
			for(int i = 0; i < hrt.size(); i++){
				if (lives == 0){
					break;
				}
				hrt.get(i).draw(graphToBack);
				if (ship.chTop(hrt.get(i)) || ship.chBottom(hrt.get(i))){
					hrt.get(i).setPos(1000, 1000);
					lives++;
				}
			}
		}

		
		if (ship.getX() < 0){
			ship.setX(0);
		}
		if (ship.getX() > 760){
			ship.setX(760);
		}
		if (ship.getY() < 0){
			ship.setY(0);
		}
		if (ship.getY() > 545){
			ship.setY(545);
		}
		
	
		if (lives == 0){
			state = 2;
			ship.explode();
		}
		

		if(keys[0] == true)
		{
			ship.move("W");
		}
		if(keys[1] == true)
		{
			ship.move("A");
		}
		if(keys[2] == true)
		{
			ship.move("S");
		}
		if(keys[3] == true)
		{
			ship.move("D");
		}
		if(keys[4] == true)
		{
			if((ammocnt > 0) && lives > 0){
				laser = new Laser (ship.getX() + 13, ship.getY() + 2, 6);
				l.add(laser);
				ammocnt--;
				keys[4] = false;
			}
		}
		
		
		for(Laser la : l){
			la.draw(graphToBack);
			for (int i = 0; i < ast.size(); i++){
				if (la.clTop(ast.get(i))){
					ast.get(i).setPos(2000,2000);
					la.setPos(3000,3000);
					score++;
				}
			}
		}
		
		twoDGraph.drawImage(back, null, 0, 0);
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z)
		{
			keys[4] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[5] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z)
		{
			keys[4] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[5] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e) { }
	

 public void run()
 {
 	try
 	{
 		while(true)
 		{
 		   Thread.currentThread().sleep(5);
          repaint();
       }
    }catch(Exception e)
    {
    }
	}
}

