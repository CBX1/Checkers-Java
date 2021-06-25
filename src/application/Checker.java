package application;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;





public class Checker extends Ellipse {
	Position pos;
	double mouseX, mouseY;
	double oldX, oldY;
		protected boolean isKing;
		Type Type;
		
		public Checker(Position p, Type v) {
		this.pos = new Position(p.row,p.col);
		this.Type = v;
		if(this.Type != Type.DEAD) {
			setRadiusX(30);
			setRadiusY(30);
			relocate(10 + this.pos.col * 100, 10+ this.pos.row * 100);
			switch(this.Type) {
			case RED:
				setFill(Color.PALEVIOLETRED);
				break;	
			case WHITE:
				setFill(Color.FLORALWHITE);
				break;
			default:
				setFill(null);
				break;
				
			}
			if(this.Type != Type.DEAD) {
				setOnMousePressed(e -> {
					
					
				});
			}
		}

    
		
		}
		
			
		public boolean isOpposite(Type c) {
			if(Type == Type.WHITE) {
				return c == Type.RED;
			}
			if(Type == Type.RED) {
				return c == Type.WHITE;
			}
			return false;
		}
		public ArrayList<Position> possibleMoves() {
			ArrayList<Position> validMoves = new ArrayList<Position>();
			if(!isKing && Type == Type.WHITE) {
			Position newpos = new Position(pos.row - 1, pos.row -1);
				if(newpos.isValidPosition()) {
					validMoves.add(newpos);
				}
				newpos = new Position(pos.row + 1, pos.row -1);
				if(newpos.isValidPosition()) {
					validMoves.add(newpos);
				}
			}
			else if(!isKing && Type == Type.RED) {
				Position newpos = new Position(pos.row + 1, pos.row + 1);
				if(newpos.isValidPosition()) {
					validMoves.add(newpos);
				}
				newpos = new Position(pos.row - 1, pos.row + 1);
				if(newpos.isValidPosition()) {
					validMoves.add(newpos);
				}
			}
			else {
			validMoves.add(new Position(pos.row - 1, pos.row -1));
			validMoves.add(new Position(pos.row + 1, pos.row -1));
			validMoves.add(new Position(pos.row + 1, pos.row + 1));
			validMoves.add(new Position(pos.row - 1, pos.row + 1));
			}
		return validMoves;
		}
		
		public Position typePosition(Position changer, Type c) {
			if(c == Type.RED) {
			Position newpos = new Position(pos.row - changer.row, pos.col - changer.col);
			return newpos;
			}
			Position newpos = new Position(- (pos.row - changer.row), -( pos.col - changer.col) );
			return newpos;
			
		}
		

		public void die(){
		}
}


