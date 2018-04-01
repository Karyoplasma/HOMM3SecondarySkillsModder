package core;

public enum Gender {
	Male("Male", 0x00000000),
	Female("Female", 0x01000000);
	
	String gender;
	int id;
	
	private Gender(String gender, int id) {
		this.gender = gender;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.gender;
	}
}
