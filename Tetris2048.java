import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

public class Tetris2048 {	//Main class of the game
	
	private static Playfield playfield = new Playfield();	//Playfield object for the game grid
	private static char[] tetroTypes = {'I', 'O', 'S', 'Z', 'T', 'L', 'J'};	//Array containing the character codes of each valid tetromino shape
	private static Random random = new Random();	//Random object for getting random numbers as needed
	private static Color backgroundColor = new Color(40, 60, 90);	//Background colour for menu backgrounds
	private static Font font = new Font("Calibri", Font.PLAIN, 16);	//font for small text 
	private static Font largeFont = new Font("Calibri", Font.PLAIN, 30);	//FONT FOR LARGE TEXT
	
	private static char getNextTetroType() {	//Private method to randomly get a character code for the next tetromino.
		char output = tetroTypes[random.nextInt(7)];
		System.out.println("Picked random piece: "+output);
		return output;
	}
	
	public static Tetromino getNextTetromino() {	//Method to randomly get the next tetromino.
		Tetromino output = new Tetromino(getNextTetroType());
		return output;
	}
	
	private static boolean moveDown(Tetromino tetromino) {	//Method for moving a tetromino down by one tile, returns true if successful.
		System.out.println("moveDown() tried to move down.");
		return tetromino.move(1,0);
	}
	
	private static boolean moveLeft(Tetromino tetromino) {	//Method for moving a tetromino left by one tile, returns true if successful.
		System.out.println("moveLeft() tried to move left.");
		return tetromino.move(0,-1);
	}
	
	private static boolean moveRight(Tetromino tetromino) {	//Method for moving a tetromino right by one tile, returns true if successful.
		System.out.println("moveRight() tried to move right.");
		return tetromino.move(0,1);
	}
	
	private static void placeTetromino(Tetromino tetromino) {	//Method for setting the tiles of a tetromino onto the playfield.
		for(int i=0;i<4;i++) {
			playfield.setValue(tetromino.getTile(i).getI(), tetromino.getTile(i).getJ(), tetromino.getTile(i).getValue());
			System.out.println("Placed a tile of value "+playfield.getValue(tetromino.getTile(i).getI(), tetromino.getTile(i).getJ())+" at ("+ tetromino.getTile(i).getI() + ", "+tetromino.getTile(i).getJ()+").");
		}
	}
	
	public static boolean hasTile(int i, int j) {	//Method for checking if a given coordinate on the playfield has a valid tile.
		System.out.println("hasTile() Checked for tile at ("+i+", "+j+").");
		boolean output = (playfield.getValue(i,j)>0);
		if(output) {
			System.out.println("hasTile() found a tile.");
		}
		return output;
	}
	
	/*	
	 * void drawScreen(int gameState, Tetromino currentPiece, Tetromino nextPiece, int score, int speed):
	 * 
	 *	The draw method used for drawing each frame onto the screen. 
	 *	Takes the current state number of the game, current tetromino on the playfield, next tetromino on the line, current score and the speed of the game.
	 * 	
	 * 	For game state 0 (main menu), draws the logo of the game, a button to start the game and a bar to change the game speed.
	 * 	
	 * 	For game state 1 (in game), draws the current playfield to the left side of the screen (8 tiles by 12 tiles, with their respective colours and values), along with the current moving piece.
	 * 	to the right side of the screen, draws a small and a large rectangle, displaying the current score and the upcoming tile respectively.
	 * 	
	 * 	For game state 2 (game over), draws a rectangle informing the player of the loss along with their final score.
	 * 	Draws two buttons below to start a new game and to return to the main menu.
	 * 
	 * 	For game state 3 (win screen), draws a rectangle informing the player of the win along with their final score.
	 * 	Also draws two buttons below to start a new game and to return to the main menu.
	 *
	 * */
	
