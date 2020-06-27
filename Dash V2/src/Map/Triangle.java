package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Triangle {

	private int x, y;
	private int width, height;
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	
	public Triangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rectangles = collision();
		System.out.println(rectangles.size());
	}
	
	public int Hypotenuse() {
		int halfWidth = width/2;
		int widthSqrd = (halfWidth*halfWidth);
		int heightSqrd = (height*height);
		return (int) Math.sqrt(widthSqrd + heightSqrd);
	}
	
	public ArrayList<Rectangle> collision(){
		int h = Hypotenuse();
		//left side
		for(int i = 0; i < h; i++) {
			rectangles.add(new Rectangle(x + (width/h), (y + height) - (h/width), 1, 1));
		}
		//right side
		for(int k = 0; k < h; k++) {
			rectangles.add(new Rectangle((x + (width/2)) + (width/h), (y) + (h/width), 1, 1));
		}
		//base
		for(int b = 0; b < width; b++) {
			rectangles.add(new Rectangle(x + b, y + height, 1, 1));
		}
		return rectangles;
	}

	public void testDraw(Graphics g) {
		g.setColor(Color.green);
		for(int i = 0; i < rectangles.size(); i++) {
			g.fillRect((int)rectangles.get(i).getX(), (int)rectangles.get(i).getY(), 1, 1);
		}
	}
	
	public int getPerimeter() {
		return (2*Hypotenuse()) + width;
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

	public ArrayList<Rectangle> getRectangles() {
		return rectangles;
	}

	public void setRectangles(ArrayList<Rectangle> rectangles) {
		this.rectangles = rectangles;
	}
	
}
