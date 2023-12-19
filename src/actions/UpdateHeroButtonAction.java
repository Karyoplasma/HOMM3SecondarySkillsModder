package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;

import core.Hero;
import core.SecondarySkill;
import core.SkillChange;
import enums.HeroTrait;
import enums.SkillLevel;
import gui.Heroes3HeroEditor;

public class UpdateHeroButtonAction extends AbstractAction {

	private static final long serialVersionUID = -4715027063983942683L;
	private Heroes3HeroEditor gui;

	public UpdateHeroButtonAction(Heroes3HeroEditor gui) {
		putValue(Action.NAME, "Update Hero");
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Hero hero = (Hero) gui.getComboBoxHero().getSelectedItem();
		JComboBox<HeroTrait> box1 = gui.getComboBoxSkill1();
		JComboBox<SkillLevel> lvlbox1 = gui.getComboBoxSkill1lvl();
		JComboBox<HeroTrait> box2 = gui.getComboBoxSkill2();
		JComboBox<SkillLevel> lvlbox2 = gui.getComboBoxSkill2lvl();

		HeroTrait skill1 = (HeroTrait) box1.getSelectedItem();
		SkillLevel skill1Lvl = (SkillLevel) lvlbox1.getSelectedItem();
		HeroTrait skill2 = (HeroTrait) box2.getSelectedItem();
		SkillLevel skill2Lvl = lvlbox2.getSelectedIndex() == -1 ? SkillLevel.NONE
				: (SkillLevel) lvlbox2.getSelectedItem();
		SecondarySkill secondary1 = new SecondarySkill(skill1, skill1Lvl);
		SecondarySkill secondary2 = new SecondarySkill(skill2, skill2Lvl);
		SkillChange change = new SkillChange(secondary1, secondary2);

		if (hero.getChange() == null) {
			// hero wasn't changed before
			if (hero.getSecondary1().equals(secondary1) && hero.getSecondary2().equals(secondary2)) {
				// no change, accidentally clicked button
				return;
			} else {
				// changed, add
				this.sanityCheckAndAdd(hero, change);
				return;
			}
		} else {
			// hero WAS changed before
			if (hero.getChange().getChanged1().equals(change.getChanged1())
					&& hero.getChange().getChanged2().equals(change.getChanged2())) {
				// same change as current, accidental button click
				return;
			} else {
				if (hero.getSecondary1().equals(change.getChanged1())
						&& hero.getSecondary2().equals(change.getChanged2())) {
					// changed back to initial configuration
					hero.setChange(null);
					gui.getHeroes().get(hero.getName()).setChange(null);
					gui.removeChanges(hero);
					return;
				} else {
					// changed to a different configuration
					this.sanityCheckAndAdd(hero, change);
					return;
				}
			}
		}

	}

	private void sanityCheckAndAdd(Hero hero, SkillChange change) {
		HeroTrait trait1 = change.getChanged1().getTrait();
		HeroTrait trait2 = change.getChanged2().getTrait();
		if (trait1 == null || trait2 == null) {
			// this never happens, still
			System.out.println("hopefully never happens");
			return;
		}
		if (trait1 == trait2) {
			// not adding, bogus configuration
			System.out.println("bogus configuration");
			return;
		}
		if (trait1 == HeroTrait.NONE) {
			// should never happen
			System.out.println("weird if this happens");
			return;
		}
		if ((trait2 == HeroTrait.NONE) && !(change.getChanged2().getLevel() == SkillLevel.NONE)) {
			// last check
			System.out.println("losing my sanity");
			return;
		}

		hero.setChange(change);
		gui.getHeroes().get(hero.getName()).setChange(change);
		gui.addChanges(hero);
	}
}
