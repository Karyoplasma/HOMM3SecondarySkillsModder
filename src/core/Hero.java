package core;

import enums.Gender;
import enums.Profession;
import enums.Race;

public class Hero {
	
	private String name, speciality;
	private Gender gender;
	private Race race;
	private Profession profession;
	private SecondarySkill secondary1, secondary2;
	private boolean hasSpellbook;
	private int spell;
		
	public Hero(String name, String speciality, SecondarySkill secondary1, SecondarySkill secondary2,
			Gender gender, Race race, Profession profession, boolean hasSpellBook, int spell) {
		this.name = name;
		this.speciality = speciality;
		this.secondary1 = secondary1;
		this.secondary2 = secondary2;
		this.gender = gender;
		this.race = race;
		this.profession = profession;
		this.hasSpellbook = hasSpellBook;
		this.spell = spell;
	}

	public SecondarySkill getSecondary1() {
		return secondary1;
	}

	public SecondarySkill getSecondary2() {
		return secondary2;
	}

	public String getName() {
		return name;
	}

	public void printOut() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.getName()).append(" (").append(this.speciality).append(") ");
		buffer.append(this.gender).append(" ").append(this.race).append(" ");
		buffer.append(this.profession).append(" ").append(getSecondary1().toString()).append(" ");
		buffer.append(getSecondary2().toString()).append(" ").append(this.hasSpellbook).append(" ");
		buffer.append(String.format("0x%08X", this.spell));
		System.out.println(buffer.toString());
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
