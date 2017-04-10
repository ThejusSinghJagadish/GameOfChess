package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Knight implements PossibleMoves {

	public int row;
	public int col;
	public char piece;
	public ArrayList<String> moveList = new ArrayList<String>();

	/*
	 * Constructor to initialize chess piece , row and column
	 * 
	 */
	public Knight(char piece, int row, int col) {
		this.row = row;
		this.col = col;
		this.piece = piece;
	}

	/*
	 * Function to find all the possible moves for Knight
	 * 
	 */
	@Override
	public ArrayList<String> findPossibleMoves() {
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				try {
					if (Character.isLowerCase(piece)) {
						if (Character.isUpperCase(Board.board[row + i][col + j * 2])
								|| Board.board[row + i][col + j * 2] == ' ') {
							moveList.add((row + i) + "-" + (col + j * 2));
						}
					} else {
						if (Character.isLowerCase(Board.board[row + i][col + j * 2])
								|| Board.board[row + i][col + j * 2] == ' ') {
							moveList.add((row + i) + "-" + (col + j * 2));
						}
					}

				} catch (Exception e) {

				}
			}
		}
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				try {
					if (Character.isLowerCase(piece)) {
						if (Character.isUpperCase(Board.board[row + i * 2][col + j])
								|| Board.board[row + i * 2][col + j] == ' ') {
							moveList.add((row + i * 2) + "-" + (col + j));
						}
					} else {
						if (Character.isLowerCase(Board.board[row + i * 2][col + j])
								|| Board.board[row + i * 2][col + j] == ' ') {
							moveList.add((row + i * 2) + "-" + (col + j));
						}
					}
				} catch (Exception e) {

				}
			}
		}
		return moveList;
	}

}
