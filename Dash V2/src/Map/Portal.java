package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Portal{

	private int x, y;
	private int width, height;
	private PortalType pt;
	
	public Portal(int x, int y, int width, int height, PortalType pt) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.pt = pt;
	}
	
	public Rectangle collision() {
		return new Rectangle(x + (width/2), y + (height/6), width, height * 3);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.pink);
		g2d.fillRoundRect(x + (width/2), y + (height/6), width, height * 3, 50, 50);
	}
	
	public void move(int speed) {
		x -= speed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public PortalType getPt() {
		return pt;
	}

	public void setPt(PortalType pt) {
		this.pt = pt;
	}
	
}
