package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;



public class Checkerboard {
	Checker[][] checkerboard = new Checker[8][8];
	boolean wasMove = false;
	public int furthestMove = 0;

	public Checkerboard(){
		for(int row = 0; row < checkerboard.length; row++) {
			for(int col = 0; col < checkerboard.length; col++) {
				Position p = new Position(row,col);
				if((row >= 0 && row <=2) && (row + col) % 2 ==0) {
					checkerboard[row][col] = new RedChecker(new Position(row,col), Type.RED);
				}
				else if(  (row >=5 && row <=7) && (row + col) % 2 ==0) {
					checkerboard[row][col] = new WhiteChecker(new Position(row,col), Type.WHITE);
				}
				else {
					checkerboard[row][col] = new NoChecker(new Position(row,col), Type.DEAD);
				}
			}
		}
	
	}
	
	
	public Checkerboard move(Position posM, Position posT, Type c) {
		// already valid
		Checkerboard checkerboard1 = null;
		Checkerboard checkerboard2 = null;
		if(checkerboard[posM.row][posM.col].isKing) {
			if(posT.isValidPosition()) {
				if(checkerboard[posM.row][posM.col].isKing && checkerboard[posM.row][posM.col].Type == c) {
					System.out.println(checkerboard[posM.row][posM.col].Type + " King");
					
					Position vp = checkerboard[posM.row][posM.col].typePosition(posT, c);
					if(checkerboard[posM.row][posM.col].Type  == Type.RED) {
						vp = new Position(vp.row *= -1, vp.col *= -1);
					}
					if((vp.row == -1 && vp.col == -1) || (vp.row ==  -1 && vp.col == 1) || (vp.row ==  1 && vp.col ==  1) || (vp.row ==   1 && vp.col ==  -1) ) {
						if(checkerboard[posT.row][posT.col].Type == Type.DEAD) {
							if(c == Type.WHITE) {
							checkerboard[posT.row][posT.col] = new WhiteChecker(posT, checkerboard[posM.row][posM.col].Type);
							checkerboard[posT.row][posT.col].setFill(Color.DARKGREEN);
							}
							else if(c == Type.RED) {
								checkerboard[posT.row][posT.col] = new RedChecker(posT, checkerboard[posM.row][posM.col].Type);
								checkerboard[posT.row][posT.col].setFill(Color.CHOCOLATE);
							}
							checkerboard[posT.row][posT.col].isKing = true;
							checkerboard[posM.row][posM.col] = new NoChecker(posM, Type.DEAD);
							wasMove = true;
							return this;
						}
					}
					else if( ( ( (vp.row == 2 && vp.col ==  2) && posM.row != 7 && posM.col !=7 && checkerboard[posM.row][posM.col].isOpposite(checkerboard[posM.row  + 1][posM.col + 1].Type)  ) || 
							( (vp.row == -2 && vp.col == -2) && posM.row != 0 && posM.col !=0 && checkerboard[posM.row][posM.col].isOpposite(checkerboard[posM.row -1][posM.col -1].Type) ) ||
							( (vp.row == -2 && vp.col == 2) && posM.row != 0 && posM.col !=7 && checkerboard[posM.row][posM.col].isOpposite(checkerboard[posM.row -1][posM.col +1].Type) )  ||    
							( (vp.row == 2 && vp.col == -2) && posM.row != 7 && posM.col !=0 && checkerboard[posM.row][posM.col].isOpposite(checkerboard[posM.row + 1][posM.col - 1].Type)) ) &&  
							checkerboard[posT.row][posT.col].Type == Type.DEAD &&
							checkerboard[posM.row][posM.col].Type == c && 
							checkerboard[posM.row][posM.col].isKing
							){
						if(c == Type.WHITE) {
						checkerboard[posT.row][posT.col] = new WhiteChecker(posT, checkerboard[posM.row][posM.col].Type);
						checkerboard[posT.row][posT.col].setFill(Color.DARKGREEN);
						}
						else if(c == Type.RED) {
							checkerboard[posT.row][posT.col] = new RedChecker(posT, checkerboard[posM.row][posM.col].Type);
							checkerboard[posT.row][posT.col].setFill(Color.CHOCOLATE);
						}
						System.out.println("hit?");
						checkerboard[posT.row][posT.col].isKing = true;
						checkerboard[posM.row][posM.col] = new NoChecker(posM, Type.DEAD);
						checkerboard[posM.row][posM.col].isKing = false;
						checkerboard[posM.row + vp.row/2][posM.col + vp.col/2] = new NoChecker(posT, Type.DEAD);
						wasMove = true;
						furthestMove++;
						Checkerboard c1 = move(posT, new Position(posT.row - 2, posT.col -2), c);
						Checkerboard c2 = move(posT, new Position(posT.row -2, posT.col +2), c );
						Checkerboard c3 = move(posT, new Position(posT.row + 2, posT.col -2), c);
						Checkerboard c4 = move(posT, new Position(posT.row + 2, posT.col +2), c);
						c1.wasMove = true;
						c2.wasMove = true;
						c3.wasMove= true;
						c4.wasMove= true;
						Checkerboard c5 = c1.furthestMove > c2.furthestMove ? c1 : c2;
						Checkerboard c6 = c3.furthestMove > c4.furthestMove ? c3 : c4;
						Checkerboard c7 = c5.furthestMove > c6.furthestMove ? c5 : c6;
						return c7;
						
					}
		
				}
			}
		}
		else if(c == Type.WHITE) {
		//	System.out.println("WHITE hit");
		if(posT.isValidPosition()) {
		//	System.out.println("posM: " + posM.toString());
			System.out.println(checkerboard[posM.row][posM.col].Type);
		
			if(checkerboard[posM.row][posM.col].Type == Type.WHITE) {
				Position vp = checkerboard[posM.row][posM.col].typePosition(posT, c);
				//System.out.println(vp.row + " " + vp.col);
				if((vp.row == -1 && vp.col == -1) || (vp.row ==  -1 && vp.col == 1) ) {
					if(checkerboard[posT.row][posT.col].Type == Type.DEAD ) {
						checkerboard[posT.row][posT.col] = new WhiteChecker(posT, checkerboard[posM.row][posM.col].Type);
						checkerboard[posT.row][posT.col].isKing = checkerboard[posM.row][posM.col].isKing;
						checkerboard[posM.row][posM.col] = new NoChecker(posM, Type.DEAD);
						wasMove = true;
						if(posT.row == 0) {
							checkerboard[posT.row][posT.col].setFill(Color.DARKGREEN);
							checkerboard[posT.row][posT.col].isKing = true;
							System.out.println("promoted");			
						}
						return this;
					}
					
				}
				else if( ( ( (vp.row == -2 && vp.col == -2) && checkerboard[posM.row -1][posM.col -1].Type == Type.RED) || ( (vp.row == -2 && vp.col == 2) && checkerboard[posM.row -1][posM.col +1].Type == Type.RED        )  )  && checkerboard[posT.row][posT.col].Type == Type.DEAD    ){
					checkerboard[posT.row][posT.col] = new WhiteChecker(posT,c);
					checkerboard[posT.row][posT.col].isKing = checkerboard[posM.row][posM.col].isKing;
					checkerboard[posM.row][posM.col] = new NoChecker(posM, Type.DEAD);
					checkerboard[posM.row][posM.col].isKing = false;
					checkerboard[posM.row + vp.row/2][posM.col + vp.col/2] = new NoChecker(posT, Type.DEAD);
					wasMove = true;
					furthestMove++;
					boolean kchange = false;
					for(int x = 0; x < checkerboard[0].length; x++) {
						if(checkerboard[0][x].Type  == Type.WHITE) {
							System.out.println(posT.toString());
							checkerboard[0][x].setFill(Color.DARKGREEN);
							checkerboard[0][x].isKing = true;	
							kchange = true;
						}
					}
					
					Checkerboard c1 = move(posT, new Position(posT.row - 2, posT.col -2), Type.WHITE);
					Checkerboard c2 = move(posT, new Position(posT.row -2, posT.col +2), Type.WHITE );
					if(kchange) {
						c1.wasMove = true;
						c2.wasMove = true;
						Checkerboard c3 = move(posT, new Position(posT.row + 2, posT.col + 2), Type.WHITE);
						Checkerboard c4 = move(posT, new Position(posT.row + 2, posT.col -2), Type.WHITE );
						c3.wasMove= true;
						c4.wasMove= true;
						Checkerboard c5 = c1.furthestMove > c2.furthestMove ? c1 : c2;
						Checkerboard c6 = c3.furthestMove > c4.furthestMove ? c3 : c4;
						Checkerboard c7 = c5.furthestMove > c6.furthestMove ? c5 : c6;
						return c7;
					}
					c1.wasMove = true;
					c2.wasMove = true;
					Checkerboard c3 = c1.furthestMove > c2.furthestMove ? c1 : c2;
					return c3;
				}
			}
			
			}
		}
		else if(c == Type.RED) {
			System.out.println("RED");
			if(posT.isValidPosition()) {
				if(checkerboard[posM.row][posM.col].Type != Type.DEAD) {
					Position vp = new Position(posT.row - posM.row, posT.col - posM.col);
					if( (vp.row ==  1 && vp.col ==  1) || (vp.row ==   1 && vp.col ==  -1)  ) {
					
						if(checkerboard[posT.row][posT.col].Type == Type.DEAD ) {
							checkerboard[posT.row][posT.col] = new RedChecker(posT, checkerboard[posM.row][posM.col].Type);
							checkerboard[posT.row][posT.col].isKing = checkerboard[posM.row][posM.col].isKing;
							checkerboard[posM.row][posM.col] = new NoChecker(posM, Type.DEAD);
							wasMove = true;
							if(posT.row == 7) {
								checkerboard[posT.row][posT.col].setFill(Color.CHOCOLATE);
								checkerboard[posT.row][posT.col].isKing = true;
								System.out.println("promoted");			
							}
							return this;
							
						}

					}
					else if( ( ( (vp.row == 2 && vp.col ==  2) && checkerboard[posM.row  + 1][posM.col + 1].Type == Type.WHITE) || ( (vp.row == 2 && vp.col == -2) && checkerboard[posM.row + 1][posM.col - 1].Type == Type.WHITE       )  )  && checkerboard[posT.row][posT.col].Type == Type.DEAD    ){
						checkerboard[posT.row][posT.col] = new RedChecker(posT,c);
						checkerboard[posT.row][posT.col].isKing = checkerboard[posM.row][posM.col].isKing;
						checkerboard[posM.row][posM.col] = new NoChecker(posM, Type.DEAD);
						checkerboard[posM.row + vp.row/2][posM.col + vp.col/2] = new NoChecker(posT, Type.DEAD);
						checkerboard[posM.row][posM.col].isKing = false;
						wasMove = true;
						furthestMove++;
						boolean kchange = false;
						for(int x = 0; x < checkerboard[0].length; x++) {
							if(checkerboard[7][x].Type  == Type.RED) {
								checkerboard[7][x].setFill(Color.CHOCOLATE);
								checkerboard[7][x].isKing = true;		
								kchange = true;
							}
						} 
						
						Checkerboard c1 = move(posT, new Position(posT.row + 2, posT.col + 2), Type.RED);
						Checkerboard c2 = move(posT, new Position(posT.row + 2, posT.col - 2), Type.RED );
						if(kchange) {
							c1.wasMove = true;
							c2.wasMove = true;
							Checkerboard c3 = move(posT, new Position(posT.row + 2, posT.col + 2), Type.RED);
							Checkerboard c4 = move(posT, new Position(posT.row + 2, posT.col -2), Type.RED );
							c3.wasMove= true;
							c4.wasMove= true;
							Checkerboard c5 = c1.furthestMove > c2.furthestMove ? c1 : c2;
							Checkerboard c6 = c3.furthestMove > c4.furthestMove ? c3 : c4;
							Checkerboard c7 = c5.furthestMove > c6.furthestMove ? c5 : c6;
						}
						c1.wasMove = true;
						c2.wasMove = true;
						Checkerboard c3 = c1.furthestMove > c2.furthestMove ? c1 : c2;

						return c3;
					}
				
				}
			}
		}
		
		wasMove = false;
		
		return this;
	}

	
	public void printBoard() {
		System.out.print("  ");
		for(int z = 0; z < 8; z++) {
			System.out.print(z + " ");
		}
		System.out.println();
		int countcol = 0;
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
					if(y==0) {
					System.out.print(countcol + " ");
					countcol++;
					}
					System.out.print(checkerboard[x][y].toString());
				
			}
			System.out.println();
		}

		
		
	}
	
	
}
