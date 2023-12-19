package actions;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.Hero;
import core.SecondarySkill;
import core.SkillChange;
import enums.HeroTrait;
import enums.SkillLevel;
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
		List<Hero> changes = new ArrayList<Hero>();

		File changesFile = this.getChangesFile();
		if (changesFile == null) {
			return;
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(changesFile))) {
			String in;

			while ((in = reader.readLine()) != null) {
				if (!in.contains(";")) {
					continue;
				}
				String[] data = in.split(";");
				Hero hero = gui.getHeroes().get(data[0]);
				SecondarySkill change1 = new SecondarySkill(HeroTrait.values()[Integer.parseInt(data[1])],
						SkillLevel.values()[Integer.parseInt(data[2])]);
				SecondarySkill change2 = new SecondarySkill(HeroTrait.values()[Integer.parseInt(data[3])],
						SkillLevel.values()[Integer.parseInt(data[4])]);
				SkillChange skillChange = new SkillChange(change1, change2);
				hero.setChange(skillChange);
				changes.add(hero);
				if (hero.equals(gui.getComboBoxHero().getItemAt(gui.getComboBoxHero().getSelectedIndex()))) {
					gui.getComboBoxSkill1().setSelectedItem(change1.getTrait());
					gui.getComboBoxSkill1lvl().setSelectedItem(change1.getLevel());
					gui.getComboBoxSkill2().setSelectedItem(change2.getTrait());
					gui.getComboBoxSkill2lvl().setSelectedItem(change2.getLevel());
				}
			}
			reader.close();

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(gui.getFrame(), "Failed to load changes. Please try again.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
			return;
		}
		gui.setChanges(changes);
		JOptionPane.showMessageDialog(gui.getFrame(), String.format("%d changes loaded.", changes.size()),
				"Information", JOptionPane.INFORMATION_MESSAGE);

	}

	private File getChangesFile() {
		File directory = new File("resources");
		JFileChooser fileChooser = new JFileChooser("Select changes you saved");
		fileChooser.setFileFilter(new FileNameExtensionFilter("mod files", "mod"));
		fileChooser.setCurrentDirectory(directory);
		int ret = fileChooser.showOpenDialog(null);
		if (ret == (JFileChooser.APPROVE_OPTION)) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
}