	public static void drawScreen(int gameState, Tetromino currentPiece, Tetromino nextPiece, int score, int speed) {
		StdDraw.clear(backgroundColor);	//Set the background before drawing
		//double currentDrawXpos, currentDrawYpos;	-unused
		StdDraw.setFont(font);	//Set the font for text
		
		//Different colours for each tile, GUI element and text
		Color text = new Color(255,255,255);
		Color button = new Color(226,135,67);
		Color emptyTile = new Color(100, 100, 100);
		Color twoTile = new Color(37,150,190);
		Color fourTile = new Color(193,210,39);
		Color eightTile = new Color(144,34,136);
		Color sixteenTile = new Color(40,34,144);
		Color thirtytwoTile = new Color(144,109,34);
		Color sixtyfourTile = new Color(173,243,77);
		Color onehunderedtwentyeightTile = new Color(185,77,243);
		Color twohunderedfiftysixTile = new Color(243,127,77);
		Color fivehunderedtwelveTile = new Color(220,103,147);
		Color onethousandtwentyfourTile = new Color(103,220,175);
		Color twothousandfortyeightTile = new Color(93,97,66);
		
		switch (gameState) {	//Switch case to draw different screens based on the state of the game.
		case 0:	//Main menu
			String imgFile = "images/menu_image.png";	//Display the title logo of the game.
			StdDraw.picture(7.5,8.5,imgFile);
			
			StdDraw.setPenColor(button);	//Draw the button for starting a new game
			StdDraw.filledRectangle(7.5,4,3,0.6);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5,4,"Start Game");
			
			StdDraw.setPenColor(emptyTile);	//Draw the field containing the current speed value
			StdDraw.filledRectangle(7.5,2.5,3,0.6);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5,2.5,"Speed: "+speed);
			
			//Draw the individual buttons for decreasing and increasing the speed of the game respectively.
			StdDraw.setPenColor(button);
			StdDraw.filledRectangle(5.1,2.5,0.5,0.5);
			StdDraw.setPenColor(text);
			StdDraw.text(5.1,2.5,"-");
			
			StdDraw.setPenColor(button);
			StdDraw.filledRectangle(9.9,2.5,0.5,0.5);
			StdDraw.setPenColor(text);
			StdDraw.text(9.9,2.5,"+");
			
		break;
		
