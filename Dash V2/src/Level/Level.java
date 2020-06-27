package Level;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import Map.Terrain;
import Player.PlayerState;
import Setup.Start;

public class Level {

	private String name;
	private Color backgroundColor;
	private Color blockColor;
	private Color groundColor;
	private File file;
	private Terrain terrain;
	private int speed;
	
	public int currentSpeed = 0;
	
	public Level(String name, Color backgroundColor, Color blockColor, Color groundColor, File file, Terrain terrain, int speed) {
		this.name = name;
		this.backgroundColor = backgroundColor;
		this.blockColor = blockColor;
		this.groundColor = groundColor;
		this.file = file;
		this.terrain = terrain;
		this.speed = speed;
	}
	
	public void run() {
		for(int i = 0; i < terrain.getBlocks().size(); i++) {
			terrain.getBlocks().get(i).move(speed);
		}
		for(int k = 0; k < terrain.getPortals().size(); k++) {
			terrain.getPortals().get(k).move(speed);
		}
	}
	
	public void pause() {
		Start.ls = LevelState.PAUSE;
		currentSpeed = speed;
		speed = 0;
	}
	
	public void resume() {
		Start.ls = LevelState.PLAY;
		speed = currentSpeed;
	}
	
	public void render(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, terrain.getMapLength() * terrain.getFileScale(), Start.screenSize.height);
		g.setColor(blockColor);
		for(int i = 0; i < terrain.getBlocks().size(); i++) {
			terrain.getBlocks().get(i).render(g);
		}
		for(int k = 0; k < terrain.getPortals().size(); k++) {
			terrain.getPortals().get(k).render(g);
		}
		g.setColor(groundColor);
		terrain.getGround().render(g);
		if(Start.player.getPs() == PlayerState.SHIP) {
			g.setColor(groundColor);
			terrain.getRoof().render(g);
		}
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getBlockColor() {
		return blockColor;
	}

	public void setBlockColor(Color blockColor) {
		this.blockColor = blockColor;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
