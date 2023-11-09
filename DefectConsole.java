package efV2;
//Prashil Adhara
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DefectConsole {
	
	String selectedProject;
    String selectedDefect;
    boolean status;
    String resolution;
    String[] stepInjected;
    String[] stepRemoved;
    String[] defectCat;

//    String[] sanitizeInputs() {
//        // implement tunnels from input event listeners
//        // and stop any inputs with blacklisted characters
//        // from being parsed, check sets of inputs as well.
//    }
//
//    boolean assertSanity() {
//        // check if sanitize inputs has been run for the current
//        // set of inputs.
//    }
//
//    String[] parseInputs(String a) {
//        // parse inputs after running sanitizeInputs() and assertSanity()
//    }


    public static GridPane start() {

        // labels
        Label l1 = new Label("1) Select the project: ");
        Label l2 = new Label("1.a) Clear this project's defect log: ");
        Label l3 = new Label("2) Select a defect or create one:  ");
        Label l4 = new Label("3) Define / Update current defect attributes: ");
        Label l5 = new Label("Defect Name: ");
        Label l6 = new Label("Status: ");
        Label li1 = new Label("Closed");
        Label l7 = new Label("4) Defect Symptoms / Cause / Resolution");
        Label l8 = new Label("5) Step when Injected: ");
        Label l9 = new Label("6) Step when removed: ");
        Label l10 = new Label("7) Defect Category: ");
        Label l11 = new Label("8) Fix: ");

        // comboboxes
        String projects[] = {"project1","project2" };
        ComboBox c1 = new ComboBox(FXCollections.observableArrayList(projects));
        String defects[] = {"one","two","three","four","five" };
        ComboBox c2 = new ComboBox(FXCollections.observableArrayList(defects));
        String fixes[] = {"one","two","three","four","five" };
        ComboBox c3 = new ComboBox(FXCollections.observableArrayList(fixes));

        // buttons
        Button b1 = new Button("Clear Defect Log");
        Button b2 = new Button("Create New Defect");
        Button b3 = new Button("Close");
        Button b4 = new Button("Reopen");
        Button b5 = new Button("Update Current Defect");
        Button b6 = new Button("Delete Current Defect");
        Button b7 = new Button("Proceed to Effort Log console");

        // textfields and areas
        TextField defectName = new TextField();
        TextArea resolution = new TextArea();
        resolution.setPrefHeight(30);
        resolution.setPrefWidth(60);

        // listviews
        String til[] = {"one","two","three","four","five" };
        ListView tfinject = new ListView(FXCollections.observableArrayList(til));
        String trl[] = {"one","two","three","four","five" };
        ListView tfremove = new ListView(FXCollections.observableArrayList(trl));
        String tdcl[] = {"one","two","three","four","five" };
        ListView tfdefectcategory = new ListView(FXCollections.observableArrayList(tdcl));

        // set the listview heights
        tfinject.setMaxHeight(100);
        tfremove.setMaxHeight(100);
        tfdefectcategory.setMaxHeight(100);

        // set the gridpane attrs
        GridPane grid = new GridPane();
        grid.setVgap(10); grid.setHgap(20);

        grid.add(l1,0,0); grid.add(l2,1,0);
        grid.add(c1,0,1); grid.add(b1,1,1);
        grid.add(l3,0,2);
        grid.add(c2,0,4); grid.add(b2,1,4);
        grid.add(l4,0,5);
        grid.add(l5,0,6); grid.add(l6, 1,6);
        grid.add(defectName,0,7); grid.add(li1, 1,7);
        grid.add(b3,0,8); grid.add(b4,1,8);
        grid.add(l7,0,9); grid.add(resolution,1,9);
        grid.add(l8,0,10); grid.add(l9,1,10); grid.add(l10,2,10);
        grid.add(tfinject,0,11); grid.add(tfremove,1,11); grid.add(tfdefectcategory,2,11);
        grid.add(l11,0,12); grid.add(c3,1,12);
        grid.add(b5,0,13); grid.add(b6,1,13); grid.add(b7,2,13);

        return grid;
    }
}
