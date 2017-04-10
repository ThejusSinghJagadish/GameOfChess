package Chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Hint implements Runnable {
	
	ArrayList<String> moves = null;
	String optimalMove = null;
	int row;
	int col;
	public static HashMap<String,ArrayList<String>> chessHints =  new HashMap<String,ArrayList<String>>();
	public char turn;
	
	/*
	 *Constructor to initialize row and column
	 */
	public Hint(int row,int col)
	{
		this.row = row;
		this.col = col;
	
	}
	
	/*
	 * Constructor to initialize players turn
	 * 
	 */
	public Hint(char turn){
		this.turn = turn;
	}
	
	/*
	 * Function to create threads for each piece and get possible moves for each piece.
	 * Pass the moves to OptimalMove class to find the optimal move.
	 * Return the single optimal move to the board class
	 */
	public String calculateHint() {
		chessHints.clear();
		
		try
		{
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				row = i;
				col = j;
				if(turn == 'W'){
					if(Character.isUpperCase(Board.board[i][j])){
						Thread t = new Thread(new Hint(row, col));
						t.start();
						t.join();
					}
				}
				else{
					if(Character.isLowerCase(Board.board[i][j])){
						Thread t = new Thread(new Hint(row, col));
						t.start();
						t.join();
					}
				}
			}
		}
		
		}
		catch(Exception e1)
		{
			
			System.out.println("Exeception in  calculate hint method");
			
		}
		OptimumMove optimumMove = new OptimumMove(chessHints, turn);
		optimalMove = optimumMove.findOptimumMoves();
		return optimalMove;
	}
	
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		getMoves();
	}
	
	/*
	 * Function to get the possible moves from the Moves class
	 * 
	 */
	synchronized public void getMoves(){
		Moves possibleMove = new Moves();
		moves = possibleMove.getPossibleMoves(row+"-"+col);
		if(moves.size() != 0){
			chessHints.put(row+"-"+col, moves);
		}
	}
}
