package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block {
	
	int x, y;
	int width = 100;
	int height = 100;
	Color color;
	BlockType bt;
	
	public Block(int x, int y, Color color, BlockType bt) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.bt = bt;
	}
	
	public Rectangle collision() {
		return new Rectangle(x, y, width, height);
	}
	
	public Triangle tcollision() {
		return new Triangle(x, y, width, height);
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		if(bt == BlockType.BLOCK) {
			g.fillRect(x, y, width, height);
		}
		if(bt == BlockType.SPIKE) {
			g.fillPolygon(new int[] {x, x + (width/2), x + width}, new int[] {y + height, y, y + height}, 3);
		}
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public BlockType getBt() {
		return bt;
	}

	public void setBt(BlockType bt) {
		this.bt = bt;
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
	
}
