package Chess;

import java.util.ArrayList;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Bishop implements PossibleMoves {
	private int row;
	private int col;
	private char piece;
	public ArrayList<String> moveList = new ArrayList<String>();

	/*
	 * Constructor to initialize chess piece , row and column
	 * 
	 */
	public Bishop(char piece, int row, int col) {
		setRow(row);
		setCol(col);
		setPiece(piece);
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col
	 *            the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the piece
	 */
	public char getPiece() {
		return piece;
	}

	/**
	 * @param piece
	 *            the piece to set
	 */
	public void setPiece(char piece) {
		this.piece = piece;
	}

	/*
	 * Function to find all the possible moves for Bishop
	 * 
	 */
	@Override
	public ArrayList<String> findPossibleMoves() {
		// TODO Auto-generated method stub
		int temp;
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				temp = 1;
				try {
					while (Board.board[getRow() + temp * i][getCol() + temp * j] == ' ') {
						moveList.add((getRow() + temp * i) + "-" + (getCol() + temp * j));
						temp++;
					}
					if (Character.isLowerCase(piece)) {
						if (Character.isUpperCase(Board.board[getRow() + temp * i][getCol() + temp * j])) {
							moveList.add((getRow() + temp * i) + "-" + (getCol() + temp * j));
						}
					} else {
						if (Character.isLowerCase(Board.board[getRow() + temp * i][getCol() + temp * j])) {
							moveList.add((getRow() + temp * i) + "-" + (getCol() + temp * j));
						}
					}
				} catch (Exception e) {

				}
			}
		}
		return moveList;
	}

}
