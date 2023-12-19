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
				System.out.println("Nothing to do - no change from initial");
				return;
			} else {
				// changed, add
				System.out.println("Adding first hero change");
				hero.setChange(change);
				gui.getHeroes().get(hero.getName()).setChange(change);
				gui.addChanges(hero);
				return;
			}
		} else {
			// hero WAS changed before
			if (hero.getChange().getChanged1().equals(change.getChanged1())
					&& hero.getChange().getChanged2().equals(change.getChanged2())) {
				// same change as current, accidental button click
				System.out.println("Nothing to do - no change from current configuration");
				return;
			} else {
				if (hero.getSecondary1().equals(change.getChanged1())
						&& hero.getSecondary2().equals(change.getChanged2())) {
					// changed back to initial configuration
					System.out.println("Removing the change from the list - back to vanilla");
					hero.setChange(null);
					gui.getHeroes().get(hero.getName()).setChange(null);
					gui.removeChanges(hero);
					return;
				} else {
					// changed to a different configuration
					System.out.println("Adding the change to the list - different configuration from current");
					hero.setChange(change);
					gui.getHeroes().get(hero.getName()).setChange(change);
					gui.addChanges(hero);
					return;
				}
			}
		}

	}

	// if (hero.getChange() == null) {
	// if (secondary1.equals(hero.getSecondary1()) &&
	// secondary2.equals(hero.getSecondary2())) {
	// System.out.println("Not adding since same");
	// return;
	// }
	// } else {
	// System.out.print("hero was changed prior: ");
	// System.out.print(hero.getChange().getFlavorString());
	// System.out.println(" now:" + change.getFlavorString());
	// if (hero.getChange().equals(change)) {
	// System.out.println("Not adding since changes same");
	// return;
	// }
	// System.out.println(String.format("1) Updating %s from %s, %s to %s, %s",
	// hero.getName(), hero.getChange().getChanged1(),
	// hero.getChange().getChanged2(), secondary1, secondary2));
	// hero.setChange(change);
	// gui.addChanges(hero, change);
	// System.out.println(gui.getChanges().size());
	// return;
	// }
	// System.out.println(String.format("2) Updating %s from %s (1), %s (2) to %s
	// (c1), %s(c2)", hero.getName(), hero.getSecondary1(),
	// hero.getSecondary2(), secondary1, secondary2));
	// hero.setChange(change);
	// gui.addChanges(hero, change);
	// System.out.println(gui.getChanges().size());

	// HeroTrait skill2 =
	// gui.getComboBoxSkill2().getItemAt(gui.getComboBoxSkill2().getSelectedIndex());
	// int skill2Lvl = skill2 == HeroTrait.NONE ? 0x00000000
	// :
	// this.getSkillLevel(gui.getComboBoxSkill2lvl().getItemAt(gui.getComboBoxSkill2().getSelectedIndex()));
	// SecondarySkill changed1 = new SecondarySkill(skill1, skill1Lvl);
	// SecondarySkill changed2 = new SecondarySkill(skill2, skill2Lvl);
	// SkillChange skillChange = new SkillChange(hero, changed1, changed2);
	//
	// gui.putChanges(hero.toString(), skillChange);
	// changed1 = new
	// SecondarySkill(hero.getSecondary1().setTrait(skill1.getItemAt(skill1.getSelectedIndex()));
	// if (skill1Lvl.isEnabled()) {
	// heroes.get(selectedIndex).getSecondary1().setLevel(skill1Lvl.getItemAt(skill1Lvl.getSelectedIndex()));
	// } else {
	// heroes.get(selectedIndex).getSecondary1().setLevel(0x00000000);
	// }
	// heroes.get(selectedIndex).getSecondary2().setTrait(skill2.getItemAt(skill2.getSelectedIndex()));
	// if (skill2Lvl.isEnabled()) {
	// heroes.get(selectedIndex).getSecondary2().setLevel(skill2Lvl.getItemAt(skill2Lvl.getSelectedIndex()));
	// } else {
	// heroes.get(selectedIndex).getSecondary2().setLevel(0x00000000);
	// }
	// String changed = gui.getChanges().put(selectedIndex,
	// String.format("%d;%d;%d;%d",
	// heroes.get(selectedIndex).getSecondary1().getTrait().getID(),
	// heroes.get(selectedIndex).getSecondary1().getLvl(),
	// heroes.get(selectedIndex).getSecondary2().getTrait().getID(),
	// heroes.get(selectedIndex).getSecondary2().getLvl()));
	// String update = (changed == null) ? String.format("Made changes to %s",
	// heroes.get(selectedIndex).getName())
	// : String.format("Updated changes to %s",
	// heroes.get(selectedIndex).getName());
	// textArea_changes.append(update);
	// textArea_changes.append(System.getProperty("line.separator"));
}
