package core;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Hero {
	
	RandomAccessFile file;
	long offset;
	String name, speciality;
	Gender gender;
	Race race;
	Profession profession;
	SecondarySkill secondary1, secondary2;
	boolean hasSpellbook;
	int spell;
	
	public Hero(RandomAccessFile file, String name, String speciality, long offset) throws IOException {
		this.file = file;
		this.name = name;
		this.speciality = speciality;
		this.offset = offset;
		this.readAll();
	}
	
	private HeroTrait getTraitById(int id) {
		for (HeroTrait trait : HeroTrait.values()) {
			if (id == trait.getId()) {
				return trait;
			}
		}
		return HeroTrait.None;
	}
	
	private Race getRaceById(int id) {
		for (Race race : Race.values()) {
			if (id == race.id) {
				return race;
			}
		}
		System.out.println(String.format("Race not found: 0x%08X (Hero: %s)", id, name));
		return null;
	}
	
	private Profession getProfessionById(int id) {
		for (Profession profession : Profession.values()) {
			if (id == profession.id) {
				return profession;
			}
		}
		return null;
	}
	
	public void setSecondary1(int trait ,int lvl) {
		this.secondary1.setTrait(this.getTraitById(trait));
		this.secondary1.setLevel(lvl);
	}
	
	public void setSecondary2(int trait ,int lvl) {
		this.secondary2.setTrait(this.getTraitById(trait));
		this.secondary2.setLevel(lvl);
	}
	
	public void readAll() throws IOException {
		file.seek(offset);
		int raw_gender = file.readInt();
		this.gender = (raw_gender == 0x00000000) ? Gender.Male : Gender.Female;
		file.seek(offset + 0x4);
		int raw_race = file.readInt();
		this.race = getRaceById(raw_race);
		file.seek(offset + 0x8);
		int raw_profession = file.readInt();
		this.profession = this.getProfessionById(raw_profession);
		file.seek(offset + 0xc);
		int raw_skill1 = file.readInt();
		file.seek(offset + 0x10);
		int raw_skill1lvl = file.readInt();
		this.secondary1 = new SecondarySkill(this.getTraitById(raw_skill1), raw_skill1lvl);
		file.seek(offset + 0x14);
		int raw_skill2 = file.readInt();
		file.seek(offset + 0x18);
		int raw_skill2lvl = file.readInt();
		this.secondary2 = new SecondarySkill(this.getTraitById(raw_skill2), raw_skill2lvl);
		file.seek(offset + 0x1c);
		int raw_spellbook = file.readInt();
		this.hasSpellbook = (raw_spellbook == 0x01000000);
		file.seek(offset + 0x20);
		int raw_spell = file.readInt();
		this.spell = raw_spell;
		file.seek(offset);
	}
	
	public boolean writeHero() {
		try {
			file.seek(this.offset + 0xc);
			file.writeInt(this.secondary1.trait.id);
			file.seek(this.offset + 0x10);
			file.writeInt(this.secondary1.lvl);
			file.seek(this.offset + 0x14);
			file.writeInt(this.secondary2.trait.id);
			file.seek(this.offset + 0x18);
			file.writeInt(this.secondary2.lvl);
			file.seek(this.offset);
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public void printOut() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.name).append(" (").append(this.speciality).append(") ");
		buffer.append(this.gender).append(" ").append(this.race).append(" ");
		buffer.append(this.profession).append(" ").append(secondary1.toString()).append(" ");
		buffer.append(secondary2.toString()).append(" ").append(this.hasSpellbook).append(" ");
		buffer.append(String.format("0x%08X", this.spell));
		System.out.println(buffer.toString());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
