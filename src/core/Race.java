package core;

public enum Race {
	Demon("Demon", 0x00000000),
	Dwarf("Dwarf", 0x01000000),
	Efreet("Efreet", 0x02000000),
	Elf("Elf", 0x03000000),
	Genie("Genie", 0x04000000),
	Gnoll("Gnoll", 0x05000000),
	Goblin("Goblin", 0x06000000),
	Human("Human", 0x07000000),
	Lich("Lich", 0x08000000),
	Lizardman("Lizardman", 0x09000000),
	Minotaur("Minotaur", 0x0a000000),
	Ogre("Ogre", 0x0b000000),
	Troglodyte("Troglodyte", 0x0c000000),
	Vampire("Vampire", 0x0d000000);
	
	String race;
	int id;
	
	private Race(String race, int id) {
		this.race = race;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.race;
	}
}
