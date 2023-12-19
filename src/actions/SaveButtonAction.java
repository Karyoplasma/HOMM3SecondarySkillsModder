package actions;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import core.Hero;
import core.SkillChange;
import gui.Heroes3HeroEditor;

public class SaveButtonAction extends AbstractAction {

	private static final long serialVersionUID = 2349318213613428462L;
	private Heroes3HeroEditor gui;

	public SaveButtonAction(Heroes3HeroEditor gui) {
		putValue(Action.NAME, "Save");
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Hero> changes = gui.getChanges();
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("resources/changes.txt"),
				StandardCharsets.UTF_8)) {
			for (Hero value : changes) {
				writer.write(String.format("%s;%s", value.getName(), value.getChange()));
				writer.newLine();
			}
			JOptionPane.showMessageDialog(gui.getFrame(), String.format("%d changes saved.", changes.size()),
					"Information", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(gui.getFrame(), "Could not write the file, please retry.", "Error",
					JOptionPane.WARNING_MESSAGE);
			ex.printStackTrace();
		}
	}
}
