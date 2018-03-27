package connect4;

public class Player {
	private String nome;
	private char symbol;
	private int vittorie;
	
	public Player(String nome) 
	{
		this.nome=nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public int getVittorie() {
		return this.vittorie;
	}
	public void setVittorie(int vittorie) {
		this.vittorie = vittorie;
	}

}
