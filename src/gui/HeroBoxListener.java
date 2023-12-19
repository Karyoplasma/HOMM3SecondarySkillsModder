package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
			StringBuffer buffer = new StringBuffer();
			
			buffer.append(hero.getName()).append("\t Specialty: ").append(hero.getSpecialty()).append(System.getProperty("line.separator"));
			buffer.append(hero.getGender()).append(" ").append(hero.getProfession());
			gui.setHeroInfo(buffer.toString());
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
}
