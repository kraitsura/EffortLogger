package efV2;

import java.util.ArrayList;
import java.util.Map;

//Kendra Newman
public class Users {
	private String name; 
	private String email;
	private String username;
	private String password;
	private ArrayList<EffortData> effort;
	private ArrayList<DefectData> defect;
	private ArrayList<ProjectList> proj;
	private Map<String, ArrayList<pokerEntry>> sessionList;
	private Map<String, ProjectList> projMap;
	private int userCode;
	
	public Users(String name, String email, String username, String password, int userCode) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.userCode = userCode;
		
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getCode() {
		return userCode;
	}
	public ArrayList<ProjectList> getProj(){
		return proj;
	}
	public ArrayList<EffortData> getEffort(){
		return effort;
	}
	public ArrayList<DefectData> getDefect(){
		return defect;
	}
	public Map<String, ArrayList<pokerEntry>> getPoker(){
		return sessionList;
	}
	public Map<String,ProjectList> getProjMap() {
		return projMap;
	}
	public void setEffort(ArrayList<EffortData> effort) {
		this.effort = effort;
	}
	public void setDefect(ArrayList<DefectData> defect) {
		this.defect = defect;
	}
	public void setPoker(Map<String, ArrayList<pokerEntry>> sessionList) {
		this.sessionList = sessionList;
	}
	public void setProj(ArrayList<ProjectList> proj) {
		this.proj = proj;
	}
	public void setProjMap(Map<String,ProjectList> sesh) {
		this.projMap = sesh;
	}
}