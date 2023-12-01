package efV2;

import java.time.LocalDate;
//Orion Choy
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectList {

    private String project;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdDate;
    private ArrayList<String> selectedUsers;
    private String description;
    private boolean current;
    
    public ProjectList(String project, LocalDate startDate, LocalDate endDate
            , LocalDate createdDate, ArrayList<String> users, String description, boolean current){
        this.project = project;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.selectedUsers = users;
        this.current = current;
    }    
    
    public void updateProj(String project, LocalDate startDate, LocalDate endDate
            , LocalDate createdDate, ArrayList<String> users, String description, boolean current){
        this.project = project;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.selectedUsers = users;
        this.current = current;
    }    
    
    public String getProjectName(){
        return project;
    }
    public String getProjectDesc(){
        return description;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
    public LocalDate getCreatedDate(){
        return createdDate;
    }
    public ArrayList<String> getSelectedUsers() {
    	return selectedUsers;
    }
    public boolean getCurrent(){
        return current;
    }
    public void setCurrent(boolean cur) {
    	this.current = cur;
    }
    
}