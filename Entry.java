package efV2;

//Aarya Reddy
 public class Entry {
	 private String entryName;
	 private String description;
	 private String type;
	 private int points;
	 private int time;
	 private EffortData effort;
//	 private Defect Data defect;
	 
	 public Entry(String name, String description, int points, int time, String type) {
		 this.entryName = name;
		 this.type = type;
		 this.description = description;
		 this.points = points;
		 this.time = time;
	 }
	 
	 public String getName() {
		 return this.entryName;
	 }
	 public String getType() {
		 return this.type;
	 }
	 public int getTime() {
		 return this.time;
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
//	 public DefectData getDefect(){
//	 	return this.defect;
//	 }
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
 