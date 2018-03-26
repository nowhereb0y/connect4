package connect4;

public class Matrix {

//	private  int ROW = 6;	//definisco le variabili righe e colonne che utilizzo per costruire la matrice
//	private  int COLUMN = 7;

	public char[][] matrix;
	
	Matrix(int ROW , int COLUMN) //definisco la matrice 
	{
		this.matrix= new char[ROW][COLUMN]; //definisco il metodo costruttore
	}

	public void riempiCampo(int ROW, int COLUMN)
//	public void riempiCampo(matrix[][])
	
		
	
	
	{
		int i,j;
		for (i=0;i<ROW;i++)
		{
			for (j=0;j<COLUMN;j++)
				matrix[i][j]=' ';
			
		}
		
	}
	public void stampaCampo( int ROW, int COLUMN)
	{
		int i,j;
		System.out.print("\n");
		for (i=0;i<ROW;i++)
		{
			System.out.print( "\t\t");
			for (j=0;j<COLUMN;j++)
				System.out.print( "|"+ matrix[i][j] +"|");
			System.out.print( "\n");
		}
		System.out.print("\t\t---------------------\n");
		System.out.print("\t\t|1||2||3||4||5||6||7|\n\n\n");
	}
}
