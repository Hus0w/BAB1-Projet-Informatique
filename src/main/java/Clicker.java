package demineur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



/**
 * Classe qui implemente l'interface MouseListener.
 * Cette classe va implementer les methodes qui va gerer
 * tout ce qui se passe avec les boutons de la souris.
 */
public class Clicker implements MouseListener
{
	public Gameboard board; //variable d'instance

	public Clicker(Gameboard board)
	{
		this.board = board;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (inBoxX() != -1 && inBoxY() != -1) //Si on est dans une case lorsque l'on clique
		{
			try
			{
			Gameboard.floodFill(inBoxX(), inBoxY());
			}
			catch (java.lang.ArrayIndexOutOfBoundsException c) {}
			catch (java.lang.StackOverflowError c) {}
			System.out.println("La curseur est en position (X= " + inBoxX() + "; Y= " + inBoxY() + ").");
			System.out.println("Le nombre de mines qu'il y a autour de cette case voisine est : " + Gameboard.neighbours[inBoxX()][inBoxY()]);
		}
		else
			System.out.println("Le curseur est en dehors d'une case.");
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Methode permettant de verifier si nous sommes dans une case en X.
	 * Retourne la position en X de la case si nous sommes dans celle-ci, -1 sinon.
	 * 
	 * @return x : position en abscisse de la case ; -1 si elle n'existe pas.
	 */
	public int inBoxX()
	{
		for (int x = 0 ; x < Gameboard.mines.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.mines.length ; y++)
			{
				if (Gameboard.getMousePosX() >= Gameboard.getSpace()+x*Gameboard.getCaseSize() && Gameboard.getMousePosX() < Gameboard.getSpace()+x*Gameboard.getCaseSize()+Gameboard.getCaseSize() && Gameboard.getMousePosY() >= Gameboard.getSpace()+y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getDeadPixel() && Gameboard.getMousePosY() < Gameboard.getSpace()+y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getDeadPixel())
					return x;
			}
		}
		return -1;
	}
	
	/**
	 * Methode permettant de verifier si nous sommes dans une case en Y.
	 * Retourne la position en Y de la case si nous sommes dans celle-ci, -1 sinon.
	 * 
	 * @return y : position en ordonnee de la case ; -1 si elle n'existe pas.
	 */
	public int inBoxY()
	{
		for (int x = 0 ; x < Gameboard.mines.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.mines.length ; y++)
			{
				if (Gameboard.getMousePosX() >= Gameboard.getSpace()+x*Gameboard.getCaseSize() && Gameboard.getMousePosX() < Gameboard.getSpace()+x*Gameboard.getCaseSize()+Gameboard.getCaseSize() && Gameboard.getMousePosY() >= Gameboard.getSpace()+y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getDeadPixel() && Gameboard.getMousePosY() < Gameboard.getSpace()+y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getDeadPixel())
					return y;
			}
		}
		return -1;
	}
	
	/**
	 * Methode permettant de verifier si, pour une case donnee en position X et Y,
	 * celle-ci a des cases voisines. Elle va notamment etre utiliser dans la boucle
	 * qui permet de calculer les mines autour d'une case.
	 * 
	 * @param aX Position en X de la case concernee.
	 * @param aY Position en Y de la case concernee.
	 * @param bX Position en X de/des case(s) voisine(s).
	 * @param bY Position en Y de/des case(s) voisine(s).
	 * @return true s'il y une case voisine ; false sinon.
	 */
	public static boolean isNeighbour(int aX, int aY, int bX, int bY)
	{
		if ((aX - bX < 2) && (aX - bX > -2) && (aY- bY < 2) && (aY - bY > -2) && (Gameboard.mines[bX][bY] == 9)) 
			return true;
		return false;
	}
}