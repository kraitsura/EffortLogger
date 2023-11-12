package efV2;

public class pokerEntry {
	 private Entry main;
	 private String notes;
	 
	 public pokerEntry(Entry add, String notes) {
		this.main = add;
		this.notes = notes;
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
}