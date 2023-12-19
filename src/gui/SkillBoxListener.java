package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import enums.HeroTrait;

public class SkillBoxListener implements ItemListener {
	Heroes3HeroEditor gui;

	public SkillBoxListener(Heroes3HeroEditor gui) {
		this.gui = gui;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (e.getSource() == gui.getComboBoxSkill1()) {
				HeroTrait trait = gui.getComboBoxSkill1().getItemAt(gui.getComboBoxSkill1().getSelectedIndex());
				if (trait == HeroTrait.NONE) {
					gui.getComboBoxSkill1lvl().setEnabled(false);
				} else {
					gui.getComboBoxSkill1lvl().setEnabled(true);
				}
			}
			if (e.getSource() == gui.getComboBoxSkill2()) {
				HeroTrait trait = gui.getComboBoxSkill2().getItemAt(gui.getComboBoxSkill2().getSelectedIndex());
				if (trait == HeroTrait.NONE) {
					gui.getComboBoxSkill2lvl().setEnabled(false);
				} else {
					gui.getComboBoxSkill2lvl().setEnabled(true);
				}
			}
		}
	}

}
