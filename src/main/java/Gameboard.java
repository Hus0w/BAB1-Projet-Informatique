package demineur;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;



/**
 * Classe qui implemente le plateau de jeu.
 * 
 * @author Huseyin Ozudogru
 */
@SuppressWarnings("serial")
public class Gameboard extends JFrame implements WindowListener, Serializable
{
	/**
	* Variables qui permet la creation du plateau de jeu.
	* Elles sont utilises dans pratiquement toutes les classes.
	*/
	
	private static int size;
	private static int windowWidth;
	private static int windowHeight;
	private static int caseSize = 40;
	private static int space = 1;
	private static int deadPixel = 26;
	private static int bombs;
	
	private static int mousePosX;
	private static int mousePosY;
	
	/**
	 * Creation des tableaux pour les mines, les cases voisines et les cases ouvertes.
	 */
	public static int[][]mines;
	public static boolean[][]opened;
	public static int[][]neighbours;
	public static int neighbors = 0;
	
	/**
	 * Variables necessaires pour la creation du timer.
	 */
	public static Date timeCounter = new Date();
	public static long timer = 0;
	public static int timerPosX = 5;
	public static int timerPosY = 35;
	public static int timerWidth = 70;
	public static int timerHeight = 40;

	public static boolean reset = false;
	public static boolean victory = false;
	public static boolean defeat = false;
	
	
	/**
	 * Constructeur de la classe Gameboard.
	 * Il cree le plateau de jeu.
	 * 
	 * @param bombs : nombre de mines a mettre sur le plateau de jeu
	 * @param size : taille du plateau de jeu
	 * @param windowWidth : longueur de la fenetre du jeu
	 * @param windowHeight : largeur de la fenetre du jeu
	 * @param caseSize : taille de la case
	 */
	public Gameboard(int bombs, int size, int windowWidth, int windowHeight, int caseSize)
	{
		this.setTitle("Demineur");
		this.setSize(windowWidth, windowHeight);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\icon.png"));
		this.setLayout(null);
		addWindowListener(this);
		Gameboard.mines = new int[size/caseSize][size/caseSize];
		Gameboard.opened = new boolean[size/caseSize][size/caseSize];
		Gameboard.neighbours = new int[size/caseSize][size/caseSize];
		
		//Placement des mines
		Game.placeMines(bombs);
		
		//Calcul du nombre de mines autour d'une case
		Game.neighboursCalculator();
		
		//Affichage du plateau
		GUI boardDrawer = new GUI();
		this.setContentPane(boardDrawer);
		
		MouseMover mouseMove = new MouseMover();
		this.addMouseMotionListener(mouseMove);
		
		Clicker click = new Clicker(this);
		this.addMouseListener(click);
		
		/**
		 * Creation des boutons. 
		 */
		Insets minSizeSetter = new Insets(0, 0, 0, 0);
		
		//Bouton Reset
		JButton resetButton = new JButton("Reset");
		resetButton.setMargin(minSizeSetter);
		resetButton.addActionListener((ActionListener) new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Game.resetBoard(bombs);
			}
		});

		//Bouton de Sauvegarde
		JButton saveButton = new JButton("Sauvegarder");
		saveButton.setMargin(minSizeSetter);
		saveButton.addActionListener((ActionListener) new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Save.writeData();
			}
		});
		
		//Affichage des boutons
		this.add(resetButton);
		this.add(saveButton);
	}
	
	/**
	 * Methode qui ouvre toutes les cases contenant des zeros lorsque
	 * l'on clique sur une case contenant un zero. Elle utilise notamment
	 * le fameux algorithme de 'flood fill'.
	 * 
	 * @param x : position d'une case en x
	 * @param y : position d'une case en y
	 */
	public static void floodFill(int x, int y)
	{
		opened[x][y] = true;
		if (neighbours[x][y] == 0)
		{
			if (x < mines.length && x > 0 && y > 0 && y < mines.length)
			{
				floodFill(x+1, y);
				floodFill(x-1, y);
				floodFill(x, y-1);
				floodFill(x, y+1);
			}
			//floodFill(x-1, y-1);
			//floodFill(x-1, y+1);
			//floodFill(x+1, y-1);
			//floodFill(x+1, y+1);
		}
		else
			return;
	}
	
	/**
	 * Methode qui va etre utiliser dans la classe 'Main'
	 * et qui va rafraichir l'etat du jeu en continue.
	 */
	public void refreshScreen() 
	{
		Timer refresher = new Timer(0, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			repaint();
	    	if (Gameboard.reset == false)
	    	{
    		Game.checkVictory();
    		//System.out.println("Victoire: " + GUI.victory + ", D�faite: " + GUI.defeat);
	    	}
	    }
		});
		refresher.setRepeats(true);
		refresher.setDelay(17);
		refresher.start();
	  }
	
	/**
	 * Classe interne qui implemente l'interface MouseMotionListener.
	 * Cette classe va implementer les methodes qui va gerer
	 * tout les mouvements effectues avec la souris.
	 */
	public static class MouseMover implements MouseMotionListener
	{
		@Override
		public void mouseMoved(MouseEvent e) 
		{
			mousePosX = e.getX();
			mousePosY = e.getY();
			//System.out.println("Le curseur est en deplacement, voici ses coordonnees :");
			//System.out.println("X: " + mousePosX + "; Y: " + mousePosY);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {}
	}
	
	/**
	 * Implementation des methodes getter et setter pour les classes externes
	 * ainsi que les methodes de l'interface WindowListener.
	 */
	public static int getMousePosX() {
		return mousePosX;
	}
	
	public static int getMousePosY() {
		return mousePosY;
	}

	public static int sizeGetter() {
		return size;
	}

	public void sizeSetter(int size) {
		Gameboard.size = size;
	}

	public static int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		Gameboard.windowWidth = windowWidth;
	}

	public static int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		Gameboard.windowHeight = windowHeight;
	}

	public static int getCaseSize() {
		return caseSize;
	}

	public void setCaseSize(int caseSize) {
		Gameboard.caseSize = caseSize;
	}

	public static int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		Gameboard.space = space;
	}

	public static int getDeadPixel() {
		return deadPixel;
	}

	public void setDeadPixel(int deadPixel) {
		Gameboard.deadPixel = deadPixel;
	}
	
	public static int getBombs() {
		return bombs;
	}

	public static void setBombs(int bombs) {
		Gameboard.bombs = bombs;
	}	     

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		Game.resetBoard(1); //Permet de regler un bug
		this.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}