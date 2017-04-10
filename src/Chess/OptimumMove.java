package Chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Thejus Singh Jagadish
 *
 */
public class OptimumMove {
	
	public HashMap<String, ArrayList<String>> allMoves = new HashMap<String, ArrayList<String>>();
	public ArrayList<String> optimalCaptureSet = new ArrayList<String>();
	public ArrayList<String> OptimalLongestColMoveSet = new ArrayList<String>();
	public ArrayList<String> OptimalLongestRowMoveSet = new ArrayList<String>();
	public ArrayList<String> OptimalLeastColMoveSet = new ArrayList<String>();
	public char turn;
	public String optimalMove;
	
	
	/*
	 * Constructor to initialize the hash map of possible moves and player's turn
	 * 
	 */
	public OptimumMove(HashMap<String, ArrayList<String>> allMoves, char turn){
		this.allMoves = allMoves;
		this.turn = turn;
	}
	
	/*
	 * Function to find the optimum move 
	 * 
	 */
	public String findOptimumMoves(){
		optimalCaptureSet.clear();
		calculateOptimalCaptures();
		if(optimalCaptureSet.size() > 1){
			calculateLongestMove();
		}
		else if(optimalCaptureSet.size() == 0){
			calculateLongestColMove();
		}
		else{
			optimalMove = optimalCaptureSet.get(0);
		}
		
		return optimalMove;
	}

	/*
	 * Find all moves that moves to the highest column when there are no captures
	 * 
	 */
	public void calculateLongestColMove() {
		// TODO Auto-generated method stub
		Set<String> set = allMoves.keySet();
		Iterator<String> iterator = set.iterator();
		int max;
		if(turn == 'W'){
			max = 0;
		}
		else{
			max = 7;
		}
		
		while(iterator.hasNext()){
			String key = iterator.next();
			ArrayList<String> list = allMoves.get(key);
			if(list.size() > 0){
				for(int i=0; i<list.size(); i++){
					if(turn == 'W'){
						if(Character.getNumericValue(list.get(i).charAt(2)) > max){
							max = Character.getNumericValue(list.get(i).charAt(2));
						}
					}
					else{
						if(Character.getNumericValue(list.get(i).charAt(2)) < max){
							max = Character.getNumericValue(list.get(i).charAt(2));
						}
					}
				}
			}
		}
		
		calculateLongestCol(max);
		
		if(OptimalLongestColMoveSet.size() == 1){
			optimalMove = OptimalLongestColMoveSet.get(0);
		}
		else{
			OptimalLongestRowMove();
		}
	}

