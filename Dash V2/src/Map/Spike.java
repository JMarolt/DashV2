package Map;

import java.awt.Color;
import java.awt.Graphics;

public class Spike extends Block{
	
	public Spike(int x, int y, Color color, BlockType bt) {
		super(x, y, color, bt);
		bt = BlockType.SPIKE;
	}
	
	public Triangle tcollision() {
		return super.tcollision();
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	
	public void move(int speed) {
		super.move(speed);
	}

}
