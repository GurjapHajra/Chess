/*Name:Gurjap Singh Hajra
 * Date: 8 Jan, 2020
 * Purpose: To make a game of chess
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;

// finish instructions
// add the 2 games tougher

@SuppressWarnings("serial")
public class chess extends Applet implements ActionListener
{

	AudioClip Button1sound;
	AudioClip Button2sound;
	AudioClip backSound;
	
	String userName1;
	String userName2;
	
	JLabel piecepic;
	JLabel descripetion;
	JLabel disPicture;
	JLabel winner;
	
	JButton reset;
	// variables of the 3-d array
	int curmove = 0;
	int xformoves = 8;
	int yformoves = 8;
	int zformoves = 200;

	// 3d array stores all the previous moves
	char[][][] allmoves = new char [xformoves][yformoves][zformoves];
	// 3d array stores all the previous piece colors
	char[][][] allcolors = new char [xformoves][yformoves][zformoves];

	//************************Global Variables*********************
	boolean gameover = false;
	boolean white_castle = false;
	boolean black_castle = false;
	boolean blackKingMove= false;
	boolean whiteKingMove = false;
	boolean checking = false;

	char pieceOriginal[][] = {	{'r','n','b','q','k','b','n','r'},
			{'p','p','p','p','p','p','p','p'},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{'p','p','p','p','p','p','p','p'},
			{'r','n','b','q','k','b','n','r'}};
	char colorOriginal[][]= {	{'b','b','b','b','b','b','b','b'},
			{'b','b','b','b','b','b','b','b'},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{'w','w','w','w','w','w','w','w'},
			{'w','w','w','w','w','w','w','w'}};

	//************************The background color of the board*****************
	/*
	 * b = Black
	 * w = White
	 */

	char back[][]= {{'w','b','w','b','w','b','w','b'},
			{'b','w','b','w','b','w','b','w'},
			{'w','b','w','b','w','b','w','b'},
			{'b','w','b','w','b','w','b','w'},
			{'w','b','w','b','w','b','w','b'},
			{'b','w','b','w','b','w','b','w'},
			{'w','b','w','b','w','b','w','b'},
			{'b','w','b','w','b','w','b','w'}};

	//***********************Color of a piece***************************

	/*
	 * b = Black
	 * w = White
	 */

	char color[][]= {	{'b','b','b','b','b','b','b','b'},
			{'b','b','b','b','b','b','b','b'},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{'w','w','w','w','w','w','w','w'},
			{'w','w','w','w','w','w','w','w'}};

	//**********************What the piece is***************************
	/* r = Rook
	 * n = Knight 
	 * b = Bishop
	 * k = King
	 * q = Queen
	 * p = Pawn
	 */

	char piece[][] = {	{'r','n','b','q','k','b','n','r'},
			{'p','p','p','p','p','p','p','p'},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{'p','p','p','p','p','p','p','p'},
			{'r','n','b','q','k','b','n','r'}};

	//*********************Status of the pieces***********************
	/*
	 * u = unselected
	 * s = selected
	 */

	char select[][] = {	{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'}};

	char kingArray[][] = {	{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'},
			{'u','u','u','u','u','u','u','u'}};

	int row = 8;
	int col = 8;
	int last = -1;
	char turn = 'w';
	JLabel turnPic;

	JButton pics[] = new JButton [row * col];

	Panel p_card;  //to hold all of the screens
	Panel card1, card2, card3, card4, card5; //the two screens
	CardLayout cdLayout = new CardLayout ();

	public void init ()
	{
		
		String input = JOptionPane.showInputDialog ("Please enter the password");
		
		if(input.equalsIgnoreCase("cool game")) {
			JOptionPane.showMessageDialog (null, "That is the right password", "alert",
			        JOptionPane.ERROR_MESSAGE);
			
		}else {
			JOptionPane.showMessageDialog (null, "You got it wrong password", "alert",
			        JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		Button1sound = getAudioClip (getDocumentBase (), "B1sound.wav");
		Button2sound = getAudioClip(getDocumentBase (), "B2sound.wav");
		backSound = getAudioClip(getDocumentBase (), "Backgroundsound.au");
		backSound.loop();

		p_card = new Panel ();
		p_card.setLayout (cdLayout);
		screen1 ();
		screen2 ();
		screen3 ();
		screen4 ();
		screen5 ();
		resize (500, 660);
		setLayout (new BorderLayout ());
		add ("Center", p_card);
	}

	public void actionPerformed (ActionEvent e)
	{ //moves between the screens
		Button2sound.play();
		
		gameover = cheapcheckmate();
		String command = e.getActionCommand();
		
		switch(command) {
		case "s1":
			cdLayout.show (p_card, "1");
			break;
		case "s2":
			cdLayout.show (p_card, "2");
			break;
		case "s3":
			cdLayout.show (p_card, "3");
			break;
		case "s6":
			System.exit (0);
			break;
		case "playAgian":
			reset();
			redraw();
			cdLayout.show (p_card, "1");
			break;
		case "rules":
			rules();
			break;
		case "save":
			save("file.txt");
			break;
		case "open":
			open("file.txt");
			break;
		case "King":
			piecepic.setIcon(createImageIcon("king.png"));
			descripetion.setIcon(createImageIcon("king dis.png"));
			disPicture.setIcon(createImageIcon("king disPic.png"));
			break;
		case "Queen":
			piecepic.setIcon(createImageIcon("queen.png"));
			descripetion.setIcon(createImageIcon("queen dis.png"));
			disPicture.setIcon(createImageIcon("queen disPic.png"));
			break;
		case "Bishop":
			piecepic.setIcon(createImageIcon("bishop.png"));
			descripetion.setIcon(createImageIcon("bishop dis.png"));
			disPicture.setIcon(createImageIcon("bishop disPic.png"));
			break;
		case "Rook":
			piecepic.setIcon(createImageIcon("rook.png"));
			descripetion.setIcon(createImageIcon("rook dis.png"));
			disPicture.setIcon(createImageIcon("rook disPic.png"));
			break;
		case "Knight":
			piecepic.setIcon(createImageIcon("knight.png"));
			descripetion.setIcon(createImageIcon("knight dis.png"));
			disPicture.setIcon(createImageIcon("knight disPic.png"));
			break;
		case "Pawn":
			piecepic.setIcon(createImageIcon("pawn.png"));
			descripetion.setIcon(createImageIcon("pawn dis.png"));
			disPicture.setIcon(createImageIcon("pawn disPic.png"));
			break;
		case "undo":
			int t1 =0;
			for(int i =0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(curmove-1!=-1) {
						if(color[i][j]!=allcolors[i][j][curmove-1]||piece[i][j]!=allmoves[i][j][curmove-1]) {
							t1++;
						}
					}
				}
			}
			if(t1>0) {
				activatePreMove();
				turn = switch_turn(turn);
				redraw();
			}else {

				JOptionPane.showMessageDialog (null, "You can't undo anymore", "Error", JOptionPane.ERROR_MESSAGE);

			}
			break;
		case "instruc":
			cdLayout.show (p_card, "2");
			break;
		case "reset":
			reset();
			redraw();
			break;
		default:
			//code to handle the game
			int ans = Integer.parseInt(e.getActionCommand());
			int n = ans;
			int x = ans/col;
			int y = ans%col;

			if(gameover==true) {
				String answer;
				char temp = switch_turn(turn);

				if(temp=='w') {
					answer = "White";
					winner.setIcon(createImageIcon("whiteWin.png"));
				}else {
					answer = "Black";
					winner.setIcon(createImageIcon("blackWin.png"));
				}
				JOptionPane.showMessageDialog (null, "The Player with the "+answer+" pieces has won", "You Win", JOptionPane.ERROR_MESSAGE);
				
				cdLayout.show(p_card,"4");


			}else {

				if(turn != color[x][y]&&last == -1&&color[x][y]!=' ') {	
					JOptionPane.showMessageDialog (null, "Its not your turn", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(turn==color[x][y]&& last ==-1) {
					if(piece[x][y] == 'p') {
						selectPawn(x,y);
					}else if(piece[x][y] == 'r') {
						selectRook(x,y);
					}else if(piece[x][y] == 'b') {
						selectBishop(x,y);
					}else if(piece[x][y] == 'k') {
						selectKing(x,y);
					}else if(piece[x][y] == 'q') {
						selectQueen(x,y);
					}else if(piece[x][y] == 'n') {
						selectKnight(x,y);
					}
					last = n;
					redraw();
				}else {
					//Copying this position so it can be saved as lasted possition
					saveprevious();			
					int lastx = last/col;
					int lasty = last%col;

					if(select[x][y] == 's') {
						piece[x][y] = piece[lastx][lasty];
						piece[lastx][lasty] = ' ';
						color[x][y] = color[lastx][lasty];
						color[lastx][lasty] = ' ';
						turn = switch_turn(turn);

						//***************Whites caste rook-side
						if(white_castle==true&&x==7&&y==6&&whiteKingMove==false) {
							piece[7][5] = 'r';
							color[7][5] = 'w';
							piece[7][7] = ' ';
							color[7][7] = ' ';
							white_castle = false;
						}
						//***************White castle queen-side
						if(white_castle==true&&x==7&&y==2&&whiteKingMove==false) {
							piece[7][3] = 'r';
							color[7][3] = 'w';
							piece[7][0] = ' ';
							color[7][0] = ' ';
							white_castle = false;
						}
						//***************Black castle rook-side
						if(black_castle==true&&x==0&&y==6&&blackKingMove==false) {
							piece[0][5] = 'r';
							color[0][5] = 'b';
							piece[0][7] = ' ';
							color[0][7] = ' ';
							black_castle = false;
						}
						//***************Black castle queen-side
						if(black_castle==true&&x==0&&y==2&&blackKingMove==false) {
							piece[0][3] = 'r';
							color[0][3] = 'b';
							piece[0][0] = ' ';
							color[0][0] = ' ';
							black_castle = false;
						}

						redraw();
						if(check() =='w'&&turn=='w') {
						int input2 = JOptionPane.showConfirmDialog (null, "White king is under Check!! Can you prevent this","choose one of these options", JOptionPane.YES_NO_OPTION);
						System.out.println("done");
						if(input2==1) {
							System.out.println(input2);
							gameover=true;
						}
						
						}else if(check()=='b'&&turn=='b') {
							int input2 = JOptionPane.showConfirmDialog (null, "Black king is under Check!! Can you prevent this","choose one of these options", JOptionPane.YES_NO_OPTION);
							System.out.println("done");					
							if(input2==1) {
								System.out.println(input2);
								gameover=true;
							}

						}if(check()=='b'&&turn=='w') {
							JOptionPane.showMessageDialog (null, "Invalid Move", "Check!", JOptionPane.ERROR_MESSAGE);
							activatePreMove();
							int input2 = JOptionPane.showConfirmDialog (null, "White king is under Check!! Can you prevent this","choose one of these options", JOptionPane.YES_NO_OPTION);
							System.out.println("done");
							if(input2==1) {
								System.out.println(input2);
								gameover=true;
							}
							turn = switch_turn(turn);
						}else if(check()=='w'&&turn=='b') {
							JOptionPane.showMessageDialog (null, "Invalid Move", "Check!", JOptionPane.ERROR_MESSAGE);
							activatePreMove();
							int input2 = JOptionPane.showConfirmDialog (null, "White king is under Check!! Can you prevent this","choose one of these options", JOptionPane.YES_NO_OPTION);
							System.out.println("done");
							if(input2==1) {
								System.out.println(input2);
								gameover=true;
							}
							turn = switch_turn(turn);
						}
						if(stealmate()) {
							JOptionPane.showMessageDialog (null, "This is a Draw", "Draw!", JOptionPane.ERROR_MESSAGE);
							gameover=true;
						}
						firstMoveKing();
					}
					resetU();
					last = -1;
				}

				//***********Place a piece code needs to go here**************************************************
				if(white_pawn_Promotion()!=-1) {

					int pawnPos = white_pawn_Promotion();
					//Show a dialog asking the user to select from a combobox:
					String [] possibleValues = {"Queen", "Rook", "Knight","Bishop"};
					String selectedValue = (String) JOptionPane.showInputDialog (null,
							"Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,
							possibleValues, possibleValues [0]);

					if(selectedValue == "Queen") {
						piece[0][pawnPos] = 'q';
					}else if(selectedValue == "Rook") {
						piece[0][pawnPos] = 'r';
					}else if(selectedValue == "Knight") {
						piece[0][pawnPos] = 'n';
					}else if(selectedValue == "Bishop") {
						piece[0][pawnPos] = 'b';
					}

				}else if(black_pawn_Promotion()!=-1) {

					int pawnPos = black_pawn_Promotion();
					//Show a dialog asking the user to select from a combobox:
					String [] possibleValues = {"Queen", "Rook", "Knight","Bishop"};
					String selectedValue = (String) JOptionPane.showInputDialog (null,
							"Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,
							possibleValues, possibleValues [0]);

					if(selectedValue == "Queen") {
						piece[7][pawnPos] = 'q';
					}else if(selectedValue == "Rook") {
						piece[7][pawnPos] = 'r';
					}else if(selectedValue == "Knight") {
						piece[7][pawnPos] = 'n';
					}else if(selectedValue == "Bishop") {
						piece[7][pawnPos] = 'b';
					}
				}
				
				redraw();
				two_d(row,col,piece);
			}
			
		}
		if(gameover==true) {
			cdLayout.show (p_card, "4");
		}
		//updating the score
		updateScore();
		//disables reset button when not in use
		disablebutton();
			
	}

	//****************Methods for all the defferent screens
	public void screen1 ()
	{ //screen 1 is set up.
		card1 = new Panel ();
		card1.setBackground (new Color(213, 245, 227));
		//Get the user's name
		userName1 = JOptionPane.showInputDialog ("Please enter black player's your name");
		userName2 = JOptionPane.showInputDialog ("Please enter white player's name");
		JLabel name = new JLabel("By:Gurjap Singh Hajra");
		name.setFont(new Font("TimesRoman",Font.PLAIN,20));
		
		
		JLabel titlepicture = new JLabel(createImageIcon("welcome.png"));

		
		JButton next = new JButton ("Next");
		next.setActionCommand ("s2");
		next.addActionListener (this);
		next.setBackground(new Color(29, 131, 72));
		
		card1.add(titlepicture);
		card1.add (next);
		card1.add(name);
		p_card.add ("1", card1);
	}
	//********Instructions Screen
	JButton rules;
	public void screen2 ()
	{ //screen 2 is set up.
		card2 = new Panel ();
		card2.setBackground (new Color(29, 131, 72));
		JLabel title = new JLabel ("Instructions");
		title.setFont(new Font("TimesRoman",Font.PLAIN,20));

		JLabel instrucpic = new JLabel (createImageIcon("instructions.png"));

		rules = new JButton("Rules of chess");
		rules.setActionCommand("rules");
		rules.addActionListener(this);
		rules.setBackground(new Color(29, 131, 72));
		
		JButton next = new JButton ("Next");
		next.setActionCommand ("s3");
		next.addActionListener (this);
		next.setBackground(new Color(29, 131, 72));
		
		card2.add (title);
		card2.add(instrucpic);
		card2.add(rules);
		card2.add (next);
		p_card.add ("2", card2);
	}
	//*******Game Screen

	int whitescore = 39;
	int blackscore = 39;

	JLabel showscore = new JLabel("| Black:"+blackscore+" | White:"+whitescore);

	public void screen3 ()
	{ //screen 3 is set up.
		card3 = new Panel ();
		card3.setBackground (new Color(241, 148, 138));

		JLabel title = new JLabel ("     CHESS     ");
		title.setFont(new Font("Arial", Font.BOLD, 70));

		showscore.setFont((new Font("Arial", Font.PLAIN, 30)));
		showscore.setForeground(new Color(255,255,255));

		JLabel TurnLabel = new JLabel("Turn: ");
		TurnLabel.setFont((new Font("Arial", Font.PLAIN, 30)));
		TurnLabel.setForeground(new Color(255,255,255));

		JButton instruc = new JButton ("instructions");
		instruc.setActionCommand ("instruc");
		instruc.addActionListener (this);
		instruc.setBackground(new Color(29, 131, 72));
		
		JButton save = new JButton ("save");
		save.setActionCommand ("save");
		save.addActionListener (this);
		save.setBackground(new Color(29, 131, 72));
		
		//Button for reset the the game board
		reset = new JButton ("reset");
		reset.setActionCommand ("reset");
		reset.addActionListener (this);
		reset.setBackground(new Color(29, 131, 72));
		
		
		JButton undo = new JButton ("Undo");
		undo.setActionCommand ("undo");
		undo.addActionListener (this);
		undo.setBackground(new Color(29, 131, 72));
		
		JButton open = new JButton("Open");
		open.addActionListener(this);
		open.setActionCommand("open");
		open.setBackground(new Color(29, 131, 72));

		//Set up grid
		Panel p = new Panel (new GridLayout (row, col));
		int move = 0;
		for (int i = 0 ; i < row ; i++)
		{
			for (int j = 0 ; j < col ; j++)
			{ //take out when you've got pictures
				pics [move] = new JButton (createImageIcon (String.valueOf(back[i][j])+String.valueOf(color[i][j])+String.valueOf(piece[i][j]) +String.valueOf(select[i][j]) + ".png"));;
				//add in when you have pictures
				//a [move] = new JButton (createImageIcon (b [i] [j] + ".jpg"));
				pics [move].setPreferredSize (new Dimension (60, 60));
				pics [move].addActionListener (this);
				pics [move].setActionCommand ("" + move);
				p.add (pics [move]);
				move++;
			}
		}
		turnPic = new JLabel(createImageIcon("one.png"));
		card3.add (title);

		card3.add (TurnLabel);
		card3.add(turnPic);
		card3.add(showscore);
		card3.add (p);
		card3.add(undo);
		card3.add(instruc);
		card3.add(save);
		card3.add(open);
		card3.add(reset);
		p_card.add ("3", card3);
	}
	
	public void screen4 ()
	{ //screen 4 is set up.
		card4 = new Panel ();
		card4.setBackground (new Color(29, 131, 72));
		
		JLabel title = new JLabel("Winner");
		title.setFont(new Font("Arial",Font.BOLD,80));
		
		winner = new JLabel(createImageIcon("whiteWin.png"));
		
		JButton PlayAgian = new JButton("Play agian?");
		PlayAgian.setPreferredSize(new Dimension (400,30));
		PlayAgian.setActionCommand("playAgian");
		PlayAgian.addActionListener(this);
		PlayAgian.setBackground(new Color(29, 131, 72));
		
		card4.add(title);
		card4.add(winner);
		card4.add(PlayAgian);
		p_card.add ("4", card4);
	}
	
	//*******End game screen two
	public void screen5 ()
	{ //screen 5 is set up.
		card5 = new Panel ();
		card5.setBackground (new Color(241, 148, 138));
		
		JLabel title = new JLabel ("Rules for chess");
		title.setFont(new Font("Arial",Font.BOLD,60));
		
		JButton back = new JButton ("To the game");
		back.setActionCommand ("s1");
		back.addActionListener (this);
		back.setPreferredSize(new Dimension(400,40));
		back.setBackground(new Color(241, 148, 138));
		
		JButton king = new JButton ("King");
		king.setActionCommand ("King");
		king.addActionListener (this);
		king.setBackground(new Color(29, 131, 72));
		
		JButton Queen = new JButton ("Queen");
		Queen.setActionCommand ("Queen");
		Queen.addActionListener (this);
		Queen.setBackground(new Color(29, 131, 72));
		
		JButton Bishop = new JButton ("Bishop");
		Bishop.setActionCommand ("Bishop");
		Bishop.addActionListener (this);
		Bishop.setBackground(new Color(29, 131, 72));
		
		JButton Rook = new JButton ("Rook");
		Rook.setActionCommand ("Rook");
		Rook.addActionListener (this);
		Rook.setBackground(new Color(29, 131, 72));
		
		JButton Knight = new JButton ("Knight");
		Knight.setActionCommand ("Knight");
		Knight.addActionListener (this);
		Knight.setBackground(new Color(29, 131, 72));
		
		JButton Pawn = new JButton ("Pawn");
		Pawn.setActionCommand ("Pawn");
		Pawn.addActionListener (this);
		Pawn.setBackground(new Color(29, 131, 72));
		
		piecepic = new JLabel(createImageIcon("king.png"));
		
		descripetion = new JLabel(createImageIcon("king dis.png"));
		disPicture = new JLabel(createImageIcon("king disPic.png"));
		
		card5.add (title);
		card5.add (king);
		card5.add (Queen);
		card5.add (Rook);
		card5.add (Bishop);
		card5.add (Knight);
		card5.add (Pawn);
		card5.add(descripetion);
		card5.add(piecepic);
		card5.add(disPicture);
		card5.add(back);
		p_card.add ("5", card5);
	}

	//************updates the score
	public void updateScore() {
		blackscore = 0;
		whitescore = 0;

		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				//these ifs make increase the score
				
				if(piece[i][j]=='p') {
					if(color[i][j]=='w') {
						whitescore++;
					}else {
						blackscore++;
					}
				}else if(piece[i][j]=='q') {
					if(color[i][j]=='w') {
						whitescore+=9;
					}else {
						blackscore+=9;
					}
				}else if(piece[i][j]=='r') {
					if(color[i][j]=='w') {
						whitescore+=3;
					}else {
						blackscore+=3;
					}
				}else if(piece[i][j]=='n') {
					if(color[i][j]=='w') {
						whitescore+=3;
					}else {
						blackscore+=3;
					}
				}else if(piece[i][j]=='b') {
					if(color[i][j]=='w') {
						whitescore+=9;
					}else {
						blackscore+=9;
					}
				}
			}
		}
		blackscore = blackscore*4;
		whitescore = whitescore*4;
		
		showscore.setText("| Black:"+blackscore+" | White:"+whitescore);
	}

	//************resets the selected array
	public void resetU() {

		for(int i = 0; i<row;i++) {
			for(int j = 0;j<col; j++) {
				select[i][j] = 'u';
			}
		}

	}
	//*************Method for switch the turns
	public char switch_turn(char turn) {
		//swiches the turn
		if(turn == 'w') {
			turnPic.setIcon(createImageIcon("two.png"));
			showStatus("Turn: "+userName1);
			return 'b';

		}else if(turn == 'b') {
			turnPic.setIcon(createImageIcon("one.png"));
			showStatus("Turn: "+userName2);
			return 'w';
		}else {
			JOptionPane.showMessageDialog (null, "Can't identify who's turn", "Error", JOptionPane.ERROR_MESSAGE);
			return 'e';
		}



	}

	//********************PAWN**************************************

	//************selects all the possible moves for a Pawn
	public void selectPawn (int x,int y) {
		//************selects all the possible moves for a Pawn
		if(color[x][y]=='w'&&x==6&&color[x-1][y]!='w'&&color[x-1][y]!='b'&&color[x-2][y]!='w'&&color[x-2][y]!='b') {
			select[x-1][y] = 's';
			select[x-2][y] = 's';
		}

		if(color[x][y]=='b'&&x==1&&color[x+1][y]!='w'&&color[x+1][y]!='b'&&color[x+2][y]!='w'&&color[x+2][y]!='b') {
			select[x+1][y] = 's';
			select[x+2][y] = 's';
		}
		if(x-1!=-1) {
			if(color[x][y]=='w'&&color[x-1][y]!='w'&&color[x-1][y]!='b') {
				select[x-1][y] = 's';
			}
		}
		if(x+1!=8) {
			if(color [x][y] == 'b'&&color[x+1][y]!='w'&&color[x+1][y]!='b') {
				select [x+1][y] = 's';
			}
		}
		if(color[x][y] =='w') {


			if(y-1!=-1&&x-1!=-1) {
				if(color[x-1][y-1]=='b') {
					select[x-1][y-1] = 's';
				}
			}

			if(y+1!=8&&x-1!=-1) {
				if(color[x-1][y+1]=='b') {
					select[x-1][y+1] = 's';
				}
			}
		}else if(color[x][y]=='b') {
			if(y+1!=8) {
				if(color[x+1][y+1]=='w') {
					select[x+1][y+1] = 's';
				}
			}
			if(y-1!=-1) {
				if(color[x+1][y-1]=='w') {
					select[x+1][y-1] = 's';
				}
			}
		}

	}
	//***********checks if a Pawn should get promoted
	public int white_pawn_Promotion() {
		int x = 0;
		int y = 0;
		boolean temp = true;

		 do{
			if(piece[x][y]=='p') {
				temp =false;
				return y;
			}
			y++;
		}
		 while(y<8&&temp==true);
		
		return -1;
	}
	public int black_pawn_Promotion() {
		int x = 7;
		int y = 0;
		boolean temp = true;

		do{
			if(piece[x][y]=='p') {
				temp =false;
				return y;
			}
			y++;
		}
		while(y<8&&temp==true);
		return -1;
	}

	//*************************ROOK**********************************

	//************selects all the possible moves for a Rook
	public void selectRook (int x,int y) {

		rookup(x,y);
		rookdown(x,y);
		rookright(x,y);
		rookleft(x,y);
	}
	//************selects all possible moves that go up	
	public void rookup(int x,int y) {
		//up
		int cx1 = x-1;
		while(cx1>=0 && color[cx1][y]==' ') {
			select[cx1][y] = 's';
			cx1--;
		}
		if(cx1>=0) {
			if(color[cx1][y]=='b'&&color[x][y]=='w'||color[cx1][y]=='w'&&color[x][y]=='b') {
				select[cx1][y]='s';
			}
		}
	}
	//***********selects all possible moves that go down	
	public void rookdown(int x, int y) {
		//down
		int cx2 = x+1;
		while(cx2<=7 && color[cx2][y]==' ') {
			select[cx2][y] = 's';
			cx2++;
		}
		if(cx2<8) {
			if(color[cx2][y]=='b'&&color[x][y]=='w'||color[cx2][y]=='w'&&color[x][y]=='b') {
				select[cx2][y]='s';
			}
		}
	}
	//**********selects all possible moves that go left		
	public void rookleft(int x, int y) {
		//left
		int cx4 = y-1;
		while(cx4>=0 && color[x][cx4]==' ') {
			select[x][cx4] = 's';
			cx4--;
		}
		if(cx4>=0) {
			if(color[x][cx4]=='b'&&color[x][y]=='w'||color[x][cx4]=='w'&&color[x][y]=='b') {
				select[x][cx4]='s';
			}
		}
	}
	//*********selects all possible moves that go right	
	public void rookright(int x, int y) {
		//right
		int cx3 = y+1;
		while(cx3<=7 && color[x][cx3]==' ') {
			select[x][cx3] = 's';
			cx3++;
		}
		if(cx3<8) {
			if(color[x][cx3]=='b'&&color[x][y]=='w'||color[x][cx3]=='w'&&color[x][y]=='b') {
				select[x][cx3]='s';
			}
		}
	}

	//**********************BISHOP*********************************

	//************selects all the possible moves for a Bishop
	public void selectBishop (int x,int y) {

		bishopUR(x,y);
		bishopUL(x,y);
		bishopDR(x,y);
		bishopDL(x,y);
	}
	//********selects all possible moves that go up and right
	public void bishopUR(int x, int y) {
		//up and right

		int cx1 = x-1;
		int cy1 = y+1;

		while(cy1<=7&&cx1>=0 && piece[cx1][cy1]==' '){

			select[cx1][cy1] = 's';
			cy1++;
			cx1--;
		}

		if(cx1!=-1&&cy1!=8){

			if(color[cx1][cy1]=='b'&& color[x][y]=='w'||color[cx1][cy1]=='w'&& color[x][y]=='b'){
				select[cx1][cy1] = 's';
			}

		}


	}
	//*******selects all possible moves that go up and left	
	public void bishopUL(int x, int y) {
		//up and left

		int cx2 = x-1;
		int cy2 = y-1;

		while(cy2>=0&cx2>=0 && piece[cx2][cy2]==' '){

			select[cx2][cy2] = 's';
			cy2--;
			cx2--;
		}

		if(cx2!=-1&&cy2!=-1){

			if(color[cx2][cy2]=='b'&& color[x][y]=='w'||color[cx2][cy2]=='w'&& color[x][y]=='b'){
				select[cx2][cy2] = 's';
			}

		}

	}
	//*******selects all possible moves that go down and left
	public void bishopDL(int x, int y) {
		//down and left

		int cx4 = x+1;
		int cy4 = y-1;

		while(cy4>=0 && cx4<=7 && piece[cx4][cy4]==' '){

			select[cx4][cy4] = 's';
			cy4--;
			cx4++;
		}
		if(cx4!=8&&cy4!=-1){

			if(color[cx4][cy4]=='b'&& color[x][y]=='w'||color[cx4][cy4]=='w'&& color[x][y]=='b'){
				select[cx4][cy4] = 's';
			}

		}
	}
	//******selects all possible moves that go down and right
	public void bishopDR(int x,int y) {
		//down and right

		int cx3 = x+1;
		int cy3 = y+1;

		while(cy3<=7 && cx3<=7 && piece[cx3][cy3]==' '){

			select[cx3][cy3] = 's';
			cy3++;
			cx3++;
		}
		if(cx3!=8&&cy3!=8){

			if(color[cx3][cy3]=='b'&& color[x][y]=='w'||color[cx3][cy3]=='w'&& color[x][y]=='b'){
				select[cx3][cy3] = 's';
			}

		}
	}

	//*********************KNIGHT*********************************

	//************selects all the possible moves for a Knight
	public void selectKnight (int x,int y) {

		//up-1 left-2
		int cx1 = x-1;
		int cy1 = y-2;

		if(cx1>=0&&cy1>=0){
			if(color[cx1][cy1]!=color[x][y]){
				select[cx1][cy1] = 's';
			}
		}

		//up-2 left-1
		int cx2 = x-2;
		int cy2 = y-1;

		if(cx2>=0&&cy2>=0){
			if(color[cx2][cy2]!=color[x][y]){
				select[cx2][cy2] = 's';
			}
		}
		//up-2 right-1
		int cx3 = x-2;
		int cy3 = y+1;

		if(cx3>=0&&cy3<=7){
			if(color[cx3][cy3]!=color[x][y]){
				select[cx3][cy3] = 's';
			}
		}
		//up-1 right-2
		int cx4 = x-1;
		int cy4 = y+2;

		if(cx4>=0&&cy4<=7){
			if(color[cx4][cy4]!=color[x][y]){
				select[cx4][cy4] = 's';
			}
		}
		//down-1 right-2
		int cx5 = x+1;
		int cy5 = y+2;

		if(cx5<=7&&cy5<=7){
			if(color[cx5][cy5]!=color[x][y]){
				select[cx5][cy5] = 's';
			}
		}
		//down-2 right-1
		int cx6 = x+2;
		int cy6 = y+1;

		if(cx6<=7&&cy6<=7){
			if(color[cx6][cy6]!=color[x][y]){
				select[cx6][cy6] = 's';
			}
		}
		//down-2 left-1
		int cx7 = x+2;
		int cy7 = y-1;

		if(cx7<=7&&cy7>=0){
			if(color[cx7][cy7]!=color[x][y]){
				select[cx7][cy7] = 's';
			}
		}
		//down-1 left-2
		int cx8 = x+1;
		int cy8 = y-2;

		if(cx8<=7&&cy8>=0){
			if(color[cx8][cy8]!=color[x][y]){
				select[cx8][cy8] = 's';
			}
		}

	}

	//***********************QUEEN******************************

	//************selects all the possible moves for a Queen
	public void selectQueen (int x,int y) {

		selectRook(x,y);
		selectBishop(x,y);

	}

	//***********************KING*******************************

	//************selects all the possible moves for a King
	public void ExampleKing (int x,int y) {

		//up-1 left-1
		int cx1 = x-1;
		int cy1 = y-1;

		if(cx1>=0&&cy1>=0){
			if(color[cx1][cy1]!=color[x][y]){
				kingArray[cx1][cy1] = 's';
			}
		}
		//up-1
		int cx2 = x-1;
		int cy2 = y;

		if(cx2>=0){
			if(color[cx2][cy2]!=color[x][y]){
				kingArray[cx2][cy2] = 's';
			}
		}
		//up-1 right-1
		int cx3 = x-1;
		int cy3 = y+1;

		if(cx3>=0&&cy3<=7){
			if(color[cx3][cy3]!=color[x][y]){
				kingArray[cx3][cy3] = 's';
			}
		}
		//left-1
		int cx4 = x;
		int cy4 = y-1;

		if(cy4>=0){
			if(color[cx4][cy4]!=color[x][y]){
				kingArray[cx4][cy4] = 's';
			}
		}
		//right-1
		int cx5 = x;
		int cy5 = y+1;

		if(cy5<=7){
			if(color[cx5][cy5]!=color[x][y]){
				kingArray[cx5][cy5] = 's';
			}
		}
		//down-1 left-1
		int cx6 = x+1;
		int cy6 = y-1;

		if(cx6<=7&&cy6>=0){
			if(color[cx6][cy6]!=color[x][y]){
				kingArray[cx6][cy6] = 's';
			}
		}
		//down-1
		int cx7 = x+1;
		int cy7 = y;

		if(cx7<=7){
			if(color[cx7][cy7]!=color[x][y]){
				kingArray[cx7][cy7] = 's';
			}
		}
		//down-1 right-1
		int cx8 = x+1;
		int cy8 = y+1;

		if(cx8<=7&&cy8<=7){
			if(color[cx8][cy8]!=color[x][y]){
				kingArray[cx8][cy8] = 's';
			}
		}
		//white castle
		if(color[x][y]=='w'&&x==7&&checking ==false) {
			if(color[7][7]=='w'&&piece[7][7]=='r'&&piece[7][6]==' '&&piece[7][5] == ' ') {
				kingArray[7][6]='s';
				white_castle=true;
			}
		}
		if(color[x][y]=='w'&&x==7&&checking==false) {
			if(color[7][0]=='w'&&piece[7][0]=='r'&&piece[7][1]==' '&&piece[7][2] == ' '&&piece[7][3]==' ') {
				kingArray[7][2]='s';
				white_castle=true;
			}
		}
		//black castle
		if(color[x][y]=='b'&&x==0&&checking==false) {
			if(color[0][7]=='b'&&piece[0][7]=='r'&&piece[0][6]==' '&&piece[0][5] == ' ') {
				kingArray[0][6]='s';
				black_castle=true;
			}
		}
		if(color[x][y]=='b'&&x==0&&checking==false) {
			if(color[0][0]=='b'&&piece[0][0]=='r'&&piece[0][1]==' '&&piece[0][2] == ' '&&piece[0][3]==' ') {
				kingArray[0][2]='s';
				black_castle=true;
			}
		}
	}

	public void selectKing(int x, int y) {

		ExampleKing(x,y);



		if(turn=='w') {
			selectallforking('b');

			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(piece[i][j]=='p'&&color[i][j]=='b'&&i+1!=8) {

						if(j-1!=-1)
							select[i+1][j-1] ='s';
						if(j+1!=8)
							select[i+1][j+1] = 's';
					}
				}
			}


		}else {
			selectallforking('w');
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(piece[i][j]=='p'&&color[i][j]=='w'&&i-1!=-1) {

						if(j-1!=-1)
							select[i-1][j-1] ='s';
						if(j+1!=8)
							select[i-1][j+1] = 's';
					}
				}
			}
		}

		for(int i = 0;i<8;i++) {
			for(int j =0;j<8;j++) {
				if(kingArray[i][j]=='s'&&select[i][j]=='u') {
					select[i][j] = 's';						
				}else {
					select[i][j] = 'u';
				}
			}
		}
		for(int i = 0;i<8;i++) {
			for(int j =0;j<8;j++) {
				kingArray[i][j]='u';
			}
		}
	}
	
	public void disablebutton() {
		
		int count = 0;
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(piece[i][j]!=pieceOriginal[i][j]) {
					count++;
				}
			}
		}
		boolean test = (count==0) ? false : true;
		reset.setEnabled(test);
		
	}
	
	// Draw the Board
	public void draw() {

		Panel grid = new Panel (new GridLayout (row, col));

		int m = 0;
		for (int i = 0 ; i < row ; i++) {
			for (int j = 0 ; j < col ; j++){
				pics [m] = new JButton (createImageIcon (String.valueOf(back[i][j])+String.valueOf(color[i][j])+String.valueOf(piece[i][j]) +String.valueOf(select[i][j]) + ".png"));
				pics [m].setPreferredSize (new Dimension (60,60));
				pics[m].setBorderPainted(true);
				pics[m].addActionListener(this);
				pics[m].setActionCommand(m+"");
				grid.add (pics [m]);
				m++;
			}
		}
		add (grid);
	}
	// redraws the board
	public void redraw() {

		Panel grid = new Panel (new GridLayout (row, col));

		int m = 0;
		for (int i = 0 ; i < row ; i++) {
			for (int j = 0 ; j < col ; j++){
				pics[m].setIcon(createImageIcon (String.valueOf(back[i][j])+String.valueOf(color[i][j])+String.valueOf(piece[i][j]) +String.valueOf(select[i][j]) + ".png"));
				m++;
			}
		}
		add (grid);
	}
	//***************Printing 1-d array
	public void one_d(char arrayname[]){
		for(int i = 0;i<arrayname.length;i++){
			System.out.println(arrayname[i]);
		}
		System.out.println();
	}
	//***************Printing 2-d array
	public void two_d (int rows, int columns,char array[][]){
		for(int i = 0; i<rows; i++)
		{
			for(int j = 0; j<columns; j++)
			{
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	//***************cheap Checkmate
	public boolean cheapcheckmate() {

		int count =0;
		for(int i = 0; i<8;i++) {
			for(int j = 0;j<8;j++) {
				if(piece[i][j]=='k') {
					count++;
				}
			}
		}
		if(count == 1){
			return true;
		}else {
			return false;
		}
	}
	//**************Checks if a piece can be selected in the next move
	public char check() {
		checking = true;
		resetU();
		char temp = 'e';
		selectall('b');
		int count = 0;
		for(int i = 0; i<8;i++) {
			for(int j = 0;j<8;j++) {
				if(select[i][j]=='s'&&piece[i][j]=='k') {
					count++;
					if(color[i][j]=='b') {
						temp='b';
					}else if(color[i][j]=='w') {
						temp='w';
					}
				}
			}
		}
		resetU();
		selectall('w');
		for(int i = 0; i<8;i++) {
			for(int j = 0;j<8;j++) {
				if(select[i][j]=='s'&&piece[i][j]=='k') {
					count++;
					if(color[i][j]=='b') {
						temp='b';
					}else {
						temp='w';
					}
				}
			}
		}
		checking = false;
		resetU();
		if(count>0) {
			return temp;
		}else {
			return 'n';
		}
	}
	//Checks if the king is still on it first move
	public void firstMoveKing() {
		if(piece[0][4] != 'k' && color [0][4] !='b') {	
			blackKingMove = true;
		}else if(piece[7][4] != 'k' && color [7][4] !='w') {
			whiteKingMove = true;
		}
	}
	//**************selects all possible moves for any one side
	public void selectall(char sidecolor) {

		if(sidecolor == 'b') {
			for (int i = 0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(piece[i][j]=='r'&&color[i][j]=='b') {
						selectRook(i,j);
					}
					if(piece[i][j]=='q'&&color[i][j]=='b') {
						selectQueen(i,j);
					}
					if(piece[i][j]=='b'&&color[i][j]=='b') {
						selectBishop(i,j);
					}
					if(piece[i][j]=='n'&&color[i][j]=='b') {
						selectKnight(i,j);
					}
					if(piece[i][j]=='p'&&color[i][j]=='b') {
						selectPawn(i,j);
					}
					if(piece[i][j]=='k'&&color[i][j]=='b') {
						selectKing(i,j);
					}
				}
			}
		}
		else if(sidecolor =='w') {
			for (int i = 0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(piece[i][j]=='r'&&color[i][j]=='w') {
						selectRook(i,j);
					}
					if(piece[i][j]=='q'&&color[i][j]=='w') {
						selectQueen(i,j);
					}
					if(piece[i][j]=='b'&&color[i][j]=='w') {
						selectBishop(i,j);
					}
					if(piece[i][j]=='n'&&color[i][j]=='w') {
						selectKnight(i,j);
					}
					if(piece[i][j]=='p'&&color[i][j]=='w') {
						selectPawn(i,j);
					}
					if(piece[i][j]=='k'&&color[i][j]=='b') {
						selectKing(i,j);
					}
				}
			}
		}

	}
	//**************When call for the king
	public void selectallforking(char sidecolor) {

		if(sidecolor == 'b') {
			for (int i = 0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(piece[i][j]=='r'&&color[i][j]=='b') {
						selectRook(i,j);
					}
					if(piece[i][j]=='q'&&color[i][j]=='b') {
						selectQueen(i,j);
					}
					if(piece[i][j]=='b'&&color[i][j]=='b') {
						selectBishop(i,j);
					}
					if(piece[i][j]=='n'&&color[i][j]=='b') {
						selectKnight(i,j);
					}
				}
			}
		}
		else if(sidecolor =='w') {
			for (int i = 0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(piece[i][j]=='r'&&color[i][j]=='w') {
						selectRook(i,j);
					}
					if(piece[i][j]=='q'&&color[i][j]=='w') {
						selectQueen(i,j);
					}
					if(piece[i][j]=='b'&&color[i][j]=='w') {
						selectBishop(i,j);
					}
					if(piece[i][j]=='n'&&color[i][j]=='w') {
						selectKnight(i,j);
					}
				}
			}
		}

	}
	//Copying this position so it can be saved as lasted possition
	public void saveprevious() {
		for(int i=0;i<xformoves;i++) {
			for(int j = 0;j<yformoves;j++) {
				allmoves[i][j][curmove] = piece[i][j];
				allcolors[i][j][curmove] = color[i][j];
			}
		}
		curmove++;
	}
	//Checks for stealMate
	public boolean stealmate()
	{
		checking = true;
		int count = 0;
		resetU();
		selectall(turn);
		for (int i = 0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(select[i][j]=='s') {
					count++;
				}
			}
		}
		checking = false;
		resetU();
		if(count>0) {
			return false;
		}else {
			//*******************************************************************************************************************fixs this
			return false;
		}
	}
	//Goes one move back
	public void activatePreMove(){
		for(int i=0;i<xformoves;i++) {
			for(int j = 0;j<yformoves;j++) {
				piece[i][j]=allmoves[i][j][curmove-1];
				color[i][j]=allcolors[i][j][curmove-1];
			}
		}
		curmove--;
	}
	//method for resting the board
	public void reset() {

		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				piece[i][j]=pieceOriginal[i][j];
				color[i][j]= colorOriginal[i][j];
			}
		}
		turn = switch_turn(turn);

	}
	//method for showing the rules
	public void rules() {
		//Show a dialog asking the user to select from a combobox:
		String [] possibleValues = {"King", "Queen", "Rook","Knight","Bishop","Pawn"};
		String selectedValue = (String) JOptionPane.showInputDialog (null,
				"Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,
				possibleValues, possibleValues [0]);
		if(selectedValue=="King") {
			piecepic.setIcon(createImageIcon("king.png"));
			descripetion.setIcon(createImageIcon("king dis.png"));
			disPicture.setIcon(createImageIcon("king disPic.png"));
			cdLayout.show (p_card, "5");
		}else if(selectedValue=="Queen") {
			piecepic.setIcon(createImageIcon("queen.png"));
			descripetion.setIcon(createImageIcon("queen dis.png"));
			disPicture.setIcon(createImageIcon("queen disPic.png"));
			cdLayout.show (p_card, "5");
		}
		else if(selectedValue=="Rook") {
			piecepic.setIcon(createImageIcon("rook.png"));
			descripetion.setIcon(createImageIcon("rook dis.png"));
			disPicture.setIcon(createImageIcon("rook disPic.png"));
			cdLayout.show (p_card, "5");
		}
		else if(selectedValue=="Knight") {
			piecepic.setIcon(createImageIcon("knight.png"));
			descripetion.setIcon(createImageIcon("knight dis.png"));
			disPicture.setIcon(createImageIcon("knight disPic.png"));
			cdLayout.show (p_card, "5");
		}
		else if(selectedValue=="Bishop") {
			piecepic.setIcon(createImageIcon("bishop.png"));
			descripetion.setIcon(createImageIcon("bishop dis.png"));
			disPicture.setIcon(createImageIcon("bishop disPic.png"));
			cdLayout.show (p_card, "5");
		}
		else if(selectedValue=="Pawn") {
			piecepic.setIcon(createImageIcon("pawn.png"));
			descripetion.setIcon(createImageIcon("pawn dis.png"));
			disPicture.setIcon(createImageIcon("pawn disPic.png"));
			cdLayout.show (p_card, "5");
		}
	}
	//Opens the file that is saves
	public void open(String filename){
		BufferedReader in;
		try {
		in = new BufferedReader (new FileReader (filename));
		String input = in.readLine ();
		System.out.println(input);
		for(int i=0; i<piece.length; i++) {
		piece[i]=input.toCharArray();
		input = in.readLine ();
		}
		for(int i=0; i<color.length; i++) {
		
		color[i]=input.toCharArray();
		input = in.readLine ();
		}
		in.close ();
		}
		catch (IOException e) {
		System.out.println ("Error opening file " + e);
		}
		redraw();
		}
	//save the file in a .txt
	public void save (String filename) {
		PrintWriter out;
		try {
			out = new PrintWriter (new FileWriter (filename));
			for(int i=0; i<piece.length; i++) {
				out.println (""+String.valueOf(piece[i]));
			}for(int i=0; i<color.length; i++) {
				out.println (""+String.valueOf(color[i]));
			}
			out.close ();
		}
		catch (IOException e) {
			System.out.println ("Error opening file " + e);
		}
	}

	//For Showing pictures
	protected static ImageIcon createImageIcon (String path)
	{ //change the red to your class name
		java.net.URL imgURL = chess.class.getResource (path);
		if (imgURL != null)
		{
			return new ImageIcon (imgURL);
		}
		else
		{
			System.err.println ("Couldn't find file: " + path);
			return null;
		}
	}
}
