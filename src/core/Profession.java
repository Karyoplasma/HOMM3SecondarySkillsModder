package core;

public enum Profession {

	Knight("Knight", 0x00000000),
	Cleric("Cleric", 0x01000000),
	Ranger("Ranger", 0x02000000),
	Druid("Druid", 0x03000000),
	Alchemist("Alchemist", 0x04000000),
	Wizard("Wizard", 0x05000000),
	Demoniac("Demoniac", 0x06000000),
	Heretic("Heretic", 0x07000000),
	DeathKnight("Death Knight", 0x08000000),
	Necromancer("Necromancer", 0x09000000),
	Overlord("Overlord", 0x0a000000),
	Warlock("Warlock", 0x0b000000),
	Barbarian("Barbarian", 0x0c000000),
	Battlemage("Battlemage", 0x0d000000),
	Beastmaster("Beastmaster", 0x0e000000),
	Witch("Witch", 0x0f000000),
	Planeswalker("Planeswalker", 0x10000000),
	Elementalist("Elementalist", 0x11000000);
	
	String profession;
	int id;
	
	private Profession(String profession, int id) {
		this.profession = profession;
		this.id = id;
	}

	@Override
	public String toString() {
		return this.profession;
	}
}