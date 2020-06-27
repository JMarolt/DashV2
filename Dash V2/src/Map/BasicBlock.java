package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicBlock extends Block{
	
	public BasicBlock(int x, int y, Color color, BlockType bt) {
		super(x, y, color, bt);
		bt = BlockType.BLOCK;
	}
	
	public Rectangle collision() {
		return super.collision();
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	
	public void move(int speed) {
		super.move(speed);
	}

}
