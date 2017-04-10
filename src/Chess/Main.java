/**
 * 
 */
package Chess;

import javax.swing.JFrame;

/**
 * @author Thejus Singh Jagadish
 *
 */
public class Main {
	public static void main(String[] args){
		Board chessBoard = new Board();
		chessBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBoard.setSize(600, 600);
		chessBoard.setVisible(true);
		chessBoard.setResizable(false);
	}

}

