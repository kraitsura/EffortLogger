package efV2;

import java.time.LocalDate;
//Orion Choy
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ProjectManagementConsole {
	
	// Testing date printing

	private String name;
	private LocalDate start;
	private LocalDate end;
	private String status;
	private LocalDate initDate;
	//
	
	private static List<ProjectList> projects = new ArrayList<>();
	
	public static GridPane addGridPane() {
		GridPane gridProjectMan = new GridPane();
	    gridProjectMan.setHgap(10);
	    gridProjectMan.setVgap(10);
	    gridProjectMan.setPadding(new Insets(0, 10, 0, 10));

	    // Initialize Buttons
	    	Button createTask = new Button("OK");
	    	
	    // DatePickers
	    	DatePicker startDate = new DatePicker();
	    	DatePicker endDate = new DatePicker();
	    // DatePicker Text
	    	Text startDateText = new Text("Start Date:");
	    	Text endDateText = new Text("End Date:");
	    // Current date getter
	    	LocalDate currentDate = LocalDate.now();
	    	 
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
	    	    Text taskUsers = new Text("Assign Users to Project: ");
	    	    Text manageProjects = new Text("Manage Ongoing Projects");

	    
	    // Potentially add managing to select project to edit
	   	    ComboBox<String> manageProjectCombo = new ComboBox<>();	
	   	    
	   	    
	   	// checkmenuitem for project user selection
	   	    Menu userMenu = new Menu("Select Users");
	   	    List<Users> usersList = Login.getUsersList();		// Get usersList from Login.java
	        List<Users> selectedUsers = new ArrayList<>();
	   	    // For each user in the userList, add them to the menu
	   	    for(Users user : usersList) {
	   	    	CheckMenuItem item = new CheckMenuItem(user.getName().toString());
	   	    	userMenu.getItems().add(item);
	   	    }
	   	    // Couple of test users
		   	    CheckMenuItem menuitem1 = new CheckMenuItem("User1"); 
		        CheckMenuItem menuitem2 = new CheckMenuItem("User2"); 
		        CheckMenuItem menuitem3 = new CheckMenuItem("User3"); 
		        // add menu items to menu 
		        userMenu.getItems().add(menuitem1); 
		        userMenu.getItems().add(menuitem2); 
		        userMenu.getItems().add(menuitem3);
		        
	   	// create a menubar 
	        MenuBar menu_bar = new MenuBar(); 
	    // add userMenu to menubar 
	        menu_bar.getMenus().add(userMenu);
	        
	        String status = "Ongoing";				// TODO Make boolean Finished/Ongoing
	   	   
	   	    
	   	// ORION NEW PAGE BUILDING //
	   	gridProjectMan.add(projectMan, 0, 0);				// Page Header
	   	gridProjectMan.add(projectManDesc, 0, 1);			// Page Description
	   	gridProjectMan.add(projectNameText, 0, 2);			// Ask for project name
	   	gridProjectMan.add(projectNameField, 0, 3);			// Text field for name
	   	
	   	gridProjectMan.add(startDateText, 4, 2);			// Date Pickers 
	   	gridProjectMan.add(startDate, 4, 3);				//
	   	gridProjectMan.add(endDateText, 4, 4);				//
	   	gridProjectMan.add(endDate, 4, 5);					//
	   	
	   	gridProjectMan.add(taskUsers, 0, 4);				// Text to add users
	   	gridProjectMan.add(menu_bar, 0, 5);					// Add menu to pick users  	
	   	
	   	gridProjectMan.add(createTask, 0, 6);				// Button to create task
	   	// Click OK button and add team dropdown opens up. Save project name to list
		    createTask.setOnAction(event -> {
		    	// We check that all fields are completed
		    	if(projectNameField.getText().isEmpty() || startDate.getValue() == null ||
		    			endDate.getValue() == null) {
		    		Alert alert = new Alert(AlertType.ERROR);
		    				alert.setTitle("Error Message");
		    				alert.setHeaderText(null);
		    				alert.setContentText("Please fill all blank fields.");
		    				alert.showAndWait();
		    	}
		    	else{
		    		// Add data to new project
		    		ProjectList project = new ProjectList(projectNameField.getText(), startDate.getValue(), 
		    			endDate.getValue(), currentDate, status, selectedUsers);
		    		projects.add(project);
		    	}
		    	// These two for loops are tests
			    for (ProjectList project : projects) {
	                System.out.println("Project: " + project.getProjectName());
	                System.out.println("Project Start Date: " + project.getStartDate());
	                System.out.println("Project End Date: " + project.getEndDate());
	                System.out.println("Project Created Date: " + project.getCreatedDate());
	                // Need to fix users
	                
	                for(Users user : usersList) {
	           	    	CheckMenuItem item = new CheckMenuItem(user.getName().toString());
	           	    	userMenu.getItems().add(item);
	           	    	System.out.println("USERS TEST: " + user.getName().toString());
	           	    }
	            }
			    for (MenuItem item : userMenu.getItems()) {
		             if (item instanceof CheckMenuItem) {
		                 CheckMenuItem checkItem = (CheckMenuItem) item;
		                 checkItem.setSelected(false);
		             }
		         }
		    	projectNameField.clear();
		    });
	   	
	   	//-------------------------//
	    return gridProjectMan;
	}
	
	@Override
	public String toString() {
		return "ProjectName: " + name + ", Start Date: " + start + ", End Date: " + end + ", Created Date: " + initDate;
	}
}
