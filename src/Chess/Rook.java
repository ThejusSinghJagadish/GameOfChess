package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Rook implements PossibleMoves {

	public int row;
	public int col;
	public char piece;

	public ArrayList<String> moveList = new ArrayList<String>();

	/*
	 * Constructor to initialize chess piece , row and column
	 * 
	 */
	public Rook(char piece, int row, int col) {
		this.row = row;
		this.col = col;
		this.piece = piece;
	}

	/*
	 * Function to find all the possible moves for Rook
	 * 
	 */
	@Override
	public ArrayList<String> findPossibleMoves() {
		int temp;
		for (int i = -1; i <= 1; i += 2) {
			temp = 1;
			try {
				while (Board.board[row][col + temp * i] == ' ') {
					moveList.add((row) + "-" + (col + temp * i));
					temp++;
				}
				if (Character.isLowerCase(piece)) {
					if (Character.isUpperCase(Board.board[row][col + temp * i])) {
						moveList.add((row) + "-" + (col + temp * i));
					}
				} else {
					if (Character.isLowerCase(Board.board[row][col + temp * i])) {
						moveList.add((row) + "-" + (col + temp * i));
					}
				}
			} catch (Exception e) {

			}
			temp = 1;
			try {
				while (Board.board[row + temp * i][col] == ' ') {
					moveList.add((row + temp * i) + "-" + (col));
					temp++;
				}
				if (Character.isLowerCase(piece)) {
					if (Character.isUpperCase(Board.board[row + temp * i][col])) {
						moveList.add((row + temp * i) + "-" + (col));
					}
				} else {
					if (Character.isLowerCase(Board.board[row + temp * i][col])) {
						moveList.add((row + temp * i) + "-" + (col));
					}
				}
			} catch (Exception e) {

			}
		}
		return moveList;
	}

}
