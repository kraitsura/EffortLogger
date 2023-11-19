package Main;

import java.time.LocalDate;
//Orion Choy
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectList {
	private String projectName;
	private List<Users> selectedUsers;
	
	//public ProjectList(String projectName) {
	//	this.projectName = projectName;
	//	this.associatedTeam = new ArrayList<>();
	//}

    private String project;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdDate;
    private String status;
    
    public ProjectList(String project, LocalDate startDate, LocalDate endDate
            , LocalDate createdDate, String status, List<Users> selectedUsers){
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.status = status;
        this.selectedUsers = selectedUsers;
    }    
    
    public String getProjectName(){
        return project;
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
    public String getStatus(){
        return status;
    }
    public List<Users> getSelectedUsers() {
    	return selectedUsers;
    }
    
}
