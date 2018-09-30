package Examen3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Bubble extends Rectangle {
	Image image;
	int vely;

	public Bubble(Image img, int vy) {
		super();
		x = (int) (Math.random() * 500);
		y = (int) (Math.random() * 550);
		width = (int) (Math.random() * 100);
		height = width;
		image = img;
		vely = vy;

	}

	public void draw(Graphics g, Applet a) {
		g.drawImage(image, x, y, width, height, a);
	}

	public void refresh() {
		y -= vely;
		if (y < 0) {
			y = 520;
		}
	}
}
