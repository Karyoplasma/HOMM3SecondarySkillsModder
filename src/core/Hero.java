package core;

import enums.Creature;
import enums.Gender;
import enums.HeroHeader;
import enums.Profession;
import enums.Race;
import enums.Spell;

public class Hero {

	private HeroHeader header;
	private Gender gender;
	private Race race;
	private Profession profession;
	private SecondarySkill secondary1, secondary2;
	private SkillChange change;
	private boolean hasSpellbook;
	private Spell spell;
	private Creature[] startingTroops;

	public Hero(HeroHeader header, SecondarySkill secondary1, SecondarySkill secondary2, SkillChange change,
			Gender gender, Race race, Profession profession, boolean hasSpellBook, Spell spell, Creature[] startingTroops) {
		this.header = header;
		this.secondary1 = secondary1;
		this.secondary2 = secondary2;
		this.setChange(change);
		this.gender = gender;
		this.race = race;
		this.profession = profession;
		this.hasSpellbook = hasSpellBook;
		this.spell = spell;
		this.startingTroops = startingTroops;
	}

	public SecondarySkill getSecondary1() {
		return secondary1;
	}

	public SecondarySkill getSecondary2() {
		return secondary2;
	}

	public String getName() {
		return header.getName();
	}

	public String getSpecialty() {
		return header.getSpecialty();
	}

	public long getOffset() {
		return header.getOffset();
	}

	public Profession getProfession() {
		return profession;
	}

	public Gender getGender() {
		return gender;
	}

	public HeroHeader getHeader() {
		return header;
	}

	public SkillChange getChange() {
		return change;
	}
	
	public Spell getSpell() {
		return spell;
	}
	
	public Creature[] getStartingTroops() {
		return startingTroops;
	}
	
	public boolean hasSpellbook() {
		return this.hasSpellbook;
	}
	
	public void setChange(SkillChange change) {
		this.change = change;
	}
	
	public Object getRace() {
		return race;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Hero)) {
			return false;
		}
		Hero other = (Hero) o;
		return this.header == other.getHeader();
	}	
}
