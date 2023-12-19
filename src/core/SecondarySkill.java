package core;

import enums.HeroTrait;
import enums.SkillLevel;

public class SecondarySkill {
	private HeroTrait trait;
	private int lvl;
	private SkillLevel level;
	
	@Deprecated
	public SecondarySkill(HeroTrait trait, int lvl) {
		this.trait = trait;
		this.lvl = lvl;
	}
	
	public SecondarySkill(HeroTrait trait, SkillLevel level) {
		this.trait = trait;
		this.level = level;
	}
	
	@Deprecated
	public String getLvlString() {
		switch (this.lvl) {
		case 0x01000000: return "Basic";
		case 0x02000000: return "Advanced";
		case 0x03000000: return "Expert";
		default: return "N/A";
		}
	}
	
	public HeroTrait getTrait() {
		return this.trait;
	}
	
	@Deprecated
	public int getLvl() {
		return this.lvl;
	}
	
	public SkillLevel getLevel() {
		return this.level;
	}
	@Override
	public String toString() {
		if (this.getTrait().equals(HeroTrait.NONE)) {
			return "None";
		}
		return String.format("%s %s", this.level, this.getTrait());
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SecondarySkill)) {
			return false;
		}
	
		SecondarySkill other = (SecondarySkill) o;
		return (trait == other.getTrait()) && (level == other.getLevel());
	}

}
