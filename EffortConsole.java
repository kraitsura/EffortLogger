package Main;

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

public class EffortConsole {
public static BorderPane start()  {

		
		//setting up BorderPane to add all the nodes into it
		BorderPane EffortConsole = new BorderPane();
		
		
		//adding title of of EffortLogger Console to the top middle of the GridPane
//		Text EffortLoggerConsole = new Text("\t\t\t\tEffort Logger Console\n");
//		EffortLoggerConsole.setFont(Font.font(null, FontWeight.BOLD, 20));
//		EffortConsole.setTop(EffortLoggerConsole);
		
		//asking the user to select the task they want to work on
		Text taskSelection = new Text("1. Please select the task that you will be working on.");
		taskSelection.setFont(Font.font(null, FontWeight.BOLD, 12));
		
		//adding the task button selection for user to select task
		ComboBox<String> taskBox = new ComboBox();
		taskBox.getItems().addAll(
				"task 1",
				"task 2",
				"task 3",
				"task 4"
		);
		taskBox.setValue("task 1");
		
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
        
		//adding grid pane to organize the nodes before adding it to border pane
		GridPane leftBorder = new GridPane();
		leftBorder.setHgap(4);
		leftBorder.setVgap(10);
		leftBorder.setPadding(new Insets(5,5,5,5));
		leftBorder.add(taskSelection, 0, 0);
		leftBorder.add(taskBox, 0, 1);
		leftBorder.add(timerText, 0, 2);
		leftBorder.add(start, 0, 3);
		leftBorder.add(stop, 0, 4);
		leftBorder.add(effortText, 0, 5);
		leftBorder.add(description, 0, 6);
		leftBorder.add(save, 0, 7);
		//adding everything to border pane
		EffortConsole.setLeft(leftBorder);
		
		
		return EffortConsole;
	
}

}
