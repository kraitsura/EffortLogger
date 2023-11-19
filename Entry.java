package Main;

//Aarya Reddy
 public class Entry {
	 private String entryName;
	 private String description;
	 private int points;
	 private EffortData effort;
//	 private Defect Data defect;
	 
	 public Entry(String name, String description, int points) {
		 this.entryName = name;
		 this.description = description;
		 this.points = points;
	 }
	 
	 public String getName() {
		 return this.entryName;
	 }
	 
	 public String getDes() {
		 return this.description;
	 }
	 public int getPoints() {
		 return this.points;
	 }
	 public EffortData getEffort() {
		 return this.effort;
	 }
	 public void setPoints(int points) {
		 this.points = points;
	 }
	 public void setEffort(EffortData effort) {
		 this.effort = effort;
	 }
//	 public void setDefect(EffortData defect) {
//		 this.defect = defect;
//	 }
 }
 