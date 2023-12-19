package core;

public class SkillChange {

	private SecondarySkill changed1, changed2;

	public SkillChange(SecondarySkill changed1, SecondarySkill changed2) {
		this.changed1 = changed1;
		this.changed2 = changed2;
	}

	public SecondarySkill getChanged1() {
		return changed1;
	}

	public SecondarySkill getChanged2() {
		return changed2;
	}

	public String getFlavorString() {
		return String.format("%s %s, %s %s", changed1.getLevel(), changed1.getTrait(), changed2.getLevel(),
				changed2.getTrait());

	}

	@Override
	public String toString() {
		return String.format("%d;%d;%d;%d", changed1.getTrait().ordinal(), changed1.getLevel().ordinal(),
				changed2.getTrait().ordinal(), changed2.getLevel().ordinal());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SkillChange)) {
			return false;
		}
		SkillChange other = (SkillChange) o;
		return (this.changed1 == other.getChanged1()) && (this.changed2 == other.getChanged2());
	}
}
