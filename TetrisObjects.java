import java.util.Random;

class Tile {	//Tile class for tiles in the playfield and in a tetromino
	
	private int value;	//Numerical value of the tile.
	private int i, j; 	//Position of the tile in the playfield.
						//The position value stored here is redundant, as a tile's position on the playfield is an inherent quality of it.
						//However, storing a tile's location again here allows us to run functions without having to search for the tile on the playfield every time.
	
	public Tile() {	//Default constructor
		value = -1;	//-1 is used to indicate invalid values.
		i = -1;
		j = -1;
	}
	
	public Tile(int i, int j) {	//Constructor with tile's location on the playfield
		value = -1;	//-1 is used to indicate invalid values
		this.i = i;	//Given location values are assigned to tile's location variables.
		this.j = j;
	}
	
	public Tile(int i, int j, int value) {	//Constructor with tile's location and value
		this.value = value;	//Given value is assigned to tile's value variable.
		this.i = i;			//Given location variables are assigned to tile's location variables.
		this.j = j;
	}
	
	//Getter and setter methods for a tile's value and location.
	public int getValue() {
		return value;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	public void setJ(int j) {
		this.j = j;
	}
	
}

class Tetromino {	//Tetromino class for different shapes in game
	private char type;	//Type of the tetromino, each piece is coded with a character based on their resemblance.
	private Tile[] tetromino = new Tile[4];	//Tile array holding each tile belonging to the tetromino, every tetromino consists of 4 tiles.
	private Random random = new Random();	//Random object for randomly assigning values as needed.
	private int originNumber;	//Index of the origin tile of the tetromino, used for the rotation function.
	
	public Tetromino(char type) {	//Constructor for the tetromino object
		this.type = type;	//Type of the tetromino is initialised to the given value
		switch(this.type) {	//Switch case for initialising the tetromino to a given shape according to the type variable.
				
			//For each possible shape, its tiles are set to locations on the playfield, in a way that will make the end tiles look similar to their desired shape.
			//Each tile's value is randomly assigned to a value of 2 or 4.
			case 'I':
				tetromino[0]=new Tile(0,2,(2*(1+random.nextInt(2))));
				tetromino[1]=new Tile(0,3,(2*(1+random.nextInt(2))));
				tetromino[2]=new Tile(0,4,(2*(1+random.nextInt(2))));
				tetromino[3]=new Tile(0,5,(2*(1+random.nextInt(2))));
				originNumber=1;
				break;
			case 'O':
				tetromino[0]=new Tile(0,2,(2*(1+random.nextInt(2))));
				tetromino[1]=new Tile(1,2,(2*(1+random.nextInt(2))));
				tetromino[2]=new Tile(0,3,(2*(1+random.nextInt(2))));
				tetromino[3]=new Tile(1,3,(2*(1+random.nextInt(2))));
				originNumber=1;
				break;
			case 'S':
				tetromino[0]=new Tile(0,3,(2*(1+random.nextInt(2))));
				tetromino[1]=new Tile(1,3,(2*(1+random.nextInt(2))));
				tetromino[2]=new Tile(1,2,(2*(1+random.nextInt(2))));
				tetromino[3]=new Tile(2,2,(2*(1+random.nextInt(2))));
				originNumber=1;
				break;
			case 'Z':
				tetromino[0]=new Tile(0,2,(2*(1+random.nextInt(2))));
				tetromino[1]=new Tile(1,2,(2*(1+random.nextInt(2))));
				tetromino[2]=new Tile(1,3,(2*(1+random.nextInt(2))));
				tetromino[3]=new Tile(2,3,(2*(1+random.nextInt(2))));
				originNumber=1;
				break;
			case 'T':
				tetromino[0]=new Tile(0,2,(2*(1+random.nextInt(2))));
				tetromino[1]=new Tile(0,3,(2*(1+random.nextInt(2))));
				tetromino[2]=new Tile(0,4,(2*(1+random.nextInt(2))));
				tetromino[3]=new Tile(1,3,(2*(1+random.nextInt(2))));
				originNumber=1;
				break;
			case 'L':
				tetromino[0]=new Tile(1,2,(2*(1+random.nextInt(2))));
				tetromino[1]=new Tile(0,2,(2*(1+random.nextInt(2))));
				tetromino[2]=new Tile(0,3,(2*(1+random.nextInt(2))));
				tetromino[3]=new Tile(0,4,(2*(1+random.nextInt(2))));
				originNumber=1;
				break;
			case 'J':
				tetromino[0]=new Tile(1,4,(2*(1+random.nextInt(2))));
				tetromino[1]=new Tile(0,4,(2*(1+random.nextInt(2))));
				tetromino[2]=new Tile(0,3,(2*(1+random.nextInt(2))));
				tetromino[3]=new Tile(0,2,(2*(1+random.nextInt(2))));
				originNumber=1;
				break;
			default:
				//If a shape not valid for the possible shapes is given, print on the console to warn.
				System.out.println("Piece could not be generated: Invalid type.");
				break;
		}
	}
	
