package efV2;

import java.time.LocalDate;
//Orion Choy
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ProjectManagementConsole {
	
	// Testing date printing

	private String name;
	private LocalDate start;
	private LocalDate end;
	private LocalDate initDate;
	//
	
	public static ArrayList<ProjectList> projects = new ArrayList<>();
	public static ObservableList<Entry> projView;
	public static Map<String, ProjectList> projMap = new HashMap<>();
	private static ProjectList proj;
	
	private static String curName;
	private static Text projectManDesc = new Text("");
	private static TextArea projectNameField = new TextArea();
	private static Menu userMenu = new Menu("Select Users");
	private static DatePicker startDate = new DatePicker();
	private static DatePicker endDate = new DatePicker();
	private static LocalDate currentDate = LocalDate.now();
	private static LocalDate created;
	private static Button status = new Button("Status");
	
	private static boolean check = false;
	private static boolean exists = false;
	
	public static void selectScreen() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Choose or Create Project");
		
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
		
		VBox layout = new VBox();
		
		HBox bottom = new HBox();
		Button newProj = new Button("New Project");
		Button oldProj = new Button("Old Project");
		Text alert = new Text("");
		
		newProj.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	Stage secondary = new Stage();
            	
            	secondary.initModality(Modality.APPLICATION_MODAL);
            	secondary.setTitle("Create Project Session");
            	secondary.setOnCloseRequest(new EventHandler<WindowEvent>() {
        		    @Override
        		    public void handle(WindowEvent event) {
        		        event.consume();
        		    }
        		});
        		window.close();
            	VBox out = new VBox();
            	
            	Text error = new Text("");
            	TextField name = new TextField();
            	name.setPromptText("Enter Project Name");
            	name.setMaxWidth(150);
            	Button addName = new Button("Start");
            	addName.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                    	if(projMap.containsKey(name.getText())) {
                    		error.setText("Project Aleady Exists. Please Enter New Name.");
                    	}
                    	else if(!name.getText().equals("")) {
                    		curName = name.getText();                    		
                    		projectManDesc.setText("Project: "+ curName);
             		
                    		secondary.close();
                    	}
                    	else{
                    		error.setText("Field is Empty. Please Enter New Name.");
                    	}
                    }
            	
            	});
            	
            	out.setSpacing(5);
        		out.setAlignment(Pos.BASELINE_CENTER);
        		out.getChildren().addAll(name, addName, error);
        		out.setMaxSize(300, 100);
            	
        		Scene sec = new Scene(out, 300, 100);
        		secondary.setScene(sec);
        		secondary.showAndWait();
            }
		});
		
		oldProj.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	Stage secondary = new Stage();
            	secondary.initModality(Modality.APPLICATION_MODAL);
            	secondary.setTitle("Select Project Session");
            	secondary.setOnCloseRequest(new EventHandler<WindowEvent>() {
        		    @Override
        		    public void handle(WindowEvent event) {
        		        event.consume();
        		    }
        		});
            	VBox out = new VBox();
            	
            
            	for (Map.Entry<String, ProjectList> entry : projMap.entrySet()) {
                       Button button = new Button(entry.getKey());
                       button.setMaxSize(100, 100);
                       out.getChildren().add(button);
                       button.setOnAction(new EventHandler<ActionEvent>() {
                    	   public void handle(ActionEvent event) {
	                       		proj = entry.getValue();
	                       		curName = entry.getKey();
	                       		
	                       		exists = true;
	                       		startDate.setValue(proj.getStartDate());
	                       		endDate.setValue(proj.getEndDate());
	                       		created = proj.getCreatedDate();
	                       		projectNameField.setPromptText(proj.getProjectDesc());	
	                       		
	                       		if(proj.getCurrent()) {
	                       			status.setText("Ongoing");
	                       		}
	                       		else {
	                       			status.setText("Not Ongoing");
	                       		}
	                       		
	                       		
	                       		projectManDesc.setText("Project: "+ curName);
	                       		window.close();
	                       		secondary.close();
                       		}
                       });
                 }
            	
            	out.setPadding(new Insets(10));
            	out.setSpacing(5);
        		out.setAlignment(Pos.BASELINE_CENTER);       	
            	
        		Scene sec = new Scene(out, 300, 150);
        		secondary.setScene(sec);
        		secondary.showAndWait();
            }
		});
		
		if(projMap.isEmpty()) {
    		alert.setText("No Sessions Exist, Please Create One");
    		bottom.getChildren().add(newProj);
    	}else {
    		alert.setText("");
    		bottom.getChildren().addAll(newProj, oldProj);
    	}
		
		bottom.setAlignment(Pos.BASELINE_CENTER);
		
		
		Text welcome = new Text();
		welcome.setText("Choose Planning Poker Session");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
		
		Text top = new Text();
		top.setText("Choose Project Session");
		top.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 15));
		
		layout.setPadding(new Insets(10));
		layout.setSpacing(10);
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(top,bottom,alert);				
		
		Scene selectScene = new Scene(layout, 400, 125);
		window.setScene(selectScene);
		window.show();
	}
	
	public static GridPane addGridPane() {
		
		GridPane gridProjectMan = new GridPane();
	    gridProjectMan.setHgap(10);
	    gridProjectMan.setVgap(10);
	    gridProjectMan.setPadding(new Insets(0, 10, 0, 10));

	    // Initialize Buttons
	    	Button createTask = new Button("OK");
	    	
	    // DatePickers

	    // DatePicker Text
	    	Text startDateText = new Text("Start Date:");
	    	Text endDateText = new Text("End Date:");
	    // Current date getter
	    	
	    	
	    	 
	    // Initialize TextFields
	    	
	    		projectNameField.setPrefWidth(200);
	    		projectNameField.setMaxWidth(200);
	    		projectNameField.setPrefHeight(20);
	    		projectNameField.setMaxHeight(20);
	    	
	    // Initialize other Text headers
	    		Text projectMan = new Text("Project Management");
	    		
	    		projectMan.setFont(Font.font(null, FontWeight.BOLD, 20));
	    		Text projectNameText = new Text("Enter Project Description:");
	    	    Text taskUsers = new Text("Assign Users to Project: ");

	   	    
	   	// checkmenuitem for project user selection
	   	    
	   	    		// Get usersList from Login.java
	    	    Button loadUsers = new Button("Load Users");
	   	    
		        
	   	    loadUsers.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	ArrayList<Users> usersList = Login.getUsersList();
			   		for(int i = 0; i < usersList.size(); i++) {
			   		 	String na = usersList.get(i).getName();
			   	    	CheckMenuItem item = new CheckMenuItem(na);
			   	    	userMenu.getItems().add(item);
			   		}
			   		
	            	if(exists) {
	            		for (MenuItem menuItem : userMenu.getItems()) {
	                        if (menuItem instanceof MenuItem) {
	                            String menuItemText = menuItem.getText();
	                            ArrayList<String> selected = proj.getSelectedUsers();
	                            if (selected.contains(menuItemText)) {
	                            	CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
	                            	checkMenuItem.setSelected(true);
	                            }
	                        }
	                    }
	            	}
	            	
	            }
	        });
	   	    
	   	// create a menubar 
	        MenuBar menu_bar = new MenuBar(); 
	    // add userMenu to menubar 
	        menu_bar.getMenus().add(userMenu);  	   
	   	    
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
	   	
	   	gridProjectMan.add(status, 4, 1);
	   	
	   	gridProjectMan.add(loadUsers, 0, 6);
	   	gridProjectMan.add(createTask, 0, 7);
	   	
	   	// Button to create task
	   	// Click OK button and add team dropdown opens up. Save project name to list
	   		   	
	   	status.setOnAction(event -> {
	   		if(check == true) {
	   			check = false;
	   			status.setText("Not Ongoing");
	   		}
	   		else if(check == false) {
	   			check = true;
	   			status.setText("Ongoing");
	   		}
	   	});
	   	
		    createTask.setOnAction(event -> {
		    	// We check that all fields are completed
		    	if(projectNameField.getText().equals("") || startDate.getValue() == null ||
		    			endDate.getValue() == null) {
		    		Alert alert = new Alert(AlertType.ERROR);
		    				alert.setTitle("Error Message");
		    				alert.setHeaderText(null);
		    				alert.setContentText("Please fill all blank fields.");
		    				alert.showAndWait();
		    				return;
		    	}
		    	else{
		    		// Add data to new project
		    					    
			    		
			    		if(exists) {
			    			ArrayList<String> selectedUsers = proj.getSelectedUsers();
			    			selectedUsers.clear();
			    			for (MenuItem menuItem : userMenu.getItems()) {
				                if (menuItem instanceof CheckMenuItem) {
				                    CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;		                    
				                    if (checkMenuItem.isSelected()) {
				                        	selectedUsers.add(checkMenuItem.getText());				                       
				                    }
				                }
				            }	
			    			exists = false;
			    			proj.updateProj(curName, startDate.getValue(), 
					    			endDate.getValue(), created, selectedUsers,projectNameField.getText(), check);
			    		} else {
			    			ArrayList<String> selectedUsers = new ArrayList<>();
			    			ArrayList<Users> usersList = Login.getUsersList();
					   	    // For each user selected, add them to the selected List
					    		
					    		for (MenuItem menuItem : userMenu.getItems()) {
					                if (menuItem instanceof CheckMenuItem) {
					                    CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;		                    
					                    if (checkMenuItem.isSelected()) {
					                        	selectedUsers.add(checkMenuItem.getText());
					                    }
					                }
					            }	
			    			ProjectList project = new ProjectList(curName, startDate.getValue(), 
					    	endDate.getValue(), currentDate, selectedUsers,projectNameField.getText(), check);
					    	projects.add(project);
					    	projMap.put(curName, project);
			    		}
		    		}
	                	               
	           
	               for (MenuItem item : userMenu.getItems()) {
		             if (item instanceof CheckMenuItem) {
		                 CheckMenuItem checkItem = (CheckMenuItem) item;
		                 checkItem.setSelected(false);
		             }
		         }
	            startDate.setValue(null);
	            endDate.setValue(null);
		    	projectNameField.clear();
		    	projectNameField.setPromptText("Enter Description");
		    	
		    });
	   	
	   	//-------------------------//
	    return gridProjectMan;
	}
	
	//Aarya 
		public static ArrayList<ProjectList> getProjList(){
			return projects;
		}
	
	@Override
	public String toString() {
		return "ProjectName: " + name + ", Start Date: " + start + ", End Date: " + end + ", Created Date: " + initDate;
	}

	public static Map<String, ProjectList> getMap() {
		// TODO Auto-generated method stub
		Map<String, ProjectList> sessions = new HashMap<>(projMap);
		return sessions;
	}
	
	
}