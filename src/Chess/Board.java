/**
 * 
 */
package Chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class Board extends JFrame implements ActionListener {

	static String path = "/Users/thejussinghj/Documents/week1/Assignment1/src/Chess/";
	JButton[][] squares;
	static char[][] board = { { 'r', 'k', 'b', 'q', 'a', 'b', 'k', 'r' }, { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
			{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, { 'R', 'K', 'B', 'Q', 'A', 'B', 'K', 'R' } };
	static ArrayList<String> moveList = new ArrayList<String>();
	static int fromRow;
	static int fromCol;
	JButton hintButton;
	JPanel buttonPanel;
	Boolean toMove = false;
	char turn = 'W';
	public boolean isHintClicked = false;

	/*
	 * Constructor used to set the initial chess board
	 * 
	 * 
	 */
	public Board() {

		JPanel chessBoardPanel = new JPanel();

		hintButton = new JButton("Hint");
		hintButton.setActionCommand("hint");
		hintButton.setEnabled(true);
		hintButton.addActionListener(this);

		buttonPanel = new JPanel();
		buttonPanel.add(hintButton);

		Container chessBoard = getContentPane();

		chessBoardPanel.setLayout(new GridLayout(8, 8, 1, 1));

		squares = new JButton[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new JButton();
				if ((i + j) % 2 == 0)
					squares[i][j].setBackground(new Color(255, 200, 100));
				else
					squares[i][j].setBackground(new Color(150, 50, 30));
				squares[i][j].setOpaque(true);
				squares[i][j].setBorderPainted(false);
				squares[i][j].setActionCommand(i + "-" + j);
				squares[i][j].addActionListener(this);
				chessBoardPanel.add(squares[i][j]);
			}
		}

		chessBoard.add(chessBoardPanel);
		chessBoard.add(buttonPanel, BorderLayout.SOUTH);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				arrangePawns(board[i][j], i, j);
			}
		}

	}

	/*
	 * 
	 * Function to define the path for each icon
	 * 
	 * @input: char, row, column
	 * 
	 */
	public void arrangePawns(char pawn, int i, int j) {
		switch (pawn) {
		case 'r':
			addToBoard(path + "black_rook.png", i, j);
			break;
		case 'k':
			addToBoard(path + "black_knight.png", i, j);
			break;
		case 'b':
			addToBoard(path + "black_bishop.png", i, j);
			break;
		case 'q':
			addToBoard(path + "black_queen.png", i, j);
			break;
		case 'a':
			addToBoard(path + "black_king.png", i, j);
			break;
		case 'p':
			addToBoard(path + "black_pawn.png", i, j);
			break;
		case 'R':
			addToBoard(path + "white_rook.png", i, j);
			break;
		case 'K':
			addToBoard(path + "white_knight.png", i, j);
			break;
		case 'B':
			addToBoard(path + "white_bishop.png", i, j);
			break;
		case 'Q':
			addToBoard(path + "white_queen.png", i, j);
			break;
		case 'A':
			addToBoard(path + "white_king.png", i, j);
			break;
		case 'P':
			addToBoard(path + "white_pawn.png", i, j);
			break;
		default:
			break;
		}
	}

	/*
	 * 
	 * Function to add icon to the chess board
	 * 
	 */
	public void addToBoard(String piece_name, int i, int j) {
		Icon image = new ImageIcon(piece_name);
		squares[i][j].setIcon(image);
		squares[i][j].revalidate();
		squares[i][j].repaint();
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String cordinates = e.getActionCommand();
		resetHint();
		if (toMove == true) {
			String[] index = cordinates.split("-");
			for (int i = 0; i < moveList.size(); i++) {
				if (cordinates.equals(moveList.get(i))) {
					moveChessPiece(Integer.parseInt(index[0]), Integer.parseInt(index[1]));
					changeTurn();
					break;
				} else {
					toMove = false;
				}
			}
			hintButton.setEnabled(true);
		} else {
			if (cordinates != "hint") {
				moveList.clear();
				String[] index = cordinates.split("-");
				if ((turn == 'W'
						&& Character.isUpperCase(board[Integer.parseInt(index[0])][Integer.parseInt(index[1])]))
						|| (turn == 'B' && Character
								.isLowerCase(board[Integer.parseInt(index[0])][Integer.parseInt(index[1])]))) {
					Moves moves = new Moves();
					moveList = moves.getPossibleMoves(e.getActionCommand());
					showPossibleMoves();
				}

			}
		}

		if (e.getActionCommand() == "hint") {
			hintClicked();
		}

	}

	/*
	 * Function used to fetch the optimal move
	 * 
	 */
	public void hintClicked() {
		moveList.clear();
		String optimalMoves = null;
		String[] stringArray;
		Hint hint = new Hint(turn);
		optimalMoves = hint.calculateHint();
		stringArray = optimalMoves.split(":");
		moveList.add(stringArray[1]);
		fromRow = Character.getNumericValue(stringArray[0].charAt(0));
		fromCol = Character.getNumericValue(stringArray[0].charAt(2));
		hintButton.setEnabled(false);
		isHintClicked = true;
		showPossibleMoves();
	}

	/*
	 * Function used to change the turns of the player
	 * 
	 */
	private void changeTurn() {
		if (turn == 'W') {
			turn = 'B';
		} else {
			turn = 'W';
		}
	}

	/*
	 * function used to move the chess piece to the destination grid
	 * 
	 * @input: row , column
	 * 
	 * 
	 */
	private void moveChessPiece(int row, int col) {
		board[row][col] = board[fromRow][fromCol];
		board[fromRow][fromCol] = ' ';
		squares[row][col].setIcon(squares[fromRow][fromCol].getIcon());
		squares[fromRow][fromCol].setIcon(null);
		squares[row][col].revalidate();
		squares[row][col].repaint();
		squares[fromRow][fromCol].revalidate();
		squares[fromRow][fromCol].repaint();
		resetHint();
		toMove = false;
	}

	/*
	 * Function used to remove the possible moves hint from the chess board
	 * 
	 * 
	 */
	private void resetHint() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j].setBorderPainted(false);
				squares[i][j].revalidate();
				squares[i][j].repaint();
			}
		}
	}

	/*
	 * Function used to display the possible
	 * 
	 */
	private void showPossibleMoves() {
		for (int i = 0; i < moveList.size(); i++) {
			String[] index = moveList.get(i).split("-");
			squares[Integer.parseInt(index[0])][Integer.parseInt(index[1])].setBorderPainted(true);
			squares[Integer.parseInt(index[0])][Integer.parseInt(index[1])].setBorder(new LineBorder(Color.BLACK, 5));
			squares[Integer.parseInt(index[0])][Integer.parseInt(index[1])].revalidate();
			squares[Integer.parseInt(index[0])][Integer.parseInt(index[1])].repaint();
			toMove = true;
		}
	}
}