	public boolean move(int i, int j) {	//Function for moving a tetromino by a given amount, returns true if movement was successful, false otherwise.
		boolean canMove = true;	//Variable for checking the success of movement, assumed true at the start.
		for(Tile tile : tetromino) {	//for each tile in a tetromino,
			if(tile.getI()+i>11 || tile.getJ()+j>7 || tile.getJ()+j<0 || Tetris2048.hasTile(tile.getI()+i,tile.getJ()+j)) {
				System.out.println("Movemet failed.");
				canMove=false;			//If any tile's movement is blocked by something, set the success to false.
				break;					//Only one tile being blocked is enough for the movement to be invalid, break to not check the rest of the tiles.
			}
		}
		if(canMove) {	//If the movement was successful,
			for(Tile tile : tetromino) {	//for each tile in a tetromino,
				tile.setI(tile.getI()+i);	//Move the tile by the specified amount.
				tile.setJ(tile.getJ()+j);
			}
		}
		return canMove;	//Return the success of the movement. 
	}
	
	public boolean rotate() {	//Function for rotating a tetromino clockwise.
		boolean canRotate = true;	//Variable for checking the success of rotation, assumed true at the start.
		for(Tile tile : tetromino) {	//for each tile in a tetromino,
			int iDiff, jDiff, temp;		//Variables for storing each tile's difference from the tetromino's origin tile and a temporary variable used for swapping.
			iDiff = tile.getI() - tetromino[originNumber].getI();	//Find the tile's difference from the origin.
			jDiff = tile.getJ() - tetromino[originNumber].getJ();
			//Swap the i and j differences around and switch the j difference's sign to rotate the tile around the origin tile by 90 degrees.
			temp = iDiff;
			iDiff = jDiff;
			jDiff = temp*-1;
			if(tetromino[originNumber].getI()+iDiff>11 || tetromino[originNumber].getI()+iDiff<0 || tetromino[originNumber].getJ()+jDiff>7 || tetromino[originNumber].getJ()+jDiff<0 || Tetris2048.hasTile(tetromino[originNumber].getI()+iDiff,tetromino[originNumber].getJ()+jDiff)) {
				System.out.println("Rotation failed.");	
				canRotate=false;	//If any tile's rotation is blocked, set the success to false.
				break;				//Only one tile being blocked is enough for the rotation to be invalid, break to not check the rest of the tiles.
			}
		}
		if(canRotate) {	//If the rotation was successful,
			for(Tile tile : tetromino) {	//for each tile in a tetromino,
				int iDiff, jDiff, temp;		//Variables for storing each tile's difference from the tetromino's origin tile and a temporary variable used for swapping.
				iDiff = tile.getI() - tetromino[originNumber].getI();	//Find the tile's difference from the origin
				jDiff = tile.getJ() - tetromino[originNumber].getJ();
				//Swap the i and j differences around and switch the j difference's sign to rotate the tile around the origin tile by 90 degrees.
				temp = iDiff;
				iDiff = jDiff;
				jDiff = temp*-1;
				tile.setI(tetromino[originNumber].getI()+iDiff);	//Add the tile's difference from the origin tile to the origin tile's coordinates to get the tile's new coordinates.
				tile.setJ(tetromino[originNumber].getJ()+jDiff);
			}
		}
		return canRotate;	//Return the success of the rotation.
	}
	
	
	//Getter methods for the tiles of the tetromino and a tile's value.
	//No setter methods as they are not needed.
	public int[] getBlockValue(int number) {
		int[] output = {tetromino[number].getI(),tetromino[number].getJ(), tetromino[number].getValue()};
		return output;
	}
	
	public Tile getTile(int number) {
		return tetromino[number];
	}
}