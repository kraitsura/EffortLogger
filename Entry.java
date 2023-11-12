package efV2;

//Aarya Reddy
 public class Entry {
	 private String entryName;
	 private String description;
	 private int points;
	 
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
 }