package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Pawn implements PossibleMoves {

	public int row;
	public int col;
	public char piece;
	public ArrayList<String> moveList = new ArrayList<String>();

	/*
	 * Constructor to initialize chess piece , row and column
	 * 
	 */
	public Pawn(char piece, int row, int col) {
		this.row = row;
		this.col = col;
		this.piece = piece;
	}

	/*
	 * Function to find all the possible moves for Pawn
	 * 
	 */
	@Override
	public ArrayList<String> findPossibleMoves() {
		if (Character.isUpperCase(piece)) {
			findPossibleWhitePawnMoves();
		} else {
			findPossibleBlackPawnMoves();
		}

		return moveList;
	}

	/*
	 * Function to find possible moves for Black Pawn
	 * 
	 */
	public void findPossibleBlackPawnMoves() {

		for (int i = -1; i <= 1; i += 2) {
			try {
				if (Character.isUpperCase(Board.board[row + 1][col + i])) {
					moveList.add((row + 1) + "-" + (col + i));
				}
			} catch (Exception e) {

			}
		}
		if (Board.board[row + 1][col] == ' ') {
			moveList.add((row + 1) + "-" + (col));
		}
		if (row == 1 && Board.board[row + 2][col] == ' ' && Board.board[row + 1][col] == ' ') {
			moveList.add((row + 2) + "-" + (col));
		}
	}

	/*
	 * Function to find possible moves for White Pawn
	 * 
	 */
	public void findPossibleWhitePawnMoves() {

		for (int i = -1; i <= 1; i += 2) {
			try {
				if (Character.isLowerCase(Board.board[row - 1][col + i])) {
					moveList.add((row - 1) + "-" + (col + i));
				}
			} catch (Exception e) {

			}
		}
		if (Board.board[row - 1][col] == ' ') {
			moveList.add((row - 1) + "-" + (col));
		}
		if (row == 6 && Board.board[row - 2][col] == ' ' && Board.board[row - 1][col] == ' ') {
			moveList.add((row - 2) + "-" + (col));
		}
	}

}
