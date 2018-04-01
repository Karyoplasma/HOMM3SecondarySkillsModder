package core;

public enum HeroTrait {
	
	Pathfinding("Pathfinding", 0x00000000),
	Archery("Archery", 0x01000000),
	Logistics("Logistics", 0x02000000),
	Scouting("Scouting", 0x03000000),
	Diplomacy("Diplomacy", 0x04000000),
	Navigation("Navigation", 0x05000000),
	Leadership("Leadership", 0x06000000),
	Wisdom("Wisdom", 0x07000000),
	Mysticism("Mysticism", 0x08000000),
	Luck("Luck", 0x09000000),
	Ballistics("Ballistics", 0x0a000000),
	EagleEye("Eagle Eye", 0x0b000000),
	Necromancy("Necromancy", 0x0c000000),
	Estates("Estates", 0x0d000000),
	FireMagic("Fire Magic",	0x0e000000),
	AirMagic("Air Magic", 0x0f000000),
	WaterMagic("Water Magic", 0x10000000),
	EarthMagic("Earth Magic", 0x11000000),
	Scholar("Scholar", 0x12000000),
	Tactics("Tactics", 0x13000000),
	Artillery("Artillery", 0x14000000),
	Learning("Learning", 0x15000000),
	Offense("Offense", 0x16000000),
	Armorer("Armorer", 0x17000000),
	Intelligence("Intelligence", 0x18000000),
	Sorcery("Sorcery", 0x19000000),
	Resistance("Resistance", 0x1a000000),
	FirstAid("First Aid", 0x1b000000),
	None("None", 0xFFFFFFFF);
	
	String name;
	int id;
	
	private HeroTrait(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public int getId() {
		return this.id;
	}
}
