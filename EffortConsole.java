package efV2;

import javafx.util.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import efV2.Login;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class EffortConsole {
	private static Instant startTimer;
	private static Instant endTimer;
	public static ArrayList<EffortData> effort = new ArrayList<>();
	private static ArrayList<String> keys = new ArrayList<>();
	private static int secondsElapsed = 0;
    private static Timeline timeline;
	private static int time;
	public static ObservableList<String> handList;
	private static boolean check = false;
	private static int user;
	private static String current;

	public static BorderPane start(Stage window, Scene set)  {

		//setting up BorderPane to add all the nodes into it
		BorderPane EffortConsole = new BorderPane();
		
		Text taskSelection = new Text("1. Please enter the task that you will be working on.");
		taskSelection.setFont(Font.font(null, FontWeight.BOLD, 12));
		
		//adding the task button selection for user to select task
		TextArea taskBox = new TextArea();
		taskBox.setPromptText("Please enter 'Task (number)'");
		taskBox.setPrefWidth(225);
        taskBox.setPrefHeight(5);
        Button save2 = new Button();
        save2.setText("Save");
		//adding start and stop instructions for effort
		Text timerText = new Text("4. Please select the 'Start' or 'Stop' button to begin and end timer.");
		timerText.setFont(Font.font(null, FontWeight.BOLD, 12));
		
		//adding timer and start and stop button
		Text timer = new Text("00:00");
		timer.setFont(Font.font(null, FontWeight.BOLD, 12));
		Button start = new Button();
		Button stop = new Button();
		
		start.setText("Start");
		stop.setText("Stop");
		
		//adding description of the work done text box
		Text effortText = new Text("3. Fill out what you have done before pressing 'Stop'.");
		effortText.setFont(Font.font(null, FontWeight.BOLD, 12));
		
		TextArea description = new TextArea();
		description.setPromptText("Description");
		description.setPrefWidth(225);
        description.setPrefHeight(5);
        
        //adding save button for text description
        Button save = new Button();
        save.setText("Save");
        
        //adding keyword text box
        Text keyword = new Text("2. Add key word.");
        keyword.setFont(Font.font(null, FontWeight.BOLD, 12));
        
        TextArea keywordBox = new TextArea();
        keywordBox.setPromptText("Key word");
        keywordBox.setPrefWidth(255);
        keywordBox.setPrefHeight(5);
        Button save1 = new Button();
        save1.setText("Add");
        
		//adding grid pane to organize the nodes before adding it to border pane
		GridPane leftBorder = new GridPane();
		HBox hB = new HBox();
		Text clock = new Text("00:00:00");
		hB.getChildren().addAll(start,clock,stop);
		hB.setPadding(new Insets(5,5,5,5));
		hB.setSpacing(10);
		hB.setAlignment(Pos.CENTER);
		leftBorder.setHgap(4);
		leftBorder.setVgap(10);
		leftBorder.setPadding(new Insets(5,5,5,5));
		
		clock.setFont(Font.font(null,FontWeight.LIGHT, 15));
		
		leftBorder.add(taskSelection, 0, 0);
		leftBorder.add(taskBox, 0, 1);
		
		 // Create UI components
		handList = FXCollections.observableArrayList();
		
        ListView<String> handView = new ListView<>(handList);
        
        //do not touch
        handView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                        	setText(item);
                        }
                    }
                };
            }
        });
        
        handView.setPrefWidth(100);
        handView.setPrefHeight(5);
        
		leftBorder.add(keyword, 0, 2);
		leftBorder.add(keywordBox, 0, 3);
		leftBorder.add(save1, 1, 3);
		leftBorder.add(handView, 2, 3);
		
		
		leftBorder.add(effortText, 0, 4);
		leftBorder.add(description, 0, 5);
		leftBorder.add(timerText, 0, 6);
		leftBorder.add(hB, 0, 7);
		leftBorder.add(save, 0, 8);
		Label warning = new Label();	//set as blank until needed
        warning.setText("");
        leftBorder.add(warning, 0, 9);
		
		//adding everything to border pane
		EffortConsole.setLeft(leftBorder);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	if(check == true) {
            		warning.setText("Timer Already Started!");
            		return;
            	}
            	startTimer(clock);
            	check = true;
            }
		});
		
		stop.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	if(check == false) {
            		warning.setText("Timer Not Started!");
            		return;
            	}
            	stopTimer();
            	clock.setText("00:00:00");
