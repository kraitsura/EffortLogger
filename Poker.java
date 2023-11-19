package Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//Aarya Reddy 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;



public class Poker {
	
	static int user;
	public static String project = "";
	public static ArrayList<pokerEntry> shortList;
	public static ArrayList<Entry> results = new ArrayList<>();

	public static ObservableList<pokerEntry> handList;	
	public static ObservableList<Entry> resultList;
	public static Map<String, ArrayList<pokerEntry>> sessionList = new HashMap<>();
	
	
	public static Scene start(Stage window, Scene set)  {
		
		selectScreen();
		if(LoginMain.currentUser!=null) {
			user = LoginMain.currentUser.getCode();
		}
		BorderPane poker = new BorderPane();
		

		resultList = FXCollections.observableArrayList(results);
		handList = FXCollections.observableArrayList();
		
		
		
		poker.setRight(searchPane());
		poker.setLeft(pokerMenu(window, set));
		poker.setPadding(new Insets(10));
		Scene scene = new Scene(poker, 600, 400); 

       
		return scene;
	}
	
	private static void selectScreen() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Planning Poker Session");
		
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		    }
		});
		
		ArrayList<pokerEntry> list = new ArrayList<>();
		VBox layout = new VBox();
		
		HBox bottom = new HBox();
		Button newProj = new Button("New Session");
		Button oldProj = new Button("Old Session");
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
                    	if(sessionList.containsKey(name.getText())) {
                    		error.setText("Session Aleady Exists. Please Enter New Name.");
                    	}
                    	else if(!name.getText().equals("")) {
                    		sessionList.put(name.getText(), list);
                    		project = name.getText();
                    		handList.clear();
                            handList.addAll(list);
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
            	
            
            	for (Map.Entry<String, ArrayList<pokerEntry>> entry : sessionList.entrySet()) {
                       Button button = new Button(entry.getKey());
                       button.setMaxSize(10, 10);
                       out.getChildren().add(button);
                       button.setOnAction(new EventHandler<ActionEvent>() {
                    	   public void handle(ActionEvent event) {
	                       		shortList = entry.getValue();
	                       		project = entry.getKey();
	                       		handList.clear();
	                       		handList.addAll(shortList);
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
		
		if(sessionList.isEmpty()) {
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
		
		layout.setPadding(new Insets(10));
		layout.setSpacing(10);
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(welcome,bottom,alert);
		
		Scene scene = new Scene(layout, 400, 125);
		window.setScene(scene);
		window.show();
	}
	
	public static VBox pokerMenu(Stage window, Scene set) {
		VBox menu = new VBox(10);

		menu.getChildren().addAll(mainGrid(window,set), pokerHand());
		return menu;
	}
	
	public static GridPane mainGrid(Stage window, Scene set) {
		GridPane main = new GridPane();
		
		Button end = new Button("End Session");
		end.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	shortList = new ArrayList<>(handList);
            	sessionList.put(project, shortList);
            	handList.clear();
            	window.setScene(set);
            }
		});
		
		
		Button estimate = new Button("Estimate");
		
		Text welcome = new Text();
		welcome.setText("Planning Poker");
		welcome.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		TextField userStory = new TextField();
		userStory.setPromptText("Enter Usesr Story");
		
		main.add(welcome, 0, 0); main.add(end, 1, 0);
		main.add(userStory, 0, 1);
		main.add(estimate, 0, 2);
		
		main.setPadding(new Insets(5));
		main.setVgap(10); main.setHgap(20);
		return main;
	}
	
	//handle searched item being clicked to open more information 
	public static void searchItemClick(Entry item) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Entry");

		
		VBox layout = new VBox();
		Button add = new Button("Add");
		add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	pokerEntry newItem = new pokerEntry(item, "");
            	handList.add(newItem);
            }
        });
		
		Text name = new Text("Task Name: ");
		Text description = new Text("Description:\n");
		Text points = new Text("Points: ");
		Text notes = new Text("Notes:\n");
		
		
		if(!item.getName().equals("")) {
			name.setText("Task Name: " + item.getName());
		}
		
		if(!item.getDes().equals("")) {
			description.setText("Description:\n" + item.getDes());
		}
		
		if(item.getPoints()!=666||item.getPoints()!=0){
			points.setText("Points: " + item.getPoints());
		}else {
			points.setText("No Points Set");
		}
		
		layout.getChildren().addAll(name, description, points, notes, add);
		layout.setPadding(new Insets(10));
		layout.setSpacing(10);
		
		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);
		window.showAndWait();
	}
	
	//handle planning poker itme being clicked to open more information
	public static void pokerItemClick(pokerEntry item) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Poker Entry");

		
		VBox layout = new VBox();
		Text name = new Text("Task Name: ");
		Text description = new Text("Description:\n");
		Text points = new Text("Points: ");
		Text notes = new Text("Notes:\n");
		Text pokerNotes = new Text();

		
		
		if(!item.getEntry().getName().equals("")) {
			name.setText("Task Name: " + item.getEntry().getName());
		}
		
		if(!item.getEntry().getName().equals("")) {
			description.setText("Description:\n" + item.getEntry().getDes());
		}
		
		if(item.getEntry().getPoints()!=666||item.getEntry().getPoints()!=0){
			points.setText("Points: " + item.getEntry().getPoints());
		}else {
			points.setText("No Points Set");
		}
		if(!item.getNotes().equals("")) {
			pokerNotes.setText("Planning Poker Notes:\n" + item.getNotes());
		}
		
		layout.getChildren().addAll(name, description, points, notes, pokerNotes);
		layout.setPadding(new Insets(10));
		layout.setSpacing(10);
		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
	public static VBox pokerHand() {
		VBox hand = new VBox(10);
		hand.setPadding(new Insets(10));
		hand.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		Button remove = new Button("Remove");
		Button notes = new Button("Add Notes");
		Button points = new Button("Change Points");
		Text banner = new Text("Current Hand");
		banner.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
		
		


        // Create UI components
        ListView<pokerEntry> handView = new ListView<>(handList);
        
        //do not touch
        handView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<pokerEntry> call(ListView<pokerEntry> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(pokerEntry item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                        	if(item.getEntry().getPoints() == 666) {
                        		setText(item.getEntry().getName() + "\n" + item.getEntry().getDes());
                        		setOnMouseClicked(event -> {
                                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                    	pokerItemClick(item);
                                    }
                                });
                        	}else {
                        		setText(item.getEntry().getName() + "    Points: " + item.getEntry().getPoints() + "\n" + item.getEntry().getDes());
                        		setOnMouseClicked(event -> {
                                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                    	pokerItemClick(item);
                                    }
                                });
                        	}
                            
                        }
                    }
                };
            }
        });
        
       
        
        remove.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	pokerEntry selectedItem = handView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    handList.remove(selectedItem);
                }
            }
        });
        
        notes.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	pokerEntry selectedItem = handView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                	Stage window = new Stage();
            		window.initModality(Modality.APPLICATION_MODAL);
            		window.setTitle("Poker Entry");

            		
            		VBox layout = new VBox();
            		
            		Text panel = new Text();
            		panel.setText("Add Notes:");
            		
            		TextField addNote = new TextField();
            		
            		
            		Button add = new Button("Add");
            		
            		add.setOnAction(new EventHandler<ActionEvent>() {
            			public void handle(ActionEvent event) {
            				if(!addNote.getText().equals("")) {
            					selectedItem.setNotes(addNote.getText());
            					window.close();
            				}
            			}
            		});
            		
            		layout.setPadding(new Insets(10));
            		layout.getChildren().addAll(panel,addNote, add);
            		layout.setAlignment(Pos.BASELINE_LEFT);
       
            		
            		Scene scene = new Scene(layout, 300, 150);
            		window.setScene(scene);
            		window.showAndWait();
                }
            }
        });
        
        points.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	pokerEntry selectedItem = handView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                	Stage window = new Stage();
            		window.initModality(Modality.APPLICATION_MODAL);
            		window.setTitle("Poker Entry");

            		
            		VBox layout = new VBox();
            		
            		Text panel = new Text();
            		panel.setText("Change Points:");
            		
            		TextField addNote = new TextField();
            		
            		
            		Button add = new Button("Change");
            		
            		add.setOnAction(new EventHandler<ActionEvent>() {
            			public void handle(ActionEvent event) {
            				if(!addNote.getText().equals("")) {
            					selectedItem.getEntry().setPoints(Integer.parseInt(addNote.getText()));
            					window.close();
            				}
            			}
            		});
            		
            		layout.setPadding(new Insets(10));
            		layout.getChildren().addAll(panel,addNote, add);
            		layout.setAlignment(Pos.BASELINE_LEFT);
       
            		
            		Scene scene = new Scene(layout, 300, 150);
            		window.setScene(scene);
            		window.showAndWait();
                }
            }
        });
        
        
        HBox control = new HBox();
        control.setSpacing(10);
        control.setPadding(new Insets(1));
        control.setAlignment(Pos.BASELINE_CENTER);
        control.getChildren().addAll(remove,notes,points);
        
        hand.getChildren().addAll(banner, handView, control);
		
		
		return hand;
	}
	
	
	public static VBox searchPane() {
		VBox searchBox = new VBox(10);
		VBox resultPage = new VBox();
		TextField searchBar = new TextField();
		searchBar.setPromptText("Enter Keyword");
		Label warning = new Label("");
		Button add = new Button("Add");
		
		searchBox.setPadding(new Insets(10));
		searchBox.setStyle("-fx-border-color: black; -fx-border-width: 1;");


		// Add thin borders to the VBox
		resultPage.setPadding(new Insets(10));
		resultPage.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		
		
		Button search = new Button("Search");
		search.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	ArrayList<EffortData> effortArray = EffortConsole.getEffort();
            	resultList.clear();
            	boolean match = false;
            	if (searchBar.getText().equals("")) {
            		warning.setText("Showing All Data");
            		//print results from all effort and defect arraylists using userCode
            		//@Kendra Newman add search results to resultList
            		
            		//loop through entire effort array checking 
            		for (int i = 0; i < effortArray.size(); i++) {
            			if (user == effortArray.get(i).getUserCode()) {
            				Entry entry = new Entry(effortArray.get(i).getTask(), effortArray.get(i).getDescription(), 666);
                    		resultList.add(entry);
            			}
            		}
            	}
            	else {
            		String key = searchBar.getText();
            		
            		///print results from all effort and defect arraylists using key and userCode
            		//@Kendra Newman add search results to resultList
            		
            		for (int i = 0; i < effortArray.size(); i++) {
            			if (user == effortArray.get(i).getUserCode()) {
            				ArrayList<String> keywordList = effortArray.get(i).getKeywords();
            				for (int j = 0; j < keywordList.size(); j++) {
            					if (keywordList.get(j).toLowerCase().contains(key.toLowerCase())) {
            						match = true;
            					}
            				}
            				if (match == true) {
            					Entry entry = new Entry(effortArray.get(i).getTask(), effortArray.get(i).getDescription(), 666);
                        		resultList.add(entry);
            				}
            			}
            		}
            	}
            }
        });
		

        ListView<Entry> resultView = new ListView<>(resultList);
        
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
                                    	searchItemClick(item);
                                    }
                                });
                        	}else {
                        		setText(item.getName() + "    Points: " + item.getPoints() + "\n" + item.getDes());
                        		 // Handle mouse click events
                                setOnMouseClicked(event -> {
                                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                                    	searchItemClick(item);
                                    }
                                });
                        	}
                            
                        }
                    }
                };
            }
        });
        
        
        resultPage.getChildren().add(resultView);
		
		
		HBox searchH = new HBox();
		searchH.getChildren().addAll(search, warning);
		searchH.setSpacing(10);
		
		searchBox.setPadding(new Insets(10));
		searchBox.getChildren().addAll(searchBar, searchH, resultPage);
		
		
		return searchBox;
	}
}
