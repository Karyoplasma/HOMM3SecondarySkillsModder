package core;

public class SkillChange {
	private Hero hero;
	private SecondarySkill changed1, changed2;
	
	public SkillChange(Hero hero, SecondarySkill changed1, SecondarySkill changed2) {
		this.hero = hero;
		this.changed1 = changed2;
		this.changed2 = changed2;
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public SecondarySkill getChanged1() {
		return changed1;
	}
	public SecondarySkill getChanged2() {
		return changed2;
	}
	
	@Override
	public String toString() {
		return String.format("%s,%d;%d,%d,%d", hero.getName(), changed1.getTrait().ordinal(), changed1.getLvl(), changed2.getTrait().ordinal(), changed2.getLvl());
	}
}