	public void calculateLongestCol(int max) {
		// TODO Auto-generated method stub
		int temp = max;
		Set<String> set = allMoves.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			ArrayList<String> list = allMoves.get(key);
			if(list.size() > 0){
				for(int i=0; i<list.size(); i++){
					String cordinates = list.get(i);
					if(Character.getNumericValue(list.get(i).charAt(2)) == temp){
						OptimalLongestColMoveSet.add(key+":"+cordinates);
					}
				}
			}
		}
	}

	
	/*
	 * Find all moves that moves to the highest column when there are optimal captures
	 * 
	 */
	private void calculateLongestMove() {
		int max = Character.getNumericValue(optimalCaptureSet.get(0).charAt(6));
		for(int i=0; i<optimalCaptureSet.size(); i++){
			if(turn == 'W'){
				if(Character.getNumericValue(optimalCaptureSet.get(i).charAt(6)) > max){
					max = Character.getNumericValue(optimalCaptureSet.get(i).charAt(6));
				}
			}
			else{
				if(Character.getNumericValue(optimalCaptureSet.get(i).charAt(6)) < max){
					max = Character.getNumericValue(optimalCaptureSet.get(i).charAt(6));
				}
			}
		}
		for(int i=0; i<optimalCaptureSet.size();i++){
			if(Character.getNumericValue(optimalCaptureSet.get(i).charAt(6)) == max){
				OptimalLongestColMoveSet.add(optimalCaptureSet.get(i));
			}
		}
		
		if(OptimalLongestColMoveSet.size() == 1){
			optimalMove = OptimalLongestColMoveSet.get(0);
		}
		else{
			OptimalLongestRowMove();
		}
	}

	
	/*
	 * Find all moves that moves to the highest row 
	 * 
	 */
	public void OptimalLongestRowMove() {

		int max = Character.getNumericValue(OptimalLongestColMoveSet.get(0).charAt(4));
		for(int i=0; i<OptimalLongestColMoveSet.size(); i++){
			if(turn == 'W'){
				if(Character.getNumericValue(OptimalLongestColMoveSet.get(i).charAt(4)) < max){
					max = Character.getNumericValue(OptimalLongestColMoveSet.get(i).charAt(4));
				}
			}
			else{
				if(Character.getNumericValue(OptimalLongestColMoveSet.get(i).charAt(4)) > max){
					max = Character.getNumericValue(OptimalLongestColMoveSet.get(i).charAt(4));
				}
			}
		}
		for(int i=0; i<OptimalLongestColMoveSet.size();i++){
			if(Character.getNumericValue(OptimalLongestColMoveSet.get(i).charAt(4)) == max){
				OptimalLongestRowMoveSet.add(OptimalLongestColMoveSet.get(i));
			}
		}
		
		if(OptimalLongestColMoveSet.size() == 1){
			optimalMove = OptimalLongestRowMoveSet.get(0);
		}
		else{
			OptimalLeastColMove();
		}
	}

	/*
	 * Find all moves that moves from the lowest column 
	 * 
	 */
	public void OptimalLeastColMove() {
		// TODO Auto-generated method stub
		int min = Character.getNumericValue(OptimalLongestRowMoveSet.get(0).charAt(2));
		for(int i=0; i<OptimalLongestRowMoveSet.size(); i++){
			if(turn == 'W'){
				if(Character.getNumericValue(OptimalLongestRowMoveSet.get(i).charAt(2)) < min){
					min = Character.getNumericValue(OptimalLongestRowMoveSet.get(i).charAt(2));
				}
			}
			else{
				if(Character.getNumericValue(OptimalLongestRowMoveSet.get(i).charAt(2)) > min){
					min = Character.getNumericValue(OptimalLongestRowMoveSet.get(i).charAt(2));
				}
			}
		}
		for(int i=0; i<OptimalLongestRowMoveSet.size();i++){
			if(Character.getNumericValue(OptimalLongestRowMoveSet.get(i).charAt(2)) == min){
				OptimalLeastColMoveSet.add(OptimalLongestRowMoveSet.get(i));
			}
		}
		
		if(OptimalLeastColMoveSet.size() == 1){
			optimalMove = OptimalLeastColMoveSet.get(0);
		}
		else{
			OptimalLeastRowMove();
		}
	}
	
	/*
	 * Find an optimal move that moves from the lowest row
	 * 
	 */
	private void OptimalLeastRowMove() {
		// TODO Auto-generated method stub
		int min = Character.getNumericValue(OptimalLeastColMoveSet.get(0).charAt(0));
		for(int i=0; i<OptimalLeastColMoveSet.size(); i++){
			if(turn == 'W'){
				if(Character.getNumericValue(OptimalLeastColMoveSet.get(i).charAt(0)) > min){
					min = Character.getNumericValue(OptimalLeastColMoveSet.get(i).charAt(0));
				}
			}
			else{
				if(Character.getNumericValue(OptimalLeastColMoveSet.get(i).charAt(0)) < min){
					min = Character.getNumericValue(OptimalLeastColMoveSet.get(i).charAt(0));
				}
			}
		}
		
		
		for(int i=0; i<OptimalLeastColMoveSet.size(); i++){
			if(Character.getNumericValue(OptimalLeastColMoveSet.get(i).charAt(0)) == min){
				optimalMove = OptimalLeastColMoveSet.get(i);
				break;
			}	
		}
	}

	/*
	 * Find all the captures that a chess piece can make
	 * 
	 */
	private void calculateOptimalCaptures() {
		// TODO Auto-generated method stub
		Set<String> set = allMoves.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			ArrayList<String> list = allMoves.get(key);
			if(list.size() > 0){
				for(int i=0; i<list.size(); i++){
					String cordinates = list.get(i);
					if(turn == 'W'){
						if (checkWhiteCaptures(cordinates)){
							optimalCaptureSet.add(key+":"+cordinates);
						}
					}
					else{
						if(checkBlackCaptures(cordinates)){
							optimalCaptureSet.add(key+":"+cordinates);
						}
					}
				}
			}
		}
	}

	/*
	 * Function to check if a black piece can capture its opponent
	 * 
	 */
	private boolean checkBlackCaptures(String cordinates) {
		String[] index = cordinates.split("-");
		if(Character.isUpperCase(Board.board[Integer.parseInt(index[0])][Integer.parseInt(index[1])])){
			return true;
		}
		else{
			return false;
		}
	}

	/*
	 * Function to check if a white piece can capture its opponent
	 * 
	 */
	private boolean checkWhiteCaptures(String cordinates) {
		String[] index = cordinates.split("-");
		if(Character.isLowerCase(Board.board[Integer.parseInt(index[0])][Integer.parseInt(index[1])])){
			return true;
		}
		else{
			return false;
		}
	}
	
}