		case 1:	//In game
		for (int i=0;i<12;i++) {	//Iterate over each tile on the playfield
			for (int j=0;j<8;j++) {
				
				switch(playfield.getValue(11-i,j)) {	//Draw each tile with the appropriate colour based on their value, then draw the text indicating their value.
				case -1:	//Invalid tiles are drawn as empty, empty tiles do not have any text.
					StdDraw.setPenColor(emptyTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					break;
				case 2:
					StdDraw.setPenColor(twoTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "2");
					break;
				case 4:
					StdDraw.setPenColor(fourTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "4");
					break;
				case 8:
					StdDraw.setPenColor(eightTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "8");
					break;
				case 16:
					StdDraw.setPenColor(sixteenTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "16");
					break;
				case 32:
					StdDraw.setPenColor(thirtytwoTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "32");
					break;
				case 64:
					StdDraw.setPenColor(sixtyfourTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "64");
					break;
				case 128:
					StdDraw.setPenColor(onehunderedtwentyeightTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "128");
					break;
				case 256:
					StdDraw.setPenColor(twohunderedfiftysixTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "256");
					break;
				case 512:
					StdDraw.setPenColor(fivehunderedtwelveTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "512");
					break;
				case 1024:
					StdDraw.setPenColor(onethousandtwentyfourTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "1024");
					break;
				case 2048:
					StdDraw.setPenColor(twothousandfortyeightTile);
					StdDraw.filledRectangle(j, i, 0.47, 0.47);
					StdDraw.setPenColor(text);
					StdDraw.text(j, i, "2048");
					break;
				default:	//If a tile's value is invalid, print to warn.
					System.out.println("Could not draw playfield tile to screen: Invalid value.");
					break;
				}
				for(int m=0;m<4;m++) {	//After drawing the playfield, draw each tile of the current tetromino on top.
					switch(currentPiece.getTile(m).getValue()) {	//Draw each tile with the appropriate colour based on their value, then draw the text indicating their value.
					case 2:
						StdDraw.setPenColor(twoTile);
						StdDraw.filledRectangle(currentPiece.getTile(m).getJ(), 11-currentPiece.getTile(m).getI(), 0.47, 0.47);
						StdDraw.setPenColor(text);
						StdDraw.text(currentPiece.getTile(m).getJ(), 11-currentPiece.getTile(m).getI(), "2");
						break;
					case 4:
						StdDraw.setPenColor(fourTile);
						StdDraw.filledRectangle(currentPiece.getTile(m).getJ(), 11-currentPiece.getTile(m).getI(), 0.47, 0.47);
						StdDraw.setPenColor(text);
						StdDraw.text((currentPiece.getTile(m).getJ()), 11-currentPiece.getTile(m).getI(), "4");
						break;
					default:	//If a tile's value is invalid, print to warn.
						System.out.println("Could not draw currentTile tile to screen: Invalid value.");
						break;
					}
				}
			}
		}
		
		StdDraw.setPenColor(emptyTile);	//Draw the small rectangle containing the current score.
		StdDraw.filledRectangle(11.5, 9, 3, 1.47);
		StdDraw.setPenColor(text);
		StdDraw.text(11.5, 9.5, "Score:");
		StdDraw.setFont(largeFont);
		StdDraw.text(11.5, 8.5, ""+score);
		StdDraw.setFont(font);
		
		StdDraw.setPenColor(emptyTile);	//Draw the large rectangle containing the next piece
		StdDraw.filledRectangle(11.5, 3.5, 3, 3.5);
		StdDraw.setPenColor(text);
		StdDraw.text(11.5, 6.5, "Next Piece:");
		
		for(int m=0;m<4;m++) {	//Draw the next piece onto the rectangle; this function is the same function as drawing the current piece with a preset offset.
			switch(nextPiece.getTile(m).getValue()) {
			case 2:
				StdDraw.setPenColor(twoTile);
				StdDraw.filledRectangle(9+nextPiece.getTile(m).getJ(), 3+nextPiece.getTile(m).getI(), 0.47, 0.47);
				StdDraw.setPenColor(text);
				StdDraw.text(9+nextPiece.getTile(m).getJ(), 3+nextPiece.getTile(m).getI(), "2");
				break;
			case 4:
				StdDraw.setPenColor(fourTile);
				StdDraw.filledRectangle(9+nextPiece.getTile(m).getJ(), 3+nextPiece.getTile(m).getI(), 0.47, 0.47);
				StdDraw.setPenColor(text);
				StdDraw.text((9+nextPiece.getTile(m).getJ()), 3+nextPiece.getTile(m).getI(), "4");
				break;
			default:	//If a tile's value is invalid, print to warn.
				System.out.println("Could not draw nextTile tile to screen: Invalid value.");
				break;
			}
		}
		break;
		
		case 2:	//Game over
			StdDraw.setPenColor(emptyTile);	//Draw the main rectangle containing the "Game Over" text and the score.
			StdDraw.filledRectangle(7.5,7.5,6,3);
			StdDraw.setFont(largeFont);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5, 9.5, "Game Over");
			StdDraw.setFont(font);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5, 7.5, "Score: "+score);
			
			StdDraw.setPenColor(button);	//Draw the button for starting a new game.
			StdDraw.filledRectangle(7.5,2.7,3,0.6);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5,2.7,"Start New Game");
			
			StdDraw.setPenColor(button);	//Draw the button for returning to the main menu.
			StdDraw.filledRectangle(7.5,1.3,3,0.6);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5,1.3,"Main Menu");
			
		break;
		
		case 3:	//Win screen
			//Same draw process as game over, only the text displays "You win!".
			StdDraw.setPenColor(emptyTile);
			StdDraw.filledRectangle(7.5,7.5,6,3);
			StdDraw.setFont(largeFont);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5, 9.5, "You Win!");
			StdDraw.setFont(font);
			
			StdDraw.setPenColor(text);
			StdDraw.text(7.5, 7.5, "Score: "+score);
			
			StdDraw.setPenColor(button);
			StdDraw.filledRectangle(7.5,2.7,3,0.6);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5,2.7,"Start New Game");
			
			StdDraw.setPenColor(button);
			StdDraw.filledRectangle(7.5,1.3,3,0.6);
			StdDraw.setPenColor(text);
			StdDraw.text(7.5,1.3,"Main Menu");
			
		break;
		
		
		default:	//If an invalid gameState is given, print to warn.
			System.out.println("Could not draw screen: Invalid gameState.");
		break;
		
		}
		StdDraw.show();	//After the current frame is drawn, show the frame.
	}
	
	
	public static void main(String[] args) {	//Main method for running the game
		int score = 0;	//Initialise the score to zero.
		
		int gridI = 12;	//Set the size of the game window.
		int gridJ = 16;
		StdDraw.setCanvasSize(gridJ*40, gridI*40);
		StdDraw.setXscale(-0.5, gridJ-0.5);
		StdDraw.setYscale(-0.5, gridI-0.5);
		StdDraw.enableDoubleBuffering();	//Double buffering is enabled to allow animations.
		
		Tetromino currentPiece = getNextTetromino();	//Initialise the two tetrominoes.
		Tetromino nextPiece = getNextTetromino();
		
		long loopStartTime, loopEndTime;	//Variables for calculating runtime.
		
		//Initialise the game variables before first run:
		int speed = 6;	//Falling speed of the current tetromino
		long time = System.currentTimeMillis();	//Variable for the current time at execution
		long nextMoveTime = time + (speed*100);	//Variable for tracking the next move of the tetromino
		boolean lastLeftValue=false;	//Boolean to track if the left arrow button is held.
		boolean lastRightValue=false;	//Boolean to track if the right arrow button is held.
		boolean lastDownValue=false;	//Boolean to track if the down arrow button is held.
		boolean lastClickValue=false;	//Boolean to track if the left mouse button is held.
		boolean lastRotate=false;		//Boolean to track if the space bar is held.
		int gameState = 0; 		//Variable for the game screen; 0: main menu, 1: in game, 2: game over screen, 3: win screen
		int moveCounter = 0;	//Variable to check if a tetromino has managed to move down before falling.
		
		while(true) {	//Game loop:
			
			loopStartTime = System.currentTimeMillis();	//Get the start time of loop execution.
			switch(gameState) {	//Game logic for each game state
			case 0:	//Main menu
				if(StdDraw.isMousePressed()) {	//If left mouse button is pressed,
					double mouseX=StdDraw.mouseX(), mouseY=StdDraw.mouseY();	//Get the position of the mouse,
					if(mouseX>7.5-(3)&&mouseX<7.5+(3)) {	//If the mouse's position is within the "Start game" button,
						if(mouseY>4-(0.6)&&mouseY<4+(0.6)) {
							playfield.clear();	//Clear the playfield and initialise every value.
												//This is done to ensure returning to main menu and starting a game will not start a game with the last played values.
												//This result in the game values being set twice at first launch.
							score = 0;
							
							currentPiece = getNextTetromino();
							nextPiece = getNextTetromino();
							
							time = System.currentTimeMillis();
							nextMoveTime = time + 600;
							lastLeftValue=false;
							lastRightValue=false;
							lastDownValue=false;
							lastRotate=false;
							
							moveCounter = 0;
							gameState=1;	//1: in game
						}
					}
				}
				//If the speed decrease or increase button is pressed, check if the state of the left mouse button changed since last loop.
				//This is done to prevent the value from changing at every run of the loop.
				if(lastClickValue!=StdDraw.isMousePressed()) {
					double mouseX=StdDraw.mouseX(), mouseY=StdDraw.mouseY();
					if(lastClickValue==false) {	
						if(mouseX>5.1-(0.5)&&mouseX<5.1+(0.5)) {
							if(mouseY>2.5-(0.5)&&mouseY<2.5+(0.5)) {
								if(speed>1) {	//Cap the speed value at 1 minimum.
									speed--;
								}		
							}
						}
						if(mouseX>9.9-(0.5)&&mouseX<9.9+(0.5)) {
							if(mouseY>2.5-(0.5)&&mouseY<2.5+(0.5)) {
								if(speed<15) {	//Cap the speed value at 15 maximum.
									speed++;
								}		
							}
						}
						lastClickValue=true;
					}
					else {
						lastClickValue=false;
					}
				}
			break;
			
			case 1:	//In game
				time = System.currentTimeMillis();	//Get current time at execution
				if(nextMoveTime<time) {	//If we passed the time at which the tetromino was to be moved down, try to move down.
					boolean setTimer = true;	//Boolean to aid setting the time at which the next move down will be performed.
					
					//Every tile operation to be done is ONLY DONE WHEN the state of the playfield has changed.
					//The state of the playfield only changes when a tetromino is placed.
					//Otherwise, as no tile is capable of changing independent from the tetrominoes, no operation needs to be done .
					
					if(!moveDown(currentPiece)) {	//If the tetromino failed to move down,
						if(moveCounter<1) {	//And if the tetromino never managed to move down before,
							gameState=2;	//Set the game state; 2: game over
						}
						moveCounter = 0;	//Reset the move counter for the next tetromino
						placeTetromino(currentPiece);	//Place the current tetromino onto the playfield
						currentPiece = nextPiece;	//Set the next tetromino as the current tetromino
						nextPiece = getNextTetromino();	//Get a new tetromino for the next-next tetromino
						nextMoveTime = time+(speed*100);	//Calculate and set the next time at which the tetrominu will be moved down.
						setTimer=false;	//Set the boolean to false to indicate that the nextMoveTime has been set.
						
						boolean merge=false;	//booleans to check if any tile has been merged or destoryed.
						boolean destroy=false;
						
						//Loop to merge equal tiles.
						//For every tile on the playfield except the bottom row,
						//Check if the tile below has the same value, merge and increment score by the new tile's value if yes.
						//Set the merge boolean to true to indicate that the state of the playfield has changed,
						//Do the merge operations again if yes to merge in chain.
						do {
							merge=false;
							for(int i=0;i<11;i++) {
								for(int j=0;j<8;j++) {
									if(playfield.getValue(i,j)>0) {
										if(playfield.getValue(i+1,j)==playfield.getValue(i,j)) {
											playfield.setValue(i+1,j,playfield.getValue(i+1,j)*2);
											score+=playfield.getValue(i+1,j);
											playfield.setValue(i,j,-1);
											merge=true;
										}
									}
								}
							}
						}while(merge);
						
						//Loop to destroy tiles that are not connected.
						//For every tile on the playfield including the bottom row,
						//Check if the tile is connected to any other tile, destroy and increment score by the tile's value if no.
						//Set the destroy boolean to true to indicate that the state of the playfield has changed,
						//Do the destroy operations again to destroy in chain.
						do {
							destroy=false;
							for(int i=0;i<12;i++) {
								for(int j=0;j<8;j++) {
									if(playfield.getValue(i,j)>0&&!playfield.isConnected(i,j)) {
										System.out.println("No connections found for ("+i+", "+j+"), destroying...");
										score+=playfield.getValue(i,j);
										playfield.setValue(i,j,-1);
										destroy=true;
									}
								}
							}
						}while(destroy);
						
						//Loop to destroy full rows, comes after the merge and destroy operations as they take precedence
						//For every row on the playfield, assume it is full,
						//If any tile on the row is invalid, it is not full, set it as such and stop checking.
						//If the row is full, set all tiles on the row invalid and move every tile above one row down.
						boolean fullRow;
						for(int i=0;i<12;i++) {
							fullRow = true;
							for(int j=0;j<8;j++) {
								if(playfield.getValue(i,j)<0) {
									fullRow=false;
									break;
								}
							}
							if(fullRow) {
								for(int m=0;m<8;m++) {
									score+=playfield.getValue(i,m);
									playfield.setValue(i,m,-1);
								}
								for(int k=i-1;k>-1;k--) {
									for(int l=0;l<8;l++) {
										playfield.setValue(k+1,l,playfield.getValue(k,l));
									}
								}
							}
						}	
						
						//Loop to check if value of any tile on the playfield has reached 2048.
						//If yes, set game state; 3: win screen
						for(int i=0;i<12;i++) {
							for(int j=0;j<8;j++) {
								if(playfield.getValue(i,j)==2048) {
									gameState = 3;
								}
							}
						}
					}	//All tile operations are done at this point.
					
					//If the time of the next move down has not been set above, set it here.
					if (setTimer) {
						nextMoveTime = time+(speed*100);
						moveCounter++;
					}
				}
				
				//If the left, right or down arrow button or the space bar is pressed, check if the state of the button has changed since last loop.
				//This is done to prevent the pieces moving at every loop.
				if(lastLeftValue!=StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
					if(lastLeftValue==false) {
						moveLeft(currentPiece);
						lastLeftValue = true;
					}
					else {
						lastLeftValue=false;
					}
				}
				if(lastRightValue!=StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
					if(lastRightValue==false) {
						moveRight(currentPiece);
						lastRightValue=true;
					}
					else {
						lastRightValue=false;
					}
				}
				if(lastDownValue!=StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
					if(lastDownValue==false) {
						if(moveDown(currentPiece)) {
							moveCounter++;
						}
						lastDownValue=true;
						nextMoveTime = time+(speed*100);
					}
					else {
						lastDownValue=false;
					}
				}
				if(lastRotate!=StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
					if(lastRotate==false) {
						currentPiece.rotate();
						lastRotate=true;
					}
					else {
						lastRotate=false;
					}
				}
			break;
			case 2:	//Game over
				if(StdDraw.isMousePressed()) {	//If the left mouse button is pressed,
					double mouseX=StdDraw.mouseX(), mouseY=StdDraw.mouseY();	//Get the loaction of the mouse.
					if(mouseX>7.5-(3)&&mouseX<7.5+(3)) {	//If the "Start new game" button is pressed, initialise game variables and set game state; 1: in game
						if(mouseY>2.7-(0.6)&&mouseY<2.7+(0.6)) {
							playfield.clear();
							
							score = 0;
							
							currentPiece = getNextTetromino();
							nextPiece = getNextTetromino();
							
							time = System.currentTimeMillis();
							nextMoveTime = time + 600;
							lastLeftValue=false;
							lastRightValue=false;
							lastDownValue=false;
							lastRotate=false;
							
							moveCounter = 0;
							gameState=1;
						}
					}
					if(mouseX>7.5-(3)&&mouseX<7.5+(3)) {	//If the "Main menu" button is pressed, set game state; 0: main menu
						if(mouseY>1.3-(0.6)&&mouseY<1.3+(0.6)) {
							gameState=0;
						}
					}	
				}
			break;
			case 3:	//Win screen
				
				//Code below is the same code as the "Game over" screen.
				if(StdDraw.isMousePressed()) {
					double mouseX=StdDraw.mouseX(), mouseY=StdDraw.mouseY();
					if(mouseX>7.5-(3)&&mouseX<7.5+(3)) {
						if(mouseY>2.7-(0.6)&&mouseY<2.7+(0.6)) {
							playfield.clear();
							
							score = 0;
							
							currentPiece = getNextTetromino();
							nextPiece = getNextTetromino();
							
							time = System.currentTimeMillis();
							nextMoveTime = time + 600;
							lastLeftValue=false;
							lastRightValue=false;
							lastDownValue=false;
							lastRotate=false;
							
							moveCounter = 0;
							gameState=1;
						}
					}
					if(mouseX>7.5-(3)&&mouseX<7.5+(3)) {
						if(mouseY>1.3-(0.6)&&mouseY<1.3+(0.6)) {
							gameState=0;
						}
					}	
				}
			break;
			}
		
			drawScreen(gameState, currentPiece, nextPiece, score, speed);	//All game related logic is done, send the variables to draw the frame.
			loopEndTime = System.currentTimeMillis();	//Check the end time of the loop.
			long executionTime=loopEndTime-loopStartTime;	//Calculate the total execution time of the loop.
			if(executionTime<20) {	//If the loop took less than 20 milliseconds,
				try {Thread.sleep(20-executionTime);}	//Sleep for the rest of the execution to not run the game logic repeatedly for no reason.
				catch(InterruptedException ie) {System.out.println("Thread Interrupted"); Thread.currentThread().interrupt();}
			}
		}
	}
}

