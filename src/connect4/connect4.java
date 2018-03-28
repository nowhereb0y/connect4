package connect4;
import java.util.*;

public class connect4 {
	
	private static final int ROW = 6;	
	private static final int COLUMN = 7;
	static int ncol;
	static int mossaplayer;
	static int partite;
	static int pareggi;
	static int count = 0;
	static int rivincita;
	
	static int mosse = 0;
	static int vittoria = -1;
	public static void main (String[] args) {
		
		stampaBannerAvvio();
		System.out.println("Benvenuti in Forza 4 " + args[1] + " !" ); /** La documentazione */

		Matrix campoGioco = new Matrix (ROW,COLUMN);	//* istanzio la Matrice che userò come campo da gioco */
	
		campoGioco.riempiCampo();				//* chiamo il metodo per riempire la matrice */
		campoGioco.stampaCampo();				//* chiamo il metodo per stampare la matrice */
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\t\t Giocatore 1, inserisci il tuo nome: ");
		
		Player Player1 = new Player (input.nextLine());			//Istanzio il giocatore 1
		System.out.println("\t\t\nBenvenuto " + Player1.getNome() + " !\n" ); /** La documentazione */
		Player1.setSymbol('X');
		System.out.println(Player1.getNome() + ", il tuo simbolo è " + Player1.getSymbol() ); /** La documentazione */
		Player1.setVittorie(0);
		
		System.out.println("\t\t Giocatore 2, inserisci il tuo nome: ");
		
		Player Player2 = new Player (input.nextLine());
		System.out.println("\t\t\nBenvenuto " + Player2.getNome() + " !" ); /** La documentzione */				
		Player2.setSymbol('@');
		System.out.println(Player2.getNome() + ", il tuo simbolo è " + Player2.getSymbol() ); /** La documentazione */
		Player2.setVittorie(0);
		

		//
		
		while (vittoria==-1) 
		{
			if (mosse==ROW*COLUMN||mosse==(1+(2*ROW*COLUMN)))
			{
				campoGioco.stampaCampo();
				System.out.println("\n\n\t\tPartita terminata, non ci sono vincitori!\n\n");
				partite++;
				pareggi++;
				vittoria=-3;
			}
		
			else if (mosse % 2 == 0)
			{
				mossaplayer = chiediMossa(Player1.getNome(), campoGioco, input );
				campoGioco.insertSymbol(Player1.getSymbol(), mossaplayer);
				mosse++;
				vittoria=campoGioco.checkVittoria(Player1.getSymbol());
				if (vittoria!= -1) 
				{
					vittoria(vittoria);
					Player1.setVittorie(Player1.getVittorie() +1);
					fineTurno(Player1, Player2,pareggi);
					rivincita=rivincita(count,partite,mosse,input);
					if(rivincita==1)
					{
						campoGioco.riempiCampo();
						vittoria=-1;
					}
				}
			}
			else if (mosse % 2 == 1)
			{
				mossaplayer = chiediMossa(Player2.getNome(), campoGioco, input );
				campoGioco.insertSymbol(Player2.getSymbol(), mossaplayer);
				mosse++;
				vittoria=campoGioco.checkVittoria(Player2.getSymbol());
				if (vittoria!= -1) 
				{
					vittoria(vittoria);
					Player2.setVittorie(Player2.getVittorie() +1);
					fineTurno(Player1, Player2,pareggi);
					rivincita=rivincita(count,partite,mosse,input);
					if(rivincita==1)
					{
						campoGioco.riempiCampo();
						vittoria=-1;
					}
				}
			}			
			
		}

		
		
		
		input.close();
		
		
	
	}

