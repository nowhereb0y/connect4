package connect4;

public class Matrix {

	private int ROW;	//definisco le variabili this.ROW e this.COLUMN che utilizzo per costruire la matrice
	private int COLUMN;

	private char[][] matrix;
	
	Matrix(int ROW , int COLUMN) //definisco la matrice 
	{
		this.matrix= new char[ROW][COLUMN]; //definisco il metodo costruttore
		this.ROW=ROW;
		this.COLUMN=COLUMN;
	}
	
	
	public char[][] getMatrix() //definisco il getter
	{ 
		return this.matrix;
	}
	
	
	public void riempiCampo()	//definisco il metodo che inizializza il campo di gioco
	
	{
		int i,j;
		for (i=0;i<this.ROW;i++)
		{
			for (j=0;j<this.COLUMN;j++)
				matrix[i][j]=' ';	
		}	
	}
	
	
	
	public void stampaCampo()
	{
		int i,j;
		System.out.print("\n");
		for (i=0;i<this.ROW;i++)
		{
			System.out.print( "\t\t");
			for (j=0;j<this.COLUMN;j++)
				System.out.print( "|"+ matrix[i][j] +"|");
			System.out.print( "\n");
		}
		System.out.print("\t\t---------------------\n");
		System.out.print("\t\t|1||2||3||4||5||6||7|\n\n\n");
	}
	
	public void insertSymbol(char simbolo, int mossaplayer) 
	{
		int i;
		
		for(i=(this.ROW -1); i>=0; i--)
		{
			if(this.matrix[i][mossaplayer-1] == ' ')
				
    		{
    			this.matrix[i][mossaplayer-1] = simbolo;

    			i=-1;
    		}
		}	
	}
	
	
	
	public int checkVittoria(char simbolo)
	{
		int i,j;
		int ritorno = -1;
		
		//Check orizzontale
		for (i=0; i<this.ROW; i++)
			{
			for (j=0; j<this.COLUMN-3 ;j++)
				{
					if (  this.matrix[i][j]==simbolo && this.matrix[i][j+1]==simbolo && this.matrix[i][j+2]==simbolo && this.matrix[i][j+3]==simbolo) //oltre a verificare che ci siano le 4 pedine allineate orizzontalmente verifico che l'incremento della colonna non sia eccessivo
						{
							//System.out.println("\n\nVITTORIA ORIZZONTALE per %s!!!! \n", p1->nome );
							ritorno=1;
						}
				}
			}
		//Chech verticale
		for (i=0; i<this.ROW-3; i++)
			{
			for (j=0; j<this.COLUMN;j++)
				{
					if ( this.matrix[i][j]==simbolo && this.matrix[i+1][j]==simbolo && this.matrix[i+2][j]==simbolo && this.matrix[i+3][j]==simbolo) //oltre a verificare che ci siano le 4 pedine allineate verticalmente verifico che l'incremento della riga non sia eccessivo
						{
							//System.out.println("\n\nVITTORIA VERTICALE per %s!!!! \n", p1->nome );
							ritorno=2;
						}
				}
			}
		//Chech diagonale decrescente
		for (i=0; i<this.ROW-3; i++)
			{
			for (j=0; j<this.COLUMN-3;j++)
				{
					if ( this.matrix[i][j]==simbolo && this.matrix[i+1][j+1]==simbolo && this.matrix[i+2][j+2]==simbolo && this.matrix[i+3][j+3]==simbolo) //oltre a verificare che ci siano le 4 pedine allineate diagonalmente verifico che l'incremento  di riga e colonna non sia eccessivo
						{
							//System.out.println("\n\nVITTORIA DIAGONALE DECRESCENTE per %s!!!! \n", p1->nome);
							ritorno=3;
						}
				}
			}


		//Chech diagonale crescente
		for (i=3; i<this.ROW; i++)
			{
			for (j=0; j<this.COLUMN-3;j++)
				{
					 if (this.matrix[i][j]==simbolo && this.matrix[(i-1)][j+1]==simbolo && this.matrix[i-2][j+2]==simbolo && this.matrix[i-3][j+3]==simbolo) //oltre a verificare che ci siano le 4 pedine allineate diagonalmente verifico che l'incremento o la sottrazione  di riga e colonna non sia eccessivo
						{
							//System.out.println("\n\nVITTORIA DIAGONALE CRESCENTE per %s!!!!\n");
							ritorno=4;
						}
				}
			} 
	return ritorno;	
	}
	
}
