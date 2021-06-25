package application;
	
import java.awt.Color;
import java.awt.Font;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Main extends Application {
	Checkerboard ab = new Checkerboard();
	Group tileGroup = new Group();
	Group pieceGroup = new Group();
    int[] clicks = {0};
    boolean[] bools = {true};
    Position[] p = new Position[1];
    boolean[] whoseT = {true};
    Tile v;
    boolean vv = true;
    

	Parent createContent() {
		
		tileGroup.getChildren().clear();
		pieceGroup.getChildren().clear();
		Pane root = new Pane();
		root.setPrefSize(800,800);
		root.getChildren().addAll(tileGroup, pieceGroup);

	
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				Tile tile = new Tile((x + y) % 2 ==0, x, y);
				tileGroup.getChildren().add(tile);
				pieceGroup.getChildren().add(ab.checkerboard[x][y]);
			}
		}

		
		root.setOnMouseClicked(e -> {

        	clicks[0] = clicks[0] + 1;
        	if(clicks[0] == 1) {
        		p[0] = new Position((int) Math.floor(e.getY()/100), (int) Math.floor(e.getX()/100));
        		// System.out.println("Going From: ["+ Math.floor(e.getX()/100) + ", "+ Math.floor(e.getY()/100) +"]");
        	}
        	else if(clicks[0] == 2) {
        		Type c = bools[0] ? Type.WHITE : Type.RED;

        	//	System.out.println("To: [" + Math.floor(e.getX()/100) + ", "+ Math.floor(e.getY()/100) + "]");
        		ab = ab.move(p[0], new Position((int) Math.floor(e.getY()/100), (int) Math.floor(e.getX()/100) ), c);
        		
        		//ab.printBoard();
        		
        	//	System.out.println(ab.wasMove);
        		if(ab.wasMove) {
        			pieceGroup.getChildren().clear();
        			for(int x = 0; x < 8; x++) {
        				for(int y = 0; y < 8; y++) {
          					pieceGroup.getChildren().add(ab.checkerboard[x][y]);
        				}
        			}

	        		bools[0] = !bools[0];
	        		clicks[0] = 0;
	        		whoseT[0] = !whoseT[0];
        		}
        		else {
        			clicks[0] = 0;
        			System.out.println("Still " + c + " to move" );
        		}
        		
        	}
		});
		return root;
		
	}
	

	public void start(Stage primaryStage) {
		try {
		       Scene[] scene = {new Scene(createContent())};
		        primaryStage.setTitle("CheckersApp");
		        primaryStage.setScene(scene[0]);
		        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
