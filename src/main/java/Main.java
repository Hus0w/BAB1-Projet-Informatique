package demineur;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * Classe qui est le point d'entree du programme.
 * Elle a ete ecrit en grande par le Swing Scene Builder.
 * Elle comporte tout simplement le Menu Principal ainsi
 * que le choix des niveeux.
 * 
 * @author Huseyin Ozudogru
 */
public class Main {

	private JFrame mainMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainMenu = new JFrame();
		mainMenu.setResizable(false);
		mainMenu.getContentPane().setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		mainMenu.getContentPane().setBackground(Color.DARK_GRAY);
		mainMenu.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\icon.png"));
		mainMenu.setTitle("Demineur - Menu Principal");
		mainMenu.setBounds(100, 100, 450, 300);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.getContentPane().setLayout(null);
		
		JTextArea title = new JTextArea();
		title.setForeground(Color.ORANGE);
		title.setBackground(Color.DARK_GRAY);
		title.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		title.setText("D\u00E9mineur : Menu Principal");
		title.setBounds(80, 10, 282, 34);
		mainMenu.getContentPane().add(title);
		
		JTextArea plsSelect = new JTextArea();
		plsSelect.setForeground(Color.WHITE);
		plsSelect.setText("Veuillez s\u00E9lectionner un niveau de jeu :");
		plsSelect.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		plsSelect.setBackground(Color.DARK_GRAY);
		plsSelect.setBounds(80, 40, 293, 22);
		mainMenu.getContentPane().add(plsSelect);
		
		JTextArea copyrights = new JTextArea();
		copyrights.setBackground(Color.DARK_GRAY);
		copyrights.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		copyrights.setForeground(Color.GRAY);
		copyrights.setText("UMONS \u00A9 - Huseyin OZUDOGRU \u00A9");
		copyrights.setBounds(92, 240, 258, 22);
		mainMenu.getContentPane().add(copyrights);
		
		JButton beginnerMod = new JButton("D\u00E9butant");
		beginnerMod.setForeground(Color.WHITE);
		beginnerMod.setBackground(Color.BLACK);
		beginnerMod.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		beginnerMod.setBounds(126, 72, 169, 21);
		mainMenu.getContentPane().add(beginnerMod);
		
		JButton intermediateMod = new JButton("Interm\u00E9diaire");
		intermediateMod.setForeground(Color.WHITE);
		intermediateMod.setBackground(Color.BLACK);
		intermediateMod.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		intermediateMod.setBounds(126, 122, 169, 21);
		mainMenu.getContentPane().add(intermediateMod);
		
		JButton hardMod = new JButton("Difficile");
		hardMod.setForeground(Color.WHITE);
		hardMod.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		hardMod.setBackground(Color.BLACK);
		hardMod.setBounds(126, 172, 169, 21);
		mainMenu.getContentPane().add(hardMod);
		
		JButton loadButton = new JButton("Charger partie");
		loadButton.setForeground(Color.WHITE);
		loadButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		loadButton.addActionListener((ActionListener) new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		loadButton.setBackground(Color.BLUE);
		loadButton.setBounds(145, 208, 136, 22);
		mainMenu.getContentPane().add(loadButton);
		
		beginnerMod.addActionListener((ActionListener) new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Gameboard beginnerGame = new Gameboard(10, 320, 334, 438, 40);
				beginnerGame.setVisible(true);
				beginnerGame.setResizable(false);
				Gameboard.timeCounter = new Date();
				beginnerGame.refreshScreen();
			}
		});
		
		intermediateMod.addActionListener((ActionListener) new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Gameboard IntermediateGame = new Gameboard(15, 480, 494, 598, 40);
				IntermediateGame.setVisible(true);
				IntermediateGame.setResizable(false);
				Gameboard.timeCounter = new Date();
				IntermediateGame.refreshScreen();
			}
		});
		
		hardMod.addActionListener((ActionListener) new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				Gameboard hardGame = new Gameboard(20, 640, 655, 758, 40);
				hardGame.setVisible(true);
				hardGame.setResizable(false);
				Gameboard.timeCounter = new Date();
				hardGame.refreshScreen();
			}
		});
	}
}
