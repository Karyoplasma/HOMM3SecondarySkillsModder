package actions;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import core.Hero;
import core.SecondarySkill;
import core.SkillChange;
import enums.HeroTrait;
import gui.Heroes3HeroEditor;

public class LoadButtonAction extends AbstractAction {

	private static final long serialVersionUID = 9217036006616143040L;
	private Heroes3HeroEditor gui;

	public LoadButtonAction(Heroes3HeroEditor gui) {
		putValue(Action.NAME, "Load");
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HashMap<String, SkillChange> changes = new HashMap<String, SkillChange>();
		File changesFile = new File("resources/changes.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(changesFile));
			String in;
			while ((in = reader.readLine()) != null) {
				if (!in.contains(";")) {
					continue;
				}
				String[] data = in.split(";");
				Hero hero = gui.getHeroes().get(data[0]);
				SecondarySkill change1 = new SecondarySkill(HeroTrait.values()[Integer.parseInt(data[1])],
						Integer.parseInt(data[2]));
				SecondarySkill change2 = new SecondarySkill(HeroTrait.values()[Integer.parseInt(data[3])],
						Integer.parseInt(data[4]));
				SkillChange skillChange = new SkillChange(hero, change1, change2);
				changes.put(hero.getName(), skillChange);
			}
			reader.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		gui.setChanges(changes);
		JOptionPane.showMessageDialog(gui.getFrame(), String.format("%d changes loaded.", changes.size()),
				"Information", JOptionPane.INFORMATION_MESSAGE);

	}
}
