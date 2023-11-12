package efV2;

//Aarya Reddy 

import efV2.Login;
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
	
	static boolean match = false;
	static int code;
	
	public static void display() {
		Stage window = new Stage();
		
		
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
		
		window.setTitle("Authentication");
		window.setMinWidth(250);
		window.setMaxHeight(150);
	
		
		Label label = new Label();
		label.setText("Enter User Code:");
		
		TextField input = new TextField();
		Label warning = new Label();
		warning.setText("");		
		
		Button enter = new Button("Enter");
		enter.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	
            	warning.setText("");
            	
            	if (input.getText().equals("")) {
            		warning.setText("Field is empty.");
            	}
            	else {
            		code = Integer.parseInt(input.getText());
                	for (int i = 0; i < Login.users.size(); i++) {
            			if (Login.users.get(i).getCode() == code && Login.users.get(i).getUsername() == Login.currentUser.getUsername()) {
                			match = true;
                		}
            		}
                	
                	if(match == true) {
                		window.close();
                	}else {
                		warning.setText("UserCode Incorrect. Please Retry!!");
                	}
            	}
        		
            }
        });
		
		VBox layout = new VBox(10);
		layout.setSpacing(5);
		layout.setPadding(new Insets(15, 12, 15, 12));
		
		layout.getChildren().addAll(label, input, enter, warning);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout, 150, 300);
		window.setScene(scene);
		window.showAndWait();
		
	}

}
