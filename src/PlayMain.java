import java.util.Scanner;

public class PlayMain {

	public static void main(String[] args) {
		DecodingBoard board = new DecodingBoard();
		Scanner sc = new Scanner(System.in);
		String colorCode;
		boolean winner = false;
		int nr=0;
		int nrOfGuesses = 0;
		
		do{
			System.out.println("\n1. Play");
			System.out.println("2. Rules");
			System.out.println("3. Exit");
			System.out.print("\nChoice: ");
			
			if(sc.hasNextInt()){
				nr=sc.nextInt();
				if(nr<1 || nr>3){
					System.out.println("\nInput a valid number!");
				}
			}else{
				sc.next();
				System.out.println("\nInput a valid number!");
			}
			switch(nr){
				case 1:
					board.setRandomColorCode();
					board.showDecodingBoard();
					do{
						char pegColor;
						boolean checkUserInputColor = true;
						do{
							System.out.println("\n(no space)");
							System.out.print("Input "+board.getHalfBoardNr()+" colors: ");
							colorCode = sc.next();
							checkUserInputColor = board.checkUserInputColors(colorCode);
							if(colorCode.length() != board.getHalfBoardNr()){
								if(colorCode.length() > board.getHalfBoardNr()){
									System.out.println("\nThere are to many colors!\n");
								}else
									System.out.println("\nThere are to few colors!\n");								
								board.showAllColors();
							}
							else if(checkUserInputColor != true){
								System.out.print("\nInput one of these ");
								board.showAllColors();
							}
						}while(colorCode.length() != board.getHalfBoardNr() || !checkUserInputColor);
						
						for(int i=0; i<board.getHalfBoardNr(); i++){
							pegColor = colorCode.charAt(i);
							board.setUsersColorPeg(pegColor, nrOfGuesses);
						}
						winner = board.checkCombination(nrOfGuesses);
						board.showDecodingBoard();
						nrOfGuesses++;
						if(winner == true){
							System.out.println("\n   Congrats you broke the code!");
						}
						else if(nrOfGuesses == board.getTotRows()){
							System.out.println("\nGAME OVER");
							board.showColorCode();
						}
					}while(!winner && nrOfGuesses != board.getTotRows());
					nrOfGuesses = 0;
					board.resetBoard();
					break;
				case 2:
					board.showRules();
					break;
				case 3:
					System.out.println("\nHave a nice day!");
					break;
			}
		}while(nr!=3);
	}
}
