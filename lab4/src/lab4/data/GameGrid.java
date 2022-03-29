package lab4.data;

import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable{
	public static int EMPTY = 0, ME = 1, OTHER = 2, INROW = 5;
	public int[][] twoDim;

	
	public GameGrid(int size){
		twoDim = new int[size][size];
	}
	public int getLocation(int x, int y){
		return twoDim[y][x];
	}
	
	public int getSize(){
		return twoDim.length; 
	}
    public boolean move(int x, int y, int player){
        int pos = twoDim[y][x];
        if(pos == EMPTY) {
            twoDim[y][x] = player;
            setChanged();
            notifyObservers();
            return true;
        }
        return false;
    }

	public void clearGrid(){
		for(int i = 0; i < twoDim.length; i++) {
            for(int j = 0; j < twoDim.length; j++) {
            	twoDim[i][j] = EMPTY;
            }
		}
		setChanged();
        notifyObservers();
	} 
	private boolean wonRow(int i, int j, int player) {
        for(int x = 0; x < INROW; x++){
        	if(twoDim[i][j+x] == player) {}
        	else {
        		return false;
        	}
        }
        return true;
	}
	
	private boolean wonCol(int i, int j, int player) {
        for(int x = 0; x < INROW; x++){
        	if(twoDim[i+x][j] == player) {}
        	else {
        		return false;
        	}
        }
        return true;
	}
	
	private boolean wonDiaR(int i, int j, int player) {
		for(int x = 0; x < INROW; x++) {
			if(twoDim[i+x][j+x] == player) {}
			else {
				return false;
			}
		}
		return true;
	}
	
	private boolean wonDiaL(int i, int j, int player) {
		for(int x = 0; x < INROW; x++) {
			if(twoDim[i+x][j-x] == player) {}
			else {
				return false;
			}
		}
		return true;
	}
	
	
	public boolean isWinner(int player){
        for(int i = 0; i < twoDim.length; i++) {
            for(int j = 0; j < twoDim.length; j++) {
                boolean rowCheck = j + INROW - 1  < twoDim.length;
            	boolean colCheck = i + INROW - 1  < twoDim.length;
            	boolean negativeColumCheck = j - INROW + 1 >= 0;
            	//System.out.println(j + ", " + i);
                if(twoDim[i][j] == player) {
                    if(rowCheck) {
                    	if(wonRow(i,j,player)) {
                    		return true;
                    	}
                    }
                    
                    if(colCheck) {
                    	if(wonCol(i,j,player)) {
                    		return true;
                    	}
                    }
                    
                    if(colCheck && rowCheck) {
                    	if(wonDiaR(i,j,player)) {
                    		return true;
                    	}
                    }
                    
                    if(colCheck && negativeColumCheck) {
                    	if(wonDiaL(i,j,player)) {
                    		return true;
                    	}
                    }
                }
            }
        }
        return false;
    }	
}