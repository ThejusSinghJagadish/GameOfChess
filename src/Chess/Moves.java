package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Moves {
	
	ArrayList<String> moveList = new ArrayList<String>();
	
	/*
	 * Function to get the possible moves for the required coordinates
	 * 
	 * 
	 */
	public ArrayList<String> getPossibleMoves(String cordinates) {
		String[] index = cordinates.split("-");
		Board.fromRow = Integer.parseInt(index[0]);
		Board.fromCol = Integer.parseInt(index[1]);
		if(Board.board[Board.fromRow][Board.fromCol] != ' '){
			possibleMoves(Board.board[Board.fromRow][Board.fromCol],Board.fromRow,Board.fromCol);
			
		}
		return moveList;
	}
	
	
	/*
	 * 
	 * Create objects for appropriate chess piece class based on the coordinates
	 * 
	 */
	public void possibleMoves(char chessPiece, int row, int col){
		switch(chessPiece){	
		    case 'p':
			case 'P': Pawn pawn = new Pawn(chessPiece, row, col);
		  	  		  moveList = pawn.findPossibleMoves();
		  	  		  break;
			case 'r':
			case 'R': Rook rook = new Rook(chessPiece, row, col);
		  		  	  moveList = rook.findPossibleMoves();
				      break;  
			case 'k':
			case 'K': Knight knight = new Knight(chessPiece, row, col);
			  		  moveList = knight.findPossibleMoves();
				      break;
			case 'b':
			case 'B': Bishop bishop = new Bishop(chessPiece, row, col);
					  moveList = bishop.findPossibleMoves();
					  break; 
			case 'q':
			case 'Q': Queen queen = new Queen(chessPiece, row, col);
			  		  moveList = queen.findPossibleMoves();
				      break;
			case 'a':
			case 'A': King king = new King(chessPiece, row, col);
					  moveList = king.findPossibleMoves();
					  break;
		}
	}
	
}
