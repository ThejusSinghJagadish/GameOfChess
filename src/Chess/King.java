package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class King implements PossibleMoves {
	public int row;
	public int col;
	public char piece;
	public ArrayList<String> moveList = new ArrayList<String>();
	
	/*
	 * Constructor to initialize chess piece , row and column
	 * 
	 */
	public King(char piece, int row, int col){
		this.row = row;
		this.col = col;
		this.piece = piece;
	}
	
	/*
	 * Function to find all the possible moves for King
	 * 
	 */
	public ArrayList<String> findPossibleMoves() {
		// TODO Auto-generated method stub
		for(int i=0; i<9; i++){
			if(i != 4){
				try{
					if(Character.isLowerCase(piece)){
						if(Character.isUpperCase(Board.board[row-1+i/3][col-1+i%3]) || Board.board[row-1+i/3][col-1+i%3] == ' '){
							moveList.add((row-1+i/3) +"-"+ (col-1+i%3));
						}
					}
					else{
						if(Character.isLowerCase(Board.board[row-1+i/3][col-1+i%3]) || Board.board[row-1+i/3][col-1+i%3] == ' '){
							moveList.add((row-1+i/3) +"-"+ (col-1+i%3));
						}
					}
				}
				catch(Exception e){
					
				}
			}
		}
		return moveList;
	}
	
	
}
