package efV2;

import java.util.ArrayList;
import java.util.Map;

//Aarya Reddy 

import efV2.Login;
import efV2.twofactor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;

public class Dashboard extends Application {
	
	public static Label hello = new Label();
	public static Text none = new Text();
	public static ArrayList<Timeline> timelines = new ArrayList<>();
	public static ListView<Entry> resultView; 
	public static ListView<Entry> viewCurProj;
	public static ObservableList<Entry> resultList = FXCollections.observableArrayList();
	public static ObservableList<Entry> curProjects = FXCollections.observableArrayList();
	
	
	public static void main(String[] args) {
        launch(args);
    }
	
	public static void startAllTimeLines() {
		resultView = resultPage(resultList, 1);
		viewCurProj = resultPage(curProjects, 2);
	}
	
    
    public void start(Stage primaryStage) {

        primaryStage.setTitle("EffortLoggerV2");
    
        //setup borderpanes for each module
        BorderPane dash = new BorderPane();
        Scene scene = new Scene(dash, 600, 400);
        
        BorderPane project = new BorderPane();
        BorderPane effort = new BorderPane();
        BorderPane defect = new BorderPane();
        BorderPane settings = new BorderPane();
        BorderPane data = new BorderPane();
        
        //setup scene to switch between them
		
		
		Scene proj = new Scene(project, 600, 400);
		Scene eff = new Scene(effort, 650, 450);
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
		effort.setCenter(EffortConsole.start(primaryStage, scene));
		
		defect.setBottom(addConsole(primaryStage, proj, eff, def));
		defect.setLeft(addOptions(primaryStage, set, dat, out, scene));
		defect.setCenter(DefectConsole.start());
		
		settings.setCenter(settings(primaryStage, scene));
		data.setCenter(data(primaryStage, scene));
		
		
        primaryStage.setScene(out);
        primaryStage.show();
    }
    
    //dashboard
    public static VBox dash() {
    	VBox screen = new VBox();
    	GridPane main = new GridPane();
    	
    	//header
    	Text das = new Text();
		das.setText("Dashboard");
		das.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		
		//setup actvity list
		Text recent = new Text();
		recent.setText("Recent Activity:");
		recent.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.ITALIC, 10));
		
		
		//setup updates
		startAllTimeLines();
        
		VBox results = new VBox();
		results.getChildren().addAll(recent, resultView);
		results.setMaxHeight(100);
		
		//setup main dash grid
		
		hello.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.ITALIC, 15));
				
		
		Label projLabel = new Label("Ongoing Projects:");
		projLabel.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.ITALIC, 10));
	
		VBox viewProj = new VBox(10);
		VBox layer = new VBox();
		
		layer.getChildren().add(viewCurProj);
		layer.setPadding(new Insets(1));
		
		viewProj.getChildren().addAll(projLabel, layer);
		viewProj.setMaxHeight(220);
		viewProj.setMaxWidth(150);
