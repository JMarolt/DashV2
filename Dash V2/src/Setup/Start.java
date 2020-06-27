package Setup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.Timer;

import Level.Level;
import Level.LevelState;
import Map.Terrain;
import Player.Player;

public class Start implements ActionListener, KeyListener, MouseMotionListener, MouseListener{

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double widthRatio = screenSize.getWidth() / 1920;
	public static double heightRatio = screenSize.getHeight() / 1080;
	
	public static final int FPS = 60;
	private JFrame frame;
	private Panel panel;
	private Timer timer;
	
	public static File level1File = new File("src/Map/level1.txt");
	
	public static Player player;
	public static Level level1 = new Level("Level1", Color.blue, Color.black, Color.black, level1File, new Terrain(level1File), 12);
	
	public static Level currentLevel = level1;
	public static LevelState ls = LevelState.PLAY;
	
	boolean[] keys = new boolean[256];

	public static int milis = 0;
	
	//creates frame
	public void makeWindow() {
		frame = new JFrame();
		panel = new Panel();
		frame.setTitle("Dash");
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.addKeyListener(this);
		frame.setUndecorated(true);
		frame.setPreferredSize(screenSize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	//creates entities
	public void makeEntities() {
		player = new Player(200, currentLevel.getTerrain().getGround().getY() - 100, 1, Color.red);
	}
	
	//starts timer
	public void run() {
		makeWindow();
		makeEntities();
		timer = new Timer(1000/FPS, this);
		timer.start();
	}
	
	public static void main(String[] args) {
		Start start = new Start();
		start.run();
	}
	
	public void update() {
		if(keys[32]) {
			player.jump();
			player.fly();
		}
		if(keys[27] && currentLevel != null) {
			currentLevel.pause();
		}
		if(keys[56]) {
			System.exit(-1);
		}
	}
	
	public int levelPercent() {
		return (int)(((double)(milis * currentLevel.getSpeed()) / (double)(currentLevel.getTerrain().getMapLength() * currentLevel.getTerrain().getFileScale())) * 100.0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.repaint();
		level1.run();
		player.run();
		update();
		milis++;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
}