	public static int chiediMossa(String Nome, Matrix campoGioco, Scanner input) 
	{
		
		
		do {
			campoGioco.stampaCampo();
			System.out.println(Nome + "scegli la colonna dove inserire la pedina: ");
			ncol = input.nextInt();
			
			if((ncol<1) || (ncol >(COLUMN))) //verifico che l'utente non inserisca un valore fuori dal campo di gioco
			{
				System.out.println("  Scelta errata, la posizione è fuori dalla griglia!\n");

			}
			else if(campoGioco.getMatrix()[0][ncol-1] != ' ') //verifico che la colonna selezionata non sia piena
			{
				System.out.println("  La colonna selezionata è piena! Ripeti la selezione. \n");
			}

		
		}
			while ((ncol < 1) || (ncol > COLUMN) || (campoGioco.getMatrix()[0][ncol-1] != ' ') );
			
			
		return ncol;
	}	
	
	
	
	
	
	
	private static void stampaBannerAvvio() 
	{
		System.out.println("\t\t _______ _______ _______ _______ _______     _______     _______ _______ ");
		System.out.println("\t\t|\\     /|\\     /|\\     /|\\     /|\\     /|   |\\     /|   |\\     /|\\     /|");
		System.out.println("\t\t| +---+ | +---+ | +---+ | +---+ | +---+ |   | +---+ |   | +---+ | +---+ |");
		System.out.println("\t\t| |   | | |   | | |   | | |   | | |   | |   | |   | |   | |   | | |   | |");
		System.out.println("\t\t| |F  | | |O  | | |R  | | |Z  | | |A  | |   | |4  | |   | |!  | | |!  | |");
		System.out.println("\t\t| +---+ | +---+ | +---+ | +---+ | +---+ |   | +---+ |   | +---+ | +---+ |");
		System.out.println("\t\t|/_____\\|/_____\\|/_____\\|/_____\\|/_____\\|   |/_____\\|   |/_____\\|/_____\\|");

		System.out.println("\n\n");
		System.out.println("\t\tBenvenuti nel gioco Forza 4!\n");
		System.out.println("\t\tLo scopo del gioco è quello di allineare 4 pedine in orizzontale, verticale o in una delle due diagonali\n");
		System.out.println("\t\tBuon divertimento!\n\n");
	}
	
	private static void stampaBannerVittoria() 
	{
		System.out.println(" _______ _______ _______     _______ _______ _______ _______ _______     _______ ");
		System.out.println("|\\     /|\\     /|\\     /|   |\\     /|\\     /|\\     /|\\     /|\\     /|   |\\     /|");
		System.out.println("| +---+ | +---+ | +---+ |   | +---+ | +---+ | +---+ | +---+ | +---+ |   | +---+ |");
		System.out.println("| |   | | |   | | |   | |   | |   | | |   | | |   | | |   | | |   | |   | |   | |");
		System.out.println("| |H  | | |A  | | |I  | |   | |V  | | |I  | | |N  | | |T  | | |O  | |   | |!  | |");
		System.out.println("| +---+ | +---+ | +---+ |   | +---+ | +---+ | +---+ | +---+ | +---+ |   | +---+ |");
		System.out.println("|/_____\\|/_____\\|/_____\\|   |/_____\\|/_____\\|/_____\\|/_____\\|/_____\\|   |/_____\\|");
		System.out.println("\n\n\n\n");
	}
	
	
	private static void fineTurno(Player Player1, Player Player2 , int pareggi) //procedura che stampa il numero di vittorie ottenute dai due giocatori oltre al numero di pareggi

	{
		System.out.println("\t\t" + Player1.getNome() + ", hai vinto " + Player1.getVittorie() + " partite!\n" );
		System.out.println("\t\t" + Player2.getNome() + ", hai vinto " + Player2.getVittorie() + " partite!\n" );
		System.out.println("\t\t Pareggi: " + pareggi + "\n\n");
	}
	
	private static void vittoria(int vittoria) 
	{
		switch(vittoria) 
		{
			case 1:
			{
				stampaBannerVittoria();
				System.out.println("\n\n\t\t Vittoria Orizzontale!! \n\n");
				break;
			}
		
			case 2:
			{
				stampaBannerVittoria();
				System.out.println("\n\n\t\t Vittoria Verticale!! \n\n");
				break;
			}
			
			case 3:
			{
				stampaBannerVittoria();
				System.out.println("\n\n\t\t Vittoria Diagonale decrescente!! \n\n");
				break;
			}
			
			case 4:
			{
				stampaBannerVittoria();
				System.out.println("\n\n\t\t Vittoria Diagonale crescente!! \n\n");
				break;
			}
		}
	}


