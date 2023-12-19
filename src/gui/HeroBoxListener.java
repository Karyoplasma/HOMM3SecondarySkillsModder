package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import core.Hero;
import enums.HeroTrait;

public class HeroBoxListener implements ItemListener {
	private Heroes3HeroEditor gui;
	
	public HeroBoxListener(Heroes3HeroEditor gui) {
		this.gui = gui;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			System.out.println(e.getSource().toString());
//			Hero hero = heroes.get(selectedIndex);
//			removeActionListeners();
//			this.textField_speciality.setText(hero.speciality);
//			this.textField_gender.setText(hero.gender.toString());
//			this.textField_race.setText(hero.race.toString());
//			this.textField_profession.setText(hero.profession.toString());
//			this.comboBoxSkill1.setSelectedItem(hero.secondary1.getTrait());
//			this.comboBoxSkill1lvl.setSelectedItem(hero.secondary1.getLvlString());
//			this.comboBoxSkill2.setSelectedItem(heroes.get(selectedIndex).secondary2.getTrait());
//			if (this.comboBoxSkill2.getSelectedItem().equals(HeroTrait.NONE)) {
//				this.comboBoxSkill2lvl.setSelectedItem("None");
//				this.comboBoxSkill2lvl.setEnabled(false);
//			} else {
//				this.comboBoxSkill2lvl.setSelectedItem(hero.secondary2.getLvlString());
//				this.comboBoxSkill2lvl.setEnabled(true);
//			}
//			
//			addActionListeners();
		}
	}
}