//		viewProj.setAlignment(Pos.BASELINE_CENTER);
		viewProj.setSpacing(5);
		
		viewProj.setPadding(new Insets(2));
		viewProj.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		
		layer.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		
		main.add(hello, 0, 0);
		
		BorderPane mainDash = new BorderPane();

		VBox left = new VBox();
		VBox center = new VBox();
		
		left.setPadding(new Insets(20));
		
		center.setMinWidth(280);
		none.setText("No entries yet, Get Logging!!");
		center.getChildren().add(none);
		
		mainDash.setRight(viewProj);
		mainDash.setCenter(center);
		mainDash.setLeft(left);
		
				
		main.add(mainDash, 0, 1);
		main.setPadding(new Insets(2,2,2,2));
		
		
		screen.setPadding(new Insets(5,5,5,5));
    	screen.getChildren().addAll(das,main,results);   
    	screen.getChildren().forEach(child -> VBox.setVgrow(child, Priority.ALWAYS));
    	return screen;
    }
    
    public static ListView<Entry> resultPage(ObservableList<Entry> rList, int flag){
    	
    	ListView<Entry> resultView = new ListView<>(rList);
		
		resultView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Entry> call(ListView<Entry> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Entry item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                        	if(item.getPoints() == 666) {
                        		setText(item.getName() + "\n" + item.getDes());
                        		setOnMouseClicked(event -> {
                                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                                    	Poker.searchItemClick(item, 1);
                                    }
                                });
                        	}else {
                        		setText(item.getName() + "    Time Taken: " + item.getTime() + "\n" + item.getDes());
                        		 // Handle mouse click events
                                setOnMouseClicked(event -> {
                                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                                    	Poker.searchItemClick(item, 1);
                                    }
                                });
                        	}
                            
                        }
                    }
                };
            }
        });
		
		if(flag == 1) {
			ArrayList<EffortData> effortArray = EffortConsole.getEffort();
			ArrayList<DefectData> defectArray = DefectConsole.getDefect();
			
			// Set up a Timeline to update the list every 10 seconds
	        Duration duration = Duration.seconds(2);
	        Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
	            // Update the ObservableList on the JavaFX Application Thread
	            Platform.runLater(() -> {

	                // Update the ObservableList on a different thread
	                rList.clear();
	                for (int i = 0; i < effortArray.size(); i++) {
	        			if (Login.currentUser.getCode() == effortArray.get(i).getUserCode()) {
	        				Entry entry = new Entry(effortArray.get(i).getName(), effortArray.get(i).getDescription(), 666, effortArray.get(i).getTime(), "Effort");
	                		rList.add(entry);
	        			}
	        		}
	                if(rList.size() > 0) {
	                	if(rList.size() == 1) {
	                		none.setText("You have logged 1 Effort and Defect Entry!");
	                	}else {
	                		none.setText("You have logged " + rList.size() + " Effort and Defect Entries!");
	                	}
	        		}
	            });
	        }));
	        
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
	        timelines.add(timeline);
	        
		}else if(flag == 2) {
			ArrayList<ProjectList> projects = ProjectManagementConsole.getProjList();
			
			// Set up a Timeline to update the list every 10 seconds
	        Duration duration = Duration.seconds(2);
	        Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
	            // Update the ObservableList on the JavaFX Application Thread
	            Platform.runLater(() -> {

	                // Update the ObservableList on a different thread
	                rList.clear();
	                
	                for(int i = 0; i < projects.size(); i++) {
	        			if(projects.get(i).getCurrent() == true) {
	        				Entry n = new Entry(projects.get(i).getProjectName(), projects.get(i).getProjectDesc(), 666, 0, "Project");
	        				rList.add(n);
	        			}
	        		}
	            });
	        }));
	        
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
	        timelines.add(timeline);
		}
	
 
        return resultView;
    }
    
    
    //settings page
    public GridPane settings(Stage window, Scene set) {
    	GridPane grid = new GridPane();
    	
    	Button home = new Button("Back To Dash");
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
    public VBox data(Stage window, Scene set) {
    	VBox screen = new VBox();
    	HBox top = new HBox();
    	Button home = new Button("Back To Dash");
    	home.setOnAction(e -> window.setScene(set));
    	
    	Text welcome = new Text();
		welcome.setText("Historical Data");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		ObservableList<Entry> resultList = FXCollections.observableArrayList();
		
		ListView<Entry> resultView = resultPage(resultList, 1);
		
		VBox results = new VBox();
		results.getChildren().add(resultView);
	    
		top.getChildren().addAll(welcome, home);
		top.setSpacing(10);
		top.setAlignment(Pos.BASELINE_LEFT);
		top.setPadding(new Insets(5,5,5,5));
		screen.setPadding(new Insets(10));
    	screen.getChildren().addAll(top,results);
    	
    	return screen;
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
		
		effort.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	window.setScene(eff);
            	EffortConsole.selectScreen();
            }
		});
		
		defect.setText("Defect Console");
		defect.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	window.setScene(def);
            }
		});
		
		
		project.setText("Project Console");
		project.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	window.setScene(proj);
            	ProjectManagementConsole.selectScreen();
            }
		});
		
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
		logout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	none.setText("No entries yet, Get Logging!!");			
    			ArrayList<EffortData> effort = EffortConsole.getEffort();
    			ArrayList<DefectData> defect = DefectConsole.getDefect();
    			ArrayList<ProjectList> projectList = ProjectManagementConsole.getProjList();
    			Map<String, ArrayList<pokerEntry>> sessions = Poker.getSesh();
    			Map<String, ProjectList> pMap = ProjectManagementConsole.getMap();

    			
    			if(effort!=null) {
    				Login.currentUser.setEffort(effort);
    				EffortConsole.effort.clear();
    			}
    			
    			if(defect!=null) {
    				Login.currentUser.setDefect(defect);
    				DefectConsole.defectList.clear();
    			}
    			
    			if(projectList!=null) {
    				Login.currentUser.setProj(projectList);
    				ProjectManagementConsole.projects.clear();
    			}
    			
    			if(sessions!=null) {
    				Login.currentUser.setPoker(sessions);
    				Poker.sessionList.clear();
    			}
    			
    			if(pMap!=null) {
    				Login.currentUser.setProjMap(pMap);
    				ProjectManagementConsole.projMap.clear();
    			}
    			
    			window.setScene(out);
            }
		});
		
		
		home.setText("Dash");
		home.setOnAction(e -> window.setScene(hom));
		
		poker.setMaxSize(90, 90);
		home.setMaxSize(90, 90);
		userLogs.setMaxSize(90, 90);
		setting.setMaxSize(90, 90);
		logout.setMaxSize(90, 90);
    	
    	return vbox;
    }
}