	private static int rivincita(int count, int partite, int mosse, Scanner input) 
	{
		System.out.println("  Inserire 1 per effettuare una nuova partita oppure 2 per uscire \n\n");
		int rivincita=input.nextInt();
		
			if (rivincita != 1 && rivincita != 2 && (count)>=4) //se la variabile rivincita è diversa da 1 e 2 avviso l'utente della scelta errata e incremento il contatore count passato tramite puntatore alla funzione. Raggiunti i 5 tentativi esco dal programma
				{
					System.out.println ("  Hai effettuato una scelta errata per 5 volte, esco dal programma.\n");
					rivincita=-2;
				}


			else if (rivincita != 1 && rivincita != 2)
				{
					rivincita=-1;
					count = count ++;
					System.out.println ("hai effettuato una scelta errata! \n");
				}


			else if (rivincita==1) // l'utente sceglie di rigiocare
				{
					rivincita=1;
					
					if (partite % 2 == 0)	//se il numero di partite giocate è pari faccio iniziare il giocatore 1 inizializzando a 0 il contatore mosse (la differenza sta nel valorizzare mosse con un numero pari piuttosto che dispari, vedi i due else if del ciclo while principale)
						{
							mosse=0;
						}
					else if (partite % 2 == 1) 
					{
						mosse=(ROW*COLUMN)+1;
					}
					
					System.out.println ("Si rigioca! \n");

				}
			
			else if (rivincita==2)
				{
					System.out.println ("\n\n\t\t Grazie per aver giocato a Forza 4!");
					System.exit(0);
				}
				
		return rivincita;
	
	
	
	
	
	
	}




}


