package demineur;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe qui permet d'implementer
 * le systeme de sauvegarde.
 * 
 * @author Huseyin Ozudogru
 */
public class Save 
{	
	public static Gameboard board;
	
	public Save(Gameboard board)
	{
		Save.board = board;
	}
	
	public static void writeData()
	{
		//Gameboard boardStatus = Gameboard.Gameboard;
		//int[][]minesStatus = CopyMines(Gameboard.mines);
		//boolean[][]openedStatus = CopyOpened(Gameboard.opened);
		
		try {
			FileOutputStream writing = new FileOutputStream("save.bin");
			
			ObjectOutputStream fos = new ObjectOutputStream(writing);
			
			fos.writeObject(board);

			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void readData()
	{
		try{
			FileInputStream reading = new FileInputStream("save.bin");
			
			ObjectInputStream fis = new ObjectInputStream(reading);
			
			Gameboard board = (Gameboard)fis.readObject();
			
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean[][] CopyOpened(boolean[][]tab)
	{
		boolean[][]copied = new boolean[tab.length][tab.length];
		for (int i = 0 ; i < tab.length ; i++)
		{
			for (int j = 0 ; j < tab.length ; j++)
			{
				copied[i][j] = tab[i][j];
			}
		}
		return copied;
	}
	
	public static int[][] CopyMines(int[][]tab1)
	{
		int[][]copied1 = new int[tab1.length][tab1.length];
		for (int i = 0 ; i < tab1.length ; i++)
		{
			for (int j = 0 ; j < tab1.length ; j++)
			{
				copied1[i][j] = tab1[i][j];
			}
		}
		return copied1;
	}
}