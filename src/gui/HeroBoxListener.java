package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

import core.Hero;
import core.SkillChange;
import enums.HeroTrait;

public class HeroBoxListener implements ItemListener {
	private Heroes3HeroEditor gui;

	public HeroBoxListener(Heroes3HeroEditor gui) {
		this.gui = gui;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Hero hero = (Hero) gui.getComboBoxHero().getSelectedItem();
			String heroInfo = this.getHeroInfo(hero);

			gui.setHeroInfo(heroInfo);
			SkillChange change = hero.getChange();
			if (change == null) {
				gui.getComboBoxSkill1().setSelectedItem(hero.getSecondary1().getTrait());
				gui.getComboBoxSkill1lvl().setSelectedItem(hero.getSecondary1().getLevel());
				gui.getComboBoxSkill2().setSelectedItem(hero.getSecondary2().getTrait());
				gui.getComboBoxSkill2lvl().setSelectedItem(hero.getSecondary2().getLevel());
			} else {
				gui.getComboBoxSkill1().setSelectedItem(change.getChanged1().getTrait());
				gui.getComboBoxSkill1lvl().setSelectedItem(change.getChanged1().getLevel());
				gui.getComboBoxSkill2().setSelectedItem(change.getChanged2().getTrait());
				if (change.getChanged2().getTrait() == HeroTrait.NONE) {
					gui.getComboBoxSkill2lvl().setSelectedIndex(-1);
				} else {
					gui.getComboBoxSkill2lvl().setSelectedItem(change.getChanged2().getLevel());
				}
			}

		}
	}

	private String getHeroInfo(Hero hero) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(String.format("%s (%s)", hero.getName(), hero.getSpecialty()));
		buffer.append(System.getProperty("line.separator"));
		buffer.append(String.format("%s %s (%s)", hero.getRace(), hero.getProfession(), hero.getGender()));
		buffer.append(System.getProperty("line.separator"));
		buffer.append(hero.hasSpellbook() ? "Brings a spellbook" : "Doesn't bring a spellbook");
		buffer.append(String.format("; Spell: %s", hero.getSpell()));
		buffer.append(System.getProperty("line.separator"));
		buffer.append(String.format("Starting skills in the executable: %s and %s", hero.getSecondary1(), hero.getSecondary2()));
		buffer.append(System.getProperty("line.separator"));
		buffer.append(String.format("Starting troops: %s", Arrays.toString(hero.getStartingTroops())));
		return buffer.toString();
	}
}
