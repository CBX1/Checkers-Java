package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Tile extends Rectangle {
	
	Checker checker;
	public boolean hasPiece() {
		if(checker != null) {
			return true;
		}
		return false;
	}
	public Tile(boolean light, int x, int y) {
		setWidth(100);
		setHeight(100);
		
		relocate(x * 100, y * 100);
		setFill(light ? Color.ORANGE : Color.CORAL);
	}
	public void setFill(int i) {
		setFill(i == 0 ? Color.WHITE : Color.BLACK);
		
	}
	

}
