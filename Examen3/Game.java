package Examen3;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Game extends Applet implements Runnable {
	Thread animation;
	Image image;
	Graphics dontSee;
	Image backGroundImage;
	Image bigFishFrame[], smallFishFrame[];
	Image BubbleFrame;

	List<SmallFish> smallFishes;
	BigFish fish;
	List<Bubble> bubbles;

	public static int NUMIMG = 3;
	public static int NUMSMALLFISHES = 10;
	public static int NUMBUBBLES = 5;
	int seconds = 0;
	int MarginReached = 0;

	public void init() {
		image = createImage(500, 500);
		dontSee = image.getGraphics();
		bigFishFrame = new Image[NUMIMG];
		smallFishFrame = new Image[NUMIMG];

		backGroundImage = getImage(getCodeBase(), "imagenes/background.jpg");
		for (int i = 0; i < NUMIMG; i++) {
			bigFishFrame[i] = getImage(getCodeBase(), "imagenes/fish" + i + ".png");
			smallFishFrame[i] = getImage(getCodeBase(), "imagenes/pez" + i + ".png");
		}
		BubbleFrame = getImage(getCodeBase(), "imagenes/burbuja.png");
		fish = new BigFish(bigFishFrame, 100, 100);

		smallFishes = new ArrayList<SmallFish>();
		bubbles = new ArrayList<Bubble>();
		for (int i = 0; i < NUMSMALLFISHES; i++) {
			smallFishes.add(new SmallFish(smallFishFrame, 20));
		}
		for (int i = 0; i < NUMBUBBLES; i++) {
			bubbles.add(new Bubble(BubbleFrame, (int) (Math.random() * 20 + 1)));
		}

		resize(500, 500);
	}

	public void start() {
		animation = new Thread(this);
		animation.start();
	}

	public void paint(Graphics g) {
		dontSee.setColor(Color.white);
		dontSee.fillRect(0, 0, 500, 500);
		for (int i = 0; i < bigFishFrame.length; i++) {
			dontSee.drawImage(image, 0, 0, this);
		}
		dontSee.drawImage(backGroundImage, 0, 0, 500, 500, this);

		fish.draw(dontSee, this);
		for (int i = 0; i < smallFishes.size(); i++) {
			smallFishes.get(i).draw(dontSee, this);
		}
		for (int i = 0; i < bubbles.size(); i++) {
			bubbles.get(i).draw(dontSee, this);
		}
		if (MarginReached == 10) {
			dontSee.setColor(Color.black);
			dontSee.drawString("You won", 250, 250);
			animation.stop();
		}
		if (smallFishes.size() == 0) {
			dontSee.setColor(Color.black);
			dontSee.drawString("You Lost", 250, 250);
			animation.stop();
		}

		g.drawImage(image, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public boolean keyDown(Event e, int tecla) {
		if (tecla == 97) {
			fish.refresh(BigFish.LEFT);
		}
		if (tecla == 100) {
			fish.refresh(BigFish.RIGHT);
		}
		if (tecla == 115) {
			fish.refresh(BigFish.UP);
		}
		if (tecla == 119) {
			fish.refresh(BigFish.DOWN);
		}
		return true;
	}

	public void run() {
		while (true) {
			seconds++;
			if (seconds == 10) {
				seconds = 0;
				smallFishes.add(new SmallFish(smallFishFrame, 20));
			}
			try {
				for (int i = 0; i < smallFishes.size(); i++) {
					smallFishes.get(i).refresh();
					if (smallFishes.get(i).x < -10) {
						smallFishes.remove(i);
						MarginReached++;
					}
					if (fish.intersects(smallFishes.get(i))) {
						smallFishes.remove(i);
					}
				}
				for (int i = 0; i < bubbles.size(); i++) {
					bubbles.get(i).refresh();
				}
			} catch (Exception e) {
			}
			repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			;
		}
	}

}