/*package connect4;

import java.util.Scanner;

public class connect4{
    //global variables
    final static int WIDTH = 6;
    final static int HEIGHT = 6;
    final static int BOTTOM_ROW = WIDTH - 1;

    //game board
    static char[][] board = new char[WIDTH][HEIGHT];

    //creates scanner
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        //creates board
        CreateBoard();

        //tells player how to play
        System.out.println("Use 0-5 to choose what column you want");

        //displays board
        PrintBoard();

        //creates boolean to determine status of game
        boolean flag = true;

        //main game loop
        while(flag){
            //activates player 1s turn, then prints board
            DropX();
            PrintBoard();

            //determines if player 1 has won
            if(!CheckX()){
                flag = false; //sets flag to false so loop is not repeated if player 1 won
                break; //break to skip player 2s turn if won
            }

            //activates player 2s turn, then prints board
            DropO();
            PrintBoard();

            //determines if player 1 has won
            if(!CheckO()){
                flag = false; //sets flag to false so loop is not repeated if player 2 won
                break; // break for consistency
            }
        }
    }
    public static void CreateBoard() {
        //fills board with '.' for the width and height
        for (int w = 0; WIDTH > w; w += 1) {
            for (int h = 0; HEIGHT > h; h += 1) {
                board[w][h] = '.';
            }
        }
    }

    public static void PrintBoard() {
        //prints the board
        for (int w = 0; WIDTH > w; w += 1) {
            for (int h = 0; HEIGHT > h; h += 1) {
                System.out.print(board[w][h]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void DropX(){
        //creates a counter
        int counter = 1;

        //shows whos turn
        System.out.println("Player 1 turn");

        //gets input
        int column = scanner.nextInt();

        while(true){
            if(column > WIDTH){
                System.out.println("That's not a valid column");
                break;
            }

            if (board[BOTTOM_ROW][column] == '.') { //checks to see if space is blank, puts X there if it is
                board[BOTTOM_ROW][column] = 'X';
                break; //breaks loop after placing
            }else if(board[BOTTOM_ROW][column] == 'X' || board[BOTTOM_ROW][column] == 'O'){ //if space isn't blank, checks to see if one above is
                if(board[BOTTOM_ROW - counter][column] == '.'){ //puts X if blank
                    board[BOTTOM_ROW - counter][column] = 'X';
                    break; //breaks loop after placing
                }
            }
            counter += 1; //adds one to counter if the space wasn't blank, then loops again
            if(counter == WIDTH){ //checks to see if at end of column
                System.out.println("That column is full");
                break;
            }
        }
    }


    public static void DropO(){
        //creates a counter
        int counter = 1;

        //shows whos turn
        System.out.println("Player 2 turn");

        //gets input
        int column = scanner.nextInt();

        while(true){
            if(column > WIDTH){
                System.out.println("That's not a valid column");
                break;
            }

            if (board[BOTTOM_ROW][column] == '.') { //checks to see if space is blank, puts O there if it is
                board[BOTTOM_ROW][column] = 'O';
                break; //breaks loop after placing
            }else if(board[BOTTOM_ROW][column] == 'X' || board[BOTTOM_ROW][column] == 'O'){ //if space isn't blank, checks to see if one above is
                if(board[BOTTOM_ROW - counter][column] == '.'){ //puts O if blank
                    board[BOTTOM_ROW - counter][column] = 'O';
                    break; //breaks loop after placing
                }
            }
            counter += 1; //adds one to counter if the space wasn't blank, then loops again
            if(counter == WIDTH){ //checks to see if at end of column
                System.out.println("That column is full");
                break;
            }
        }
    }

    public static boolean CheckXHorizontal(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board horizontally
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == 'X'){ //if it finds an X, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckXVertical(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board vertically
            for(int h = 0; HEIGHT > h; h += 1){
                for(int w = 0; WIDTH > w; w += 1){
                    if(board[w][h] == 'X'){ //if it finds an X, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckOVertical(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board vertically
            for(int h = 0; HEIGHT > h; h += 1){
                for(int w = 0; WIDTH > w; w += 1){
                    if(board[w][h] == 'O'){ //if it finds an O, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an O, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckOHorizontal(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board vertically
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == 'O'){ //if it finds an O, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an O, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckXDiagonalForward(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an X is found
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == 'X'){ //if X is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Xs
                            if(checkColumn + w <= WIDTH - 1&& checkRow + h <= HEIGHT - 1){
                                if(board[w + checkColumn][h + checkRow] == 'X'){ //if X is found, add 1 to counter
                                    counter += 1;
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == WIDTH -1 || checkRow == HEIGHT -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckODiagonalForward(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an O is found
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == 'O'){ //if O is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Os
                            if(checkColumn + w <= WIDTH - 1&& checkRow + h <= HEIGHT - 1){
                                if(board[w + checkColumn][h + checkRow] == 'O'){ //if O is found, add 1 to counter
                                    counter += 1;
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == WIDTH -1 || checkRow == HEIGHT -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckXDiagonalBack(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an X is found
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == 'X'){ //if X is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Xs
                            if(w - checkColumn >= 0 && h - checkRow >= 0){
                                if(board[w - checkColumn][h - checkRow] == 'X'){
                                    counter += 1; //if X is found, add 1 to counter
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == HEIGHT -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckODiagonalBack(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){

            //goes through until an O is found
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == 'O'){ //if O is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Os
                            if(w - checkColumn >= 0 && h - checkRow >= 0){
                                if(board[w - checkColumn][h - checkRow] == 'O'){
                                    counter += 1; //if O is found, add 1 to counter
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == HEIGHT -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public static boolean CheckX(){
        //creates flag
        boolean flag = true;

        //checks all Xs at once, for clearner main loop
        if(!CheckXVertical() || !CheckXHorizontal()|| !CheckXDiagonalBack()|| !CheckXDiagonalForward()){
            flag = false;
        }
        return flag;
    }

    public static boolean CheckO(){
        //creates flag
        boolean flag = true;

        //checks all Os at once, for clearner main loop
        if(!CheckOVertical() || !CheckOHorizontal() || !CheckODiagonalBack() || !CheckODiagonalForward()){
            flag = false;
        }
        return flag;
    }
}
*/
