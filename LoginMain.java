package efV2;
//Kendra Newman

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.util.Random;

public class Login {
	
	static boolean match;
	boolean empty;
	public static ArrayList<Users> users = new ArrayList<>();
	public static Users currentUser;
	
	public static Scene start(Stage window, Scene set) {
	        
	        //login section
	        Label login = new Label();
	        login.setText("Login");
	        login.setStyle("-fx-text-fill:black;-fx-font-size:25px;");
	        login.setTextAlignment(TextAlignment.CENTER);
	        login.setAlignment(Pos.CENTER);
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
	            			currentUser = users.get(i);
	            			Poker.user = currentUser.getCode();
	            			
	            			if(currentUser.getPoker()!= null) {
	            				Poker.sessionList = currentUser.getPoker();
	            			}
	            			
	            			if(currentUser.getEffort()!= null) {
	            				EffortConsole.effort = currentUser.getEffort();
	            			}
	            			
	            			if(currentUser.getDefect()!= null) {
	            				DefectConsole.defectList = currentUser.getDefect();
	            			}
	            			
	            			if(currentUser.getProj()!= null) {
	            				ProjectManagementConsole.projects = currentUser.getProj();
	            			}
	            			if(currentUser.getProjMap()!=null) {
	            				ProjectManagementConsole.projMap = currentUser.getProjMap();
	            			}
	            			
	            			Dashboard.none.setText("No entries yet, Get Logging!!");
	            			
//	            			if(Dashboard.timelines.size()>0) {
//	            				Dashboard.startAllTimeLines();
//	            			}	            			
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
	        create.setStyle("-fx-text-fill:black;-fx-font-size:25px;");
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
	            	} else if (passwordText2.getText().length() < 8) {
	            		createWarning.setText("Password length is " + passwordText2.getText().length() + " add " + (8 - passwordText2.getText().length())+ " more characters.");
	            	}
	            	else {
	            		for (int i = 0; i < users.size(); i++) {
	            			if (users.get(i).getUsername().equals(usernameText2.getText())) {
	                			match = true;
	                		}
	            		}
	            		int k = 0;
	            		boolean charAmount = true;
	            		for (int j = 0; j < passwordText2.getText().length(); j++) {
	            			if (passwordText2.getText().substring(j, j+1).equals("@") || passwordText2.getText().substring(j, j+1).equals("#") 
	            					|| passwordText2.getText().substring(j, j+1).equals("*") || passwordText2.getText().substring(j, j+1).equals("_")) {
	            				k++;
	            			}
	            		}
	            		if (k < 2) {
	            			charAmount = false;
	            		}
	            		if (charAmount == false) {
	            			createWarning.setText("Your password is missing at least 1 of the following: @, #, *, or _.");
	            		} else {
	            			if (match == false) {
		            			//add to list
		            			Random rand = new Random();
		            			   
		            	        // Generate random integers in range 0 to 999
		            	        int rand_int = rand.nextInt(1000);
		            	        //check if user code exists
		            	        boolean match2 = true;
		            	        while(match2)
		            	        {
		            	        	match2 = false;
			            	        for (int i = 0; i < users.size(); i++) {
				            			if (users.get(i).getCode() == rand_int) {
				                			rand_int = rand.nextInt(1000); //make new code if user code exists
				                			match2 = true;
				                		}
				            		}
		            	        }
		            	        
		            	        Users newUser = new Users(nameText.getText(), emailText.getText(), usernameText2.getText(), passwordText2.getText(), rand_int);
		            	        newUser.setDefect(null);
		            	        newUser.setEffort(null);
		            	        newUser.setPoker(null);
		            	        newUser.setProj(null);
		            	        newUser.setProjMap(null);
		            	        users.add(newUser);
		            	        createWarning.setText("Account created!! User Code: " + rand_int);
		                		
		            		} else {
		            			//error message
		                		createWarning.setText("Username already taken. Try again.");
		            		}
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
	        BorderPane root = new BorderPane();
	        grid.setPadding(new Insets(10));
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
	        
	        grid.setPrefWidth(300);
	        
	        Text welcome = new Text();
			welcome.setText("Welcome to \nEffortLoggerV2");
			welcome.setFont(Font.font("helvetica", FontWeight.THIN, FontPosture.ITALIC, 30));
			
	        root.setLeft(welcome);
	        BorderPane.setAlignment(welcome, Pos.CENTER);
	        root.setRight(grid);
			root.setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY)));
	        
        Scene scene = new Scene(root, 600,450);
        return scene;
	}
	
	// Orion: Added getter for the userlist
		public static ArrayList<Users> getUsersList() {
			return users;
		}
}
