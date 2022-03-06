package demineur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import javax.swing.JPanel;



/**
 * Classe qui herite de JPanel.
 * Cette classe va gerer toute la partie 
 * concerant l'interface graphique.
 * 
 * @author Huseyin Ozudogru
 */

@SuppressWarnings("serial")
public class GUI extends JPanel
{	
	public void paintComponent(Graphics gui)
	{
		/**
		 * Creation des cases
		 */
		gui.setColor(Color.BLACK); //Coloriage des cases a cocher
		for (int x = 0 ; x < Gameboard.mines.length ; x++)
		{
			for (int y = 0 ; y < Gameboard.mines.length ; y++)
			{
				gui.setColor(Color.BLACK); //Permet de regler un bug
				
				//Ouverture des cases
				if (Gameboard.opened[x][y] == true)
				{
					gui.setColor(Color.WHITE); //Coloriage des cases qui vont etre ouverts
					if (Gameboard.mines[x][y] == 9)
					gui.setColor(Color.RED);//Coloriage des cases ou les mines sont presentes
				}
					
				//Condition pour separer les cases
				if (Gameboard.getMousePosX() >= Gameboard.getSpace()+x*Gameboard.getCaseSize() && Gameboard.getMousePosX() < Gameboard.getSpace()+x*Gameboard.getCaseSize()+Gameboard.getCaseSize() && Gameboard.getMousePosY() >= Gameboard.getSpace()+y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getDeadPixel() && Gameboard.getMousePosY() < Gameboard.getSpace()+y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getDeadPixel())
					gui.setColor(Color.LIGHT_GRAY); //Coloriage de la case quand le curseur est dessus
				
				gui.fillRect(Gameboard.getSpace()+x*Gameboard.getCaseSize(), Gameboard.getSpace()+y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize(), Gameboard.getCaseSize() - 2*Gameboard.getSpace(), Gameboard.getCaseSize() - 2*Gameboard.getSpace()); //Cree les cases | C'est ici qu'on descend les cases pour creer space au dessus
				
				//Placement des chiffres avec differentes couleurs
				if (Gameboard.opened[x][y] == true)
				{
					gui.setColor(Color.BLACK); //Couleur des chiffres par defaut mais aussi ceux de 7 et 9
					if (Gameboard.mines[x][y] == 0 && Gameboard.neighbours[x][y] != 0)
					{
						if (Gameboard.neighbours[x][y] == 1)
							gui.setColor(Color.BLUE);
						else if (Gameboard.neighbours[x][y] == 2)
							gui.setColor(Color.GREEN);
						else if (Gameboard.neighbours[x][y] == 3)
							gui.setColor(Color.RED);
						else if (Gameboard.neighbours[x][y] == 4)
							gui.setColor(new Color(0, 0, 128)); //Couleur 'NAVY'
						else if (Gameboard.neighbours[x][y] == 5)
							gui.setColor(new Color(178, 34, 34)); //Couleur 'FIREBRICK'
						else if (Gameboard.neighbours[x][y] == 6)
							gui.setColor(new Color(72, 209, 204)); //Couleur 'MEDIUM TURQUOISE'
						else if (Gameboard.neighbours[x][y] == 8)
							gui.setColor(Color.DARK_GRAY);
						gui.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25)); //Specs du texte
						gui.drawString(Integer.toString(Gameboard.neighbours[x][y]), x*Gameboard.getCaseSize()+14, y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getDeadPixel()+2); //Ajuster les chiffres pour qu'ils se trouvent au milieu de la case
					}
					else if (Gameboard.mines[x][y] == 9) //Creation de la mine
					{
						int mineSize = 30;
						gui.fillOval(x*Gameboard.getCaseSize()+5, y*Gameboard.getCaseSize()+Gameboard.getCaseSize()+Gameboard.getCaseSize()+5, mineSize, mineSize); //Ajuster les mines avec les nbres 'magiques'
					}
				}
				
				/**
				 * Creation du Timer.
				 */
				
				gui.setColor(Color.YELLOW);
				gui.fillRect(Gameboard.timerPosX, Gameboard.timerPosY, Gameboard.timerWidth, Gameboard.timerHeight);
				if (Gameboard.defeat == false && Gameboard.victory == false)
				{
					Gameboard.timer = (new Date().getTime() - Gameboard.timeCounter.getTime()) / 1000;
				}
				
				if (Gameboard.timer > 999) //Bloquage du timer dans le cas ou le joueur depasse les 999 secondes de jeu
					Gameboard.timer = 999;
				
				//System.out.println(timer);
				gui.setColor(Color.BLACK); //Couleur du timer
				
				//Changement de couleur en cas de victoire ou defaite
				if (Gameboard.defeat == true)
					gui.setColor(Color.RED);
				else if (Gameboard.victory == true)
					gui.setColor(Color.GREEN);
				
				gui.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35)); //Specs du texte pour le timer
				
				if (Gameboard.timer < 10)
					gui.drawString("00"+Integer.toString((int) Gameboard.timer), Gameboard.timerPosX+Gameboard.getSpace(), Gameboard.timerPosY+35);
				else if (Gameboard.timer < 100)
					gui.drawString("0"+Integer.toString((int) Gameboard.timer), Gameboard.timerPosX+Gameboard.getSpace(), Gameboard.timerPosY+35);
				else
					gui.drawString(Integer.toString((int) Gameboard.timer), Gameboard.timerPosX+Gameboard.getSpace(), Gameboard.timerPosY+35);
				
				/**
				 * Creation du message a afficher lors d'une victoire ou defaite
				 */
				String endGameMessage = null;
				if(Gameboard.victory == true)
				{
					gui.setColor(Color.GREEN);
					endGameMessage = "VOUS AVEZ GAGNE !";
				}
				else if (Gameboard.defeat == true)
				{
					gui.setColor(Color.RED);
					endGameMessage = "VOUS AVEZ PERDU !";
				}
				
				if (Gameboard.victory == true || Gameboard.defeat == true)
				{
					gui.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20)); //Specs du texte pour victoire || defaite
					gui.drawString(endGameMessage, 80, 75);
				} 
				
				/**
				 * Affichage de mines dans la partie
				 */
				gui.setColor(Color.BLACK);
				gui.fillRect(80, 35, 65, 20);
				int restOfMines = Game.totalMines();
				gui.setColor(Color.WHITE);
				gui.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
				gui.drawString("Mines: " + Integer.toString(restOfMines), 82, 50);
			}
		}
	}
}