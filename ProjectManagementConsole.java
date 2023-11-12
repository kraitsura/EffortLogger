package efV2;

//Orion Choy
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ProjectManagementConsole {
	
	private static List<ProjectList> projects = new ArrayList<>();
	
	public static GridPane addGridPane() {
		GridPane gridProjectMan = new GridPane();
	    gridProjectMan.setHgap(10);
	    gridProjectMan.setVgap(10);
	    gridProjectMan.setPadding(new Insets(0, 10, 0, 10));

	    // Initialize Buttons
	    	Button createProjectText = new Button("Start a New Project");
	    	Button createTask = new Button("OK");
	    	Button assignTeam = new Button("Assign");
	    	Button printButton = new Button("Print Ongoing Projects");
	    	
	    // Initialize TextFields
	    	TextArea projectNameField = new TextArea();
	    		projectNameField.setPrefWidth(200);
	    		projectNameField.setMaxWidth(200);
	    		projectNameField.setPrefHeight(20);
	    		projectNameField.setMaxHeight(20);
	    	TextField userNameField = new TextField();
	    		userNameField.setPrefWidth(200);
	    		userNameField.setMaxWidth(200);
	    		userNameField.setPrefHeight(20);
	    		userNameField.setMaxHeight(20);
	    	
	    // Initialize other Text headers
	    		Text projectMan = new Text("Project Management");
	    		Text projectManDesc = new Text("Add or Manage Ongoing Projects");
	    			projectMan.setFont(Font.font(null, FontWeight.BOLD, 20));
	    		Text projectNameText = new Text("Enter Project Name:");
	    	    Text taskUsers = new Text("Assign Team to Project: ");
	    
	    // Will edit to pull teams from user accounts
	        ComboBox<String> teamComboBox = new ComboBox<>();
	   	    teamComboBox.getItems().addAll(
	   	    		"Team1",
	   	    		"Team2",
	   	    		"Team3",
    	    		"Team4"
	   	    ); 
	   
	    // Start Building Page
	    gridProjectMan.add(projectMan, 2, 0);				// Page Header
	    gridProjectMan.add(projectManDesc, 2, 1);
	    gridProjectMan.add(createProjectText, 2, 2);		// Create Project Button
	    gridProjectMan.add(projectNameText, 2, 4);		// Ask for project name
	    	projectNameText.setVisible(false);
    	gridProjectMan.add(projectNameField, 2, 5);			// Text field for name
    		projectNameField.setVisible(false);
	    gridProjectMan.add(createTask, 2, 6);				// Button to create task
	    	createTask.setVisible(false);
	    gridProjectMan.add(taskUsers, 2, 8);				// Text to add users
	    	taskUsers.setVisible(false);
	    gridProjectMan.add(teamComboBox, 2, 9);
	    	teamComboBox.setVisible(false);
	    gridProjectMan.add(assignTeam, 2, 10);
	    	assignTeam.setVisible(false);
	    gridProjectMan.add(printButton, 8, 2);
	       
	    // Click create project button to open projectName text field
	    createProjectText.setOnAction(event -> {
	    	projectNameText.setVisible(true);
		    projectNameField.setVisible(true);
		    createTask.setVisible(true);
	    });
	    
	    // Click OK button and add team dropdown opens up. Save project name to list
	    createTask.setOnAction(event -> {
	    	taskUsers.setVisible(true);
	    	assignTeam.setVisible(true);
	    	teamComboBox.setVisible(true);
	    	
	    	String projectName = projectNameField.getText();
	    	if (!projectName.isEmpty()) {
	    		ProjectList project = new ProjectList(projectName);
	    		projects.add(project);	
	    	}
	    });
	    
	    // Hides all of the opened elements when you click assign button
	    assignTeam.setOnAction(event -> {
	    	projectNameText.setVisible(false);
		    projectNameField.setVisible(false);
		    createTask.setVisible(false);
		    taskUsers.setVisible(false);
	    	assignTeam.setVisible(false);
	    	teamComboBox.setVisible(false);
	    	projectNameField.clear();
	    });
	    
	    //
	    printButton.setOnAction(event -> {
            for (ProjectList project : projects) {
                System.out.println("Project: " + project.getProjectName());
                System.out.println("Associated Team: Team");		// TODO Add team selection
            }
        });
	
	    return gridProjectMan;
	}
}