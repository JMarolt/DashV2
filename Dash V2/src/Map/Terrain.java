package Map;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Setup.Start;

public class Terrain {

	private File file;
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Portal> portals = new ArrayList<Portal>();
	private Color color;
	private Ground ground;
	private Roof roof;
	private int fileScale = 100;
	private int mapLength = 0;
	private int groundWidth = 0;

	public Terrain(File file) {
		this.file = file;
		createTerrain();
	}

	public void createTerrain() {
		try {
			int ycounter = 0;
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			while (line != null) {
				String temp = line;
				mapLength = temp.length();
				groundWidth = temp.length() * fileScale;
				for (int i = 0; i < temp.length(); i++) {
					if (temp.substring(i, i + 1).equals("b")) {
						blocks.add(new BasicBlock(i * fileScale, ycounter * fileScale, color, BlockType.BLOCK));
					}
					if (temp.substring(i, i + 1).equals("s")) {
						blocks.add(new Spike(i * fileScale, ycounter * fileScale, color, BlockType.SPIKE));
					}
					if(temp.substring(i, i + 1).equals("p")) {
						portals.add(new Portal(i * fileScale, ycounter * fileScale, fileScale/2, fileScale, PortalType.SHIP));
					}
				}
				line = br.readLine();
				ycounter++;
			}
			ground = new Ground(0, Start.screenSize.height - 100, groundWidth, 100, color);
			roof = new Roof(0, 0, groundWidth, 100, color);
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found...");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File is not compatible or method is broken");
			e.printStackTrace();
		}
	}
	
	public void addRoof() {
		
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getFileScale() {
		return fileScale;
	}

	public void setFileScale(int fileScale) {
		this.fileScale = fileScale;
	}

	public int getMapLength() {
		return mapLength;
	}

	public void setMapLength(int mapLength) {
		this.mapLength = mapLength;
	}

	public Ground getGround() {
		return ground;
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	public ArrayList<Portal> getPortals() {
		return portals;
	}

	public void setPortals(ArrayList<Portal> portals) {
		this.portals = portals;
	}

	public int getGroundWidth() {
		return groundWidth;
	}

	public void setGroundWidth(int groundWidth) {
		this.groundWidth = groundWidth;
	}

	public Roof getRoof() {
		return roof;
	}

	public void setRoof(Roof roof) {
		this.roof = roof;
	}

}
