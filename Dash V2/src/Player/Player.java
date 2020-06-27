package Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Map.BlockType;
import Map.Terrain;
import Setup.Start;

public class Player {

	private int x, y;
	private Color color;
	private int gravity;
	private int vy;
	private boolean canJump = true;
	private int width = 100;
	private int height = 100;
	
	private final int MAXVY = -20;

	private Terrain terrain = Start.currentLevel.getTerrain();
	
	private boolean isInAir = false;
	private double pos = 0.0;
	private double rotSpeed = 0.1;
	private double dvy;
	private double dmaxvy;
	private PlayerState ps = PlayerState.NORMAL;

	public Player(int x, int y, int gravity, Color color) {
		this.x = x;
		this.y = y;
		this.gravity = gravity;
		this.color = color;
	}

	public void run() {
		vy += gravity;
		y += vy;
		checkHorizontalCollision();
		checkVerticalCollision();
		checkSpikeCollision();
		checkPortalCollision();
		checkVelocity();
		if(canJump == false) {
			isInAir = true;
		}else {
			isInAir = false;
		}
	}

	public void checkVerticalCollision() {
		// check for ground collision
		if (collision().intersects(terrain.getGround().collision())) {
			canJump = true;
			gravity = 0;
			vy = 0;
		} else {
			gravity = 1;
		}
		// check for basicblock collisions
		for (int i = 0; i < terrain.getBlocks().size(); i++) {
			if (collision().intersects(terrain.getBlocks().get(i).collision())
					&& terrain.getBlocks().get(i).getBt().equals(BlockType.BLOCK)) {
				if ((y + height) <= (terrain.getBlocks().get(i).getY() + terrain.getBlocks().get(i).getHeight())) {
					if ((x + width) > terrain.getBlocks().get(i).getX()
							&& x < (terrain.getBlocks().get(i).getX() + terrain.getBlocks().get(i).getWidth())) {
						setY(terrain.getBlocks().get(i).getY() - height);
						canJump = true;
						gravity = 0;
						vy = 0;
					} else {
						gravity = 1;
					}
				}
			}
		}
		//set y value to the ground if it is lower than the ground
		if (getY() + height >= terrain.getGround().getY()) {
			setY(terrain.getGround().getY() - height);
		}
		//check for roof collisions when flying
		if(ps == PlayerState.SHIP && collision().intersects(terrain.getRoof().collision())) {
			setY(terrain.getRoof().getY() + terrain.getRoof().getHeight());
			vy = 0;
		}
	}

	public void checkSpikeCollision() {
		for (int i = 0; i < terrain.getBlocks().size(); i++) {
			if(collision().intersects(terrain.getBlocks().get(i).collision())) {
				if(terrain.getBlocks().get(i).getBt() == BlockType.SPIKE){
					die();
				}
			}
		}
	}

	public void die() {
		Start.currentLevel.setSpeed(0);
		canJump = false;
	}

	public void checkHorizontalCollision() {
		for (int i = 0; i < terrain.getBlocks().size(); i++) {
			if (collision().intersects(terrain.getBlocks().get(i).collision())) {
				if (getY() > terrain.getBlocks().get(i).getY()
						&& getY() < (terrain.getBlocks().get(i).getY() + terrain.getBlocks().get(i).getHeight())) {
					die();
				}
			}
		}
	}
	
	public void checkPortalCollision() {
		for(int i = 0; i < terrain.getPortals().size(); i++) {
			if(collision().intersects(terrain.getPortals().get(i).collision())) {
				ps = PlayerState.SHIP;
			}
		}
	}
	
	public void checkVelocity() {
		if(vy < MAXVY) {
			vy = MAXVY;
		}
		if(vy > Math.abs(MAXVY)) {
			vy = Math.abs(MAXVY);
		}
	}

	public void jump() {
		if (canJump && ps == PlayerState.NORMAL) {
			vy = -20;
			canJump = false;
		}
	}
	
	public void fly() {
		if(ps == PlayerState.SHIP) {
			vy -= 2;
		}
	}

	public Rectangle collision() {
		return new Rectangle(getX(), getY(), width, height);
	}
	
	public double rotate() {
		if(isInAir) {
			return pos += rotSpeed;
		}else {
			return 0.0;
		}
	}
	
	public double shipAngle() {
		dvy = (double)vy;
		dmaxvy = (double)MAXVY;
		if(ps == PlayerState.SHIP) {
			if(vy > 0) {
				return -(dvy/dmaxvy);
			}else if(vy < 0){
				return -(dvy/dmaxvy);
			}else {
				return 0.0;
			}
		}
		return 0.0;
	}
	
	public void explode() {
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		if(ps == PlayerState.NORMAL) {
			g2d.rotate(rotate(), x + (width/2), y + (height/2));
			g2d.fillRect(x, y, width, height);
		}
		if(ps == PlayerState.SHIP) {
			g2d.rotate(shipAngle(), x + (width/2), y + (height/2));
			g2d.fillRect(x + 30, y, 40, 40);
			drawShip(g2d);
		}
	}
	
	public void drawShip(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(x + 20, y + 15, 10, 45);
		g2d.fillRect(x + 20, y + 40, 60, 20);
		g2d.fillRect(x + 30, y + 60, 50, 25);
		g2d.fillRect(x + 10, y + 60, 20, 25);
		g2d.fillRect(x, y + 60, 10, 25);
		g2d.fillRect(x + 80, y + 60, 20, 25);
		g2d.fillPolygon(new int[] {x, x, x + 20}, new int[] {y + 50, y + 60, y + 60}, 3);
		g2d.fillPolygon(new int[] {x, x, x + 20}, new int[] {y + 85, y + 85, y + 100}, 3);
		g2d.setColor(Color.black);
		g2d.drawRect(x + 20, y + 15, 10, 45);
		g2d.drawRect(x + 20, y + 40, 60, 20);
		g2d.drawRect(x + 30, y + 60, 50, 25);
		g2d.drawRect(x + 10, y + 60, 20, 25);
		g2d.drawRect(x, y + 60, 10, 25);
		g2d.drawRect(x + 80, y + 60, 20, 25);
		g2d.drawPolygon(new int[] {x, x, x + 20}, new int[] {y + 50, y + 60, y + 60}, 3);
		//g2d.drawPolygon(new int[] {x, x, x + 20}, new int[] {y + 85, y + 85, y + 100}, 3);
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

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public boolean isCanJump() {
		return canJump;
	}

	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
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

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public boolean isInAir() {
		return isInAir;
	}

	public void setInAir(boolean isInAir) {
		this.isInAir = isInAir;
	}

	public double getPos() {
		return pos;
	}

	public void setPos(double pos) {
		this.pos = pos;
	}

	public double getRotSpeed() {
		return rotSpeed;
	}

	public void setRotSpeed(double rotSpeed) {
		this.rotSpeed = rotSpeed;
	}

	public PlayerState getPs() {
		return ps;
	}

	public void setPs(PlayerState ps) {
		this.ps = ps;
	}

	public int getMAXVY() {
		return MAXVY;
	}

}
