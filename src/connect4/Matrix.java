package connect4;

public class Matrix {

	private static int ROW = 6;	//definisco le variabili righe e colonne che utilizzo per costruire la matrice
	private static int COLUMN = 7;

	private char[][] matrix;
	
	Matrix(int ROW , int COLUMN) //definisco la matrice 
	{
		matrix= new char[ROW][COLUMN]; //definisco il metodo costruttore
	}

	public void riempiCampo(int ROW, int COLUMN)
	{
		int i,j;
		for (i=0;i<ROW;i++)
		{
			for (j=0;j<COLUMN;j++)
				matrix[i][j]=' ';
			
		}
		
	}
	public void stampaCampo(int ROW, int COLUMN)
	{
		int i,j;
		for (i=0;i<ROW;i++)
		{
			for (j=0;j<COLUMN;j++)
				System.out.print( matrix[i][j]);
			
		}
		
	}
}
