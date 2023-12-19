package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import core.Hero;
import core.SecondarySkill;
import core.SkillChange;
import enums.HeroTrait;
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
		int selectedIndex = gui.getComboBoxHero().getSelectedIndex();
		Hero hero = gui.getComboBoxHero().getItemAt(selectedIndex);

		HeroTrait skill1 = gui.getComboBoxSkill1().getItemAt(gui.getComboBoxSkill1().getSelectedIndex());
		int skill1Lvl = skill1 == HeroTrait.NONE ? 0x00000000
				: this.getSkillLevel(gui.getComboBoxSkill1lvl().getItemAt(gui.getComboBoxSkill1().getSelectedIndex()));
		HeroTrait skill2 = gui.getComboBoxSkill2().getItemAt(gui.getComboBoxSkill2().getSelectedIndex());
		int skill2Lvl = skill2 == HeroTrait.NONE ? 0x00000000
				: this.getSkillLevel(gui.getComboBoxSkill2lvl().getItemAt(gui.getComboBoxSkill2().getSelectedIndex()));
		SecondarySkill changed1 = new SecondarySkill(skill1, skill1Lvl);
		SecondarySkill changed2 = new SecondarySkill(skill2, skill2Lvl);
		SkillChange skillChange = new SkillChange(hero, changed1, changed2);
		
		gui.putChanges(hero.toString(), skillChange);
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
//		String changed = gui.getChanges().put(selectedIndex,
//				String.format("%d;%d;%d;%d", heroes.get(selectedIndex).getSecondary1().getTrait().getID(),
//						heroes.get(selectedIndex).getSecondary1().getLvl(),
//						heroes.get(selectedIndex).getSecondary2().getTrait().getID(),
//						heroes.get(selectedIndex).getSecondary2().getLvl()));
//		String update = (changed == null) ? String.format("Made changes to %s", heroes.get(selectedIndex).getName())
//				: String.format("Updated changes to %s", heroes.get(selectedIndex).getName());
		// textArea_changes.append(update);
		// textArea_changes.append(System.getProperty("line.separator"));

	}

	private int getSkillLevel(String levelString) {
		if (levelString.equals("Basic")) {
			return 0x01000000;
		}
		if (levelString.equals("Advanced")) {
			return 0x02000000;
		}
		if (levelString.equals("Expert")) {
			return 0x03000000;
		}
		return 0x00000000;
	}

}
