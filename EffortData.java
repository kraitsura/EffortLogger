package Main;

//Viet Le

import java.util.ArrayList;

public class EffortData {
	private int time;
	private String task;
	private String description;
	private ArrayList<String> keyWords;
	private int userCode;
	
	public EffortData() {
//		this.time = time;
//		this.task = task;
//		this.description = description;
//		this.keyWords = keyWords;
//		this.userCode = userCode;
	}
	public int getTime() {
		return time;
	}
	public String getTask() {
		return task;
	}
	public String getDescription() {
		return description;
	}
	public ArrayList<String> getKeywords() {
		return keyWords;
	}
	public int getUserCode() {
		return userCode;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addKeyword(String keyword) {
		this.keyWords = new ArrayList<String>();
		this.keyWords.add(keyword);
	}
	public void setUser(int code) {
		this.userCode = code;
	}
}