package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*; 

public class twofactor {
	
	public static void display() {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Authentication");
		window.setMinWidth(250);
		window.setMaxHeight(150);
	
		
		Label label = new Label();
		label.setText("Enter User Code:");
		
		TextField input = new TextField();
		
		
		Button enter = new Button("Enter");
		enter.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.setSpacing(5);
		layout.setPadding(new Insets(15, 12, 15, 12));
		
		layout.getChildren().addAll(label, input, enter);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 150, 300);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