//            	endTimer = Instant.now();
//            	Duration t = Duration.between(startTimer, endTimer);
//            	time = t.toSecondsPart();
            }
		});
		
		save1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	String keyword = keywordBox.getText();
           		keys.add(keyword);
               	handList.add(keyword);
            }
		});
		
		
		save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	
            	if(description.getText().equals("") || taskBox.getText().equals("")) {
            		warning.setText("One or more fields are empty.");
            	} else {
            		
            		if(keys.size() == 0 || keys == null) {
            			warning.setText("No Keywords Added!");
            			return;
            		}
            		
            		if(check == false) {
            			warning.setText("Cannot Save, Timer Not Started!");
            			return;
            		}
            		check = false;
            		
            		if(Login.currentUser != null) {
            			user = Login.currentUser.getCode();
            		}else {
            			warning.setText("No User Error");
            			return; 
            		}
            		
            		EffortData data = new EffortData();
                	
            		data.setName(current);
                	
            		data.setUser(user);
            		
            		ArrayList<String> newset = new ArrayList<>(keys);
                	data.setKeyword(newset);
                	
                	
                	String descriptionData = description.getText();
                	data.setDescription(descriptionData);
                	
                	String taskData = taskBox.getText();
                	data.setTask(taskData);
                	
                	data.setTime(time);
                	
                	taskBox.clear();
                	description.clear();
                	keywordBox.clear();
                	warning.setText("Effort Entry Saved!");
                	handList.clear();
                	secondsElapsed = 0;
                	
                	effort.add(data);
                	Dashboard.resultView = Dashboard.resultPage(Dashboard.resultList, 1);
                	keys.clear();
                	window.setScene(set);
                	
            	}
            }
		});
		return EffortConsole;

}
	//Aarya 
	private static void startTimer(Text clock) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsElapsed++; 
            int seconds = 0; 
            if(secondsElapsed < 10) {
            	clock.setText("00:00:0" + secondsElapsed);
            }
            else if(secondsElapsed < 60) {
            	clock.setText("00:00:"+ secondsElapsed);
            }
            else if(secondsElapsed > 60 && secondsElapsed < 3600) {
            	if(seconds < 60) {
            		if(seconds < 10) {
            			seconds++;
            			if(Math.round(secondsElapsed/60)<10) {
            				clock.setText("00:0" + Math.round(secondsElapsed/60)+":0" + seconds);
            			}else {
            				clock.setText("00:"+ Math.round(secondsElapsed/60)+":0" + seconds);
            			}
            			
            		}else {
            			clock.setText("00:" + Math.round(secondsElapsed/60)+ ":"+ seconds);
            		}
            	} 
            	else {
            		seconds = 0;
            	}
            }
//            clock.setText(Math.round(secondsElapsed/3600)+ ":" + Math.round(secondsElapsed/60) + ":" + secondsElapsed);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static void stopTimer() {
        if (timeline != null) {
        	time = secondsElapsed;
            timeline.stop();
        }
    }
    
    public static void selectScreen() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Add Effort Entry");
		
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
	
		VBox layout = new VBox();
		Button newEff = new Button("Add Entry");
		TextField text = new TextField();
		text.setPrefWidth(100);
		text.setPrefHeight(3);
		text.setPromptText("Enter Effort Name");
		
		newEff.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	current = text.getText();
            	window.close();
            }
		});
		
		Text welcome = new Text();
		welcome.setText("Record Effort Data");
		welcome.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.ITALIC, 15));
		
		layout.setPadding(new Insets(5,5,5,5));
		layout.setSpacing(10);
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(welcome,text,newEff);
		
		Scene scene = new Scene(layout, 400, 125);
		window.setScene(scene);
		window.show();
	}
    
	//Kendra added what is below :)
public static ArrayList<EffortData> getEffort() {
	ArrayList<EffortData> send = new ArrayList<>(effort);
	return send;
}
}