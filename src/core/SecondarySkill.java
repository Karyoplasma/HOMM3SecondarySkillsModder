package core;

public class SecondarySkill {
	HeroTrait trait;
	int lvl;
	
	public SecondarySkill(HeroTrait trait, int lvl) {
		this.trait = trait;
		this.lvl = lvl;
	}
	
	public String getLvlString() {
		switch (this.lvl) {
		case 0x01000000: return "Basic";
		case 0x02000000: return "Advanced";
		case 0x03000000: return "Expert";
		default: return "N/A";
		}
	}
	
	@Override
	public String toString() {
		if (this.trait.equals(HeroTrait.None)) {
			return "None";
		}
		return String.format("%s %s", this.getLvlString(), this.trait.getName());
	}

	public void setTrait(HeroTrait trait) {
		this.trait = trait;
		
	}

	public void setLevel(int i) {
		this.lvl = i;
		
	}

	public void setLevel(String s) {
		if (s.equals("Basic")) {
			this.lvl = 0x01000000;
			return;
		}
		if (s.equals("Advanced")) {
			this.lvl = 0x02000000;
			return;
		}
		if (s.equals("Expert")) {
			this.lvl = 0x03000000;
			return;
		}
		this.lvl = 0x00000000;
	}
}
