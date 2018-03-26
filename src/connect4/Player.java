package connect4;

public class Player {
	public String nome;
	public char symbol;
	public int vittorie;
	public Player(String nome) 
	{
		this.nome=nome;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	

}
