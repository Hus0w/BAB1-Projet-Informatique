package demineur;

import java.util.Date;
import java.util.Random;



/**
 * Classe qui implemente quelques methodes du jeu.
 * Cette classe peut etre considere en quelque
 * sorte le moteur du jeu.
 * 
 * @author Huseyin Ozudogru
 */
public class Game 
{
	public Gameboard board;
	
	/**
	 * Constructeur de la classe Game.
	 * 
	 * @param board : parametre qui permet de recuperer le plateau du jeu dans la classe Gameboard
	 */
	public Game(Gameboard board)
	{
		this.board = board;
	}
	
	/**
	 * Methode qui permet de devoiler toutes les cases
	 * du plateau de jeu. Elle a ete utilise notamment
	 * durant le developpement du jeu dans les debuts.
	 */
	public static void revealCases()
	{
		for (int x = 0 ; x < Gameboard.opened.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.opened.length ; y++)
			{
				Gameboard.opened[x][y] = true; //montre les cases
			}
		}
	}
	
	/**
	 * Methode permettant de placer les mines aleatoirement.
	 * 
	 * @param n : nombre de mines a placer
	 */
	public static void placeMines(int n)
	{
		for (int i = 0 ; i < n ; i++)
		{
			Random rdm = new Random();
			int randLine = rdm.nextInt(Gameboard.mines.length);
			int randColumn = rdm.nextInt(Gameboard.mines.length);
			if (Gameboard.mines[randLine][randColumn] == 9)
				i--; //Revient en arriere dans la boucle.
			else
				Gameboard.mines[randLine][randColumn] = 9;
		}
	}
	
	/**
	 * Methode qui permet de compter le nombre de mines
	 * present autour d'une case. Elle va notamment
	 * utiliser la methode 'isNeighbour' definit
	 * dans la classe 'Clicker'.
	 */
	public static void neighboursCalculator()
	{
		for (int x = 0 ; x < Gameboard.mines.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.mines.length ; y++)
			{
				Gameboard.neighbors = 0;
				for (int i = 0 ; i < Gameboard.mines.length ; i++)
				{
					for (int j = 0 ; j < Gameboard.mines.length ; j++)
					{
						if (!(i == x && j == y)) //permet de ne pas compter quand la case ou on clique est deja une mine
						if (Clicker.isNeighbour(x, y, i, j) == true)
							Gameboard.neighbors++;
					}
				}
				Gameboard.neighbours[x][y] = Gameboard.neighbors;
			}
		}
	}
	
	/**
	 * Methode qui permet de recommencer le jeu.
	 * 
	 * @param n : nombre de mines a replacer car cette methode appelle 'placeMines'
	 */
	public static void resetBoard(int n)
	{
		Gameboard.reset = true;
		Gameboard.victory = false;
		Gameboard.defeat = false;
		Gameboard.timeCounter = new Date(); //Remise du timer a zero
		
		//Retrait des mines
		for (int x = 0 ; x < Gameboard.mines.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.mines.length ; y++)
			{
				Gameboard.mines[x][y] = 0;
				Gameboard.opened[x][y] = false;
			}
		}
		
		//Replacement des mines avec une boucle
		placeMines(n);					
		
		//Recalcule du nombre de mines autour d'une case
		neighboursCalculator();
		
		Gameboard.reset = false;
	}
	
	/**
	 * Methode permettant de verifier le statut de victoire ou defaite.
	 */
	public static void checkVictory()
	{
		if (Gameboard.defeat == false)
		{
			for (int x = 0 ; x < Gameboard.mines.length ; x++)
			{
				for (int y = 0 ; y < Gameboard.mines.length ; y++)
				{
					if (Gameboard.mines[x][y] == 9 && Gameboard.opened[x][y] == true)
						Gameboard.defeat = true;
				}
			}
		}
		
		int totalCases = (Gameboard.mines.length) * (Gameboard.mines.length);
		if(openedBoxes() >= totalCases - totalMines() && Gameboard.victory == false)
			Gameboard.victory = true;
	}
	
	/**
	 * Methode qui permet de compter le nombre
	 * total de mines sur le plateau de jeu.
	 * 
	 * @return result : le nombre de mines present sur le plateau
	 */
	public static int totalMines()
	{
		int result = 0;
		for (int x = 0 ; x < Gameboard.mines.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.mines.length ; y++)
			{
				if (Gameboard.mines[x][y] == 9)
				{
					result++;
				}
			}
		}
		return result;
	}
	
	/**
	 * Methode qui permet de compter le nombre
	 * de cases ouverts.
	 * 
	 * @return res : le nombre de cases ouverts
	 */
	public static int openedBoxes()
	{
		int res = 0;
		for (int x = 0 ; x < Gameboard.opened.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.opened.length ; y++)
			{
				if (Gameboard.opened[x][y] == true)
				{
					res++;
				}
			}
		}
		return res;
	}
}