package efV2;
//Kendra Newman

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Login {
	
	static boolean match;
	boolean empty;
	
	public static Scene start(Stage window, Scene set) {
			ArrayList<Users> users = new ArrayList<>();
	        Label heading = new Label();
	        heading.setText("EffortLogger");
	        
	        //login section
	        Label login = new Label();
	        login.setText("Login");
	        Label username1 = new Label();
	        username1.setText("Username");
	        TextArea usernameText1 = new TextArea();
	        usernameText1.setPrefWidth(75);
	        usernameText1.setMaxWidth(75);
	        usernameText1.setPrefHeight(20);
	        usernameText1.setMaxHeight(20);
	        Label password1 = new Label();
	        password1.setText("Password");
	        TextArea passwordText1 = new TextArea();
	        passwordText1.setPrefWidth(75);
	        passwordText1.setMaxWidth(75);
	        passwordText1.setPrefHeight(20);
	        passwordText1.setMaxHeight(20);
	        Button loginButton = new Button();
	        loginButton.setText("Login");
	        Label loginWarning = new Label();	//set as blank until needed
	        loginWarning.setText("");
	        
	        //login button action handler
	        loginButton.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	                //search all users in list for a match of username and password
	            	match = false;
	            	loginWarning.setText("");
	            	for (int i = 0; i < users.size(); i++) {
	            		if ((users.get(i).getUsername().equals(usernameText1.getText())) && (users.get(i).getPassword().equals(passwordText1.getText()))) {
	            			match = true;
	            			
	            		}
	            	}
	            	
	            	if (usernameText1.getText().equals("") || passwordText1.getText().equals("")) {
	            		loginWarning.setText("One or more fields are empty.");
	            	}
	            	else if (match == true) {
	            		//proceed to dashboard
	            		loginWarning.setText("Login accepted.");
	            		window.setScene(set);
	            		twofactor.display();
	            	} else {
	            		//login warning needs to say wrong username or password
	            		loginWarning.setText("Wrong username or password. Try again.");
	            	}
	            	usernameText1.setText("");
	        		passwordText1.setText("");
	            }
	        });
	        
	        //create account section
	        Label create = new Label();
	        create.setText("Create Account");
	        Label name = new Label();
	        name.setText("Name");
	        TextArea nameText = new TextArea();
	        nameText.setPrefWidth(75);
	        nameText.setMaxWidth(75);
	        nameText.setPrefHeight(20);
	        nameText.setMaxHeight(20);
	        Label email = new Label();
	        email.setText("Email");
	        TextArea emailText = new TextArea();
	        emailText.setPrefWidth(75);
	        emailText.setMaxWidth(75);
	        emailText.setPrefHeight(20);
	        emailText.setMaxHeight(20);
	        Label username2 = new Label();
	        username2.setText("Username");
	        TextArea usernameText2 = new TextArea();
	        usernameText2.setPrefWidth(75);
	        usernameText2.setMaxWidth(75);
	        usernameText2.setPrefHeight(20);
	        usernameText2.setMaxHeight(20);
	        Label password2 = new Label();
	        password2.setText("Password");
	        TextArea passwordText2 = new TextArea();
	        passwordText2.setPrefWidth(75);
	        passwordText2.setMaxWidth(75);
	        passwordText2.setPrefHeight(20);
	        passwordText2.setMaxHeight(20);
	        Button createButton = new Button();
	        createButton.setText("Create account");
	        Label createWarning = new Label(); //leave empty until needed
	        createWarning.setText("");
	        
	        //create button action handler
	        createButton.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	            	match = false;
	            	createWarning.setText("");
	                //search list to see if username is already in list
	            	//check if one of the text boxes is empty or not
	            	if (nameText.getText().equals("") || emailText.getText().equals("") || usernameText2.getText().equals("") || passwordText2.getText().equals("")) {
	            		createWarning.setText("One or more fields are empty.");
	            	} else {
	            		for (int i = 0; i < users.size(); i++) {
	            			if (users.get(i).getUsername().equals(usernameText2.getText())) {
	                			match = true;
	                		}
	            		}
	            		if (match == false) {
	            			//add to list
	                		Users newUser = new Users(nameText.getText(), emailText.getText(), usernameText2.getText(), passwordText2.getText());
	                		users.add(newUser);
	                		createWarning.setText("Account created.");
	            		} else {
	            			//error message
	                		createWarning.setText("Username already taken. Try again.");
	            		}
	            	}
	            	nameText.setText("");
	            	emailText.setText("");
	            	usernameText2.setText("");
	        		passwordText2.setText("");
	            }
	        });
	        
	        
	        
	        //grid pane and stack pane stuff:
	        GridPane grid = new GridPane();
	        StackPane root = new StackPane();
	        grid.add(heading, 0, 0);
	        grid.add(login, 0, 1);
	        grid.add(username1, 0, 2);
	        grid.add(usernameText1, 1, 2);
	        grid.add(password1, 0, 3);
	        grid.add(passwordText1, 1, 3);
	        grid.add(loginButton, 0, 4);
	        grid.add(loginWarning, 0, 5);
	       
	        grid.add(create, 0, 6);
	        grid.add(name, 0, 7);
	        grid.add(nameText, 1, 7);
	        grid.add(email, 0, 8);
	        grid.add(emailText, 1, 8);
	        grid.add(username2, 0, 9);
	        grid.add(usernameText2, 1, 9);
	        grid.add(password2, 0, 10);
	        grid.add(passwordText2, 1, 10);
	        grid.add(createButton, 0, 11);
	        grid.add(createWarning, 0, 12);
	        root.getChildren().add(grid);
        Scene scene = new Scene(root, 750,750);
        return scene;
	}
}



