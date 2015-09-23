import java.util.Random;;

public class DecodingBoard {
	
	private char [][] decodingBoard;
	private char [] colorCode;
	private char [] colorPeg = {'R', 'Y', 'B', 'G', 'V', 'O'};
	private char keyPeg;
	private Random rng;
	
	private int currentPos;
	private int halfBoard;
	private int row;
	private int column;
	
	public DecodingBoard(){
		this.currentPos = 0;
		this.row = 12;
		this.column = 8;
		this.halfBoard = 4;
		
		this.decodingBoard = new char [row][column];
		this.colorCode = new char [this.halfBoard];
		this.keyPeg = ' ';
		
		for(int i=0; i<row; i++){
			for(int a=0; a<column; a++){
				this.decodingBoard[i][a] = ' ';
			}
		}
		
		for(int i=0; i<this.halfBoard; i++){
			this.colorCode[i] = ' ';
		}
	}
	
	public DecodingBoard(int column, int row){
		this.currentPos = 0;
		this.column = column*2;
		this.row = row;
		this.halfBoard = this.column/2;
		
		this.decodingBoard = new char [this.row][this.column];
		this.colorCode = new char [this.halfBoard];
		this.keyPeg = ' ';
		
		for(int i=0; i<this.row; i++){
			for(int a=0; a<this.column; a++){
				this.decodingBoard[i][a] = ' ';
			}
		}
		
		for(int i=0; i<this.halfBoard; i++){
			this.colorCode[i] = ' ';
		}
	}
	
	public void setColorCode(int pos, char color){
		this.colorCode[pos] = color;
	}
	
	public boolean checkUserInputColors(String colorString){
		boolean colorExist = false;
		char color;
		Character userColor = ' ';
		int counter = 0;
		
		if(colorString.length() == this.halfBoard){
			for(int i=0; i<colorString.length(); i++){
				color = userColor.toUpperCase(colorString.charAt(i));
				for(int a=0; a<6; a++){
					if(color == this.colorPeg[a]){
						counter++;
					}
				}
			}
			if(counter == this.halfBoard){
				colorExist = true;
			}
		}
		return colorExist;
	}
	
	public void setUsersColorPeg(char color, int nrOfGuesses){
		Character pegColor=' ';
		color = pegColor.toUpperCase(color);
		
		this.decodingBoard[nrOfGuesses][this.currentPos] = color;
		this.currentPos++;
		if(this.currentPos == this.halfBoard){
			this.currentPos= 0;
		}
	}
	public int getHalfBoardNr(){
		return this.halfBoard;
	}
	
	public int getTotRows(){
		return this.row;
	}
	
	public void setRandomColorCode(){
		this.rng = new Random();
		
		for(int i=0; i<this.halfBoard; i++){
			int nr = this.rng.nextInt(6);
			if(nr == 0){
				this.colorCode[i] = 'R';
			}
			else if(nr == 1){
				this.colorCode[i] = 'Y';
			}
			else if(nr == 2){
				this.colorCode[i] = 'B';
			}
			else if(nr == 3){
				this.colorCode[i] = 'G';
			}
			else if(nr == 4){
				this.colorCode[i] = 'V';
			}
			else if(nr == 5){
				this.colorCode[i] = 'O';
			}
		}
	}
	
	public void guessColorCode(int guesses, int pos, char color){
		this.decodingBoard[guesses][pos] = color;
	}
	
	public boolean checkCombination(int nrOfGuesses){
		boolean foundCombination = false;
		int counter = 0;
		int keyPegPos = this.halfBoard;
		char [] tempColorCodeHolder = new char[this.halfBoard];
		char [] tempBoard = new char[this.halfBoard];
		
		for(int k=0; k<this.halfBoard; k++){
			tempColorCodeHolder[k] = this.colorCode[k];
			tempBoard[k] = this.decodingBoard[nrOfGuesses][k];
		}
		
		for(int a=0; a<this.halfBoard; a++){
			if(tempBoard[a] == this.colorCode[a]){
				this.decodingBoard[nrOfGuesses][keyPegPos] = 'W';
				tempColorCodeHolder[a] = '-';
				tempBoard[a] = '*';
				keyPegPos++;
				counter++;
			}
				
		}
		for(int a=0; a<this.halfBoard; a++){
			for(int x=0; x<this.halfBoard; x++){
				if(tempBoard[a] == tempColorCodeHolder[x]){
					tempColorCodeHolder[x] = '-';
					tempBoard[a] = '*';
					this.decodingBoard[nrOfGuesses][keyPegPos] = 'B';
					keyPegPos++;
				}
			}
		}
		if(counter == this.halfBoard){
			foundCombination = true;
		}else{
			counter = 0;
		}	
	return foundCombination;
	}
	
	public void resetBoard(){
		this.currentPos = 0;
		for(int i=0; i<this.row; i++){
			for(int a=0; a<this.column; a++){
				this.decodingBoard[i][a] = ' ';
			}
		}
	}
	
	public void showAllColors(){
		System.out.print("colors: ");
		for(int i=0; i<6; i++){
			System.out.print(this.colorPeg[i]+" ");
		}
	}
	
	public void showColorCode(){
		System.out.print("Code : ");
		for(int i=0; i<this.halfBoard; i++){
			System.out.print(this.colorCode[i]+" ");
		}
		System.out.println();
	}
	
	public void showRules(){
		System.out.println("\nThe object of this game is to figure out the auto generated color code.");
		System.out.println("You have "+this.row+" tries to figure out the color code.");
		System.out.println("There are 6 colors used in this game R = Red, Y = Yellow, B = Blue, G = Green, V = Violet and O = Orange.");
		System.out.println("Each time you input your colors you get clues if those colors exist in the color code.");
		System.out.println("The clues are shown on the right side of the board.");
		System.out.println("If you get right color on the correct position you get a W = White pegg.");
		System.out.println("But if you get right color on wrong position you get a B = Black pegg and if you dont guess correct color you dont get any clue pegg's.");
		System.out.println("The clue pegg's doesn't correspond with the users color input. The clues will always show White pegg's first then Black pegg's and last no clue pegg at all");
		
		System.out.println("\nExample: \n     Player's Color input          Clues");
		System.out.println("        [R][Y][Y][G]          (W)(W)(B)( )");
		
		System.out.println("\n     Auto generated color code");
		System.out.println("        [R][G][Y][O]");
		
	}
	
	public void showDecodingBoard(){
		System.out.print("\n      Colors: ");
		for(int i=0; i<6; i++){
			System.out.print(this.colorPeg[i]+" ");
		}
		
		System.out.println("\n\n   Decoding Board");
		
		for(int i=0; i<this.row; i++){
			for(int a=0; a<this.column; a++){
				if(a<this.halfBoard){
					if(a == 0){
						System.out.print("   ");
					}
					System.out.print("["+this.decodingBoard[i][a]+"]");
				}else{
					if(a == this.halfBoard){
						System.out.print("   ");
					}
					System.out.print("("+this.decodingBoard[i][a]+")");
				}
			}
			System.out.print("\n");
		}
		//for testing purpes
		/*System.out.println("\n   Color Code");
		for(int i=0; i<this.halfBoard; i++){
			if(i==0){
				System.out.print("   ");
			}
			System.out.print("["+this.colorCode[i]+"]");
		}
		System.out.println();*/
	}
	
}
