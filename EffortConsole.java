package efV2;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import efV2.Login;

public class EffortConsole {
	private static Instant startTimer;
	private static Instant endTimer;
	public static ArrayList<EffortData> effort = new ArrayList<>();
	private static int user;

	public static BorderPane start()  {

		EffortData data = new EffortData();
		//setting up BorderPane to add all the nodes into it
		BorderPane EffortConsole = new BorderPane();
		
		
		//adding title of of EffortLogger Console to the top middle of the GridPane
//		Text EffortLoggerConsole = new Text("\t\t\t\tEffort Logger Console\n");
//		EffortLoggerConsole.setFont(Font.font(null, FontWeight.BOLD, 20));
//		EffortConsole.setTop(EffortLoggerConsole);
		
		//asking the user to select the task they want to work on
		if(Login.currentUser!=null) {
			user = Login.currentUser.getCode();
			data.setUser(user);
		}
		
		
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
		Text timerText = new Text("2. Please select the 'Start' or 'Stop' button to begin and end timer.");
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
        description.setPrefHeight(100);
        
        //adding save button for text description
        Button save = new Button();
        save.setText("Save");
        
        //adding keyword text box
        Text keyword = new Text("4. Add key word.");
        keyword.setFont(Font.font(null, FontWeight.BOLD, 12));
        
        TextArea keywordBox = new TextArea();
        keywordBox.setPromptText("Key word");
        keywordBox.setPrefWidth(100);
        keywordBox.setPrefHeight(5);
        Button save1 = new Button();
        save1.setText("Save");
        
		//adding grid pane to organize the nodes before adding it to border pane
		GridPane leftBorder = new GridPane();
		leftBorder.setHgap(4);
		leftBorder.setVgap(10);
		leftBorder.setPadding(new Insets(5,5,5,5));
		leftBorder.add(taskSelection, 0, 0);
		leftBorder.add(taskBox, 0, 1);
		leftBorder.add(save2, 0, 2);
		leftBorder.add(timerText, 0, 3);
		leftBorder.add(start, 0, 4);
		leftBorder.add(stop, 0, 5);
		leftBorder.add(effortText, 0, 6);
		leftBorder.add(description, 0, 7);
		leftBorder.add(save, 0, 8);
		leftBorder.add(keyword, 0, 9);
		leftBorder.add(keywordBox, 0, 10);
		leftBorder.add(save1, 1, 10);
		
		//adding everything to border pane
		EffortConsole.setLeft(leftBorder);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	startTimer = Instant.now();
            }
		});
		stop.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	endTimer = Instant.now();
            	Duration time = Duration.between(startTimer, endTimer);
            	data.setTime(time.toSecondsPart());
            	taskBox.clear();
            	description.clear();
            	keywordBox.clear();
            }
		});
		
		save2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	String taskData = taskBox.getText();
            	data.setTask(taskData);
            }
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	String descriptionData = description.getText();
            	data.setDescription(descriptionData);
            }
		});
		
		save1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	String keyword = keywordBox.getText();
            	data.addKeyword(keyword);
            }
		});
		return EffortConsole;
	
}

}