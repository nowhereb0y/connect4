package connect4;
import java.util.Scanner;

public class Connect4 {
	
	static final int ROW = 6;	
	static final int COLUMN = 7;
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
		System.out.println("Benvenuti in Forza 4 !" ); 

		Matrix campoGioco = new Matrix (ROW,COLUMN);	//* istanzio la Matrice che userò come campo da gioco */
	
		campoGioco.riempiCampo();				//* chiamo il metodo per riempire la matrice */

		
		Scanner input = new Scanner(System.in);	//Utilizzo lo scanner per catturare l'input dell'utente
		
		System.out.println("\t\t Giocatore 1, inserisci il tuo nome: ");
		
		Player Player1 = new Player (org.apache.commons.text.CaseUtils.toCamelCase(input.nextLine(), true));			//Creo l'istanza Player1 della classe Player, l'input dell'utente viene passato al metodo toCamelCase per avere solamente il primo carattere della stringa maiuscolo
		System.out.println("\tBenvenuto " + Player1.getNome() + " !\n" ); /** La documentazione */
		Player1.setSymbol('X');
		Player1.setVittorie(0);
		System.out.println("\t"+ Player1.getNome() + ", il tuo simbolo è " + Player1.getSymbol() +"\n"); 

		System.out.println("\t------------------------------\n\n"); 
		System.out.println("\t\t Giocatore 2, inserisci il tuo nome: ");
		
		Player Player2 = new Player (org.apache.commons.text.CaseUtils.toCamelCase(input.nextLine(), true));			//Creo l'istanza Player2 della classe Player, l'input dell'utente viene passato al metodo toCamelCase per avere solamente il primo carattere della stringa maiuscolo
		System.out.println("\tBenvenuto " + Player2.getNome() + " !" ); 				
		Player2.setSymbol('@');
		Player2.setVittorie(0);
		System.out.println("\t" + Player2.getNome() + ", il tuo simbolo è " + Player2.getSymbol() ); 
		
		System.out.println("\t Si inizia!!!!" );
		
		
		while (vittoria==-1) 
		{
			if (mosse==(ROW*COLUMN)||mosse==(1+(2*ROW*COLUMN)))
			{
				campoGioco.stampaCampo();
				System.out.println("\n\n\t\tPartita terminata, non ci sono vincitori!\n\n ") ;				
				partite++;
				pareggi++;
				vittoria=-3;
				rivincita=rivincita(count,partite,input);
				if(rivincita==1)
				{
					campoGioco.riempiCampo();
					vittoria=-1;
				}
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
					campoGioco.stampaCampo();
					partite++;
					Player1.setVittorie(Player1.getVittorie() +1);
					fineTurno(Player1, Player2,pareggi);
					rivincita=rivincita(count,partite,input);
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
					campoGioco.stampaCampo();
					partite++;
					Player2.setVittorie(Player2.getVittorie() +1);
					fineTurno(Player1, Player2,pareggi);
					rivincita=rivincita(count,partite,input);
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
			System.out.println("\t" + Nome + ", scegli la colonna dove inserire la pedina: ");
			{
			while (!input.hasNextInt()) 
			{
				System.out.println("\t" + Nome + " , inserisci un numero!!!!");
				input.next();
			}
			 
			//input.next();	
			ncol = input.nextInt();
			
			}
			
			if((ncol<1) || (ncol >(COLUMN))) //verifico che l'utente non inserisca un valore fuori dal campo di gioco
			{
				System.out.println("\tScelta errata, la posizione è fuori dalla griglia!\n");

			}
			else if(campoGioco.getMatrix()[0][ncol-1] != ' ') //verifico che la colonna selezionata non sia piena
			{
				System.out.println("\tLa colonna selezionata è piena! Ripeti la selezione. \n");
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


	private static int rivincita(int count, int partite, Scanner input) 
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
							Connect4.mosse=0;
						}
					else if (partite % 2 == 1) 
						{
							Connect4.mosse=((ROW*COLUMN)+1);						
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

 