package Examen3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class SmallFish extends Rectangle {
	Image[] images;
	int position = 0;
	int velx;

	public SmallFish(Image[] imgs, int vx) {
		super();
		x = (int) (Math.random() * 600);
		y = (int) (Math.random() * 500);
		width = 50;
		height = 50;
		images = imgs;
		velx = vx;

	}

	public void draw(Graphics g, Applet a) {
		g.drawImage(images[position], x, y, width, height, a);
		position = (position + 1) % images.length;
	}

	public void refresh() {
		x -= velx;
	}
}
