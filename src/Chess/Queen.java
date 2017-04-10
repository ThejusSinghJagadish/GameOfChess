package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Queen implements PossibleMoves {

	public int row;
	public int col;
	public char piece;
	public ArrayList<String> moveList = new ArrayList<String>();
	
	/*
	 * Constructor to initialize chess piece , row and column
	 * 
	 */
	public Queen(char piece, int row, int col){
		this.row = row;
		this.col = col;
		this.piece = piece;
	}
	
	/*
	 * Function to find all the possible moves for Queen
	 * 
	 */
	@Override
	public ArrayList<String> findPossibleMoves() {
		ArrayList<String> rookMoveList = new ArrayList<String>();
		ArrayList<String> bishopMoveList = new ArrayList<String>();
		Rook rook = new Rook(piece,row,col);
		Bishop bishop = new Bishop(piece,row,col);
		rookMoveList = rook.findPossibleMoves();
		bishopMoveList = bishop.findPossibleMoves();
		for(int i=0; i<rookMoveList.size(); i++){
			moveList.add(rookMoveList.get(i));
		}
		for(int i=0; i<bishopMoveList.size(); i++){
			moveList.add(bishopMoveList.get(i));
		}
		return moveList;
	}
}
