package efV2;

public class pokerEntry {
	 private Entry main;
	 private String notes;
	 private int time;
	 
	 public pokerEntry(Entry add, String notes, int time) {
		this.main = add;
		this.notes = notes;
		this.time = time;
	 }
	 
	 public Entry getEntry() {
		 return this.main;
	 }
	 public String getNotes() {
		 return notes;
	 }
	 public void setNotes(String notes) {
		 this.notes = notes;
	 }

	 public int getTime() {
		return time;
	 }
}