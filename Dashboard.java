package efV2;

//Aarya Reddy 

import efV2.Login;
import efV2.twofactor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture; 
import javafx.scene.text.Text;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.*; 

public class Dashboard extends Application {
	public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {

        primaryStage.setTitle("EffortLoggerV2");
    
        //setup borderpanes for each module
        
        BorderPane dash = new BorderPane();
        BorderPane project = new BorderPane();
        BorderPane effort = EffortConsole.start();
        BorderPane defect = new BorderPane();
        BorderPane settings = new BorderPane();
        BorderPane data = new BorderPane();
        
        //setup scene to switch between them
		
		Scene scene = new Scene(dash, 600, 400);
		Scene proj = new Scene(project, 600, 400);
		Scene eff = new Scene(effort, 600, 400);
		Scene def = new Scene(defect, 750, 550);
		Scene set = new Scene(settings, 600, 400);
		Scene dat = new Scene(data, 680, 400);
		Scene out = Login.start(primaryStage, scene);
		
		//link scenes to each module
				
		dash.setBottom(addConsole(primaryStage, proj, eff, def));
		dash.setLeft(addOptions(primaryStage, set, dat, out, scene));
		dash.setCenter(dash());
		
		project.setBottom(addConsole(primaryStage, proj, eff, def));
		project.setLeft(addOptions(primaryStage, set, dat, out, scene));
		project.setCenter(ProjectManagementConsole.addGridPane());
		
		effort.setBottom(addConsole(primaryStage, proj, eff, def));
		effort.setLeft(addOptions(primaryStage, set, dat, out, scene));
		effort.setCenter(EffortConsole.start());
		
		defect.setBottom(addConsole(primaryStage, proj, eff, def));
		defect.setLeft(addOptions(primaryStage, set, dat, out, scene));
		defect.setCenter(DefectConsole.start());
		
		settings.setCenter(settings(primaryStage, scene));
		data.setCenter(data(primaryStage, scene));
		
		
        primaryStage.setScene(out);
        primaryStage.show();
    }
    
    //dashboard
    public GridPane dash() {
    	GridPane grid = new GridPane();
    	
    	
    	Text welcome = new Text();
		welcome.setText("Dashboard");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    	
    	grid.add(welcome, 0, 0);
    	
    	return grid;
    }
    
    
    //settings page
    public GridPane settings(Stage window, Scene set) {
    	GridPane grid = new GridPane();
    	
    	Button home = new Button("Home");
    	home.setOnAction(e -> window.setScene(set));
    	
    	
    	Text welcome = new Text();
		welcome.setText("Settings Console");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    	
		grid.setPadding(new Insets(10));
    	grid.add(welcome, 0, 0);
    	grid.add(home, 3, 3);
    	
    	return grid;
    }
    
    
    //data page
    public GridPane data(Stage window, Scene set) {
    	GridPane grid = new GridPane();
    	Button home = new Button("Home");
    	home.setOnAction(e -> window.setScene(set));
    	
    	Text welcome = new Text();
		welcome.setText("Historical Data");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    	
		grid.setPadding(new Insets(10));
    	grid.add(welcome, 0, 0);
    	grid.add(home, 3, 3);
    	
    	return grid;
    }
    
    //addConsole to display console options 
    public HBox addConsole(Stage window, Scene proj, Scene eff, Scene def) {
    	HBox hbox = new HBox();
    	
		Button effort = new Button();
		Button defect = new Button();
		Button project = new Button();
		
		
		hbox.setAlignment(Pos.BASELINE_CENTER);
		
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		
		effort.setText("Effort Console");
		effort.setOnAction(e -> window.setScene(eff));
		defect.setText("Defect Console");
		defect.setOnAction(e -> window.setScene(def));
		project.setText("Project Console");
		project.setOnAction(e -> window.setScene(proj));
		
		
		effort.setMaxSize(200, 200);
		defect.setMaxSize(200, 200);
		project.setMaxSize(200, 200);
		

		hbox.getChildren().add(effort);
		hbox.getChildren().add(defect);
		hbox.getChildren().add(project);
    	return hbox;
    }
    
    //addOptions to display additional options
    public VBox addOptions(Stage window, Scene set, Scene dat, Scene out, Scene hom) {
    	VBox vbox = new VBox();
    	Button setting = new Button();
		Button logout = new Button();
		Button userLogs = new Button();
		Button poker = new Button();
		Button home = new Button();
		Text title = new Text("Options:");
		
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		vbox.getChildren().add(title);
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		
		vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		VBox.setMargin(poker, new Insets(0, 0, 0, 8));
    	vbox.getChildren().add(poker);
		VBox.setMargin(userLogs, new Insets(0, 0, 0, 8));
		vbox.getChildren().add(userLogs);
		VBox.setMargin(setting, new Insets(0, 0, 0, 8));
    	vbox.getChildren().add(setting);
    	VBox.setMargin(home, new Insets(0, 0, 0, 8));
    	vbox.getChildren().add(home);
    	VBox.setMargin(logout, new Insets(0, 0, 0, 8));
		vbox.getChildren().add(logout);
		
		
		poker.setText("P.Poker");
		poker.setOnAction(e -> window.setScene(Poker.start(window, hom)));
		userLogs.setText("Your Data");
		userLogs.setOnAction(e -> window.setScene(dat));
		setting.setText("Settings");
		setting.setOnAction(e -> window.setScene(set));
		logout.setText("Log Out");
		logout.setOnAction(e -> window.setScene(out));
		home.setText("Home");
		home.setOnAction(e -> window.setScene(hom));
		
		poker.setMaxSize(90, 90);
		home.setMaxSize(90, 90);
		userLogs.setMaxSize(90, 90);
		setting.setMaxSize(90, 90);
		logout.setMaxSize(90, 90);
    	
    	return vbox;
    }
}