class Playfield {	//Playfield class for the tile array and its methods
	
	private Tile[][] playfield = new Tile[12][8];	//Tile array for the game grid.
	
	public Playfield() {	//Constructor for the playfield
		for(int i=0;i<playfield.length;i++) {
			for(int j=0;j<playfield[i].length;j++) {
				playfield[i][j] = new Tile(i,j);	//Initialise each tile on the grid to -1 using the Tile(i,j) constructor.
			}
		}
	}
	
	public boolean isConnected(int i, int j) {	//Method to check if a given tile is connected to any valid tile
		if (getValue(i,j)<0) {	//If the value of a tile at the given coordinates is invalid,
			System.out.println("Tried to check connections for an empty tile.");	//Print to warn,
			return true;	//and return true to avoid errors.
		}
		if(j-1>-1) {	//If the tile to the left is inbounds,
			System.out.println("Checked for a tile left of ("+i+", "+j+").");
			if(getValue(i,j-1)>0) {	//Check if tile is valid,
				return true;	//return true if it is.
			}
		}
		if(j+1<8) {	//If the tile to the right is inbounds,
			System.out.println("Checked for a tile right of ("+i+", "+j+").");
			if(getValue(i,j+1)>0) {	//Check if the tile is valid,
				return true;	//return true if it is.
			}
		}
		if(i-1>-1) {	//If the tile above is inbounds,
			System.out.println("Checked for a tile above ("+i+", "+j+").");
			if(getValue(i-1,j)>0) {	//Check if the tile is valid,
				return true;	//return true if it is.
			}
		}
		if(i+1<12) {	//If the tile below is inbounds,
			System.out.println("Checked for a tile below ("+i+", "+j+").");
			if(getValue(i+1,j)>0) {	//Check if the tile is valid,
				return true;	//return true if it is.
			}
		}
		return false;	//If none of the checks above returned anything then tile is not connected, return false.
	}
	
	public void clear() { //Method for clearing the grid
		for(int i=0;i<12;i++) {
			for(int j=0;j<8;j++) {
				setValue(i,j,-1);	//Set the value of each tile on the playfield to the invalid value.
			}
		}
	}
	
	//Getter and setter methods for the tiles and values.
	public Tile getTile(int i, int j) {
		return  playfield[i][j];
	}
	public void setTile(Tile tile) {
		playfield[tile.getI()][tile.getJ()] = tile;
	}
	public int getValue(int i, int j) {
		return playfield[i][j].getValue();
	}
	public void setValue(int i, int j, int value) {
		playfield[i][j].setValue(value);
	}
	
}