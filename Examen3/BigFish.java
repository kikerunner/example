package Examen3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class BigFish extends Rectangle {
	Image[] images;
	int position = 0;
	int velx, vely;
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;

	public BigFish(Image[] imgs, int vx, int vy) {
		super(0, 0, 100, 100);
		images = imgs;
		velx = vx;
		vely = vy;
	}

	public void draw(Graphics g, Applet a) {
		g.drawImage(images[position], x, y, width, height, a);
		position = (position + 1) % images.length;
	}

	public void refresh(int direccion) {
		if (direccion == RIGHT) {
			x += velx;
		}
		if (direccion == LEFT) {
			x -= velx;
		}
		if (direccion == UP) {
			y += vely;
		}
		if (direccion == DOWN) {
			y -= vely;
		}
		if (x < 0)
			x = 0;
		if (x >= 500)
			x = 500;
		if (y < 0)
			y = 0;
		if (y >= 500)
			y = 500;
	}
}
