package efV2;

//Orion Choy
import java.util.ArrayList;
import java.util.List;

public class ProjectList {
	private String projectName;
	private List<String> associatedTeam;
	
	public ProjectList(String projectName) {
		this.projectName = projectName;
		this.associatedTeam = new ArrayList<>();
	}
	public String getProjectName() {
		return projectName;
	}
	public List<String> getAssociatedPeople() {
		return associatedTeam;
	}
	public void addPerson(String personName) {
		associatedTeam.add(personName);
	}
}